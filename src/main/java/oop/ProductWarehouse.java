package oop;

import java.util.*;

public class ProductWarehouse extends Warehouse<Product> {

    private final List<Product> products = new ArrayList<>();

    public void add(Product item) {
        products.add(item);
    }

    public Optional<Product> findByID(String id) {
        for (Product item : products) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return List.copyOf(products);
    }

    public void remove(String id) {
        products.removeIf(item -> item.getId().equals(id));
    }

    public int count() {
        return products.size();
    }

    static <Product extends Comparable<Product>> List<Product> sortedCopy(List<Product> list) {
        List<Product> copy = new ArrayList<>(list);
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

    public Optional<Product> findCheapest() {
        if (products.isEmpty()) {
            return Optional.empty();
        }
        Product cheapestProduct = products.get(0);
        for (Product item : products) {
            if (item.getNetPrice().amount().compareTo(cheapestProduct.getNetPrice().amount()) < 0) {
                cheapestProduct = item;
            }
        }
        return Optional.of(cheapestProduct);
    }

    public Optional<String> findSupplierName(String sku) {
        return findByID(sku).flatMap(Product::findSupplier).map(Supplier::name);
    }

    public Optional<String> findSupplierEmail(String sku) {
        return findByID(sku).flatMap(Product::findSupplier).flatMap(Supplier::findEmail);
    }

    public Optional<String> findSupplierCity(String sku) {
        return findByID(sku).flatMap(Product::findSupplier).flatMap(Supplier::findAddress).map(Address::city).map(String::toUpperCase);
    }

    public String getSupplierLabel1(String sku) {
        return findByID(sku)
                .flatMap(Product::findSupplier)
                .map((supplier -> {
                    String city = supplier.findAddress()
                            .map(Address::city)
                            .orElse(null);
                    if (city == null) {
                        return "Missing supplier data";
                    }

                    return String.format("Supplier: %s (%s)", supplier.name(), city);
                })).orElse("Missing supplier data");
    }

    //  5. Unclear instructions, stock ?
    public Optional<String> findEmailForContact(String sku) {
        return findByID(sku).flatMap(Product::findSupplier).flatMap(Supplier::findEmail).filter(e -> e.contains("@"));
    }

    public Optional<Address> findAddressOfCheapest() {
        return findCheapest().flatMap(Product::findSupplier).flatMap(Supplier::findAddress);
    }

    public String getShippingCity(String sku) {
        return findByID(sku).map(Product::getSupplier).flatMap(Supplier::findAddress).map(Address::city).map(s -> String.format("Shipping city: %s", s)).orElse("central warehouse");
    }

//    8. Unclear instructions, stock ?
//     Optional<Product> findBySkuAndMinStock(String sku, int minStock)

    public List<String> collectSupplierCities() {
        //Hashset will prevent repetitions
        Set<String> cities = new HashSet<>();

        for (Product item : findAll()) {
            item.findSupplier()
                    .flatMap(Supplier::findAddress)
                    .map(Address::city)
                    .filter(city -> !city.isBlank())
                    .ifPresent(cities::add);
        }
        return new ArrayList<>(cities);
    }

    public Product getOrThrow(String sku) {
        return findByID(sku).orElseThrow(()-> new IllegalArgumentException(String.format("Sku: %s",sku)));

    }
}
