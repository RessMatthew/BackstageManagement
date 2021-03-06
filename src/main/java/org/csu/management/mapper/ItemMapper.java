package org.csu.management.mapper;


import org.csu.management.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemMapper {
    void updateInventoryQuantity(Map<String, Object> param);
    void updateItemItemDB(Item item);
    int getInventoryQuantity(String itemId);
    List<Item> getItemListByProductId(String productId);
    Item getItemByItemId(String itemId);
    List<Item> getAllItemList();
    void deleteItemByItemId(String itemId);
    void deleteItemByProductId(String productId);
    void insertItemToItemTable(Item item);
    void insertItemToInventoryTable(Item item);

}
