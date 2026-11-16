package oop;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends ShopException {
    private final String sku;

    ProductNotFoundException(String sku) {
        super("Product not found", "PRODUCT_NOT_FOUND");
        this.sku = sku;
    }


}
