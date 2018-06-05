package com.scorg.farmaeasy.helpers.batchlist;

import android.content.Context;

import com.android.volley.Request;
import com.scorg.farmaeasy.interfaces.ConnectionListener;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.requestModel.addressdetailssearchtext.AddressDetailsRequestModel;
import com.scorg.farmaeasy.model.requestModel.batchlist.BatchListRequestModel;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchListResponseModel;
import com.scorg.farmaeasy.network.ConnectRequest;
import com.scorg.farmaeasy.network.ConnectionFactory;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Config;
import com.scorg.farmaeasy.util.Constants;

/**
 * Created by sandeepBahalkar on 18/05/2018.
 */

public class BatchListHelper implements ConnectionListener {
    private String TAG = this.getClass().getName();
    private Context mContext;
    private HelperResponse mHelperResponseManager;

    public BatchListHelper(Context context, HelperResponse helperResponse) {
        this.mContext = context;
        this.mHelperResponseManager = helperResponse;
    }


    @Override
    public void onResponse(int responseResult, CustomResponse customResponse, String mOldDataTag) {

        //CommonMethods.Log(TAG, customResponse.toString());
        switch (responseResult) {
            case ConnectionListener.RESPONSE_OK:
                switch (mOldDataTag) {
                    case Constants.TASK_BATCHLIST:
                        BatchListResponseModel batchListResponseModel = (BatchListResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, batchListResponseModel);
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

    public void doBatchList(String productId) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, true,Constants.TASK_BATCHLIST, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        BatchListRequestModel batchListRequestModel = new BatchListRequestModel();
        batchListRequestModel.setProductID(productId);
        mConnectionFactory.setPostParams(batchListRequestModel);
        mConnectionFactory.setIntranetUrl(PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH,mContext),Config.URL_GET_BATCHLIST);
        mConnectionFactory.createConnection(Constants.TASK_BATCHLIST);
    }


}