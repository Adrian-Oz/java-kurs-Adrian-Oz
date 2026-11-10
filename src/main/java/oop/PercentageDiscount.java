package oop;

import java.math.BigDecimal;

public class PercentageDiscount implements DiscountPolicy {

    private final double discount;

    public PercentageDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public Money apply(Money price) {
//        double newPrice = price * (100 - discount) / 100;
        Money newPrice = price.applyPercent(discount);
        Money minPrice = new Money(new BigDecimal("0.01"),price.currency());
        if (!(newPrice.isGreaterThan(minPrice))) {
            return minPrice;
        }
        return newPrice;
    }

    @Override
    public String getName() {
        return "PercentageDiscount";
    }
}
