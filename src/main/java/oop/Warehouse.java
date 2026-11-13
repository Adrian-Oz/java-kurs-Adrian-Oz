package oop;

import java.util.*;

public class Warehouse<T extends Identifiable<String>> {

    private final List<T> products = new ArrayList<>();

    public void add(T item) {
        products.add(item);
    }

    public Optional<T> findByID(String id) {
        for (T item : products) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public List<T> findAll() {
        return List.copyOf(products);
    }

    public void remove(String id) {
        products.removeIf(item -> item.getId().equals(id));
    }

    public int count() {
        return products.size();
    }

    static <T extends Comparable<T>> List<T> sortedCopy(List<T> list) {
        List<T> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;

    }

    static Money sumPrices(List<? extends Product> products) {
        Money sum = Money.zero("Zł");
        for (Product item : products) {
            sum = sum.add(item.getTotalPrice());
        }
        return sum;
    }



}
