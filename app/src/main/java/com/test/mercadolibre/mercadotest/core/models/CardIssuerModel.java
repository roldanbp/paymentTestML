package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbookpro on 17/6/18.
 */

public class CardIssuerModel {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("secure_thumbnail")
    @Expose
    public String secureThumbnail;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("processing_mode")
    @Expose
    public String processingMode;
    @SerializedName("merchant_account_id")
    @Expose
    public String merchantAccountId;

}
