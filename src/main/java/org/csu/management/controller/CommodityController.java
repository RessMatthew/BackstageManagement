package org.csu.management.controller;

import org.csu.management.domain.Category;
import org.csu.management.domain.Item;
import org.csu.management.domain.Photo;
import org.csu.management.domain.Product;
import org.csu.management.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * @Description
 * @Date 2022/3/15 10:34 上午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Controller
@RequestMapping("/commodity")
@SessionAttributes(value={"allCategoryList","allProductList","allItemList","allPhotoList"})
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/viewCommodity")
    public String viewCommodity(Model model){
        List<Category> allCategoryList = commodityService.getAllCategoryList();
        List<Product> allProductList = commodityService.getAllProductList();
        List<Item> allItemList = commodityService.getAllItemList();
        List<Photo> allPhotoList = commodityService.getAllPhotoList();

        model.addAttribute("allCategoryList",allCategoryList);
        model.addAttribute("allProductList",allProductList);
        model.addAttribute("allItemList",allItemList);
        model.addAttribute("allPhotoList",allPhotoList);
        return "/catalog/Commodity";
    }

    @RequestMapping("/viewItemByItemId")
    public String viewItemByItemId(String itemId,Model model){
        Item item = commodityService.getItemByItemId(itemId);
        model.addAttribute("item",item);

        return "/catalog/Item";
    }

    @PostMapping("/updateItem")
    public String updateItem(Item item){
        commodityService.updateItem(item);
        return "redirect:/commodity/viewCommodity";
    }

    @RequestMapping("/viewProductByProductId")
    public String viewProductByProductId(String productId,Model model){
        Product product = commodityService.getProductByProductId(productId);
        model.addAttribute("product",product);
        return "/catalog/Product";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(Product product){
        commodityService.updeatePrdouct(product);
        return "redirect:/commodity/viewCommodity";
    }

    @GetMapping("/viewCategoryByCategoryId")
    public String viewCategoryByCategoryId(String categoryId,Model model){
        Category category = commodityService.getCategoryByCategoryId(categoryId);

        model.addAttribute("category",category);
        return "/catalog/Category";

    }

    @PostMapping("/updateCategory")
    public String updateCategory(Category category){
        commodityService.updateCategory(category);

        return "redirect:/commodity/viewCommodity";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(String categoryId){
        commodityService.deleteCategoryByCategoryId(categoryId);
        return "redirect:/commodity/viewCommodity";
    }

    @GetMapping("/deleteItem")
    public String deleteItem(String itemId){
        commodityService.deleteItemByItemId(itemId);
        return "redirect:/commodity/viewCommodity";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(String productId){
        commodityService.deleteProductByProductId(productId);
        return "redirect:/commodity/viewCommodity";
    }

    @GetMapping("/ToViewAddCategory")
    public String toViewAddCategory(){
        return "/catalog/AddCategory";
    }

    @GetMapping("/ToViewAddProduct")
    public String toViewAddProduct(){
        return "/catalog/AddProduct";
    }

    @GetMapping("/ToViewAddItem")
    public String toViewAddItem(){
        return "/catalog/AddItem";
    }

    @PostMapping("/addItem")
    public String addItem(Item item){
        commodityService.addItem(item);
        return "redirect:/commodity/viewCommodity";
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product){
        commodityService.addProduct(product);
        return "redirect:/commodity/viewCommodity";
    }

    @PostMapping("/addCategory")
    public String addCategory(Category category){
        commodityService.addCategory(category);
        return "redirect:/commodity/viewCommodity";
    }


}
