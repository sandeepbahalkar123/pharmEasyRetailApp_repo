package com.scorg.farmaeasy.model.responseModel.login;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeData implements Parcelable
{

    @SerializedName("empName")
    @Expose
    private String empName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("empId")
    @Expose
    private String empId;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    public final static Parcelable.Creator<EmployeeData> CREATOR = new Creator<EmployeeData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EmployeeData createFromParcel(Parcel in) {
            return new EmployeeData(in);
        }

        public EmployeeData[] newArray(int size) {
            return (new EmployeeData[size]);
        }

    }
            ;

    protected EmployeeData(Parcel in) {
        this.empName = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.empId = ((String) in.readValue((String.class.getClassLoader())));
        this.userType = ((String) in.readValue((String.class.getClassLoader())));
        this.shopName = ((String) in.readValue((String.class.getClassLoader())));
        this.shopId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public EmployeeData() {
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(empName);
        dest.writeValue(userName);
        dest.writeValue(empId);
        dest.writeValue(userType);
        dest.writeValue(shopName);
        dest.writeValue(shopId);
    }

    public int describeContents() {
        return 0;
    }

}