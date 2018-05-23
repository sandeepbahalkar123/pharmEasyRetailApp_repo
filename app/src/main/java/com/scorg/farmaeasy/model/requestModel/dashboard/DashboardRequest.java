package com.scorg.farmaeasy.model.requestModel.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;

public class DashboardRequest implements CustomResponse {

    @SerializedName("shopId")
    @Expose
    private int shopId;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

}