package oop;

public class FixedAmountDiscount implements DiscountPolicy{

    private final double discount;

    public FixedAmountDiscount(double discount) {
        this.discount = discount;
    }


    @Override
    public double apply(double price) {
        double newPrice = price - discount;
        //IntelliJ suggestion.
        return Math.max(newPrice, 0.01d);
    }

    @Override
    public String getName() {
        return "FixedAmountDiscount";
    }
}
