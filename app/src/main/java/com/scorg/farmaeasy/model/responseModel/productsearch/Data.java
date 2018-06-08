package com.scorg.farmaeasy.model.responseModel.productsearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data implements Parcelable {

    @SerializedName("maxDiscPercetage")
    @Expose
    private String maxDiscPercetage;
    @SerializedName("productList")
    @Expose
    private ArrayList<ProductList> productList = new ArrayList<>();
    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    };

    protected Data(Parcel in) {
        this.maxDiscPercetage = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productList, (com.scorg.farmaeasy.model.responseModel.productsearch.ProductList.class.getClassLoader()));
    }

    public Data() {
    }

    public String getMaxDiscPercetage() {
        return maxDiscPercetage;
    }

    public void setMaxDiscPercetage(String maxDiscPercetage) {
        this.maxDiscPercetage = maxDiscPercetage;
    }


    public ArrayList<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductList> productList) {
        this.productList = productList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(maxDiscPercetage);
        dest.writeList(productList);
    }

    public int describeContents() {
        return 0;
    }

}