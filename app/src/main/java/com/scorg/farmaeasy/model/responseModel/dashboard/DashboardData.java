package com.scorg.farmaeasy.model.responseModel.dashboard;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardData implements Parcelable
{

    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopAddress")
    @Expose
    private String shopAddress;
    @SerializedName("expiredProduct")
    @Expose
    private Integer expiredProduct;
    @SerializedName("nearExpiry")
    @Expose
    private Integer nearExpiry;
    @SerializedName("todayCheque")
    @Expose
    private Integer todayCheque;
    @SerializedName("depositCheque")
    @Expose
    private Integer depositCheque;
    @SerializedName("pendingPurchase")
    @Expose
    private Integer pendingPurchase;
    @SerializedName("pendingOrder")
    @Expose
    private Integer pendingOrder;
    @SerializedName("recordDate")
    @Expose
    private String recordDate;
    public final static Parcelable.Creator<DashboardData> CREATOR = new Creator<DashboardData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DashboardData createFromParcel(Parcel in) {
            return new DashboardData(in);
        }

        public DashboardData[] newArray(int size) {
            return (new DashboardData[size]);
        }

    }
            ;

    protected DashboardData(Parcel in) {
        this.shopId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.shopName = ((String) in.readValue((String.class.getClassLoader())));
        this.shopAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.expiredProduct = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nearExpiry = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.todayCheque = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.depositCheque = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pendingPurchase = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pendingOrder = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.recordDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DashboardData() {
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
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

    public Integer getExpiredProduct() {
        return expiredProduct;
    }

    public void setExpiredProduct(Integer expiredProduct) {
        this.expiredProduct = expiredProduct;
    }

    public Integer getNearExpiry() {
        return nearExpiry;
    }

    public void setNearExpiry(Integer nearExpiry) {
        this.nearExpiry = nearExpiry;
    }

    public Integer getTodayCheque() {
        return todayCheque;
    }

    public void setTodayCheque(Integer todayCheque) {
        this.todayCheque = todayCheque;
    }

    public Integer getDepositCheque() {
        return depositCheque;
    }

    public void setDepositCheque(Integer depositCheque) {
        this.depositCheque = depositCheque;
    }

    public Integer getPendingPurchase() {
        return pendingPurchase;
    }

    public void setPendingPurchase(Integer pendingPurchase) {
        this.pendingPurchase = pendingPurchase;
    }

    public Integer getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(Integer pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(shopId);
        dest.writeValue(shopName);
        dest.writeValue(shopAddress);
        dest.writeValue(expiredProduct);
        dest.writeValue(nearExpiry);
        dest.writeValue(todayCheque);
        dest.writeValue(depositCheque);
        dest.writeValue(pendingPurchase);
        dest.writeValue(pendingOrder);
        dest.writeValue(recordDate);
    }

    public int describeContents() {
        return 0;
    }

}