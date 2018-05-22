package com.scorg.farmaeasy.network;

/**
 * @author Sandeep Bahalkar
 */

import android.content.Context;
import android.view.View;

import com.scorg.farmaeasy.interfaces.ConnectionListener;
import com.scorg.farmaeasy.interfaces.Connector;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.singleton.Device;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Config;
import com.scorg.farmaeasy.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class ConnectionFactory extends ConnectRequest {

    private final String TAG = this.getClass().getName();
    private Connector connector = null;
    private Device device;

    public ConnectionFactory(Context context, ConnectionListener connectionListener, View viewById, boolean isProgressBarShown, String mOldDataTag, int reqPostOrGet, boolean isOffline) {
        super();
        this.mConnectionListener = connectionListener;
        this.mContext = context;
        this.mViewById = viewById;
        this.isProgressBarShown = isProgressBarShown;
        this.mOldDataTag = mOldDataTag;
        this.reqPostOrGet = reqPostOrGet;
        this.isOffline = isOffline;

        device = Device.getInstance(mContext);
    }

    public void setHeaderParams(Map<String, String> headerParams) {
        this.mHeaderParams = headerParams;
    }

    public void setHeaderParams() {

        Map<String, String> headerParams = new HashMap<>();
        String authorizationString = PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.AUTHTOKEN, mContext);
        headerParams.put(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
        headerParams.put(Constants.AUTHORIZATION_TOKEN, authorizationString);
//        headerParams.put(Constants.DEVICEID, device.getDeviceId());
//        headerParams.put(Constants.OS, device.getOS());
//        headerParams.put(Constants.OSVERSION, device.getOSVersion());
//        headerParams.put(Constants.DEVICE_TYPE, device.getDeviceType());
        CommonMethods.Log(TAG, "setHeaderParams:" + headerParams.toString());
        this.mHeaderParams = headerParams;
    }


    //THis is done for now, as DMS API IS NOT AVAILABLE RIGHT NOW
    public void setDMSHeaderParams() {

        Map<String, String> headerParams = new HashMap<>();

        String authorizationString = "";
        String contentType = PreferencesManager.getString(Constants.LOGIN_SUCCESS, mContext);

        if (contentType.equalsIgnoreCase(Constants.TRUE)) {
            authorizationString = PreferencesManager.getString(Constants.TOKEN_TYPE, mContext)
                    + " " + PreferencesManager.getString(Constants.ACCESS_TOKEN, mContext);
            headerParams.put(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
        } else {
            headerParams.put(Constants.CONTENT_TYPE, Constants.APPLICATION_URL_ENCODED);
        }

        headerParams.put(Constants.AUTHORIZATION, authorizationString);
        headerParams.put(Constants.DEVICEID, device.getDeviceId());

        headerParams.put(Constants.OS, device.getOS());
        headerParams.put(Constants.DMS_OSVERSION, device.getOSVersion());
        //  headerParams.put(Constants.DEVICETYPE, device.getDeviceType());
//        headerParams.put(Constants.ACCESS_TOKEN, "");
        CommonMethods.Log(TAG, "setHeaderParams:" + headerParams.toString());
        this.mHeaderParams = headerParams;
    }


    public void setPostParams(CustomResponse customResponse) {
        this.customResponse = customResponse;
    }

    public void setPostParams(Map<String, String> postParams) {
        this.mPostParams = postParams;
    }

    public void setUrl(String url) {
        this.mURL = Config.BASE_URL + url;
        CommonMethods.Log(TAG, "mURL: " + this.mURL);
    }

    public void setDMSUrl(String url) {
        String baseUrl = PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mContext);

        this.mURL = baseUrl + url;
        CommonMethods.Log(TAG, "mURL: " + this.mURL);
    }

    public Connector createConnection(String type) {

        connector = new RequestManager(mContext, mConnectionListener, type, mViewById, isProgressBarShown, mOldDataTag, reqPostOrGet, isOffline);

        if (customResponse != null) connector.setPostParams(customResponse);

        if (mPostParams != null) connector.setPostParams(mPostParams);

        if (mHeaderParams != null) connector.setHeaderParams(mHeaderParams);

        if (mURL != null) connector.setUrl(mURL);

        connector.connect();

        return connector;
    }

    public void cancel() {
        connector.abort();
    }
}