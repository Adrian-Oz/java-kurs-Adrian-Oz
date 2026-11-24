package oop;

import lombok.Getter;

@Getter
public class OutOfStockException extends ShopException {

    private final String sku;
    private final int requested;
    private final int available;

    OutOfStockException(String sku, int requested, int available) {
        super("Not enough product in stock, requested: %d, available: %d".formatted(requested, available), "INSUFFICIENT_STOCK");
        this.sku = sku;
        this.requested = requested;
        this.available = available;
    }

}
