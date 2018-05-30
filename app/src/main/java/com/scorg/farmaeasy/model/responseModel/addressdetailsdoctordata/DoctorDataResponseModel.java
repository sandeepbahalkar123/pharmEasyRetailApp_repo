package com.scorg.farmaeasy.model.responseModel.addressdetailsdoctordata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class DoctorDataResponseModel implements CustomResponse
{

@SerializedName("common")
@Expose
private Common common;
@SerializedName("data")
@Expose
private Data data;
public final static Parcelable.Creator<DoctorDataResponseModel> CREATOR = new Creator<DoctorDataResponseModel>() {


@SuppressWarnings({
"unchecked"
})
public DoctorDataResponseModel createFromParcel(Parcel in) {
return new DoctorDataResponseModel(in);
}

public DoctorDataResponseModel[] newArray(int size) {
return (new DoctorDataResponseModel[size]);
}

}
;

protected DoctorDataResponseModel(Parcel in) {
this.common = ((Common) in.readValue((Common.class.getClassLoader())));
this.data = ((Data) in.readValue((Data.class.getClassLoader())));
}

public DoctorDataResponseModel() {
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