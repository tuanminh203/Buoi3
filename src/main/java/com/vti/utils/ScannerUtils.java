package com.vti.utils;

import java.util.Scanner;

public class ScannerUtils {

    public static Scanner scanner = new Scanner(System.in);

    public static int nextInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập một số nguyên!");
                System.out.print("Nhập lại: ");
            }
        }
    }

    public static double nextDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập một số thực!");
                System.out.print("Nhập lại: ");
            }
        }
    }

    public static String nextLine() {
        return scanner.nextLine().trim();
    }
}