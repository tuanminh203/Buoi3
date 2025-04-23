package com.vti.frontend;

import com.vti.backend.presentationlayer.CategoryController;
import com.vti.backend.presentationlayer.ProductController;
import com.vti.utils.ScannerUtils;

public class Program {

    private static CategoryController categoryController = new CategoryController();
    private static ProductController productController = new ProductController();

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = ScannerUtils.nextInt();
            ScannerUtils.scanner.nextLine();

            switch (choice) {
                case 1:
                    categoryController.showAllCategories();
                    break;
                case 2:
                    categoryController.addCategory();
                    break;
                case 3:
                    categoryController.editCategory();
                    break;
                case 4:
                    categoryController.deleteCategory();
                    break;
                case 5:
                    productController.showAllProducts();
                    break;
                case 6:
                    productController.showProductById();
                    break;
                case 7:
                    productController.addProduct();
                    break;
                case 8:
                    productController.editProduct();
                    break;
                case 9:
                    productController.deleteProduct();
                    break;
                case 10:
                    productController.searchProductByName();
                    break;
                case 11:
                    productController.searchProductByPriceRange();
                    break;
                case 12:
                    productController.searchProductByDateRange();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("Chương trình quản lý thể loại và sản phẩm:");
        System.out.println("1. Hiển thị tất cả thể loại");
        System.out.println("2. Thêm thể loại");
        System.out.println("3. Sửa thể loại");
        System.out.println("4. Xóa thể loại");
        System.out.println("5. Hiển thị tất cả sản phẩm");
        System.out.println("6. Hiển thị sản phẩm theo ID");
        System.out.println("7. Thêm mới sản phẩm");
        System.out.println("8. Sửa sản phẩm");
        System.out.println("9. Xóa sản phẩm");
        System.out.println("10. Tìm kiếm sản phẩm theo tên");
        System.out.println("11. Tìm kiếm sản phẩm theo khoảng giá");
        System.out.println("12. Tìm kiếm sản phẩm trong khoảng thời gian");
        System.out.println("0. Thoát");
    }
}