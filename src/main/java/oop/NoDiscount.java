package oop;

public class NoDiscount implements DiscountPolicy {


    private final double discount = 0;


    @Override
    public double apply(double price) {
        return price;
    }

    @Override
    public String getName() {
        return "NoDiscount";
    }

}
