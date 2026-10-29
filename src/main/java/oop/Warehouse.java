package oop;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    List<Product> products = new ArrayList<>();


    public void addProduct(Product item) {
        products.add(item);
    }

    public Product findBySku(String otherSku) {
        for (Product item : products) {
            if (item.getSku().equals(otherSku)) {
                return item;
            }

        }
        return null;
    }



    public List<Product> getAll() {
        return List.copyOf(products);
    }

    public double getTotalValue() {
        double sum = 0;
        for (Product item : products) {
            sum += item.getGrossPrice();
        }
        return sum;
    }

    public int count() {
        return products.size();
    }


}
