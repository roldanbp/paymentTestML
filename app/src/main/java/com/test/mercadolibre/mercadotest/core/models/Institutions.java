package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbookpro on 17/6/18.
 */

public class Institutions {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("description")
    @Expose
    public String description;
}
