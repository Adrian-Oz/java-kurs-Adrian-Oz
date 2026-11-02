package oop;

import java.time.LocalDate;

public class FoodProduct extends Product implements Perishable {
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

    @Override
    public boolean isExpired() {
        LocalDate expiry = LocalDate.parse(expiryDate);
        return expiry.isBefore(LocalDate.now());
    }
}
