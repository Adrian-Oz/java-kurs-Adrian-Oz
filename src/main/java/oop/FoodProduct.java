package oop;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
public class FoodProduct extends Product implements Perishable {
    public FoodProduct(String sku, String name, Money netPrice, Category category, String expiryDate,Supplier supplier) {
        super(sku, name, netPrice, category,supplier);
        this.expiryDate = expiryDate;
    }

    String expiryDate;

    @Override
    public String getDescription() {
        return super.getDescription() + " (Termin: " + expiryDate + ")";
    }

    @Override
    public Money getShippingCost() {
//        return 29.99d;
        return new Money(new BigDecimal("29.99"), "zł");
    }

    @Override
    public Category getCategory() {
        return super.getCategory();

    }

    @Override
    public boolean isExpired() {
        LocalDate expiry = LocalDate.parse(expiryDate);
        return expiry.isBefore(LocalDate.now());
    }

    @Override
    public String getId() {
        return getSku();
    }
    @Override
    public String getLabel() {
        return String.format("%s [%s] - %.2f zł (z wysyłką %.2f zł)", getName(), getCategory(), getGrossPrice().amount(), getTotalPrice().amount());
    }
}
