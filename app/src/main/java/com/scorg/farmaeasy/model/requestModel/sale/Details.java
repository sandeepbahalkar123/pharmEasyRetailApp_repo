package com.scorg.farmaeasy.model.requestModel.sale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("patientId")
    @Expose
    private String patientId;
    @SerializedName("patientName")
    @Expose
    private String patientName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("patientGSTNo")
    @Expose
    private String patientGSTNo;
    @SerializedName("doctorName")
    @Expose
    private String doctorName;
    @SerializedName("doctorId")
    @Expose
    private String doctorId;
    @SerializedName("clinicAddress")
    @Expose
    private String clinicAddress;
    @SerializedName("doctorMobileNo")
    @Expose
    private String doctorMobileNo;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPatientGSTNo() {
        return patientGSTNo;
    }

    public void setPatientGSTNo(String patientGSTNo) {
        this.patientGSTNo = patientGSTNo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getDoctorMobileNo() {
        return doctorMobileNo;
    }

    public void setDoctorMobileNo(String doctorMobileNo) {
        this.doctorMobileNo = doctorMobileNo;
    }

}