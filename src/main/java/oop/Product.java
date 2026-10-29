package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Product {

    private static int createdCount;

    public static int getCreatedCount() {
        return createdCount;
    }

    static final double DEFAULT_VAT_RATE = 0.23;

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
        createdCount++;
    }

//    public Product(Product other) {
//        this(other.sku, other.name, other.netPrice);
//        this.tags.addAll(other.tags);
//    }


//    public static Product of(String sku, String name, double netPrice) {
//        return new Product(sku, name, netPrice);
//    }

//    public static Product fromCsvLine(String line) {
//        String[] csvLineParts = line.split(";");
//        if (csvLineParts.length != 3) {
//            throw new IllegalArgumentException(line);
//        }
//        double price = Double.parseDouble(csvLineParts[2]);
//        if (csvLineParts[0].isEmpty() || !csvLineParts[0].equals(csvLineParts[0].toUpperCase()) || !csvLineParts[0].equals(csvLineParts[0].strip())) {
//            throw new IllegalArgumentException(line);
//        }
//        if (csvLineParts[1].isEmpty() || !csvLineParts[1].equals(csvLineParts[1].strip())) {
//            throw new IllegalArgumentException(line);
//        }
//        if (price <= 0) {
//            throw new IllegalArgumentException(line);
//        }
//        return new Product(csvLineParts[0], csvLineParts[1], price);
//
//    }


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
        return getNetPrice() * (1 + DEFAULT_VAT_RATE);
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
        return name + " - " +getGrossPrice();
    }
    public abstract double getShippingCost();

    public abstract String getCategory();

    public final String getLabel() {
        return String.format("%s [%s] - %.2f zł (z wysyłką %.2f zł",getName(),getCategory(),getGrossPrice(),(getGrossPrice()+getShippingCost()));
    }

    public final double getTotalPrice(){
        return getGrossPrice()+getShippingCost();
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

}
