
package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Setting {

    @SerializedName("card_number")
    @Expose
    public CardNumber cardNumber;
    @SerializedName("bin")
    @Expose
    public Bin bin;
    @SerializedName("security_code")
    @Expose
    public SecurityCode securityCode;

}
