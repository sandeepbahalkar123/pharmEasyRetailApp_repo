package com.scorg.farmaeasy.model.responseModel.addressdetailspatientdata;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data implements Parcelable {

    @SerializedName("patientList")
    @Expose
    private List<PatientList> patientList = null;
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
        in.readList(this.patientList, (com.scorg.farmaeasy.model.responseModel.addressdetailspatientdata.PatientList.class.getClassLoader()));
    }

    public Data() {
    }

    public List<PatientList> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<PatientList> patientList) {
        this.patientList = patientList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(patientList);
    }

    public int describeContents() {
        return 0;
    }

}