package com.scorg.farmaeasy.helpers.login;

import android.content.Context;

import com.android.volley.Request;
import com.scorg.farmaeasy.interfaces.ConnectionListener;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.login.LoginResponseModel;
import com.scorg.farmaeasy.model.requestModel.login.LoginRequestModel;
import com.scorg.farmaeasy.network.ConnectRequest;
import com.scorg.farmaeasy.network.ConnectionFactory;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Config;
import com.scorg.farmaeasy.util.Constants;

/**
 * Created by sandeepBahalkar on 18/05/2018.
 */

public class LoginHelper implements ConnectionListener {
    private String TAG = this.getClass().getName();
    private Context mContext;
    private HelperResponse mHelperResponseManager;

    public LoginHelper(Context context, HelperResponse loginActivity) {
        this.mContext = context;
        this.mHelperResponseManager = loginActivity;
    }


    @Override
    public void onResponse(int responseResult, CustomResponse customResponse, String mOldDataTag) {

        //CommonMethods.Log(TAG, customResponse.toString());
        switch (responseResult) {
            case ConnectionListener.RESPONSE_OK:
                switch (mOldDataTag) {
                    case Constants.TASK_LOGIN:
                        LoginResponseModel loginResponseModel = (LoginResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, loginResponseModel);
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

    //Do login using userId and password
    public void doLogin(String userId, String password) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, true,Constants.TASK_LOGIN, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setUserId(userId);
        loginRequestModel.setPassword(password);
        mConnectionFactory.setPostParams(loginRequestModel);
        mConnectionFactory.setUrl(Config.LOGIN_URL);
        mConnectionFactory.createConnection(Constants.TASK_LOGIN);
    }
}
