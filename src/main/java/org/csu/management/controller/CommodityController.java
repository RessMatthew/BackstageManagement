package org.csu.management.controller;

import org.csu.management.domain.Category;
import org.csu.management.domain.Item;
import org.csu.management.domain.Product;
import org.csu.management.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@SessionAttributes(value={"allCategoryList","allProductList","allItemList"})
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/viewCommodity")
    public String viewCommodity(Model model){
        List<Category> allCategoryList = commodityService.getAllCategoryList();
        List<Product> allProductList = commodityService.getAllProductList();
        List<Item> allItemList = commodityService.getAllItemList();

        model.addAttribute("allCategoryList",allCategoryList);
        model.addAttribute("allProductList",allProductList);
        model.addAttribute("allItemList",allItemList);
        return "/catalog/Commodity";
    }

    @RequestMapping("/viewItemByItemId")
    public String viewItemByItemId(String itemId,Model model){
        Item item = commodityService.getItemByItemId(itemId);
        model.addAttribute("item",item);

        return "/catalog/Item";
    }

    @RequestMapping("/updateItem")
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


}
