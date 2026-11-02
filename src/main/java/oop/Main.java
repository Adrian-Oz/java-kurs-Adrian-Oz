package oop;



public class Main {
    public static void main(String[] args) {


        Electronics e1 = new Electronics("ELEC-1", "PC", 20.00, 24);
        FoodProduct f1 = new FoodProduct("FOOD-1", "Milk", 10.00, "2026-10-15");
        DigitalProduct d1 = new DigitalProduct("DIGIT-1", "Photoshop", 15.50, "SomeURL");
        DigitalProduct d2 = new DigitalProduct("DIGIT-2", "IntelliJ", 39.99, "SomeURL");

        Warehouse<Product> wh1 = new Warehouse<>();
        Warehouse<Person> wh2 = new Warehouse<>();
        Person person1 = new Person("Adrian");

        wh1.add(e1);
        wh1.add(f1);
        wh1.add(d1);
        wh1.add(d2);
        wh2.add(person1);

        System.out.println(wh1.findAll());
        double sum = Warehouse.sumPrices(wh1.findAll());
        System.out.println(sum);
        System.out.println(Warehouse.sortedCopy(wh1.findAll()));


    }
}
