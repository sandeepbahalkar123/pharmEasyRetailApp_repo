package com.scorg.farmaeasy.model.requestModel.daybook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;

public class DayBookRequestModel implements CustomResponse {

    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}