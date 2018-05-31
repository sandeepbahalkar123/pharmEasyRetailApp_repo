package com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class DoctorAddressResponseModel implements CustomResponse {

    @SerializedName("common")
    @Expose
    private Common common;
    @SerializedName("data")
    @Expose
    private DoctorData data;
    public final static Parcelable.Creator<DoctorAddressResponseModel> CREATOR = new Creator<DoctorAddressResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DoctorAddressResponseModel createFromParcel(Parcel in) {
            return new DoctorAddressResponseModel(in);
        }

        public DoctorAddressResponseModel[] newArray(int size) {
            return (new DoctorAddressResponseModel[size]);
        }

    };

    protected DoctorAddressResponseModel(Parcel in) {
        this.common = ((Common) in.readValue((Common.class.getClassLoader())));
        this.data = ((DoctorData) in.readValue((DoctorData.class.getClassLoader())));
    }

    public DoctorAddressResponseModel() {
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public DoctorData getData() {
        return data;
    }

    public void setData(DoctorData data) {
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