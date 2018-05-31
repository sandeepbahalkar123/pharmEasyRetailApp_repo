package com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DoctorData implements Parcelable {

    @SerializedName("doctorAddressList")
    @Expose
    private ArrayList<String> addressList = new ArrayList<>();
    public final static Parcelable.Creator<DoctorData> CREATOR = new Creator<DoctorData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DoctorData createFromParcel(Parcel in) {
            return new DoctorData(in);
        }

        public DoctorData[] newArray(int size) {
            return (new DoctorData[size]);
        }

    };

    protected DoctorData(Parcel in) {
        in.readList(this.addressList, (java.lang.String.class.getClassLoader()));
    }

    public DoctorData() {
    }

    public ArrayList<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(ArrayList<String> addressList) {
        this.addressList = addressList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(addressList);
    }

    public int describeContents() {
        return 0;
    }

}