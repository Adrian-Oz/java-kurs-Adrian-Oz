package oop;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class Product implements Identifiable<String>, Comparable<Product> {

    private static int createdCount;
    static final double DEFAULT_VAT_RATE = 0.23;

    private final String sku;
    private final String name;
    private Money netPrice;
    private final Category category;
    @Getter(AccessLevel.NONE)
    private final List<String> tags;

    public Product(String sku, String name, Money netPrice, Category category) {
        if (sku.isEmpty() || !sku.equals(sku.toUpperCase()) || !sku.equals(sku.strip())) {
            throw new IllegalArgumentException("Sku must not be empty , contain all uppercase and can't contain whitespace at the beginning or end.");
        }
        if (name.isEmpty() || !name.equals(name.strip())) {
            throw new IllegalArgumentException("Name must not be empty or contain whitespace at the beginning or end.");
        }

        this.sku = sku;
        this.name = name;
        this.netPrice = netPrice;
        this.category = category;
        this.tags = new ArrayList<String>();

        createdCount++;
    }

    public abstract double getShippingCost();

    public abstract String getLabel();

    public final Money getTotalPrice() {
        return new Money(getGrossPrice().amount().add(BigDecimal.valueOf(getShippingCost())), this.netPrice.currency());
    }


    public Money getNetPrice() {
        return new Money(netPrice.amount(), this.netPrice.currency());
    }

    public Money getGrossPrice() {
        return new Money(getNetPrice().amount().multiply(BigDecimal.valueOf(1 + category.getVatRate())), this.netPrice.currency());
    }

    public void raisePriceBy(double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Percent have to be a positive number");
        }
        this.netPrice = this.netPrice.applyPercent(percent);

    }

    public void applyDiscount(double percent) {
        if (percent <= 0 || percent >= 100) {
            throw new IllegalArgumentException("Discount must be greater than 0 and less than 100");
        }
        BigDecimal newPrice = this.netPrice.applyPercent(percent).amount();
        if (newPrice.compareTo(BigDecimal.valueOf(0.01)) < 0) {
            throw new IllegalArgumentException("Price can't go bellow 0,01");
        }
        this.netPrice = new Money(newPrice, this.netPrice.currency());
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
                + String.format("%s", getNetPrice()) + " / brutto " + String.format("%s", getGrossPrice());
    }


    public int compareTo(Product other) {
        return this.netPrice.amount().compareTo(other.netPrice.amount());
    }

}
