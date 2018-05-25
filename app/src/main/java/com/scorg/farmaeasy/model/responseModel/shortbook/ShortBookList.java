package com.scorg.farmaeasy.model.responseModel.shortbook;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShortBookList implements Parcelable
{

@SerializedName("shopId")
@Expose
private Integer shopId;
@SerializedName("productId")
@Expose
private String productId;
@SerializedName("prodName")
@Expose
private String prodName;
@SerializedName("prodLoosePack")
@Expose
private Integer prodLoosePack;
@SerializedName("prodPack")
@Expose
private String prodPack;
@SerializedName("prodpacktype")
@Expose
private String prodpacktype;
@SerializedName("prodCompShortName")
@Expose
private String prodCompShortName;
@SerializedName("accShortName")
@Expose
private String accShortName;
@SerializedName("availableStock")
@Expose
private Integer availableStock;
@SerializedName("orderQuantity")
@Expose
private Integer orderQuantity;
@SerializedName("schemeQuantity")
@Expose
private Integer schemeQuantity;
public final static Parcelable.Creator<ShortBookList> CREATOR = new Creator<ShortBookList>() {


@SuppressWarnings({
"unchecked"
})
public ShortBookList createFromParcel(Parcel in) {
return new ShortBookList(in);
}

public ShortBookList[] newArray(int size) {
return (new ShortBookList[size]);
}

}
;

protected ShortBookList(Parcel in) {
this.shopId = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.productId = ((String) in.readValue((String.class.getClassLoader())));
this.prodName = ((String) in.readValue((String.class.getClassLoader())));
this.prodLoosePack = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.prodPack = ((String) in.readValue((String.class.getClassLoader())));
this.prodpacktype = ((String) in.readValue((String.class.getClassLoader())));
this.prodCompShortName = ((String) in.readValue((String.class.getClassLoader())));
this.accShortName = ((String) in.readValue((String.class.getClassLoader())));
this.availableStock = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.orderQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.schemeQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
}

public ShortBookList() {
}

public Integer getShopId() {
return shopId;
}

public void setShopId(Integer shopId) {
this.shopId = shopId;
}

public String getProductId() {
return productId;
}

public void setProductId(String productId) {
this.productId = productId;
}

public String getProdName() {
return prodName;
}

public void setProdName(String prodName) {
this.prodName = prodName;
}

public Integer getProdLoosePack() {
return prodLoosePack;
}

public void setProdLoosePack(Integer prodLoosePack) {
this.prodLoosePack = prodLoosePack;
}

public String getProdPack() {
return prodPack;
}

public void setProdPack(String prodPack) {
this.prodPack = prodPack;
}

public String getProdpacktype() {
return prodpacktype;
}

public void setProdpacktype(String prodpacktype) {
this.prodpacktype = prodpacktype;
}

public String getProdCompShortName() {
return prodCompShortName;
}

public void setProdCompShortName(String prodCompShortName) {
this.prodCompShortName = prodCompShortName;
}

public String getAccShortName() {
return accShortName;
}

public void setAccShortName(String accShortName) {
this.accShortName = accShortName;
}

public Integer getAvailableStock() {
return availableStock;
}

public void setAvailableStock(Integer availableStock) {
this.availableStock = availableStock;
}

public Integer getOrderQuantity() {
return orderQuantity;
}

public void setOrderQuantity(Integer orderQuantity) {
this.orderQuantity = orderQuantity;
}

public Integer getSchemeQuantity() {
return schemeQuantity;
}

public void setSchemeQuantity(Integer schemeQuantity) {
this.schemeQuantity = schemeQuantity;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(shopId);
dest.writeValue(productId);
dest.writeValue(prodName);
dest.writeValue(prodLoosePack);
dest.writeValue(prodPack);
dest.writeValue(prodpacktype);
dest.writeValue(prodCompShortName);
dest.writeValue(accShortName);
dest.writeValue(availableStock);
dest.writeValue(orderQuantity);
dest.writeValue(schemeQuantity);
}

public int describeContents() {
return 0;
}

}