package oop;

import lombok.Getter;

@Getter
public class InvalidPriceException extends ShopException {
    private final String price;

    InvalidPriceException(String price) {
        super("Invalid price: "+ price, " INVALID_PRICE");
        this.price = price;
    }
}
