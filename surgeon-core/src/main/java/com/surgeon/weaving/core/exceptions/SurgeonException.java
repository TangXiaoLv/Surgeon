package com.surgeon.weaving.core.exceptions;

public class SurgeonException extends Exception {

    public SurgeonException() {
        super();
    }

    public SurgeonException(String message) {
        super(message);
    }

    public SurgeonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SurgeonException(Throwable cause) {
        super(cause);
    }
}
