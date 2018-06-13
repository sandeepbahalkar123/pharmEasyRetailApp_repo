package com.scorg.farmaeasy.model.responseModel.productsearchusingbarcode;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.Common;

public class ProductSearchUsingBarcodeResponseModel implements CustomResponse
{

@SerializedName("common")
@Expose
private Common common;
@SerializedName("data")
@Expose
private Data data;
public final static Parcelable.Creator<ProductSearchUsingBarcodeResponseModel> CREATOR = new Creator<ProductSearchUsingBarcodeResponseModel>() {


@SuppressWarnings({
"unchecked"
})
public ProductSearchUsingBarcodeResponseModel createFromParcel(Parcel in) {
return new ProductSearchUsingBarcodeResponseModel(in);
}

public ProductSearchUsingBarcodeResponseModel[] newArray(int size) {
return (new ProductSearchUsingBarcodeResponseModel[size]);
}

}
;

protected ProductSearchUsingBarcodeResponseModel(Parcel in) {
this.common = ((Common) in.readValue((Common.class.getClassLoader())));
this.data = ((Data) in.readValue((Data.class.getClassLoader())));
}

public ProductSearchUsingBarcodeResponseModel() {
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