package oop;

import java.math.BigDecimal;

public class FixedAmountDiscount implements DiscountPolicy {

    private final double discount;

    public FixedAmountDiscount(double discount) {
        this.discount = discount;
    }


    @Override
    public Money apply(Money price) {
//        Money newPrice = price - discount;
        Money newPrice = new Money(price.amount().subtract(BigDecimal.valueOf(discount)), price.currency());
        //IntelliJ suggestion.
//        return Math.max(newPrice, new Money(new BigDecimal("0.01"),price.currency()));
        Money minPrice = new Money(new BigDecimal("0.01"),price.currency());
        if (!(newPrice.isGreaterThan(minPrice))) {
            return minPrice;
        }

        return newPrice;
    }

    @Override
    public String getName() {
        return "FixedAmountDiscount";
    }
}
