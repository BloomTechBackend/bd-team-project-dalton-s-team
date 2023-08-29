package main.exceptions;

public class SpendingCategoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1254874485668412478L;

    public SpendingCategoryNotFoundException() { super(); }

    public SpendingCategoryNotFoundException(String message) { super(message); }

    public SpendingCategoryNotFoundException(Throwable cause) { super(cause); }

    public SpendingCategoryNotFoundException(String message, Throwable cause) { super(message, cause); }
}
