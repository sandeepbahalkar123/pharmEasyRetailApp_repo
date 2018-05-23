package com.scorg.farmaeasy.model.responseModel.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class DashboardResponseModel implements Parcelable, CustomResponse {

    @SerializedName("common")
    @Expose
    private Common common;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<DashboardResponseModel> CREATOR = new Creator<DashboardResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DashboardResponseModel createFromParcel(Parcel in) {
            return new DashboardResponseModel(in);
        }

        public DashboardResponseModel[] newArray(int size) {
            return (new DashboardResponseModel[size]);
        }

    };

    protected DashboardResponseModel(Parcel in) {
        this.common = ((Common) in.readValue((Common.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public DashboardResponseModel() {
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