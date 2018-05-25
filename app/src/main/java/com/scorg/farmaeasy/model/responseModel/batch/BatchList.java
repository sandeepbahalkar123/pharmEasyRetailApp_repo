package com.scorg.farmaeasy.model.responseModel.batch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BatchList {

    @SerializedName("batchInfo")
    @Expose
    private String batchInfo;
    @SerializedName("stock")
    @Expose
    private int stock;
    @SerializedName("totalQTY")
    @Expose
    private int totalQTY;
    @SerializedName("expiryDateInfo")
    @Expose
    private String expiryDateInfo;
    @SerializedName("packType")
    @Expose
    private String packType;
    @SerializedName("rateInfo")
    @Expose
    private float rateInfo;

    public String getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(String batchInfo) {
        this.batchInfo = batchInfo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public float getRateInfo() {
        return rateInfo;
    }

    public void setRateInfo(float rateInfo) {
        this.rateInfo = rateInfo;
    }

}