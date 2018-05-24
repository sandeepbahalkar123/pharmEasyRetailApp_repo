package com.scorg.farmaeasy.model.responseModel.dashboard;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardData {

    @SerializedName("shopId")
    @Expose
    private int shopId;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopAddress")
    @Expose
    private String shopAddress;
    @SerializedName("expiredProduct")
    @Expose
    private int expiredProduct;
    @SerializedName("nearExpiry")
    @Expose
    private int nearExpiry;
    @SerializedName("todayCheque")
    @Expose
    private int todayCheque;
    @SerializedName("depositCheque")
    @Expose
    private int depositCheque;
    @SerializedName("pendingPurchase")
    @Expose
    private int pendingPurchase;
    @SerializedName("pendingOrder")
    @Expose
    private int pendingOrder;
    @SerializedName("recordDate")
    @Expose
    private String recordDate;

    @SerializedName("visitingPatients")
    @Expose
    private int visitingPatients;
    @SerializedName("todaysBirthday")
    @Expose
    private int todaysBirthday;



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

    public int getExpiredProduct() {
        return expiredProduct;
    }

    public void setExpiredProduct(int expiredProduct) {
        this.expiredProduct = expiredProduct;
    }

    public int getNearExpiry() {
        return nearExpiry;
    }

    public void setNearExpiry(int nearExpiry) {
        this.nearExpiry = nearExpiry;
    }

    public int getTodayCheque() {
        return todayCheque;
    }

    public void setTodayCheque(int todayCheque) {
        this.todayCheque = todayCheque;
    }

    public int getDepositCheque() {
        return depositCheque;
    }

    public void setDepositCheque(int depositCheque) {
        this.depositCheque = depositCheque;
    }

    public int getPendingPurchase() {
        return pendingPurchase;
    }

    public void setPendingPurchase(int pendingPurchase) {
        this.pendingPurchase = pendingPurchase;
    }

    public int getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(int pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public int getVisitingPatients() {
        return visitingPatients;
    }

    public void setVisitingPatients(int visitingPatients) {
        this.visitingPatients = visitingPatients;
    }

    public int getTodaysBirthday() {
        return todaysBirthday;
    }

    public void setTodaysBirthday(int todaysBirthday) {
        this.todaysBirthday = todaysBirthday;
    }
}