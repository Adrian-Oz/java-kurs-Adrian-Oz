package oop;


import java.math.BigDecimal;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {


        Product prod1 = new Electronics("ELEC-001","Laptop",new Money(new BigDecimal("10.10"),"zł"),Category.ELECTRONICS,24);
        Product prod2 = new Electronics("ELEC-003","PC",new Money(new BigDecimal("3000.10"),"zł"),Category.ELECTRONICS,24);
        Product prod3 = new DigitalProduct("DIGI-011","D4Bad",new Money(new BigDecimal("200"),"zł"),Category.DIGITAL,"abc");
        Product prod4 = new FoodProduct("FOOD-002","CanedTuna",new Money(new BigDecimal("20"),"zł"),Category.FOOD,"10-11-2027");

//
//        InvoiceLine line1 = new InvoiceLine(prod1, 4);
//        InvoiceLine line2 = new InvoiceLine(prod2, 10);
//        InvoiceLine line3 = new InvoiceLine(prod3, 5);
//        InvoiceLine line4 = new InvoiceLine(prod4, 20);
//
//
//        Invoice inv1 = new Invoice();
//        inv1.addLine(line1);
//        inv1.addLine(line2);
//        inv1.addLine(line3);
//        inv1.addLine(line4);
//
//        System.out.println(inv1.getVatSummary());
//
//        System.out.println(inv1.render());


        ShoppingCart cart1 = new ShoppingCart(new NoDiscount());
        cart1.add(prod1,1);
        cart1.add(prod2,1);
        cart1.add(prod3,1);
        cart1.add(prod4,1);

        System.out.println(cart1.getReceipt());
        Comparator<Product> bySku = Comparator.comparing(Product::getSku);
        Comparator<Product> byNetPriceAscending = Comparator.comparing(Product::getNetPrice,(m1,m2)-> m1.amount().compareTo(m2.amount()));
        Comparator<Product> byNetPriceDescending = Comparator.comparing(Product::getNetPrice,(m1,m2)-> m1.amount().compareTo(m2.amount())).reversed();

        System.out.println(cart1);

        System.out.println(cart1.getSortedBy(bySku));
        System.out.println(cart1.getSortedBy(byNetPriceAscending));
        System.out.println(cart1.getSortedBy(byNetPriceDescending));







    }
}
