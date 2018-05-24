package com.scorg.farmaeasy.model.responseModel.dashboard;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("dashboardData")
    @Expose
    private DashboardData dashboardData;
    @SerializedName("shopList")
    @Expose
    private List<ShopList> shopList = null;

    public DashboardData getDashboardData() {
        return dashboardData;
    }

    public void setDashboardData(DashboardData dashboardData) {
        this.dashboardData = dashboardData;
    }

    public List<ShopList> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopList> shopList) {
        this.shopList = shopList;
    }

}