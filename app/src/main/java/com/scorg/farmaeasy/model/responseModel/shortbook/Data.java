package com.scorg.farmaeasy.model.responseModel.shortbook;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

@SerializedName("shortBookList")
@Expose
private ArrayList<ShortBookList> shortBookList = new ArrayList<>();
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
in.readList(this.shortBookList, (com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookList.class.getClassLoader()));
}

public Data() {
}

public ArrayList<ShortBookList> getShortBookList() {
return shortBookList;
}

public void setShortBookList(ArrayList<ShortBookList> shortBookList) {
this.shortBookList = shortBookList;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeList(shortBookList);
}

public int describeContents() {
return 0;
}

}