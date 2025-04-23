package com.vti.backend.businesslayer;
import com.vti.backend.datalayer.CategoryReponsitory;
import com.vti.backend.datalayer.ICategoryReponsitory;
import com.vti.backend.datalayer.IProductReponsitory;
import com.vti.backend.datalayer.ProductReponsitory;
import com.vti.entity.Category;
import com.vti.entity.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ProductService implements IProductService {

    private IProductReponsitory productReponsitory = new ProductReponsitory();
    private ICategoryReponsitory categoryReponsitory = new CategoryReponsitory();

    @Override
    public List<Product> getAllProducts() {
        return productReponsitory.getAllProducts();
    }

    @Override
    public Product getProductById(int productId) {
        return productReponsitory.getProductById(productId);
    }

    @Override
    public void addProduct(String productName, int categoryId, double price, int stockQuantity, String importDateStr) {
        if (productName == null || productName.trim().isEmpty()) {
            System.err.println("Tên sản phẩm không được để trống.");
            return;
        }
        Category category = categoryReponsitory.getCategoryById(categoryId);
        if (category == null) {
            System.err.println("Không tìm thấy thể loại với ID " + categoryId);
            return;
        }
        if (price <= 0) {
            System.err.println("Giá sản phẩm phải lớn hơn 0.");
            return;
        }
        if (stockQuantity < 0) {
            System.err.println("Số lượng tồn kho không được âm.");
            return;
        }
        LocalDate importDate = null;
        try {
            importDate = LocalDate.parse(importDateStr);
        } catch (DateTimeParseException e) {
            System.err.println("Định dạng ngày nhập không hợp lệ (yyyy-MM-dd).");
            return;
        }

        Product product = new Product(productName, category, BigDecimal.valueOf(price), stockQuantity, importDate);
        productReponsitory.addProduct(product);
    }

    @Override
    public void updateProduct(int productId, String newProductName, Integer newCategoryId, Double newPrice, Integer newStockQuantity, String newImportDateStr) {
        Product existingProduct = productReponsitory.getProductById(productId);
        if (existingProduct == null) {
            System.err.println("Không tìm thấy sản phẩm với ID " + productId);
            return;
        }

        if (newProductName != null && !newProductName.trim().isEmpty()) {
            existingProduct.setProductName(newProductName);
        }

        if (newCategoryId != null) {
            Category category = categoryReponsitory.getCategoryById(newCategoryId);
            if (category == null) {
                System.err.println("Không tìm thấy thể loại với ID " + newCategoryId);
                return;
            }
            existingProduct.setCategory(category);
        }

        if (newPrice != null) {
            if (newPrice <= 0) {
                System.err.println("Giá sản phẩm phải lớn hơn 0.");
                return;
            }
            existingProduct.setPrice(BigDecimal.valueOf(newPrice));
        }

        if (newStockQuantity != null) {
            if (newStockQuantity < 0) {
                System.err.println("Số lượng tồn kho không được âm.");
                return;
            }
            existingProduct.setStockQuantity(newStockQuantity);
        }

        if (newImportDateStr != null && !newImportDateStr.trim().isEmpty()) {
            try {
                existingProduct.setImportDate(LocalDate.parse(newImportDateStr));
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày nhập không hợp lệ (yyyy-MM-dd).");
                return;
            }
        }

        productReponsitory.updateProduct(existingProduct);
    }

    @Override
    public void deleteProduct(int productId) {
        productReponsitory.deleteProduct(productId);
    }

    @Override
    public List<Product> searchProductsByName(String productName) {
        return productReponsitory.searchProductsByName(productName);
    }

    @Override
    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            System.err.println("Giá bắt đầu không được lớn hơn giá kết thúc.");
            return List.of();
        }
        return productReponsitory.searchProductsByPriceRange(BigDecimal.valueOf(minPrice), BigDecimal.valueOf(maxPrice));
    }

    @Override
    public List<Product> searchProductsByDateRange(String startDateStr, String endDateStr) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            startDate = LocalDate.parse(startDateStr);
            endDate = LocalDate.parse(endDateStr);
        } catch (DateTimeParseException e) {
            System.err.println("Định dạng ngày không hợp lệ (yyyy-MM-dd).");
            return List.of();
        }
        if (startDate.isAfter(endDate)) {
            System.err.println("Ngày bắt đầu không được sau ngày kết thúc.");
            return List.of();
        }
        return productReponsitory.searchProductsByDateRange(startDate, endDate);
    }
}