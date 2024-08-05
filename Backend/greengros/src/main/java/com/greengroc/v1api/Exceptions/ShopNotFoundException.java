package com.greengroc.v1api.Exceptions;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(String message) {
        super(message);
    }
}
