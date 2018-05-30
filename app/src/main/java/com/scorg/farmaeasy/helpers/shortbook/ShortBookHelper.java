package com.scorg.farmaeasy.helpers.shortbook;

import android.content.Context;

import com.android.volley.Request;
import com.scorg.farmaeasy.interfaces.ConnectionListener;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.requestModel.shortbook.ShortBookRequestModel;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookResponseModel;
import com.scorg.farmaeasy.network.ConnectRequest;
import com.scorg.farmaeasy.network.ConnectionFactory;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Config;
import com.scorg.farmaeasy.util.Constants;

/**
 * Created by sandeepBahalkar on 18/05/2018.
 */

public class ShortBookHelper implements ConnectionListener {
    private String TAG = this.getClass().getName();
    private Context mContext;
    private HelperResponse mHelperResponseManager;

    public ShortBookHelper(Context context, HelperResponse dayBookActivity) {
        this.mContext = context;
        this.mHelperResponseManager = dayBookActivity;
    }


    @Override
    public void onResponse(int responseResult, CustomResponse customResponse, String mOldDataTag) {

        //CommonMethods.Log(TAG, customResponse.toString());
        switch (responseResult) {
            case ConnectionListener.RESPONSE_OK:
                switch (mOldDataTag) {
                    case Constants.TASK_SHORTBOOK:
                        ShortBookResponseModel shortBookResponseModel = (ShortBookResponseModel) customResponse;
                        mHelperResponseManager.onSuccess(mOldDataTag, shortBookResponseModel);
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
    public void doShortBook(String fromDate, String toDate, String orderBy) {
        ConnectionFactory mConnectionFactory = new ConnectionFactory(mContext, this, null, true,Constants.TASK_SHORTBOOK, Request.Method.POST, false);
        mConnectionFactory.setHeaderParams();
        ShortBookRequestModel shortBookRequestModel = new ShortBookRequestModel();
        shortBookRequestModel.setFromDate(fromDate);
        shortBookRequestModel.setToDate(toDate);
        shortBookRequestModel.setOrderBy(orderBy);
        mConnectionFactory.setPostParams(shortBookRequestModel);
        mConnectionFactory.setUrl(Config.URL_GET_SHORTBOOK_DATA);
        mConnectionFactory.createConnection(Constants.TASK_SHORTBOOK);
    }
}
