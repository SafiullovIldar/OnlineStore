package com.shop.demo.service.impl;

import com.shop.demo.dao.ItemDao;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Item;
import com.shop.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final ConversionService conversion;

    @Override
    public List<ItemDto> getItemsFromCatalog(String catalogId) {
        List<Item> itemsFromCatalog = itemDao.getItemsFromCatalog(catalogId);
        List<ItemDto> itemDtos = new ArrayList<>();

        for (Item item : itemsFromCatalog) {
            itemDtos.add(conversion.convert(item, ItemDto.class));
        }

        return itemDtos;
    }

    @Override
    public ItemDto getItemById(String catalogId, String itemId) {
        Item itemById = itemDao.getItemById(catalogId, itemId);
        ItemDto itemDto = conversion.convert(itemById, ItemDto.class);
        return itemDto;
    }

    @Override
    public void createItems(List<Item> items, String catalogId) throws SQLException {
        itemDao.createItems(items, catalogId);
    }

    @Override
    public void createItem(ItemDto itemDto, String catalogId) {
        Item item = conversion.convert(itemDto, Item.class);
        itemDao.createItem(item, catalogId);
    }

    @Override
    public void deleteItem(String itemId) {
        itemDao.deleteItem(itemId);
    }

}
