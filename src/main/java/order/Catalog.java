package order;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class Catalog {

    private final List<Product> insertionOrder = new ArrayList<>();
    private final Set<String> categories = new HashSet<>();
    private final Map<String, Product> bySku = new HashMap<>();

    public void add(Product p) {
        insertionOrder.add(p);
        categories.add(p.category());
        bySku.put(p.sku(), p);
    }

    public Optional<Product> findBySku(String sku) {
        return Optional.ofNullable(bySku.get(sku));
    }

    public List<Product> findByCategory(String cat) {
        List<Product> byCategory = new ArrayList<>();
        for (Product item : bySku.values()) {
            if (item.category().equals(cat)) {
                byCategory.add(item);
            }
        }
        byCategory.sort(Comparator.comparing(Product::price));
        return byCategory;
    }

    public Set<String> getCategories() {
        return new HashSet<>(categories);
    }

    //Could use the default sort from Product compareTo. Will leave this for now.
    public List<Product> sortedByName() {
        List<Product> products = new ArrayList<>(bySku.values());
        products.sort(Comparator.comparing(Product::name));
        return products;
    }

    public List<Product> sortedByPriceDesc() {
        List<Product> products = new ArrayList<>(bySku.values());
        products.sort(Comparator.comparing(Product::price).reversed());
        return products;
    }

    public int size() {
        return bySku.size();
    }

    public void removeExpired(Predicate<Product> condition) {
        insertionOrder.removeIf(condition);
    }

    public Map<String, List<Product>> groupByCategory() {
        Map<String, List<Product>> groups = new HashMap<>();
        for (Product item : bySku.values()) {
            groups.computeIfAbsent(item.category(), (key) -> new ArrayList<>()).add(item);
        }
        return groups;
    }

    public Map<String, Integer> countByCategory() {
        Map<String, Integer> groups = new HashMap<>();
        for (Product item : bySku.values()) {
            groups.merge(item.category(), 1, Integer::sum);
        }
        return groups;
    }

    public Map<String, BigDecimal> totalValueByCategory() {
        Map<String, BigDecimal> groups = new HashMap<>();
        for (Product item : bySku.values()) {
            groups.merge(item.category(), item.price(), BigDecimal::add);
        }
        return groups;
    }


    public Optional<Product> findCheapest() {
//      Because min() throws NoSuchElementException, I can't use Optional.ofNullable
        return bySku.isEmpty() ? Optional.empty() :
                Optional.of(Collections.min(
                        bySku.values(),
                        Comparator.comparing(Product::price)
                ));
    }

    public Optional<Product> findMostExpensive() {
//      Because min() throws NoSuchElementException, I can't use Optional.ofNullable
        return bySku.isEmpty() ? Optional.empty() :
                Optional.of(Collections.max(
                        bySku.values(),
                        Comparator.comparing(Product::price)
                ));
    }


}




