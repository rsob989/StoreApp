package pl.store.exceptions;

public class MaximumNumberOfLoginTrialException extends RuntimeException {
    public MaximumNumberOfLoginTrialException(String message) {
        super(message);
    }
}
