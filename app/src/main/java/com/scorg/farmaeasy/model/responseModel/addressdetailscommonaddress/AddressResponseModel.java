package com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class AddressResponseModel implements CustomResponse
{

@SerializedName("common")
@Expose
private Common common;
@SerializedName("data")
@Expose
private Data data;
public final static Parcelable.Creator<AddressResponseModel> CREATOR = new Creator<AddressResponseModel>() {


@SuppressWarnings({
"unchecked"
})
public AddressResponseModel createFromParcel(Parcel in) {
return new AddressResponseModel(in);
}

public AddressResponseModel[] newArray(int size) {
return (new AddressResponseModel[size]);
}

}
;

protected AddressResponseModel(Parcel in) {
this.common = ((Common) in.readValue((Common.class.getClassLoader())));
this.data = ((Data) in.readValue((Data.class.getClassLoader())));
}

public AddressResponseModel() {
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