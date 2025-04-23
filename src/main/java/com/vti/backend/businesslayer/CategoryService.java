package com.vti.backend.businesslayer;
import com.vti.backend.datalayer.CategoryReponsitory;
import com.vti.backend.datalayer.ICategoryReponsitory;
import com.vti.entity.Category;
import java.util.List;

public class CategoryService implements ICategoryService {

    private ICategoryReponsitory categoryReponsitory = new CategoryReponsitory();


    @Override
    public List<Category> getAllCategories() {
        return categoryReponsitory.getAllCategories();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryReponsitory.getCategoryById(id);
    }

    @Override
    public void addCategory(Category category) {
        if (category.getCategoryName() != null && !category.getCategoryName().trim().isEmpty()) {
            categoryReponsitory.addCategory(category);
        }
    }

    @Override
    public void updateCategory(int id, String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            categoryReponsitory.updateCategory(id, newName);
        }
    }

    @Override
    public void deleteCategory(int id) {
        categoryReponsitory.deleteCategory(id);
    }
}