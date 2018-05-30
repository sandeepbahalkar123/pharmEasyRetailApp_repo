package com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

@SerializedName("addressList")
@Expose
private ArrayList<String> addressList = new ArrayList<>();
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

}
;

protected Data(Parcel in) {
in.readList(this.addressList, (java.lang.String.class.getClassLoader()));
}

public Data() {
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