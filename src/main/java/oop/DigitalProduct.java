package oop;

import lombok.Getter;

@Getter
public class DigitalProduct extends Product {
    public DigitalProduct(String sku, String name, Money netPrice, Category category, String downloadUrl) {
        super(sku, name, netPrice, category);
        this.downloadUrl = downloadUrl;
    }

    String downloadUrl;

    @Override
    public String getDescription() {
        return super.getDescription() + " (" + downloadUrl + ")";
    }

    @Override
    public double getShippingCost() {
        return 0;
    }

    @Override
    public String getLabel() {
     return String.format("%s [%s] - %.2f zł (z wysyłką %.2f zł)", getName(), getCategory(), getGrossPrice().amount(), getTotalPrice().amount());
    }

    @Override
    public Category getCategory() {
        return super.getCategory();
    }

    @Override
    public String getId() {
        return getSku();
    }
}
