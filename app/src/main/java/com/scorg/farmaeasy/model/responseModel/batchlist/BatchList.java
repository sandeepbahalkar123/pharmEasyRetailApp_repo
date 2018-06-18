package com.scorg.farmaeasy.model.responseModel.batchlist;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BatchList implements Parcelable {

    @SerializedName("stockID")
    @Expose
    private String stockID;
    @SerializedName("batchNumber")
    @Expose
    private String batchNumber;
    @SerializedName("closingStock")
    @Expose
    private int closingStock;
    @SerializedName("saleRate")
    @Expose
    private double saleRate;
    @SerializedName("expiry")
    @Expose
    private String expiry;
    @SerializedName("prodCompShortName")
    @Expose
    private String prodCompShortName;
    @SerializedName("prodpack")
    @Expose
    private String prodpack;
    @SerializedName("saleQuantity")
    @Expose
    private int saleQTY;
    @SerializedName("mrp")
    @Expose
    private double mrp;

    public final static Parcelable.Creator<BatchList> CREATOR = new Creator<BatchList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BatchList createFromParcel(Parcel in) {
            return new BatchList(in);
        }

        public BatchList[] newArray(int size) {
            return (new BatchList[size]);
        }

    };

    protected BatchList(Parcel in) {
        this.stockID = ((String) in.readValue((String.class.getClassLoader())));
        this.batchNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.closingStock = ((int) in.readValue((int.class.getClassLoader())));
        this.saleRate = ((double) in.readValue((double.class.getClassLoader())));
        this.expiry = ((String) in.readValue((String.class.getClassLoader())));
        this.prodCompShortName = ((String) in.readValue((String.class.getClassLoader())));
        this.prodpack = ((String) in.readValue((String.class.getClassLoader())));
        this.saleQTY = ((int) in.readValue((int.class.getClassLoader())));
        this.mrp = ((double) in.readValue((double.class.getClassLoader())));

    }

    public BatchList() {
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getClosingStock() {
        return closingStock;
    }

    public void setClosingStock(int closingStock) {
        this.closingStock = closingStock;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getProdCompShortName() {
        return prodCompShortName;
    }

    public void setProdCompShortName(String prodCompShortName) {
        this.prodCompShortName = prodCompShortName;
    }

    public String getProdpack() {
        return prodpack;
    }

    public void setProdpack(String prodpack) {
        this.prodpack = prodpack;
    }

    public int getSaleQTY() {
        return saleQTY;
    }

    public void setSaleQTY(int saleQTY) {
        this.saleQTY = saleQTY;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(stockID);
        dest.writeValue(batchNumber);
        dest.writeValue(closingStock);
        dest.writeValue(saleRate);
        dest.writeValue(expiry);
        dest.writeValue(prodCompShortName);
        dest.writeValue(prodpack);
        dest.writeValue(saleQTY);
        dest.writeValue(mrp);
    }

    public int describeContents() {
        return 0;
    }

}