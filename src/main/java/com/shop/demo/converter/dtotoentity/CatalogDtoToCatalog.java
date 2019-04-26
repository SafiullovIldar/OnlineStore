package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.CatalogDto;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Catalog;
import com.shop.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;


public class CatalogDtoToCatalog implements Converter<CatalogDto, Catalog> {

    // TODO: 25.04.2019  

    @Autowired
    private ConversionService conversion;

    @Override
    public Catalog convert(CatalogDto catalogDto) {
        ItemDtoToItem dtoToItem = new ItemDtoToItem();
        Catalog catalog = new Catalog();
        catalog.setId(catalogDto.getId());
        catalog.setName(catalogDto.getName());

        List<Item> items = new ArrayList<>();
        List<ItemDto> itemDtos = catalogDto.getItems();

        for (ItemDto itemDto : itemDtos) {
            items.add(dtoToItem.convert(itemDto));
        }

        catalog.setItems(items);
        return catalog;
    }
}
