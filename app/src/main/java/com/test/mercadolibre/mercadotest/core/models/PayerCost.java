package com.test.mercadolibre.mercadotest.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayerCost {

@SerializedName("installments")
@Expose
public Integer installments;
@SerializedName("installment_rate")
@Expose
public Float installmentRate;
@SerializedName("discount_rate")
@Expose
public Integer discountRate;
@SerializedName("labels")
@Expose
public List<String> labels = null;
@SerializedName("installment_rate_collector")
@Expose
public List<String> installmentRateCollector = null;
@SerializedName("min_allowed_amount")
@Expose
public Integer minAllowedAmount;
@SerializedName("max_allowed_amount")
@Expose
public Integer maxAllowedAmount;
@SerializedName("recommended_message")
@Expose
public String recommendedMessage;
@SerializedName("installment_amount")
@Expose
public Float installmentAmount;
@SerializedName("total_amount")
@Expose
public Float totalAmount;

}