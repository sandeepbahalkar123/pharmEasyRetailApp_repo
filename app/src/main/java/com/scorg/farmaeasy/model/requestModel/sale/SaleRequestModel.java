package com.scorg.farmaeasy.model.requestModel.sale;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;

import java.util.List;

public class SaleRequestModel implements Parcelable, CustomResponse {

    @SerializedName("productList")
    @Expose
    private List<ProductList> productList = null;
    @SerializedName("patient")
    @Expose
    private Patient patient;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("billing")
    @Expose
    private Billing billing;
    public final static Parcelable.Creator<SaleRequestModel> CREATOR = new Creator<SaleRequestModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SaleRequestModel createFromParcel(Parcel in) {
            return new SaleRequestModel(in);
        }

        public SaleRequestModel[] newArray(int size) {
            return (new SaleRequestModel[size]);
        }

    };

    protected SaleRequestModel(Parcel in) {
        in.readList(this.productList, (ProductList.class.getClassLoader()));
        this.patient = ((Patient) in.readValue((Patient.class.getClassLoader())));
        this.doctor = ((Doctor) in.readValue((Doctor.class.getClassLoader())));
        this.billing = ((Billing) in.readValue((Billing.class.getClassLoader())));
    }

    public SaleRequestModel() {
    }

    public List<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductList> productList) {
        this.productList = productList;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(productList);
        dest.writeValue(patient);
        dest.writeValue(doctor);
        dest.writeValue(billing);
    }

    public int describeContents() {
        return 0;
    }

}