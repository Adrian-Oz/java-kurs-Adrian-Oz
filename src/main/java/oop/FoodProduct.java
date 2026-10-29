package oop;

public class FoodProduct extends Product {
    public FoodProduct(Product other, String expiryDate) {
        super(other);
        this.expiryDate = expiryDate;
    }

    String expiryDate;
    @Override
    public String getDescription() {
        return super.getDescription() + " (Termin: " + expiryDate + ")";
    }

    @Override
    public double getShippingCost() {
        return 29.99d;
    }
}
