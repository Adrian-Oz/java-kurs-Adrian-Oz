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

    public Money getTotal() {
        Money cartTotal = Money.zero("Zł");
        for (Product item : cart) {
            if (policy.appliesTo(item)) {
                cartTotal.add(policy.apply(item.getGrossPrice()));
            } else {
                cartTotal.add(item.getGrossPrice());
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
        sb.append("Total value after discount : ").append(String.format("%.2f", getTotal().amount()));

        return sb.toString();
    }

}
