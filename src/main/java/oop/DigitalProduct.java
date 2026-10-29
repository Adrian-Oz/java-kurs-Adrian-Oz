package oop;

public class DigitalProduct extends Product {
    public DigitalProduct(Product other, String downloadUrl) {
        super(other);
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
}
