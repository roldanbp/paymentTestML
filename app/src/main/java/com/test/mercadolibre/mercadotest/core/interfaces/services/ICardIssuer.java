package com.test.mercadolibre.mercadotest.core.interfaces.services;

import com.test.mercadolibre.mercadotest.core.models.CardIssuerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by macbookpro on 16/6/18.
 */

public interface ICardIssuer {

    public String apiToken = "444a9ef5-8a6b-429f-abdf-587639155d88";

    @GET("/v1/payment_methods/card_issuers?public_key=" + apiToken)
    Call<List<CardIssuerModel>> getCardIssuer(@Query("payment_method_id") String id);

}
