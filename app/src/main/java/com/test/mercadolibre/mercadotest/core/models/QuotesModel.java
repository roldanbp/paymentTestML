package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by macbookpro on 17/6/18.
 */

public class QuotesModel {

    @SerializedName("payment_method_id")
    @Expose
    public String paymentMethodId;

    @SerializedName("payment_type_id")
    @Expose
    public String paymentTypeId;

    @SerializedName("issuer")
    @Expose
    public Issuer issuer;

    @SerializedName("processing_mode")
    @Expose
    public String processingMode;
    @SerializedName("merchant_account_id")
    @Expose
    public Object merchantAccountId;
    @SerializedName("payer_costs")
    @Expose
    public List<PayerCost> payerCosts = null;
}
