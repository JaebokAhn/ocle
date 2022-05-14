package com.yeol.ocle.comn.exception;

public class OcleException extends RuntimeException{
    private static final long serialVersionUID = 784456343117206010L;

    public OcleException() {

    }

    public OcleException(String message, Throwable cause) {
        super(message, cause);
    }

    public OcleException(String message) {
        super(message);
    }

    public OcleException(Throwable cause) {
        super(cause);
    }
}
