package oop;

public class PercentageDiscount implements DiscountPolicy {

    private final double discount;

    public PercentageDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public double apply(double price) {
        double newPrice = price * (100 - discount) / 100;
        return Math.max(newPrice, 0.01);
    }

    @Override
    public String getName() {
        return "PercentageDiscount";
    }
}
