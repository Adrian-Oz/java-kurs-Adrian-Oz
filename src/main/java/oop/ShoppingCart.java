package oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShoppingCart {

    private final DiscountPolicy policy;
    private final List<Product> cart = new ArrayList<>();

    public ShoppingCart(DiscountPolicy policy) {

        this.policy = policy;
    }

    public void add(Product item, int amount) {

        for (int i = 0; i < amount; i++) {
            cart.add(item);
        }
    }

    public void remove(String sku) {
        cart.removeIf(product -> product.getSku().equals(sku));
    }

    public Money getTotal() {
        Money cartTotal = Money.zero("Zł");
        for (Product item : cart) {
            if (policy.appliesTo(item)) {
                cartTotal = cartTotal.add(policy.apply(item.getGrossPrice()));
            } else {
                cartTotal.add(item.getGrossPrice());
            }

        }
        return cartTotal;
    }

    public String getReceipt() {
        StringBuilder sb = new StringBuilder();

        String header = """
                ==========================================================
                                         Receipt
                ==========================================================
                """;
        sb.append(header);
        for (Product item : cart) {
            sb.append(item.toString())
                    .append(System.lineSeparator());
        }
        sb.append("Total value after discount : ").append(String.format("%.2f", getTotal().amount()));

        return sb.toString();
    }

    public List<Product> getSortedBy(Comparator<Product> comparator) {
        List<Product> sorted = new ArrayList<>(cart);
        sorted.sort(comparator);
        return sorted;
    }

    @Override
    public String toString() {
        return "ShoppingCart" +
                cart +
                '}';
    }
}
