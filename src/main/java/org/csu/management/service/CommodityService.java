package org.csu.management.service;

import org.csu.management.domain.Category;
import org.csu.management.domain.Item;
import org.csu.management.domain.Product;
import org.csu.management.mapper.CategoryMapper;
import org.csu.management.mapper.ItemMapper;
import org.csu.management.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @Description
 * @Date 2022/3/15 10:39 上午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Service
public class CommodityService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * @Description //TODO 查询所有ITEM
     * @Author RessMatthew
     * @Date 11:15 上午 2022/3/15
     **/
    public List<Item> getAllItemList(){
        return itemMapper.getAllItemList();
    }

    /**
     * @Description //TODO 查询所有的Category
     * @Date 11:15 上午 2022/3/15
     **/
    public List<Category> getAllCategoryList(){
        return categoryMapper.getCategoryList();
    }

    /**
     * @Description //TODO 查询所有的Prdouct
     * @Date 12:06 下午 2022/3/15
     **/
    public List<Product> getAllProductList(){
        return productMapper.getAllProductList();
    }

    public Item getItemByItemId(String itemId){
        return itemMapper.getItemByItemId(itemId);
    }

    /**
     * @Description //TODO 以itme的itemid为主键修改inventory和item表
     * @Date 2:45 下午 2022/3/15
     **/
    public void updateItem(Item item){
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("itemId", item.getItemId());        //itemId当String放入increment当Integer放入Map
        param.put("quantity", item.getQuantity());  //increment当Integer放入Map
        itemMapper.updateInventoryQuantity(param);
        itemMapper.updateItemItemDB(item);

    }

    public Product getProductByProductId(String productId){
        return productMapper.getProductByProductId(productId);
    }

}
