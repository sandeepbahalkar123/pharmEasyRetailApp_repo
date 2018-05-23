package com.scorg.farmaeasy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.bottom_menus.BottomMenu;
import com.scorg.farmaeasy.bottom_menus.BottomMenuActivity;
import com.scorg.farmaeasy.helpers.dashboard.DashboardHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.dashboard.DashboardData;
import com.scorg.farmaeasy.model.responseModel.dashboard.DashboardResponseModel;
import com.scorg.farmaeasy.model.responseModel.login.LoginResponseModel;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BottomMenuActivity implements HelperResponse {

    private static final String TAG = "Home";

    @BindView(R.id.bannerLayout)
    RelativeLayout bannerLayout;
    @BindView(R.id.expiredProduct)
    LinearLayout expiredProduct;
    @BindView(R.id.nearExpiry)
    LinearLayout nearExpiry;
    @BindView(R.id.todaysCheque)
    LinearLayout todaysCheque;
    @BindView(R.id.depositCheque)
    LinearLayout depositCheque;
    @BindView(R.id.pendingOnlinePurchase)
    LinearLayout pendingOnlinePurchase;
    @BindView(R.id.pendingOrders)
    LinearLayout pendingOrders;
    @BindView(R.id.expiredProduct2)
    LinearLayout expiredProduct2;
    @BindView(R.id.nearExpiry2)
    LinearLayout nearExpiry2;
    @BindView(R.id.menuLayout)
    LinearLayout menuLayout;

    @BindView(R.id.shopSelection)
    AppCompatSpinner shopSelection;
    @BindView(R.id.shopDetailsText)
    TextView shopDetailsText;
    @BindView(R.id.expiredProductText)
    TextView expiredProductText;
    @BindView(R.id.nearExpiryText)
    TextView nearExpiryText;
    @BindView(R.id.todaysChequeText)
    TextView todaysChequeText;
    @BindView(R.id.depositChequeText)
    TextView depositChequeText;
    @BindView(R.id.pendingOnlinePurchaseText)
    TextView pendingOnlinePurchaseText;
    @BindView(R.id.expiredProduct2Text)
    TextView expiredProduct2Text;
    @BindView(R.id.nearExpiry2Text)
    TextView nearExpiry2Text;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;
        DashboardHelper dashboardHelper = new DashboardHelper(mContext, this);
        dashboardHelper.doGetDashboardData(-1);
    }

    @OnClick({R.id.expiredProduct, R.id.nearExpiry, R.id.todaysCheque, R.id.depositCheque, R.id.pendingOnlinePurchase, R.id.pendingOrders, R.id.expiredProduct2, R.id.nearExpiry2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.expiredProduct:
                break;
            case R.id.nearExpiry:
                break;
            case R.id.todaysCheque:
                break;
            case R.id.depositCheque:
                break;
            case R.id.pendingOnlinePurchase:
                break;
            case R.id.pendingOrders:
                break;
            case R.id.expiredProduct2:
                break;
            case R.id.nearExpiry2:
                break;
        }
    }

    @Override
    public void onBottomMenuClick(BottomMenu bottomMenu) {
        if (bottomMenu.getMenuName().equalsIgnoreCase("Home")) {

        } else if (bottomMenu.getMenuName().equalsIgnoreCase("Book")) {
            Intent intentObj = new Intent(mContext, BooksActivity.class);
            startActivity(intentObj);
        } else if (bottomMenu.getMenuName().equalsIgnoreCase("Sale")) {
            Intent intentObj = new Intent(mContext, ProductsActivity.class);
            startActivity(intentObj);
        } else if (bottomMenu.getMenuName().equalsIgnoreCase("Support")) {
            Intent intentObj = new Intent(mContext, SupportActivity.class);
            startActivity(intentObj);
        }

        super.onBottomMenuClick(bottomMenu);
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_DASHBOARD)) {
            //After login user navigated to HomeActivity
            DashboardResponseModel receivedModel = (DashboardResponseModel) customResponse;
            if (receivedModel.getCommon().getSuccess()) {

                DashboardData dashboardData = receivedModel.getData().getDashboardData();
                List<String> spinnerArray = new ArrayList<String>();
                spinnerArray.add("Mediplus Drug Point");
                spinnerArray.add("Om Sai Drug Point");

                //Creating the ArrayAdapter instance having the country list
                ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, spinnerArray);
                aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                shopSelection.setAdapter(aa);

            } else {
                CommonMethods.showToast(mContext, receivedModel.getCommon().getStatusMessage());
            }
        }
    }

    @Override
    public void onParseError(String mOldDataTag, String errorMessage) {
        CommonMethods.showToast(mContext, errorMessage);
    }

    @Override
    public void onServerError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(mContext, serverErrorMessage);
    }

    @Override
    public void onNoConnectionError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(mContext, serverErrorMessage);
    }
}
