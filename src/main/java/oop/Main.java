package oop;

public class Main {
    public static void main(String[] args) {


        Electronics e1 = new Electronics("ELEC-1", "PC", 20.00, 24);
        FoodProduct f1 = new FoodProduct("FOOD-1", "Milk", 10.00, "2026-10-15");
        DigitalProduct d1 = new DigitalProduct("DIGIT-1", "Photoshop", 15.50, "SomeURL");
        DigitalProduct d2 = new DigitalProduct("DIGIT-2", "IntelliJ", 39.99, "SomeURL");

        ShoppingCart cart1 = new ShoppingCart(new NoDiscount());
        ShoppingCart cart2 = new ShoppingCart(new ElectronicsOnlyDiscount());
        ShoppingCart cart3 = new ShoppingCart(new PercentageDiscount(10));
        ShoppingCart cart4 = new ShoppingCart(new FixedAmountDiscount(25));

        cart1.add(e1);
        cart1.add(f1);
        cart1.add(d1);
        cart1.add(d2);

        cart2.add(e1);
        cart2.add(f1);
        cart2.add(d1);
        cart2.add(d2);

        cart3.add(e1);
        cart3.add(f1);
        cart3.add(d1);
        cart3.add(d2);

        cart4.add(e1);
        cart4.add(f1);
        cart4.add(d1);
        cart4.add(d2);

        System.out.println("####################### NoDiscount ########################");
        System.out.println(cart1.getReceipt());
        System.out.println("####################### ElectronicsOnlyDiscount ########################");
        System.out.println(cart2.getReceipt());
        System.out.println("####################### PercentageDiscount ########################");
        System.out.println(cart3.getReceipt());
        System.out.println("######################## FixedAmountDiscount #######################");
        System.out.println(cart4.getReceipt());


    }
}
