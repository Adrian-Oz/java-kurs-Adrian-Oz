package oop;

public class FoodProduct extends Product {
    public FoodProduct(String sku, String name, double netPrice, String expiryDate) {
        super(sku, name, netPrice);
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

    @Override
    public String getCategory() {
        return "FoodProduct";
    }
}
