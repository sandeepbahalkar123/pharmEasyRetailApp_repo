package com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PatientData implements Parcelable {

    @SerializedName("patientAddressList")
    @Expose
    private ArrayList<String> addressList = new ArrayList<>();
    public final static Creator<PatientData> CREATOR = new Creator<PatientData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PatientData createFromParcel(Parcel in) {
            return new PatientData(in);
        }

        public PatientData[] newArray(int size) {
            return (new PatientData[size]);
        }

    };

    protected PatientData(Parcel in) {
        in.readList(this.addressList, (String.class.getClassLoader()));
    }

    public PatientData() {
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