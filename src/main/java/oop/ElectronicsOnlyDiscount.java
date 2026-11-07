package oop;

public class ElectronicsOnlyDiscount implements DiscountPolicy {

    @Override
    public double apply(double price) {
        return price * 0.9;
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
