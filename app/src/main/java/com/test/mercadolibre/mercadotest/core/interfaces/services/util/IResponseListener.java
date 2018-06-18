package com.test.mercadolibre.mercadotest.core.interfaces.services.util;

public interface IResponseListener<T,K> {

    void initRequest();
    void successful(T object);
    void unsuccessful(K object);
    void error(Throwable t);
    void finishRequest();
}
