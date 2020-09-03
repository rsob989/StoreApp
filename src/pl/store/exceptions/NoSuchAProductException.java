package pl.store.exceptions;

public class NoSuchAProductException extends RuntimeException {
    public NoSuchAProductException(String message) {
        super(message);
    }
}
