package com.vti.backend.presentationlayer;

import com.vti.backend.businesslayer.CategoryService;
import com.vti.backend.businesslayer.ICategoryService;
import com.vti.entity.Category;
import com.vti.utils.ScannerUtils;

import java.util.List;

public class CategoryController {
    private ICategoryService categoryService = new CategoryService();

    public void showAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("Không có thể loại nào.");
        } else {
            System.out.println("--- Danh sách tất cả thể loại ---");
            for (Category category : categories) {
                System.out.println(category.getCategoryId() + ". " + category.getCategoryName());
            }
        }
    }

    public void addCategory() {
        System.out.print("Nhập tên thể loại mới: ");
        String categoryName = ScannerUtils.nextLine();
        categoryService.addCategory(new Category(categoryName));
        System.out.println("Thêm thể loại thành công.");
    }

    public void editCategory() {
        System.out.print("Nhập ID thể loại cần sửa: ");
        int categoryId = ScannerUtils.nextInt();
        ScannerUtils.scanner.nextLine();
        Category existingCategory = categoryService.getCategoryById(categoryId);
        if (existingCategory == null) {
            System.out.println("Không tìm thấy thể loại với ID " + categoryId);
            return;
        }
        System.out.print("Nhập tên thể loại mới: ");
        String newCategoryName = ScannerUtils.nextLine();
        categoryService.updateCategory(categoryId, newCategoryName);
        System.out.println("Sửa thể loại thành công.");
    }

    public void deleteCategory() {
        System.out.print("Nhập ID thể loại cần xóa: ");
        int categoryId = ScannerUtils.nextInt();
        ScannerUtils.scanner.nextLine();
        categoryService.deleteCategory(categoryId);
        System.out.println("Xóa thể loại thành công.");
    }
}
