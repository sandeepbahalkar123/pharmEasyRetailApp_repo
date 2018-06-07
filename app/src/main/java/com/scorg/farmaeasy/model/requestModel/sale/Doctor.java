package com.scorg.farmaeasy.model.requestModel.sale;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor implements Parcelable {

    @SerializedName("doctorID")
    @Expose
    private String doctorID;
    @SerializedName("doctorName")
    @Expose
    private String doctorName;
    @SerializedName("doctorAddress")
    @Expose
    private String doctorAddress;
    @SerializedName("mobileNumberForSMS")
    @Expose
    private String mobileNumberForSMS;
    public final static Parcelable.Creator<Doctor> CREATOR = new Creator<Doctor>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        public Doctor[] newArray(int size) {
            return (new Doctor[size]);
        }

    };

    protected Doctor(Parcel in) {
        this.doctorID = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNumberForSMS = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Doctor() {
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getMobileNumberForSMS() {
        return mobileNumberForSMS;
    }

    public void setMobileNumberForSMS(String mobileNumberForSMS) {
        this.mobileNumberForSMS = mobileNumberForSMS;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(doctorID);
        dest.writeValue(doctorName);
        dest.writeValue(doctorAddress);
        dest.writeValue(mobileNumberForSMS);
    }

    public int describeContents() {
        return 0;
    }

}