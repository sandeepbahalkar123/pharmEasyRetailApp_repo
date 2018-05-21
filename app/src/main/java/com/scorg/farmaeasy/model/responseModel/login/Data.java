package com.scorg.farmaeasy.model.responseModel.login;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("employeeData")
    @Expose
    private EmployeeData employeeData;
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
        this.authToken = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeData = ((EmployeeData) in.readValue((EmployeeData.class.getClassLoader())));
    }

    public Data() {
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public EmployeeData getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(EmployeeData employeeData) {
        this.employeeData = employeeData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(authToken);
        dest.writeValue(employeeData);
    }

    public int describeContents() {
        return 0;
    }

}