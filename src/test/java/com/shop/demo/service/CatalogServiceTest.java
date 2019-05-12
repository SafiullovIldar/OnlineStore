package com.shop.demo.service;

import com.shop.demo.util.Util;
import com.shop.demo.dto.CatalogDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.shop.demo.util.Util.createTestCatalog;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceTest {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private Util util;
    
    @Before
    public void setUp() {
        util.setUp();
    }

    @Test()
    public void ShouldGetCatalogIfItWasCreated() {
        CatalogDto expectedCatalog = createTestCatalog("3", "5", "6");
        catalogService.createCatalog(expectedCatalog);
        CatalogDto actualCatalog = catalogService.getCatalogById(expectedCatalog.getId());

        assertThat(actualCatalog).isEqualTo(expectedCatalog);
    }

    @Test
    public void ShouldNotGetCatalogIfItWasDeleted() {
        CatalogDto expectedCatalog = catalogService.getCatalogById("1");
        catalogService.deleteCatalog(expectedCatalog.getId());
        CatalogDto actualCatalog = catalogService.getCatalogById("1");

        assertThat(expectedCatalog).isNotNull();
        assertThat(actualCatalog).isNull();
    }

    @Test
    public void ShouldGetExpectedCatalogCount() {
        int expectedCatalogCount = 2;
        int actualCatalogCount = catalogService.getAllCatalogs().size();

        assertThat(actualCatalogCount).isEqualTo(expectedCatalogCount);
    }

    @Test
    public void ShouldGetCatalogWithExpectedFields() {
        String expectedId = "1";
        String expectedName = "Electronics";
        int expectedItemsCount = 2;

        CatalogDto catalog = catalogService.getCatalogById(expectedId);

        assertThat(catalog.getId()).isEqualTo(expectedId);
        assertThat(catalog.getName()).isEqualTo(expectedName);
        assertThat(catalog.getItems().size()).isEqualTo(expectedItemsCount);
    }



}