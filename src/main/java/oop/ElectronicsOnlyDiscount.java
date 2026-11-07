package oop;

import java.math.BigDecimal;

public class ElectronicsOnlyDiscount implements DiscountPolicy {

    @Override
    public Money apply(Money price) {
//        return price.amount() * 0.9;
        return new Money(price.amount().multiply(new BigDecimal("0.9")),price.currency());
    }



    @Override
    public String getName() {
        return "ElectronicsOnlyDiscount";
    }

    @Override
    public boolean appliesTo(Product p) {
        return p.getCategory().equals(Category.ELECTRONICS);
    }
}
