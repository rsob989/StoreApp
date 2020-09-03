package pl.store.exceptions;

public class UserWithSuchAnUsernameAlreadyExistsException extends RuntimeException {
    public UserWithSuchAnUsernameAlreadyExistsException(String message) {
        super(message);
    }
}
