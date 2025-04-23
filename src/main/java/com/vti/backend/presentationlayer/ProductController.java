package com.vti.backend.presentationlayer;

import com.vti.backend.businesslayer.IProductService;
import com.vti.backend.businesslayer.ProductService;
import com.vti.entity.Product;
import com.vti.utils.ScannerUtils;

import java.util.List;

public class ProductController {
    private IProductService productService = new ProductService();

    public void showAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            System.out.println("--- Danh sách tất cả sản phẩm ---");
            for (Product product : products) {
                System.out.println(product.getProductId() + ". " + product.getProductName() +
                        " - Thể loại: " + (product.getCategory() != null ? product.getCategory().getCategoryName() : "N/A") +
                        " - Giá: " + product.getPrice() +
                        " - Tồn kho: " + product.getStockQuantity() +
                        " - Ngày nhập: " + product.getImportDate());
            }
        }
    }

    public void showProductById() {
        System.out.print("Nhập ID sản phẩm cần xem: ");
        int productId = ScannerUtils.nextInt();
        ScannerUtils.scanner.nextLine();
        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm với ID " + productId);
        } else {
            System.out.println("--- Thông tin sản phẩm ---");
            System.out.println("ID: " + product.getProductId());
            System.out.println("Tên sản phẩm: " + product.getProductName());
            System.out.println("Thể loại: " + (product.getCategory() != null ? product.getCategory().getCategoryName() : "N/A"));
            System.out.println("Giá: " + product.getPrice());
            System.out.println("Tồn kho: " + product.getStockQuantity());
            System.out.println("Ngày nhập: " + product.getImportDate());
        }
    }

    public void addProduct() {
        System.out.print("Nhập tên sản phẩm: ");
        String productName = ScannerUtils.nextLine();
        System.out.print("Nhập ID thể loại: ");
        int categoryId = ScannerUtils.nextInt();
        ScannerUtils.scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = ScannerUtils.nextDouble();
        System.out.print("Nhập số lượng tồn kho: ");
        int stockQuantity = ScannerUtils.nextInt();
        ScannerUtils.scanner.nextLine();
        System.out.print("Nhập ngày nhập (yyyy-MM-dd): ");
        String importDateStr = ScannerUtils.nextLine();

        productService.addProduct(productName, categoryId, price, stockQuantity, importDateStr);
        System.out.println("Thêm sản phẩm thành công.");
    }

    public void editProduct() {
        System.out.print("Nhập ID sản phẩm cần sửa: ");
        int productId = ScannerUtils.nextInt();
        ScannerUtils.scanner.nextLine();

        System.out.print("Nhập tên sản phẩm mới (để trống nếu không đổi): ");
        String newProductName = ScannerUtils.nextLine();
        System.out.print("Nhập ID thể loại mới (để trống nếu không đổi): ");
        String newCategoryIdStr = ScannerUtils.nextLine();
        Integer newCategoryId = newCategoryIdStr.isEmpty() ? null : ScannerUtils.nextInt();
        if (newCategoryId != null) ScannerUtils.scanner.nextLine();
        System.out.print("Nhập giá sản phẩm mới (để trống nếu không đổi): ");
        String newPriceStr = ScannerUtils.nextLine();
        Double newPrice = newPriceStr.isEmpty() ? null : ScannerUtils.nextDouble();
        System.out.print("Nhập số lượng tồn kho mới (để trống nếu không đổi): ");
        String newStockQuantityStr = ScannerUtils.nextLine();
        Integer newStockQuantity = newStockQuantityStr.isEmpty() ? null : ScannerUtils.nextInt();
        if (newStockQuantity != null) ScannerUtils.scanner.nextLine();
        System.out.print("Nhập ngày nhập mới (yyyy-MM-dd, để trống nếu không đổi): ");
        String newImportDateStr = ScannerUtils.nextLine();

        productService.updateProduct(productId, newProductName, newCategoryId, newPrice, newStockQuantity, newImportDateStr);
        System.out.println("Sửa sản phẩm thành công.");
    }

    public void deleteProduct() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int productId = ScannerUtils.nextInt();
        ScannerUtils.scanner.nextLine();
        productService.deleteProduct(productId);
        System.out.println("Xóa sản phẩm thành công.");
    }

    public void searchProductByName() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String productName = ScannerUtils.nextLine();
        List<Product> products = productService.searchProductsByName(productName);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + productName + "'.");
        } else {
            System.out.println("--- Kết quả tìm kiếm sản phẩm theo tên '" + productName + "' ---");
            for (Product product : products) {
                System.out.println(product.getProductId() + ". " + product.getProductName() +
                        " - Thể loại: " + (product.getCategory() != null ? product.getCategory().getCategoryName() : "N/A") +
                        " - Giá: " + product.getPrice() +
                        " - Tồn kho: " + product.getStockQuantity() +
                        " - Ngày nhập: " + product.getImportDate());
            }
        }
    }

    public void searchProductByPriceRange() {
        System.out.print("Nhập giá bắt đầu: ");
        double minPrice = ScannerUtils.nextDouble();
        System.out.print("Nhập giá kết thúc: ");
        double maxPrice = ScannerUtils.nextDouble();
        ScannerUtils.scanner.nextLine();

        List<Product> products = productService.searchProductsByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào trong khoảng giá từ " + minPrice + " đến " + maxPrice + ".");
        } else {
            System.out.println("--- Kết quả tìm kiếm sản phẩm theo khoảng giá (" + minPrice + " - " + maxPrice + ") ---");
            for (Product product : products) {
                System.out.println(product.getProductId() + ". " + product.getProductName() +
                        " - Thể loại: " + (product.getCategory() != null ? product.getCategory().getCategoryName() : "N/A") +
                        " - Giá: " + product.getPrice() +
                        " - Tồn kho: " + product.getStockQuantity() +
                        " - Ngày nhập: " + product.getImportDate());
            }
        }
    }

    public void searchProductByDateRange() {
        System.out.print("Nhập ngày bắt đầu (yyyy-MM-dd): ");
        String startDateStr = ScannerUtils.nextLine();
        System.out.print("Nhập ngày kết thúc (yyyy-MM-dd): ");
        String endDateStr = ScannerUtils.nextLine();

        List<Product> products = productService.searchProductsByDateRange(startDateStr, endDateStr);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào trong khoảng thời gian từ " + startDateStr + " đến " + endDateStr + ".");
        } else {
            System.out.println("--- Kết quả tìm kiếm sản phẩm theo khoảng thời gian (" + startDateStr + " - " + endDateStr + ") ---");
            for (Product product : products) {
                System.out.println(product.getProductId() + ". " + product.getProductName() +
                        " - Thể loại: " + (product.getCategory() != null ? product.getCategory().getCategoryName() : "N/A") +
                        " - Giá: " + product.getPrice() +
                        " - Tồn kho: " + product.getStockQuantity() +
                        " - Ngày nhập: " + product.getImportDate());
            }
        }
    }
}
