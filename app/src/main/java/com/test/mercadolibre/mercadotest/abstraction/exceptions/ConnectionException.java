package com.test.mercadolibre.mercadotest.abstraction.exceptions;

/**
 * Created by Technifiser2 on 07/11/2017.
 */

public class ConnectionException extends Exception {

    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }


}
