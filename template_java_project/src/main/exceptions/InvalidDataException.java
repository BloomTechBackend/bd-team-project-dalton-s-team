package main.exceptions;

public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = -6817461009083612096L;

    /**
     * Exception with no message or cause.
     */
    public InvalidDataException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public InvalidDataException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
