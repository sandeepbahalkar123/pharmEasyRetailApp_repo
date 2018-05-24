package com.scorg.farmaeasy.model.responseModel.daybook;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningBal implements Parcelable
{

@SerializedName("particularName")
@Expose
private String particularName;
@SerializedName("dbAmount")
@Expose
private Integer dbAmount;
@SerializedName("crAmount")
@Expose
private Integer crAmount;
public final static Parcelable.Creator<OpeningBal> CREATOR = new Creator<OpeningBal>() {


@SuppressWarnings({
"unchecked"
})
public OpeningBal createFromParcel(Parcel in) {
return new OpeningBal(in);
}

public OpeningBal[] newArray(int size) {
return (new OpeningBal[size]);
}

}
;

protected OpeningBal(Parcel in) {
this.particularName = ((String) in.readValue((String.class.getClassLoader())));
this.dbAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.crAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
}

public OpeningBal() {
}

public String getParticularName() {
return particularName;
}

public void setParticularName(String particularName) {
this.particularName = particularName;
}

public Integer getDbAmount() {
return dbAmount;
}

public void setDbAmount(Integer dbAmount) {
this.dbAmount = dbAmount;
}

public Integer getCrAmount() {
return crAmount;
}

public void setCrAmount(Integer crAmount) {
this.crAmount = crAmount;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(particularName);
dest.writeValue(dbAmount);
dest.writeValue(crAmount);
}

public int describeContents() {
return 0;
}

}