package org.csu.management.mapper;


import org.csu.management.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Product> getProductListByCategory(String categoryId);
    Product getProductByProductId(String productId);
    List<Product>searchProductList(String keywords);
    List<Product>getAllProductList();
    void updateProduct(Product product);
    void deleteProductByProductId(String productId);
    void deleteProductByCategoryId(String categoryId);
    void insertProduct(Product product);
}
