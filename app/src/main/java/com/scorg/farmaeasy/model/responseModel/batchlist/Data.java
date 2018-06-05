package com.scorg.farmaeasy.model.responseModel.batchlist;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

@SerializedName("batchList")
@Expose
private ArrayList<BatchList> batchList = new ArrayList<>();
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
in.readList(this.batchList, (com.scorg.farmaeasy.model.responseModel.batchlist.BatchList.class.getClassLoader()));
}

public Data() {
}

public ArrayList<BatchList> getBatchList() {
return batchList;
}

public void setBatchList(ArrayList<BatchList> batchList) {
this.batchList = batchList;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeList(batchList);
}

public int describeContents() {
return 0;
}

}