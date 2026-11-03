package oop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    public ShoppingCart(DiscountPolicy policy) {
        this.policy = policy;
    }

    private DiscountPolicy policy;

    private final List<Product> cart = new ArrayList<>();


    public void add(Product item) {
        cart.add(item);
    }

    public void remove(String sku) {
        cart.removeIf(product -> product.getSku().equals(sku));
    }

    public double getTotal() {
        double cartTotal = 0;
        for (Product item : cart) {
            if (policy.appliesTo(item)) {
                cartTotal += policy.apply(item.getGrossPrice());
            } else {
                cartTotal += item.getGrossPrice();
            }

        }
        return cartTotal;
    }

    public String getReceipt() {
        StringBuilder sb = new StringBuilder();

        for (Product item : cart) {
            sb.append(item.toString())
                    .append(System.lineSeparator());
        }
        sb.append("Total value after discount : ").append(String.format("%.2f", getTotal()));

        return sb.toString();
    }

}
