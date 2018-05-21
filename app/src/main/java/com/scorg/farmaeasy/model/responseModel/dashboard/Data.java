package com.scorg.farmaeasy.model.responseModel.dashboard;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("dashboardData")
    @Expose
    private DashboardData dashboardData;
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
        this.dashboardData = ((DashboardData) in.readValue((DashboardData.class.getClassLoader())));
    }

    public Data() {
    }

    public DashboardData getDashboardData() {
        return dashboardData;
    }

    public void setDashboardData(DashboardData dashboardData) {
        this.dashboardData = dashboardData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dashboardData);
    }

    public int describeContents() {
        return 0;
    }

}