package org.csu.management.service;

import org.csu.management.domain.Category;
import org.csu.management.domain.Item;
import org.csu.management.domain.Photo;
import org.csu.management.domain.Product;
import org.csu.management.mapper.CategoryMapper;
import org.csu.management.mapper.ItemMapper;
import org.csu.management.mapper.PhotoMapper;
import org.csu.management.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private PhotoMapper photoMapper;

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

    public void updeatePrdouct(Product product){
        productMapper.updateProduct(product);
    }

    public Category getCategoryByCategoryId(String categoryId){
        return categoryMapper.getCategoryByCategoryId(categoryId);
    }

    public void updateCategory(Category category){
        categoryMapper.updateCategory(category);
    }

    public void deleteProductByCategoryId(String categoryId){
        productMapper.deleteProductByCategoryId(categoryId);
    }

    public void deleteItemByProductId(String productId){
        itemMapper.deleteItemByProductId(productId);
    }

    /**
     * @Description //TODO 删除Category，并且删除属于该删除Category的Product和Item
     * @Date 7:31 下午 2022/3/15
     **/
    public void deleteCategoryByCategoryId(String categoryId){
        List<Product> prdouctList = productMapper.getProductListByCategory(categoryId);
        List<String> productIdList = new ArrayList<>();
        Iterator<Product> prdouctListIterator=prdouctList.iterator();
        while(prdouctListIterator.hasNext()){
            Product product = prdouctListIterator.next();
            productIdList.add(product.getProductId());
        }
        Iterator<String> productIdListIterator=productIdList.iterator();
        while(productIdListIterator.hasNext()){
            String productId =productIdListIterator.next();
            itemMapper.deleteItemByProductId(productId);
        }

        productMapper.deleteProductByCategoryId(categoryId);

        categoryMapper.deleteCategoryByCategoryId(categoryId);
    }

    /**
     * @Description //TODO 删除Product，并且删除属于该删除Product的Item,当所属Category为空时，也将Category删除
     * @Date 7:31 下午 2022/3/15
     **/
    public void deleteProductByProductId(String productId){
        itemMapper.deleteItemByProductId(productId);
        String categoryId = productMapper.getProductByProductId(productId).getCategoryId();
        productMapper.deleteProductByProductId(productId);
        boolean i=false;

        List<Product> allProductList = productMapper.getAllProductList();
        Iterator<Product> allProductListIterator=allProductList.iterator();
        while(allProductListIterator.hasNext()){
            String categoryIdForSameOne = allProductListIterator.next().getCategoryId();
            if(categoryIdForSameOne.equals(categoryId)){i=true;}
        }
        if(i==false){
            categoryMapper.deleteCategoryByCategoryId(categoryId);
        }
    }

    /**
     * @Description //TODO 删除Item，并且删除属于该删除Item所属的Product为空时,也将Product，删除当所属Category为空时，也将Category删除
     * @Date 7:31 下午 2022/3/15
     **/
    public void deleteItemByItemId(String itemId){
        String productId = itemMapper.getItemByItemId(itemId).getProductId();
        itemMapper.deleteItemByItemId(itemId);

        boolean i=false;
        List<Item> allItemList = itemMapper.getAllItemList();
        Iterator<Item> allItemListIterator = allItemList.iterator();
        while(allItemListIterator.hasNext()){
            String productIdForSameOne = allItemListIterator.next().getProductId();
            if(productIdForSameOne.equals(productId)){i=true;}
        }
        if(i==false){
            deleteProductByProductId(productId);
        }
    }

    public void addItem(Item item){
        itemMapper.insertItemToItemTable(item);
        itemMapper.insertItemToInventoryTable(item);
    }

    public void addProduct(Product product){
        productMapper.insertProduct(product);
    }

    public void addCategory(Category category){
        categoryMapper.insertCategory(category);
    }

    public List<Photo> getAllPhotoList(){
        return photoMapper.getAllPhotoDescn();
    }
}
