package com.niit.BookStore.exception;

public class CartIsEmptyAppException extends BaseAppException {
    private static String DEFAULT_EXCEPTION_MESSAGE = "Cart is empty";
    public CartIsEmptyAppException(String message) {
        super(message);
    }

    public CartIsEmptyAppException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
