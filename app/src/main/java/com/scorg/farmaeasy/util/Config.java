package com.scorg.farmaeasy.util;

import android.content.Context;

/**
 * Created by Sandeep Bahalkar
 */
public class Config {
    public static final String HTTP = "http://";
    public static final String API = "/api/";
    public static final String TOKEN_TYPE = "Bearer";
    public static final String LOGIN_URL = "PhEasyService.svc/login";
    public static final String GET_DASHBOARD_DATA = "PhEasyService.svc/GetDashBoardData";
    public static final String GET_DAYBOOK_DATA = "PhEasyService.svc/GetDayBook";
    public static final String GET_SHORTBOOK_DATA = "PhEasyService.svc/GetShortBook";


    //---------------QA SERVER Internet URL START-----------------------

    public static String BASE_URL = "http://172.16.100.221/";
    //---------------QA SERVER Internet URL END-----------------------

    //---------------LIVE Internet URL START-----------------------
//    public static String BASE_URL = "http://rescribe.in:3003/";
    //---------------LIVE Internet URL ENDS-----------------------

    public static final String MY_RECORDS_DOCTOR_LIST = "api/doctors/getDoctorsWithPatientVisits";
    public static final String MY_RECORDS_ADD_DOCTOR = "api/doctors/addDoctor";
    public static final String REQUEST_SEND_SMS = "doctor/api/appointment/sendSmsToPatients";

    public Context mContext;
    //  Declared all URL used in app here
    public static final String LOGIN_WITH_OTP_URL = "authApi/authenticate/otpLogin";
    public static final String GET_PATIENT_LIST = "api/patient/getChatPatientList?docId=";

    public static final String SEND_MSG_TO_PATIENT = "api/chat/sendMsgToPatient";
    public static final String CHAT_HISTORY = "api/chat/getChatHistory?";

    public static final String CHAT_FILE_UPLOAD = "api/upload/chatDoc";
    public static final String GET_PATIENT_CHAT_LIST = "api/chat/getChatTabUsers?user1id=";

    public static final String LOGOUT = "api/doctors/logDoctorSignOut";
    public static final String ACTIVE = "api/doctors/logDoctorActivity";
    public static final String MY_RECORDS_UPLOAD = "api/upload/addOpdAttachments";
    public static final String ADD_NEW_PATIENT_WEB_URL_SUCCESS = "addpatientmobilesuccess";

    public static final String GET_DOCTOR_PATIENT_CITY = "doctor/api/patient/getDoctorPatientCity";
    public static final String GET_PATIENTS_SYNC = "doctor/api/patient/getPatientSyncList";
    public static final String ADD_PATIENTS_SYNC = "doctor/api/patient/addPatients";


    //--DMS URLs-
    //Declared all URL used in app here

    public static final String URL_LOGIN = "userLogin";
    public static final String URL_PATIENT_LIST = "result/ShowSearchResults";
    public static final String URL_ANNOTATIONS_LIST = "documenttype/getAnnotations";
    public static final String URL_PATIENT_NAME_LIST = "Patient";

    public static final String URL_GET_ARCHIVED_LIST = "getArchived";
    public static final String URL_GET_PDF_DATA = "showfile";
    public static final String URL_CHECK_SERVER_CONNECTION = "connectionCheck";

}


