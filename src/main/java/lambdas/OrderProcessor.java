package lambdas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class OrderProcessor {
    // auditLog
    private final List<String> auditLog = new ArrayList<>();
    public Consumer<Order> LOG_TO_CONSOLE = o -> System.out.println(o);
    public Consumer<Order> ADD_TO_AUDIT = o -> auditLog.add(o.toString());
    public Consumer<Order> SEND_CONFIRMATION = o -> System.out.println("EMAIL");

    public Consumer<Order> buildPipeline(boolean withAudit, boolean withEmail) {
        Consumer<Order> pipeline = LOG_TO_CONSOLE;
        if (withAudit) {
            pipeline = pipeline.andThen(ADD_TO_AUDIT);
        }
        if (withEmail) {
            pipeline = pipeline.andThen(SEND_CONFIRMATION);
        }
        return pipeline;
    }

    //    Counter ?
    public static int COUNTER = 0;
    public Supplier<String> generateOrderId = () -> "ORD-" + COUNTER++;


    public Order createOrder(Customer c, List<Product> items, Supplier<String> idGenerator) {

        return new Order(idGenerator.get(), c, items);
    }

    private final Function<Product, BigDecimal> fullPrice = PriceCalculator.BASE_PRICE
            .andThen(PriceCalculator.WITH_VAT);


    public BigDecimal calculateTotal(Order o) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product item : o.items()) {
            total = total.add(fullPrice.apply(item));
        }
        total = total.add(new BigDecimal("15"));
        return total;
    }


}
