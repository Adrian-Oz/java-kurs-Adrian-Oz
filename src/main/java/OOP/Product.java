package OOP;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private final String sku;
    private final String name;
    private double netPrice;
    private final List<String> tags;

    public Product(String sku, String name, double netPrice) {
        if (sku.isEmpty() || !sku.equals(sku.toUpperCase()) || !sku.equals(sku.strip())) {
            throw new IllegalArgumentException("Sku must not be empty , contain all uppercase and can't contain whitespace at the beginning or end.");
        }
        if (name.isEmpty() || !name.equals(name.strip())) {
            throw new IllegalArgumentException("Name must not be empty or contain whitespace at the beginning or end.");
        }
        if (netPrice <= 0) {
            throw new IllegalArgumentException("Price has to be a positive number");
        }
        this.sku = sku;
        this.name = name;
        this.netPrice = netPrice;
        this.tags = new ArrayList<String>();
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void raisePriceBy(double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Percent have to be a positive number");
        }
        this.netPrice = this.netPrice * ((100 + percent) / 100);

    }

    public void applyDiscount(double percent) {
        if (percent <= 0 || percent >= 100) {
            throw new IllegalArgumentException("Discount must be greater than 0 and less than 100");
        }
        double newPrice = this.netPrice * ((100 - percent) / 100);
        if (newPrice < 0.01) {
            throw new IllegalArgumentException("Price can't go bellow 0,01");
        }
        this.netPrice = newPrice;
    }

    public void addTag(String tag) {
        String newTag = tag.strip().toLowerCase();
        if(newTag.isEmpty()){
            throw new IllegalArgumentException("Tag cannot be empty");
        }
        this.tags.add(newTag);
    }

    public List<String> getTags() {
        return List.copyOf(this.tags);
    }
    @Override
    public String toString() {
        return sku + " " + name + " — "
                + String.format("%.2f", netPrice)
                + " zł [" + String.join(", ", tags) + "]";
    }

}
