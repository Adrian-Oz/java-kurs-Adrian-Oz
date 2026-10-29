package oop;
public class Main {
    public static void main(String[] args) {



        Electronics e1 = new Electronics("ELEC-1", "PC", 20.00,24);
        FoodProduct f1 = new FoodProduct("FOOD-1", "Milk", 10.00,"2026-10-15");
        DigitalProduct d1 = new DigitalProduct("DIGIT-1", "Photoshop", 15.50,"SomeURL");
        DigitalProduct d2 = new DigitalProduct("DIGIT-2", "IntelliJ", 39.99,"SomeURL");

        Warehouse wh1 = new Warehouse();

        wh1.addProduct(e1);
        wh1.addProduct(f1);
        wh1.addProduct(d1);
        wh1.addProduct(d2);

        System.out.println(wh1.findBySku("ELEC-1"));

        System.out.println(wh1.getAll());
        System.out.println(wh1.getTotalValue());
        System.out.println(wh1.count());




    }
}
