package org.csu.management.mapper;


import org.csu.management.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    //select all Category
    List<Category> getCategoryList();
    //select a Category By a Id
    Category getCategory(String categoryId);
}
