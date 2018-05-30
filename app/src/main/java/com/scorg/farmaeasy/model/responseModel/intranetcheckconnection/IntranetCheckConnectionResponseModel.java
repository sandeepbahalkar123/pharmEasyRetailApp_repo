package com.scorg.farmaeasy.model.responseModel.intranetcheckconnection;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class IntranetCheckConnectionResponseModel implements CustomResponse
{

@SerializedName("common")
@Expose
private Common common;
public final static Parcelable.Creator<IntranetCheckConnectionResponseModel> CREATOR = new Creator<IntranetCheckConnectionResponseModel>() {


@SuppressWarnings({
"unchecked"
})
public IntranetCheckConnectionResponseModel createFromParcel(Parcel in) {
return new IntranetCheckConnectionResponseModel(in);
}

public IntranetCheckConnectionResponseModel[] newArray(int size) {
return (new IntranetCheckConnectionResponseModel[size]);
}

}
;

protected IntranetCheckConnectionResponseModel(Parcel in) {
this.common = ((Common) in.readValue((Common.class.getClassLoader())));
}

public IntranetCheckConnectionResponseModel() {
}

public Common getCommon() {
return common;
}

public void setCommon(Common common) {
this.common = common;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(common);
}

public int describeContents() {
return 0;
}

}