package com.shop.demo.service;

import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Item;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface ItemService {

    List<ItemDto> getItemsFromCatalog(String id);
    ItemDto getItemById(String catalogId, String itemId);
    void createItems(List<Item> items, String catalogId) throws SQLException;
    void createItem(ItemDto itemDto, String catalogId);
    void deleteItem(String itemId);

}
