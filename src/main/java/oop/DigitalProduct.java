package oop;

public class DigitalProduct extends Product {
    public DigitalProduct(String sku, String name, double netPrice, Category category, String downloadUrl) {
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
    public Category getCategory() {
        return super.getCategory();
    }
}
