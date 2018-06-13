package com.scorg.farmaeasy.model.responseModel.productsearchusingbarcode;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;

public class Data implements Parcelable
{

    @SerializedName("barcodeProductList")
    @Expose
    private ArrayList<ProductList> productLists = new ArrayList<>();
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

    }
            ;

    protected Data(Parcel in) {
        in.readList(this.productLists, (com.scorg.farmaeasy.model.responseModel.productsearch.ProductList.class.getClassLoader()));
    }

    public Data() {
    }

    public ArrayList<ProductList> getProductLists() {
        return productLists;
    }

    public void setProductLists(ArrayList<ProductList> productLists) {
        this.productLists = productLists;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(productLists);
    }

    public int describeContents() {
        return 0;
    }

}