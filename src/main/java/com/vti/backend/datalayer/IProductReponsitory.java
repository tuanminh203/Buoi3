package com.vti.backend.datalayer;

import com.vti.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IProductReponsitory {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    List<Product> searchProductsByName(String name);
    List<Product> searchProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> searchProductsByDateRange(LocalDate startDate, LocalDate endDate);
}
