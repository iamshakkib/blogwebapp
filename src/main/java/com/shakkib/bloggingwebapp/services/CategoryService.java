package com.shakkib.bloggingwebapp.services;

import com.shakkib.bloggingwebapp.helpers.DTOs.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO category);

    CategoryDTO updateCategory(CategoryDTO category,Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDTO getCategory(Integer categoryId);

    List<CategoryDTO> getCategories();
}
