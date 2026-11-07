package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Product implements Identifiable<String>, Comparable<Product> {

    private static int createdCount;

    public static int getCreatedCount() {
        return createdCount;
    }

    static final double DEFAULT_VAT_RATE = 0.23;

    private final String sku;
    private final String name;
    private double netPrice;
    private final List<String> tags;
    private final Category category;

    public Product(String sku, String name, double netPrice, Category category) {
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
        this.category = category;
        this.tags = new ArrayList<String>();

        createdCount++;
    }

    @Override
    public String getId() {
        return getSku();
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

    public double getGrossPrice() {
        return getNetPrice() * (1 + category.getVatRate());
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
        if (newTag.isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be empty");
        }
        this.tags.add(newTag);
    }

    public Product addTags(String... tags) {
        for (String tag : tags) {
            addTag(tag);
        }
        return this;
    }

    public List<String> getTags() {
        return List.copyOf(this.tags);
    }

    public String getDescription() {
        return name + " - " + getGrossPrice();
    }

    public abstract double getShippingCost();

    public Category getCategory() {
        return this.category;
    }

    public final String getLabel() {
        return String.format("%s [%s] - %.2f zł (z wysyłką %.2f zł)", getName(), getCategory(), getGrossPrice(), (getGrossPrice() + getShippingCost()));
    }

    public final double getTotalPrice() {
        return getGrossPrice() + getShippingCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(sku);
    }

    @Override
    public String toString() {
        return sku + " " + name + " — netto "
                + String.format("%.2f", getNetPrice()) + " / brutto " + String.format("%.2f", getGrossPrice())
                + " zł";
    }


    public int compareTo(Product other) {
        return Double.compare(this.getGrossPrice(), other.getGrossPrice());
    }

}
