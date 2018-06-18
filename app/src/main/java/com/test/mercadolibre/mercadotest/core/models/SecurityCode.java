
package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecurityCode {

    @SerializedName("length")
    @Expose
    public Integer length;

    @SerializedName("card_location")
    @Expose
    public String cardLocation;

    @SerializedName("mode")
    @Expose
    public String mode;

}
