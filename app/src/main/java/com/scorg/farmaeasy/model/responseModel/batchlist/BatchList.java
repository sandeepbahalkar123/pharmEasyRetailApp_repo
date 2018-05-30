package com.scorg.farmaeasy.model.responseModel.batchlist;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BatchList implements Parcelable
{

@SerializedName("stockID")
@Expose
private String stockID;
@SerializedName("batchNumber")
@Expose
private String batchNumber;
@SerializedName("closingStock")
@Expose
private Integer closingStock;
@SerializedName("saleRate")
@Expose
private Integer saleRate;
@SerializedName("expiry")
@Expose
private String expiry;
@SerializedName("prodpack")
@Expose
private String prodpack;
public final static Parcelable.Creator<BatchList> CREATOR = new Creator<BatchList>() {


@SuppressWarnings({
"unchecked"
})
public BatchList createFromParcel(Parcel in) {
return new BatchList(in);
}

public BatchList[] newArray(int size) {
return (new BatchList[size]);
}

}
;

protected BatchList(Parcel in) {
this.stockID = ((String) in.readValue((String.class.getClassLoader())));
this.batchNumber = ((String) in.readValue((String.class.getClassLoader())));
this.closingStock = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.saleRate = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.expiry = ((String) in.readValue((String.class.getClassLoader())));
this.prodpack = ((String) in.readValue((String.class.getClassLoader())));
}

public BatchList() {
}

public String getStockID() {
return stockID;
}

public void setStockID(String stockID) {
this.stockID = stockID;
}

public String getBatchNumber() {
return batchNumber;
}

public void setBatchNumber(String batchNumber) {
this.batchNumber = batchNumber;
}

public Integer getClosingStock() {
return closingStock;
}

public void setClosingStock(Integer closingStock) {
this.closingStock = closingStock;
}

public Integer getSaleRate() {
return saleRate;
}

public void setSaleRate(Integer saleRate) {
this.saleRate = saleRate;
}

public String getExpiry() {
return expiry;
}

public void setExpiry(String expiry) {
this.expiry = expiry;
}

public String getProdpack() {
return prodpack;
}

public void setProdpack(String prodpack) {
this.prodpack = prodpack;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(stockID);
dest.writeValue(batchNumber);
dest.writeValue(closingStock);
dest.writeValue(saleRate);
dest.writeValue(expiry);
dest.writeValue(prodpack);
}

public int describeContents() {
return 0;
}

}