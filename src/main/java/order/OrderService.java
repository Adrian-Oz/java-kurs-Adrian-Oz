package order;


import java.math.BigDecimal;
import java.util.*;

public class OrderService {
    private final Catalog catalog;
    private final PriceList priceList;
    private final OrderQueue orderQueue;

    public OrderService(Catalog catalog, PriceList priceList, OrderQueue orderQueue) {
        this.catalog = catalog;
        this.priceList = priceList;
        this.orderQueue = orderQueue;
    }

    public BigDecimal calculateTotal(Order o) {
        List<Product> orderProducts = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (String sku : o.skus()) {
            catalog.findBySku(sku).ifPresent(orderProducts::add);
        }
        for (Product prod : orderProducts) {
            total = total.add(prod.price());
        }
        BigDecimal discount = priceList.getDiscountFor(orderProducts.size());
        System.out.println(total);
        total = total.subtract(total.multiply(discount));
        return total;
    }


    public List<String> findUnavailableSkus(Order o) {
        List<String> missing = new ArrayList<>();
        Set<String> skus = new HashSet<>(o.skus());
        for (String sku : skus) {
            if (catalog.findBySku(sku).isEmpty()) {
                missing.add(sku);
            }
        }
        return missing;
    }

    public Map<String, Long> countProductsInPendingOrders() {
        Map<String, Long> groups = new HashMap<>();
        for (Order o : orderQueue.getPending()) {
            for (String sku : o.skus()) {
                groups.merge(sku, 1L, Long::sum);
            }
        }
        return groups;
    }

}


