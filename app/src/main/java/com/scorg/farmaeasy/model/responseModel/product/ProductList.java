package com.scorg.farmaeasy.model.responseModel.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductList {

    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("totalQTY")
    @Expose
    private int totalQTY;
    @SerializedName("expiryDateInfo")
    @Expose
    private String expiryDateInfo;
    @SerializedName("rateInfo")
    @Expose
    private float rateInfo;
    @SerializedName("batchInfo")
    @Expose
    private String batchInfo;
    @SerializedName("content")
    @Expose
    private String content;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTotalQTY() {
        return totalQTY;
    }

    public void setTotalQTY(int totalQTY) {
        this.totalQTY = totalQTY;
    }

    public String getExpiryDateInfo() {
        return expiryDateInfo;
    }

    public void setExpiryDateInfo(String expiryDateInfo) {
        this.expiryDateInfo = expiryDateInfo;
    }

    public float getRateInfo() {
        return rateInfo;
    }

    public void setRateInfo(float rateInfo) {
        this.rateInfo = rateInfo;
    }

    public String getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(String batchInfo) {
        this.batchInfo = batchInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}