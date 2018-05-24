package com.scorg.farmaeasy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
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
import com.scorg.farmaeasy.model.responseModel.dashboard.ShopList;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

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

    @BindView(R.id.pendingOrdersText)
    TextView pendingOrdersText;

    private Context mContext;
    private DashboardHelper dashboardHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;
        dashboardHelper = new DashboardHelper(mContext, this);
        dashboardHelper.doGetDashboardData(PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, mContext));
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
                List<ShopList> shopList = receivedModel.getData().getShopList();

                expiredProductText.setText(String.valueOf(dashboardData.getExpiredProduct()));
                nearExpiryText.setText(String.valueOf(dashboardData.getNearExpiry()));
                todaysChequeText.setText(String.valueOf(dashboardData.getTodayCheque()));
                depositChequeText.setText(String.valueOf(dashboardData.getDepositCheque()));
                pendingOnlinePurchaseText.setText(String.valueOf(dashboardData.getPendingPurchase()));
                pendingOrdersText.setText(String.valueOf(dashboardData.getPendingOrder()));

                if (shopList != null) {
                    // Last two menu need to confirm
                    if (!shopList.isEmpty()) {

                        shopDetailsText.setText(shopList.get(0).getShopAddress());
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter<ShopList> aa = new ArrayAdapter<ShopList>(this, R.layout.simple_spinner_item, shopList);
                        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        shopSelection.setAdapter(aa);

                        shopSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                dashboardHelper.doGetDashboardData(shopList.get(position).getShopId());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }

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
