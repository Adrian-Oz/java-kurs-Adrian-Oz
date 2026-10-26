package oop;

public class Main {
    public static void main(String[] args) {
//        Product p1 = new Product("  ELEC-001","Laptop" , 390000);
//        Product p2 = new Product("","",-123);
        Product p1 = new Product("ELEC-001","Laptop" , 3999.00 );

        System.out.println(p1.toString());
        p1.applyDiscount(10);
        System.out.println(p1.toString());
        p1.raisePriceBy(10);
        System.out.println(p1.toString());

        p1.addTag("Gaming");
        p1.addTag("Promotion         ");

        System.out.println(p1.getTags());


    }
}
