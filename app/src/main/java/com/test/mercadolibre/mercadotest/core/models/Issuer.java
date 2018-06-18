package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Issuer {

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
}
