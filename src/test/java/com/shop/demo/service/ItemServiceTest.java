package com.shop.demo.service;

import com.shop.demo.dto.ItemDto;
import com.shop.demo.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private Util util;

    @Before
    public void setUp() {
        util.setUp();
    }

    @After
    public void tearDown() {
        util.tearDown();
    }

    @Test
    public void ShouldGetExpectedItemIfItWasCreated() {
        ItemDto expectedItem = Util.createTestItem("5");
        itemService.createItem(expectedItem, "1");
        ItemDto actualItem = itemService.getItemById("1", "5");

        assertThat(actualItem).isEqualTo(expectedItem);
    }

    @Test
    public void ShouldNotGetExpectedItemIfItWasDeleted() throws SQLException {
        ItemDto expectedItem = itemService.getItemById("1", "2");
        itemService.deleteItem("2");
        ItemDto actualItem = itemService.getItemById("1", "2");

        assertThat(expectedItem).isNotNull();
        assertThat(actualItem).isNull();
    }

    @Test
    public void ShouldGetExpectedItemsFromCatalog() {
        List<ItemDto> expectedItems = new ArrayList<>();
        expectedItems.add(itemService.getItemById("1", "2"));
        expectedItems.add(itemService.getItemById("1", "4"));

        List<ItemDto> actualItems = itemService.getItemsFromCatalog("1");

        assertThat(actualItems).isEqualTo(expectedItems);
    }
}