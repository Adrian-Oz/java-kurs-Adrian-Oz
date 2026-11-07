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
        ProductDTO prod5 = ProductDTO.fromCsvLine(";AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA;-10;ELECTRONICS");

        List<String> erorrs = ProductValidator.validate(prod5);
        List<String> erorrs2 = ProductValidator.validate(prod1);

        System.out.println(erorrs);
        System.out.println(erorrs2);


    }
}
