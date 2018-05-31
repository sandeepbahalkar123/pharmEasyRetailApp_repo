package com.scorg.farmaeasy.helpers.addressdetails;

import android.content.Context;

import com.android.volley.Request;
import com.scorg.farmaeasy.interfaces.ConnectionListener;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.requestModel.addressdetailssearchtext.AddressDetailsRequestModel;
import com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress.DoctorAddressResponseModel;
import com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress.PatientAddressResponseModel;
import com.scorg.farmaeasy.model.responseModel.addressdetailsdoctordata.DoctorDataResponseModel;
import com.scorg.farmaeasy.model.responseModel.addressdetailspatientdata.PatientDataResponseModel;
import com.scorg.farmaeasy.network.ConnectRequest;
import com.scorg.farmaeasy.network.ConnectionFactory;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Config;
import com.scorg.farmaeasy.util.Constants;

/**
 * Created by sandeepBahalkar on 18/05/2018.
 */

public class AddressDetailsHelper implements ConnectionListener {
    private String TAG = this.getClass().getName();
    private Context mContext;
    private HelperResponse mHelperResponseManager;

    public AddressDetailsHelper(Context context, HelperResponse helperResponse) {
        this.mContext = context;
        this.mHelperResponseManager = helperResponse;
    }


    @Override
    public void onResponse(int responseResult, CustomResponse customResponse, String mOldDataTag) {

        //CommonMethods.Log(TAG, customResponse.toString());
        switch (responseResult) {
            case ConnectionListener.RESPONSE_OK:
                switch (mOldDataTag) {
                    case Constants.TASK_ADDRESSDETAILS_PATIENTDATA:
                        PatientDataResponseModel patientDataResponseModel = (PatientDataResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, patientDataResponseModel);
                        break;

                    case Constants.TASK_ADDRESSDETAILS_PATIENTADDRESS:
                        PatientAddressResponseModel patientaddressResponseModelDoctor = (PatientAddressResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, patientaddressResponseModelDoctor);
                        break;

                    case Constants.TASK_ADDRESSDETAILS_DOCTORDATA:
                        DoctorDataResponseModel doctorDataResponseModel = (DoctorDataResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, doctorDataResponseModel);
                        break;

                    case Constants.TASK_ADDRESSDETAILS_DOCTORADDRESS:
                        DoctorAddressResponseModel doctoraddressResponseModelDoctor = (DoctorAddressResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, doctoraddressResponseModelDoctor);
                        break;

                }
                break;
            case ConnectionListener.PARSE_ERR0R:
                CommonMethods.Log(TAG, "parse error");
                break;
            case ConnectionListener.SERVER_ERROR:
                CommonMethods.Log(TAG, "server error");
                mHelperResponseManager.onServerError(mOldDataTag, "server error");
                break;
            case ConnectionListener.NO_CONNECTION_ERROR:
                CommonMethods.Log(TAG, "no connection error");
                mHelperResponseManager.onNoConnectionError(mOldDataTag, "no connection error");
                break;
            default:
                CommonMethods.Log(TAG, "default error");
                break;
        }
    }

    @Override
    public void onTimeout(ConnectRequest request) {

    }


    public void doPatientData(String searchString) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, true, Constants.TASK_ADDRESSDETAILS_PATIENTDATA, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        AddressDetailsRequestModel addressDetailsRequestModel = new AddressDetailsRequestModel();
        addressDetailsRequestModel.setSearchString(searchString);
        mConnectionFactory.setPostParams(addressDetailsRequestModel);
        mConnectionFactory.setIntranetUrl(PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mContext), Config.URL_GET_PATIENT_DATA);
        mConnectionFactory.createConnection(Constants.TASK_ADDRESSDETAILS_PATIENTDATA);
    }

    public void doPatientAddress(String searchString) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, false, Constants.TASK_ADDRESSDETAILS_PATIENTADDRESS, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        AddressDetailsRequestModel addressDetailsRequestModel = new AddressDetailsRequestModel();
        addressDetailsRequestModel.setSearchString(searchString);
        mConnectionFactory.setPostParams(addressDetailsRequestModel);
        mConnectionFactory.setIntranetUrl(PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mContext), Config.URL_GET_PATIENT_ADDRESS);
        mConnectionFactory.createConnection(Constants.TASK_ADDRESSDETAILS_PATIENTADDRESS);
    }

    public void doDoctorData(String searchString) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, true, Constants.TASK_ADDRESSDETAILS_DOCTORDATA, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        AddressDetailsRequestModel addressDetailsRequestModel = new AddressDetailsRequestModel();
        addressDetailsRequestModel.setSearchString(searchString);
        mConnectionFactory.setPostParams(addressDetailsRequestModel);
        mConnectionFactory.setIntranetUrl(PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mContext), Config.URL_GET_DOCTOR_DATA);
        mConnectionFactory.createConnection(Constants.TASK_ADDRESSDETAILS_DOCTORDATA);
    }

    public void doDoctorAddress(String searchString) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, false, Constants.TASK_ADDRESSDETAILS_DOCTORADDRESS, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        AddressDetailsRequestModel addressDetailsRequestModel = new AddressDetailsRequestModel();
        addressDetailsRequestModel.setSearchString(searchString);
        mConnectionFactory.setPostParams(addressDetailsRequestModel);
        mConnectionFactory.setIntranetUrl(PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mContext), Config.URL_GET_DOCTOR_ADDRESS);
        mConnectionFactory.createConnection(Constants.TASK_ADDRESSDETAILS_DOCTORADDRESS);
    }

}
