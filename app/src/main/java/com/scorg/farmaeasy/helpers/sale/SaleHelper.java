package com.scorg.farmaeasy.helpers.sale;

import android.content.Context;

import com.android.volley.Request;
import com.scorg.farmaeasy.interfaces.ConnectionListener;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.requestModel.sale.SaleRequestModel;
import com.scorg.farmaeasy.model.responseModel.sale.SaleResponseModel;
import com.scorg.farmaeasy.network.ConnectRequest;
import com.scorg.farmaeasy.network.ConnectionFactory;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Config;
import com.scorg.farmaeasy.util.Constants;

/**
 * Created by sandeepBahalkar on 18/05/2018.
 */

public class SaleHelper implements ConnectionListener {
    private String TAG = this.getClass().getName();
    private Context mContext;
    private HelperResponse mHelperResponseManager;

    public SaleHelper(Context context, HelperResponse helperResponse) {
        this.mContext = context;
        this.mHelperResponseManager = helperResponse;
    }


    @Override
    public void onResponse(int responseResult, CustomResponse customResponse, String mOldDataTag) {

        //CommonMethods.Log(TAG, customResponse.toString());
        switch (responseResult) {
            case ConnectionListener.RESPONSE_OK:
                switch (mOldDataTag) {
                    case Constants.TASK_POST_SALE:
                        SaleResponseModel saleResponseModel = (SaleResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, saleResponseModel);
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

    public void doPostSaleData(SaleRequestModel saleRequestModel) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, true, Constants.TASK_POST_SALE, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        mConnectionFactory.setPostParams(saleRequestModel);
        mConnectionFactory.setIntranetUrl(PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mContext), Config.URL_SALE_POST);
        mConnectionFactory.createConnection(Constants.TASK_POST_SALE);
    }


}