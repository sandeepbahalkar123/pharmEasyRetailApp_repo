package com.scorg.farmaeasy.model.responseModel.productsearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class ProductSearchResponseModel implements CustomResponse {

    @SerializedName("common")
    @Expose
    private Common common;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<ProductSearchResponseModel> CREATOR = new Creator<ProductSearchResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductSearchResponseModel createFromParcel(Parcel in) {
            return new ProductSearchResponseModel(in);
        }

        public ProductSearchResponseModel[] newArray(int size) {
            return (new ProductSearchResponseModel[size]);
        }

    };

    protected ProductSearchResponseModel(Parcel in) {
        this.common = ((Common) in.readValue((Common.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public ProductSearchResponseModel() {
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(common);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

}