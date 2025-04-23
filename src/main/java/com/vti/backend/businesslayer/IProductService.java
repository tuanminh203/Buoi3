package com.vti.backend.businesslayer;

import com.vti.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void addProduct(String productName, int categoryId, double price, int stockQuantity, String importDateStr);
    void updateProduct(int productId, String newProductName, Integer newCategoryId, Double newPrice, Integer newStockQuantity, String newImportDateStr);
    void deleteProduct(int id);
    List<Product> searchProductsByName(String name);
    List<Product> searchProductsByPriceRange(double minPrice, double maxPrice);
    List<Product> searchProductsByDateRange(String startDateStr, String endDateStr);
}
