package com.scorg.farmaeasy.model.requestModel.sale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Billing {

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("netAmt")
    @Expose
    private String netAmt;
    @SerializedName("trasactionMode")
    @Expose
    private String trasactionMode;
    @SerializedName("billingType")
    @Expose
    private String billingType;

    @SerializedName("isHomeDelivery")
    @Expose
    private boolean isHomeDelivery;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(String netAmt) {
        this.netAmt = netAmt;
    }

    public String getTrasactionMode() {
        return trasactionMode;
    }

    public void setTrasactionMode(String trasactionMode) {
        this.trasactionMode = trasactionMode;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public boolean isHomeDelivery() {
        return isHomeDelivery;
    }

    public void setHomeDelivery(boolean homeDelivery) {
        isHomeDelivery = homeDelivery;
    }
}