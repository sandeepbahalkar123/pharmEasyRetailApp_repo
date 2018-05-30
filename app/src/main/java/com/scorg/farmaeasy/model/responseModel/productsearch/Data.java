package com.scorg.farmaeasy.model.responseModel.productsearch;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

@SerializedName("productList")
@Expose
private ArrayList<ProductList> productList = new ArrayList<>();
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
in.readList(this.productList, (com.scorg.farmaeasy.model.responseModel.productsearch.ProductList.class.getClassLoader()));
}

public Data() {
}

public ArrayList<ProductList> getProductList() {
return productList;
}

public void setProductList(ArrayList<ProductList> productList) {
this.productList = productList;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeList(productList);
}

public int describeContents() {
return 0;
}

}