package com.shakkib.bloggingwebapp.services.impl;

import com.shakkib.bloggingwebapp.entities.Category;
import com.shakkib.bloggingwebapp.exceptions.ResourceNotFoundException;
import com.shakkib.bloggingwebapp.helpers.DTOs.CategoryDTO;
import com.shakkib.bloggingwebapp.repositories.CategoryRepository;
import com.shakkib.bloggingwebapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        Category convertedCategory = this.modelMapper.map(category,Category.class);
        Category savedCategory = this.categoryRepository.save(convertedCategory);
        CategoryDTO savedConvertedCategory = this.modelMapper.map(savedCategory, CategoryDTO.class);
        return savedConvertedCategory;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO category, Integer categoryId) {
        Category category1 = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        category1.setCategoryTitle(category.getCategoryTitle());
        category1.setCategoryDescription(category.getCategoryDescription());
        Category savedCategory = this.categoryRepository.save(category1);
        CategoryDTO savedConvertedCategory = this.modelMapper.map(savedCategory, CategoryDTO.class);
        return savedConvertedCategory;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        /*scope of improvement since finding then deleting*/
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        CategoryDTO categoryDTO = this.modelMapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = categories.stream().map((category -> this.modelMapper.map(category,CategoryDTO.class))).collect(Collectors.toList());
        return categoryDTOList;
    }
}
