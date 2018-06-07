package com.scorg.farmaeasy.model.requestModel.sale;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient implements Parcelable {

    @SerializedName("patientID")
    @Expose
    private String patientID;
    @SerializedName("patientName")
    @Expose
    private String patientName;
    @SerializedName("patientAddress1")
    @Expose
    private String patientAddress1;
    @SerializedName("patientAddress2")
    @Expose
    private String patientAddress2;
    @SerializedName("mobileNumberForSMS")
    @Expose
    private String mobileNumberForSMS;
    @SerializedName("patientGSTNo")
    @Expose
    private String patientGSTNo;
    public final static Parcelable.Creator<Patient> CREATOR = new Creator<Patient>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        public Patient[] newArray(int size) {
            return (new Patient[size]);
        }

    };

    protected Patient(Parcel in) {
        this.patientID = ((String) in.readValue((String.class.getClassLoader())));
        this.patientName = ((String) in.readValue((String.class.getClassLoader())));
        this.patientAddress1 = ((String) in.readValue((String.class.getClassLoader())));
        this.patientAddress2 = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNumberForSMS = ((String) in.readValue((String.class.getClassLoader())));
        this.patientGSTNo = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Patient() {
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAddress1() {
        return patientAddress1;
    }

    public void setPatientAddress1(String patientAddress1) {
        this.patientAddress1 = patientAddress1;
    }

    public String getPatientAddress2() {
        return patientAddress2;
    }

    public void setPatientAddress2(String patientAddress2) {
        this.patientAddress2 = patientAddress2;
    }

    public String getMobileNumberForSMS() {
        return mobileNumberForSMS;
    }

    public void setMobileNumberForSMS(String mobileNumberForSMS) {
        this.mobileNumberForSMS = mobileNumberForSMS;
    }

    public String getPatientGSTNo() {
        return patientGSTNo;
    }

    public void setPatientGSTNo(String patientGSTNo) {
        this.patientGSTNo = patientGSTNo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(patientID);
        dest.writeValue(patientName);
        dest.writeValue(patientAddress1);
        dest.writeValue(patientAddress2);
        dest.writeValue(mobileNumberForSMS);
        dest.writeValue(patientGSTNo);
    }

    public int describeContents() {
        return 0;
    }

}