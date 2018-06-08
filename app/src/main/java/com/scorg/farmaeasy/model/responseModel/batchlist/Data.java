package com.scorg.farmaeasy.model.responseModel.batchlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data implements Parcelable {

    @SerializedName("batchList")
    @Expose
    private ArrayList<BatchList> batchList = null;
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
        in.readList(this.batchList, (BatchList.class.getClassLoader()));
    }

    public Data() {
    }

    public ArrayList<BatchList> getBatchList() {
        return batchList;
    }

    public void setBatchList(ArrayList<BatchList> batchList) {
        this.batchList = batchList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(batchList);
    }

    public int describeContents() {
        return 0;
    }

}