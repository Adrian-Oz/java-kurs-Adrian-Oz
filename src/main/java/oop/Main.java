package oop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Product p1 = Product.of("ELEC-001", "Laptop", 3999.00);
        Electronics e1 =  new Electronics(  Product.of("ELEC-1", "PC", 20.00),24);
        FoodProduct f1 = new FoodProduct(  Product.of("FOOD-1", "Milk", 10.00),"2026-10-15");
        DigitalProduct d1 = new DigitalProduct(  Product.of("DIGIT-1", "Photoshop", 15.50),"SomeURL");
        DigitalProduct d2 = new DigitalProduct(  Product.of("DIGIT-2", "IntelliJ", 39.99),"SomeURL");

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(e1);
        products.add(f1);
        products.add(d1);
        products.add(d2);
        double cartValue = 0;
        for (Product item : products) {
            double totalItemCost = item.getGrossPrice()+item.getShippingCost();
            System.out.printf("%s - %.2f zł%n", item.getDescription(), totalItemCost);
            cartValue+= totalItemCost;
        }
        System.out.printf("%.2f zł%n",cartValue);

    }
}
