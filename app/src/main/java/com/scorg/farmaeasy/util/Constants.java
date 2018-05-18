package com.scorg.farmaeasy.util;


/**
 * @author Sandeep Bahalkar
 */
public class Constants {

    public static final String RESCRIBE_LOG_FOLDER = "PhramEasyRetail_LOG";
    public static final String RESCRIBE_LOG_FILE = "PhramEasyRetail_LOG_FILE.txt";

    public static final String MT_TABLET = "TABLET";
    public static final String MT_SYRUP = "SYRUP";
    public static final String MT_OINTMENT = "OINTMENT";
    public static final String YES = "yes";
    public static final String DOCUMENTS = "documents";
    public static final String ATTACHMENTS_LIST = "ATTACHMENTS_LIST";
    public static final String ALERT = "alert";
    public static final String ID = "_id";
    public static final String USER_ID = "User-ID";
    public static final String DEVICEID = "Device-Id";
    public static final String OS = "OS";
    public static final String OSVERSION = "OsVersion";
    public static final String DMS_OSVERSION = "OS-Version";
    public static final String DEVICE_TYPE = "DeviceType";
    public static final String APP_NAME = "appName";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String TOKEN_TYPE = "token_type";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String LDPI = "/LDPI/";
    public static final String MDPI = "/MDPI/";
    public static final String HDPI = "/HDPI/";
    public static final String XHDPI = "/XHDPI/";
    public static final String XXHDPI = "/XXHDPI/";
    public static final String XXXHDPI = "/XXXHDPI/";
    public static final String TABLET = "Tablet";
    public static final String PHONE = "Phone";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_TOKEN = "authtoken";
    public static final String AUTH_KEY = "Auth-Key";
    public static final String CLIENT_SERVICE = "Client-Service";
    public static final String GRANT_TYPE_KEY = "grant_type";

    public static final String APPLICATION_FORM_DATA = "multipart/form-data";
    public static final String APPLICATION_URL_ENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String APPLICATION_JSON = "application/json; charset=utf-8";
    //--- Request Params
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_ID_VALUE = "334a059d82304f4e9892ee5932f81425";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String APPOINTMENT_TIME = "appointment_time";
    public static final String NOTIFICATION_TIME = "notification_time";
    public static final String APPOINTMENT_DATE = "appointment_date";
    public static final String NOTIFICATION_DATE = "notification_date";
    public static final String TIME = "time";

    // Connection codes
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final String DATE = "date";
    public static final String MEDICINE_NAME = "medicine_name";
    public static final String MEDICINE_SLOT = "edicine_slot";
    public static final String MEDICINE_TYPE = "medicine_type";
    public static final String NOTIFICATION_ID = "notification_id";

    public static final String APPOINTMENT_NOTIFICATION_ID = "appointment_notification_id";
    public static final String APPOINTMENT_MESSAGE = "appointment_message";
    public static final String BLANK = "";
    //Click codes

    public static final String TASK_LOGIN = Constants.BLANK + 1;
    public static final String DATA = "DATA";
    public static final Integer SUCCESS = 200;
    public static final String TASK_CHECK_SERVER_CONNECTION = Constants.BLANK + 39;
    public static final String TITLE = "title";
    public static final int MAX_RETRIES = 3;

    public static final String STARTFOREGROUND_ACTION = "com.rescribe.download";
    public static final int FOREGROUND_SERVICE = 21;
    public static final String MY_PATIENTS_DATA = "my_patient_data";
    public static final String MY_APPOINTMENTS = "my_appointments";
    public static final String PATIENT_CONNECT = "patient_connect";
    public static final String IS_APPOINTMENT_TYPE_RESHEDULE = "appointment_type_reschedule";
    public static final String DOCTOR = "doctor";
    public static final String APPOINTMENT_ID = "appointment_id";
    public static String HEADER_COLOR = "#E4422C";
    public static String BUTTON_TEXT_COLOR = "#FFFFFF";
    public static String TEXT_COLOR = "#000000";

    public static final String[] SALUTATION = {"Mr. ", "Mrs. ", "Miss ", ""};
    public static final String[] GENDER = {"MALE", "FEMALE", "TRANSGENDER"};
    public static String OT_AND_OPD = "ot_and_opd";
    public static String OPD = "opd";
    public static String OT = "ot";

    public interface APPOINTMENT_STATUS {
        int BOOKED = 1;
        int CONFIRM = 2;
        int COMPLETED = 3;
        int CANCEL = 4;
        int NO_SHOW = 5;
        int OTHER = 6;
        int IN_CONSULTATION = 7;
        int IN_QUEUE = 8;
        int COMING = 9;
    }

    public interface USER_STATUS {
        String ONLINE = "Online";
        String OFFLINE = "Offline";
        String TYPING = "Typing";
        String IDLE = "Idle";
    }

    // change
    public interface MESSAGE_STATUS {
        String SEEN = "seen";
        String REACHED = "reached";
        String SENT = "sent";
        String PENDING = "pending";

        int READ = 1;
        int UNREAD = 0;
    }

    public interface FILE_STATUS {
        int UPLOADING = 1;
        int DOWNLOADING = 1;
        int FAILED = 0;
        int COMPLETED = 3;
    }

    public interface PRESCRIPTION_LIST_PARAMS {
        String PATIENT_NAME = "User-ID";
        String FILE_TYPE = "fileType";
        String DATE_TYPE = "dateType";
        String FROM_DATE = "fromDate";
        String TO_DATE = "toDate";
        String ANNOTATION_TEXT = "annotationText";
        String DOC_TYPE_ID = "DocTypeId";
    }


    public interface DATE_PATTERN {
        String YYYY_MM_DD_hh_mm_a = "yyyy-MM-dd hh:mm a";
        String DD_MM = "dd/MM";
        String DD_MMM = "dd MMM";
        String MM = "MM";
        String MMM = "MMM";
        String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String DD_MM_YYYY = "dd-MM-yyyy";
        String DD_MM_YYYY_SLASH = "dd/MM/yyyy";
        String DD_MMMM_YYYY = "dd MMMM yyyy"; // 12-September-2017
        String hh_mm_a = "hh:mm a";
        String TOTIMEZONE = "Asia/Kolkata";
        String EEEE_dd_MMM_yyyy_hh_mm_a = "EEEE dd MMM yyyy | hh:mm a";
        String HH_mm_ss = "HH:mm:ss";
        String DD_MM_YYYY_hh_mm = "dd/MM/yyyy hh:mm aa";
        String HH_MM = "hh:mm";
        String HH_mm = "HH:mm";
        String MMM_YYYY = "MMM, yyyy";
        String MMM_YY = "MMM, yy";
        String DD_MM_YYYY_hh_mm_ss = "dd-MM-yyyy hh:mm:ss";
        String YYYY_MM_DD_hh_mm_ss = "yyyy-MM-dd hh:mm:ss";
        String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
        String d_M_YYYY = "d-M-yyyy";
    }

    public interface TIME_STAMPS {
        int FIVE_FIFTY = 500;
        int ONE_SECONDS = 1000;
        int TWO_SECONDS = 2000;
        int THREE_SECONDS = 3000;
    }

    public interface FILE {
        String IMG = "img";
        String DOC = "doc";
        String VID = "vid";
        String AUD = "aud";
        String LOC = "loc";
    }

    public interface FILE_EMOJI {
        String IMG_FILE = "🏞 Image";
        String DOC_FILE = "📒 Document";
        String VID_FILE = "📹 Video";
        String AUD_FILE = "🔊 Audio";
        String LOC_FILE = "📍 Location";
    }


}


