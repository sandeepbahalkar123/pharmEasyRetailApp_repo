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
private double dbAmount;
@SerializedName("crAmount")
@Expose
private double crAmount;
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
this.dbAmount = ((double) in.readValue((double.class.getClassLoader())));
this.crAmount = ((double) in.readValue((double.class.getClassLoader())));
}

public OpeningBal() {
}

public String getParticularName() {
return particularName;
}

public void setParticularName(String particularName) {
this.particularName = particularName;
}

public double getDbAmount() {
return dbAmount;
}

public void setDbAmount(double dbAmount) {
this.dbAmount = dbAmount;
}

public double getCrAmount() {
return crAmount;
}

public void setCrAmount(double crAmount) {
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