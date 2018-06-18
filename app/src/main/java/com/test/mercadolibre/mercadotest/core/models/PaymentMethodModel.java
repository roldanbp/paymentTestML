
package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentMethodModel {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("payment_type_id")
    @Expose
    public String paymentTypeId;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("secure_thumbnail")
    @Expose
    public String secureThumbnail;

    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

    @SerializedName("deferred_capture")
    @Expose
    public String deferredCapture;

    @SerializedName("settings")
    @Expose
    public List<Setting> settings = null;


    @SerializedName("additional_info_needed")
    @Expose
    public List<String> additionalInfoNeeded = null;

    @SerializedName("min_allowed_amount")
    @Expose
    public String minAllowedAmount;

    @SerializedName("max_allowed_amount")
    @Expose
    public String maxAllowedAmount;

    @SerializedName("accreditation_time")
    @Expose
    public String accreditationTime;

    @SerializedName("financial_institutions")
    @Expose
    public List<Institutions> financialInstitutions = null;

    @SerializedName("processing_modes")
    @Expose
    public List<String> processingModes = null;

}
