package oop;

import lombok.Getter;

@Getter
public class ShopException extends RuntimeException {
    private final String errorCode;


    ShopException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    ShopException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ShopException(String message) {
        super(message);
        errorCode = null;
    }
}
