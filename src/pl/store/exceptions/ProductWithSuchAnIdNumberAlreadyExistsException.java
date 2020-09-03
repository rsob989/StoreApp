package pl.store.exceptions;

public class ProductWithSuchAnIdNumberAlreadyExistsException extends RuntimeException {
    public ProductWithSuchAnIdNumberAlreadyExistsException(String message) {
        super(message);
    }
}
