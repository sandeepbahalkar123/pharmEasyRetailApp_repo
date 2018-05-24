package com.scorg.farmaeasy.model.responseModel.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopList {

    @SerializedName("shopId")
    @Expose
    private int shopId;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopAddress")
    @Expose
    private String shopAddress;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    @Override
    public String toString() {
        return shopName;
    }
}