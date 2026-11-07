package oop;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        Electronics e1 = new Electronics("ELEC-1", "PC", 20.00, Category.ELECTRONICS, 24);
        FoodProduct f1 = new FoodProduct("FOOD-1", "Milk", 10.00, Category.FOOD, "2026-10-15");
        ProductDTO prod1 = ProductDTO.fromCsvLine("ELEC-001;Laptop;10.00;ELECTRONICS");
        ProductDTO prod2 = ProductDTO.fromCsvLine("FOOD-002;Rum;20.00;ALCOHOL");
        ProductDTO prod3 = ProductDTO.fromCsvLine("FOOD-003;Banany;30.00;FOOD");
        ProductDTO prod4 = ProductDTO.fromCsvLine("BOOK-004;ShadowSlave;40.00;BOOKS");
        ProductDTO prod5 = ProductDTO.fromCsvLine("ELEC-005;PC;50.00;ELECTRONICS");

        List<ProductDTO> products = List.of(
                prod1,
                prod2,
                prod3,
                prod4,
                prod5
        );


        System.out.println("Ceny brutto :");
        System.out.printf("%.2f%n", prod1.grossPrice());
        System.out.printf("%.2f%n", prod2.grossPrice());
        System.out.printf("%.2f%n", prod3.grossPrice());
        System.out.printf("%.2f%n", prod4.grossPrice());
        System.out.printf("%.2f%n", prod5.grossPrice());


        Map<Category, Integer> categoryCounts = new HashMap<>();
        for (ProductDTO item : products) {
            Category category = item.category();
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);

        }
        System.out.println(categoryCounts.toString());

    }
}
