package com.vti.backend.businesslayer;

import com.vti.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    void addCategory(Category category);
    void updateCategory(int id, String newName);
    void deleteCategory(int id);
}
