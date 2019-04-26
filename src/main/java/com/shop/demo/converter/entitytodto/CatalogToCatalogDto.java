package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.CatalogDto;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Catalog;
import com.shop.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class CatalogToCatalogDto implements Converter<Catalog, CatalogDto> {

    // TODO: 25.04.2019  

    @Autowired
    private ConversionService conversion;

    @Override
    public CatalogDto convert(Catalog catalog) {
        ItemToItemDto itemToItemDto = new ItemToItemDto();
        CatalogDto dto = new CatalogDto();
        dto.setId(catalog.getId());
        dto.setName(catalog.getName());

        if (catalog.getItems() != null) {
            List<Item> items = catalog.getItems();
            List<ItemDto> itemDtos = new ArrayList<>();

            for (Item item : items) {
                itemDtos.add(itemToItemDto.convert(item));
            }

            dto.setItems(itemDtos);
        }

        return dto;
    }
}
