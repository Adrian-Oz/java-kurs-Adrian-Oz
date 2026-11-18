package oop;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Address address1 = new Address("A1","C1","1-11");
        Address address2 = new Address("A2","C2","2-22");


        Supplier supplierA = new Supplier("SupplierA",address1,"MAIL@");
        Supplier supplierB = new Supplier("SupplierA",address2,null);
        Supplier supplierC = new Supplier("SupplierA",null,null);


        Product prod1 = new Electronics("ELEC-001",
                "Laptop",
                new Money(new BigDecimal("10.10"),"zł"),
                Category.ELECTRONICS,
                24,
                supplierA);
        Product prod2 = new Electronics("ELEC-003","PC",new Money(new BigDecimal("3000.10"),"zł"),Category.ELECTRONICS,24,supplierB);
        Product prod3 = new DigitalProduct("DIGI-011","D4Bad",new Money(new BigDecimal("200"),"zł"),Category.DIGITAL,"abc",supplierC);
        Product prod4 = new FoodProduct("FOOD-002","CanedTuna",new Money(new BigDecimal("20"),"zł"),Category.FOOD,"10-11-2027",supplierC);

        prod1.setStock(10);
        prod2.setStock(2);


        ProductWarehouse pw = new ProductWarehouse();
        pw.add(prod1);
        pw.add(prod2);
        pw.add(prod3);
        pw.add(prod4);


//
//        System.out.println(pw.findSupplierName("ELEC-001"));
//        System.out.println(pw.findAddressOfCheapest());
//        System.out.println(pw.findEmailForContact("ELEC-001"));
//        System.out.println(pw.findSupplierEmail("ELEC-001"));
//
//        System.out.println(pw.findSupplierName("ELEC-003"));
//        System.out.println(pw.findEmailForContact("ELEC-003"));
//        System.out.println(pw.findSupplierEmail("ELEC-003"));
//
        List<String> order1 = new ArrayList<>();
        order1.add("asdasdas");
        order1.add("das");
        order1.add("ELEC-003");
        order1.add("FOOD-002");
        System.out.println(pw.processOrder(order1));









    }
}
