package com.blog.api.service;

import com.blog.api.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
    void deleteCategory(Integer categoryId);
    CategoryDTO getCategory(Integer categoryId);
    List<CategoryDTO> getAllCategories();

}
