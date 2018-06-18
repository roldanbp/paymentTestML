
package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardNumber {

    @SerializedName("validation")
    @Expose
    public String validation;

    @SerializedName("length")
    @Expose
    public String length;

}
