package com.niit.BookStore.exception;

public class AddressNotSpecifiedAppException extends BaseAppException {
    private static String DEFAULT_EXCEPTION_MESSAGE = "Address doesn't specified exception. Please add address in your account.";
        public AddressNotSpecifiedAppException(String message) {
            super(message);
        }

    public AddressNotSpecifiedAppException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}

