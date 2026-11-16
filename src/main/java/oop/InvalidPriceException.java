package oop;

import lombok.Getter;

@Getter
public class InvalidPriceException extends ShopException {
    private final Money price;

    InvalidPriceException(Money price) {
        super("Invalid price", "INVALID_PRICE");
        this.price = price;
    }
}
