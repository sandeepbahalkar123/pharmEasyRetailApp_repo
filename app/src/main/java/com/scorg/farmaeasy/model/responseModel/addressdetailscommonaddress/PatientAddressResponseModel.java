package com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress;

import android.os.Parcel;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class PatientAddressResponseModel implements CustomResponse {

    @SerializedName("common")
    @Expose
    private Common common;
    @SerializedName("data")
    @Expose
    private PatientData data;
    public final static Creator<PatientAddressResponseModel> CREATOR = new Creator<PatientAddressResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PatientAddressResponseModel createFromParcel(Parcel in) {
            return new PatientAddressResponseModel(in);
        }

        public PatientAddressResponseModel[] newArray(int size) {
            return (new PatientAddressResponseModel[size]);
        }

    };

    protected PatientAddressResponseModel(Parcel in) {
        this.common = ((Common) in.readValue((Common.class.getClassLoader())));
        this.data = ((PatientData) in.readValue((PatientData.class.getClassLoader())));
    }

    public PatientAddressResponseModel() {
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public PatientData getData() {
        return data;
    }

    public void setData(PatientData data) {
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