package com.scorg.farmaeasy.model.responseModel.productsearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;

import java.util.ArrayList;

public class ProductList implements Parcelable
{

    @SerializedName("productID")
    @Expose
    private String productID;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("prodLoosePack")
    @Expose
    private int prodLoosePack;
    @SerializedName("prodPack")
    @Expose
    private String prodPack;
    @SerializedName("prodPackType")
    @Expose
    private String prodPackType;
    @SerializedName("totalQTY")
    @Expose
    private int totalQTY;
    @SerializedName("prodCompShortName")
    @Expose
    private String prodCompShortName;
    @SerializedName("shelfNo")
    @Expose
    private String shelfNo;
    @SerializedName("drugInfo")
    @Expose
    private String drugInfo;
    @SerializedName("rateInfo")
    @Expose
    private Double rateInfo;
    @SerializedName("batchInfo")
    @Expose
    private String batchInfo;
    @SerializedName("batchList")
    @Expose
    private ArrayList<BatchList> batchList=new ArrayList<>();

    @SerializedName("individualProductTotalBatchAmount")
    @Expose
    private Double individualProductTotalBatchAmount;

    @SerializedName("individualProductTotalBatchQty")
    @Expose
    private int individualProductTotalBatchQty;

    private boolean isLongPressed;

    public final static Parcelable.Creator<ProductList> CREATOR = new Creator<ProductList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductList createFromParcel(Parcel in) {
            return new ProductList(in);
        }

        public ProductList[] newArray(int size) {
            return (new ProductList[size]);
        }

    }
            ;

    protected ProductList(Parcel in) {
        this.productID = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.prodLoosePack = ((int) in.readValue((int.class.getClassLoader())));
        this.prodPack = ((String) in.readValue((String.class.getClassLoader())));
        this.prodPackType = ((String) in.readValue((String.class.getClassLoader())));
        this.totalQTY = ((int) in.readValue((int.class.getClassLoader())));
        this.prodCompShortName = ((String) in.readValue((String.class.getClassLoader())));
        this.shelfNo = ((String) in.readValue((String.class.getClassLoader())));
        this.drugInfo = ((String) in.readValue((String.class.getClassLoader())));
        this.rateInfo = ((Double) in.readValue((Double.class.getClassLoader())));
        this.batchInfo = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.batchList, (com.scorg.farmaeasy.model.responseModel.batchlist.BatchList.class.getClassLoader()));
        this.individualProductTotalBatchAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.individualProductTotalBatchQty = ((int) in.readValue((int.class.getClassLoader())));
    }

    public ProductList() {
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProdLoosePack() {
        return prodLoosePack;
    }

    public void setProdLoosePack(int prodLoosePack) {
        this.prodLoosePack = prodLoosePack;
    }

    public String getProdPack() {
        return prodPack;
    }

    public void setProdPack(String prodPack) {
        this.prodPack = prodPack;
    }

    public String getProdPackType() {
        return prodPackType;
    }

    public void setProdPackType(String prodPackType) {
        this.prodPackType = prodPackType;
    }

    public int getTotalQTY() {
        return totalQTY;
    }

    public void setTotalQTY(int totalQTY) {
        this.totalQTY = totalQTY;
    }

    public String getProdCompShortName() {
        return prodCompShortName;
    }

    public void setProdCompShortName(String prodCompShortName) {
        this.prodCompShortName = prodCompShortName;
    }

    public String getShelfNo() {
        return shelfNo;
    }

    public void setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo;
    }

    public String getDrugInfo() {
        return drugInfo;
    }

    public void setDrugInfo(String drugInfo) {
        this.drugInfo = drugInfo;
    }

    public Double getRateInfo() {
        return rateInfo;
    }

    public void setRateInfo(Double rateInfo) {
        this.rateInfo = rateInfo;
    }

    public String getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(String batchInfo) {
        this.batchInfo = batchInfo;
    }

    public ArrayList<BatchList> getBatchList() {
        return batchList;
    }

    public void setBatchList(ArrayList<BatchList> batchList) {
        this.batchList = batchList;
    }

    public Double getIndividualProductTotalBatchAmount() {
        return individualProductTotalBatchAmount;
    }

    public void setIndividualProductTotalBatchAmount(Double individualProductTotalBatchAmount) {
        this.individualProductTotalBatchAmount = individualProductTotalBatchAmount;
    }

    public int getIndividualProductTotalBatchQty() {
        return individualProductTotalBatchQty;
    }

    public void setIndividualProductTotalBatchQty(int individualProductTotalBatchQty) {
        this.individualProductTotalBatchQty = individualProductTotalBatchQty;
    }

    public boolean isLongPressed() {
        return isLongPressed;
    }

    public void setLongPressed(boolean longPressed) {
        isLongPressed = longPressed;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productID);
        dest.writeValue(productName);
        dest.writeValue(prodLoosePack);
        dest.writeValue(prodPack);
        dest.writeValue(prodPackType);
        dest.writeValue(totalQTY);
        dest.writeValue(prodCompShortName);
        dest.writeValue(shelfNo);
        dest.writeValue(drugInfo);
        dest.writeValue(rateInfo);
        dest.writeValue(batchInfo);
        dest.writeList(batchList);
        dest.writeValue(individualProductTotalBatchAmount);
        dest.writeValue(individualProductTotalBatchQty);
    }

    public int describeContents() {
        return 0;
    }

}