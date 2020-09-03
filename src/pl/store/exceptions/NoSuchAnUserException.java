package pl.store.exceptions;

public class NoSuchAnUserException extends RuntimeException {
    public NoSuchAnUserException(String message) {
        super(message);
    }
}
