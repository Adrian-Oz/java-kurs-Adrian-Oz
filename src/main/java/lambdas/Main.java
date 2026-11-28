package lambdas;

import oop.Electronics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("ELEC-2", "PC2", new BigDecimal("50"), 10, "ELECTRONICS");
        Product p2 = new Product("ELEC-3", "PC3", new BigDecimal("2000"), 0, "ELECTRONICS");
        Product p3 = new Product("DIGI-1", "POE", new BigDecimal("0"), 1200, "GAME");
        Product p4 = new Product("ELEC-12312", "PS5", new BigDecimal("150"), 150, "ELECTRONICS");

        Predicate<Product> cheapAndAvailable = ProductFilters.CHEAP.and(ProductFilters.IN_STOCK);
        Predicate<Product> cheapOrExpensive = ProductFilters.CHEAP.or(ProductFilters.EXPENSIVE);
        Predicate<Product> unavailable = ProductFilters.IN_STOCK.negate();
        Predicate<Product> availableElectronics = ProductFilters.priceBetween(new BigDecimal(100), new BigDecimal(1000))
                .and(ProductFilters.IN_STOCK)
                .and(p -> p.category().equals("ELECTRONICS"));


//        System.out.println(cheapAndAvailable.test(p1));
//        System.out.println(cheapAndAvailable.test(p2));
//        System.out.println(cheapAndAvailable.test(p3));
//        System.out.println(cheapOrExpensive.test(p1));
//        System.out.println(cheapOrExpensive.test(p2));
//        System.out.println(cheapOrExpensive.test(p3));
//        System.out.println(unavailable.test(p3));
//        System.out.println(availableElectronics.test(p4));


        Function<Product, String> fullPriceLabel = PriceCalculator.BASE_PRICE
                .andThen(PriceCalculator.WITH_SHIPPING)
                .andThen(PriceCalculator.WITH_VAT)
                .andThen(PriceCalculator.FORMAT);
        Function<Product, String> fullPriceLabel2 = PriceCalculator.BASE_PRICE
                .andThen(PriceCalculator.WITH_VAT)
                .andThen(PriceCalculator.discount(new BigDecimal("0.9")))
                .andThen(PriceCalculator.WITH_SHIPPING)
                .andThen(PriceCalculator.FORMAT);

//        System.out.println(fullPriceLabel.apply(p1));
//        System.out.println(fullPriceLabel2.apply(p1));


        OrderProcessor processor = new OrderProcessor();

        DiscountPolicy premium = o -> {
            BigDecimal total = processor.calculateTotal(o);
            if (o.customer().premium()) {
                return total.multiply(new BigDecimal("0.10"));
            }
            return BigDecimal.ZERO;
        };

        DiscountPolicy above500 = o -> {
            BigDecimal total = processor.calculateTotal(o);
            if (total.compareTo(new BigDecimal("500")) > 0) {
                return total.multiply(new BigDecimal("0.05"));
            }
            return BigDecimal.ZERO;
        };


        DiscountPolicy flat = o -> {
            BigDecimal total = processor.calculateTotal(o);
            if (o.items().size() > 5) {
                return new BigDecimal("20");
            }
            return BigDecimal.ZERO;
        };

        DiscountPolicy discountLine = premium.andThen(above500).andThen(flat);
        Customer c1 = new Customer("1", "Adrian", "a@aa.pl", true, 10);

        Order o1 = new Order("10", c1, List.of(p1,p1,p2,p2,p3,p4));
        System.out.println(processor.calculateTotal(o1));


        System.out.println(premium.apply(o1));
        System.out.println(above500.apply(o1));
        System.out.println(flat.apply(o1));
        System.out.println(discountLine.apply(o1));








    }


}
