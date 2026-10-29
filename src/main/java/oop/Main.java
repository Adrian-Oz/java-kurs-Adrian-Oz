package oop;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {


        Product p0 = Product.of("ELEC-001", "Laptop", 3999.00);
        Product p1 = Product.of("KEKW-2137", "Meme", 39.00);
        Product p2 = Product.fromCsvLine("ELELE;PC;10000.99");

        Product p3 = new Product(p0);
        System.out.println(Product.getCreatedCount());

        HashSet<Product> productsSet = new HashSet<>();
        productsSet.add(p0);
        productsSet.add(p1);
        productsSet.add(p2);
        System.out.println(productsSet.size());
        productsSet.add(p3);
        System.out.println(productsSet.size());

        System.out.println((p0 == p3) + " | " + p0.equals(p3));

        Product p4 = Product.fromCsvLine("ELELE;PC");
    }
}
