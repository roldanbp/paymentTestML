
package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bin {

    @SerializedName("pattern")
    @Expose
    public String pattern;
    @SerializedName("installments_pattern")
    @Expose
    public String installmentsPattern;
    @SerializedName("exclusion_pattern")
    @Expose
    public String exclusionPattern;

}
