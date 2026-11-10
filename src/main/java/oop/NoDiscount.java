package oop;

public class NoDiscount implements DiscountPolicy {


    private final double discount = 0;


    @Override
    public Money apply(Money price) {
        return price;
    }

    @Override
    public String getName() {
        return "NoDiscount";
    }

}
