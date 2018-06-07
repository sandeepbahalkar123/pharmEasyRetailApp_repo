package com.scorg.farmaeasy.model.requestModel.sale;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Billing implements Parcelable {

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
    @SerializedName("isHomeDelivery")
    @Expose
    private Boolean isHomeDelivery;
    @SerializedName("billingType")
    @Expose
    private String billingType;
    public final static Parcelable.Creator<Billing> CREATOR = new Creator<Billing>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Billing createFromParcel(Parcel in) {
            return new Billing(in);
        }

        public Billing[] newArray(int size) {
            return (new Billing[size]);
        }

    };

    protected Billing(Parcel in) {
        this.total = ((String) in.readValue((String.class.getClassLoader())));
        this.discount = ((String) in.readValue((String.class.getClassLoader())));
        this.netAmt = ((String) in.readValue((String.class.getClassLoader())));
        this.trasactionMode = ((String) in.readValue((String.class.getClassLoader())));
        this.isHomeDelivery = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.billingType = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Billing() {
    }

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

    public Boolean getIsHomeDelivery() {
        return isHomeDelivery;
    }

    public void setIsHomeDelivery(Boolean isHomeDelivery) {
        this.isHomeDelivery = isHomeDelivery;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(total);
        dest.writeValue(discount);
        dest.writeValue(netAmt);
        dest.writeValue(trasactionMode);
        dest.writeValue(isHomeDelivery);
        dest.writeValue(billingType);
    }

    public int describeContents() {
        return 0;
    }

}