package com.test.mercadolibre.mercadotest.abstraction.enums;

/**
 * Created by Technifiser2 on 06/11/2017.
 */

public enum StatusError {

    ERROR_401(401),
    ERROR_403(403),
    ERROR_404(404);

    private int value;

    StatusError(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    }
