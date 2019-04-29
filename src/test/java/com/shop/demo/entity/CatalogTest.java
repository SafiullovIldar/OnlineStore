package com.shop.demo.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogTest {

    private Catalog catalog;
    private Catalog catalogCopy;

    @Before
    public void initCatalogWithItemsAndCreatingCopyCatalog() {
        catalog = new Catalog();
        List<Item> items = new ArrayList<>();

        Item item1 = new Item();
        item1.setName("Item1");
        item1.setPrice(100);
        item1.setAvailability(150);

        Item item2 = new Item();
        item2.setName("Item2");
        item2.setPrice(200);
        item2.setAvailability(250);

        items.add(item1);
        items.add(item2);

        catalog.setName("Sasha");
        catalog.setItems(items);

        catalogCopy = new Catalog(catalog);
    }

    @Test
    public void CheckThatCatalogCopyWorksAndThrowsErrorIfAddedNewFieldWasForgottenToBeAddedToConstructor() {

        List<Item> catalogItems = catalog.getItems();
        List<Item> catalogCopyItems = catalogCopy.getItems();

        assertThat(catalog)
                .isEqualToIgnoringGivenFields(catalogCopy, "id", "items");

        assertThat(catalogItems)
                .usingElementComparatorIgnoringFields("id")
                .isEqualTo(catalogCopyItems);
    }

}