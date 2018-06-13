package com.scorg.farmaeasy.util;

/**
 * Created by Sandeep Bahalkar
 */
public class Config {
    public static final String HTTP = "http://";
    public static final String SERVICE_NAME = "/pheasyservice.svc/";
    public static final String URL_LOGIN = "login";
    public static final String URL_GET_DASHBOARD_DATA = "GetDashBoardData";
    public static final String URL_GET_DAYBOOK_DATA = "GetDayBook";
    public static final String URL_GET_SHORTBOOK_DATA = "GetShortBook";
    public static final String URL_CHECK_INTRANET_CONNECTION = "CheckConnection";
    public static final String URL_GET_PATIENT_DATA = "GetPatientList";
    public static final String URL_GET_PATIENT_ADDRESS = "GetPatientAddressList";
    public static final String URL_GET_DOCTOR_DATA = "GetDoctorList";
    public static final String URL_GET_DOCTOR_ADDRESS = "GetDoctorAddressList";
    public static final String URL_PRODUCT_SEARCH = "GetProductList";
    public static final String URL_GET_BATCHLIST = "GetBatchList";
    public static final String URL_SALE_POST = "SaveData";
    public static final String URL_PRODUCT_SEARCH_USING_BARCODE = "GetProductDetailByBarcode";


//    public static String BASE_URL = "http://172.16.100.221"+SERVICE_NAME;  //QA server
    public static String BASE_URL = "http://125.99.46.208" + SERVICE_NAME;  //LIVE server

}


