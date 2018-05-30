package com.scorg.farmaeasy.ui.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.bottom_menus.BottomMenu;
import com.scorg.farmaeasy.bottom_menus.BottomMenuActivity;
import com.scorg.farmaeasy.helpers.IntranetCheckConnection.IntranetCheckConnectionHelper;
import com.scorg.farmaeasy.helpers.dashboard.DashboardHelper;
import com.scorg.farmaeasy.helpers.login.LoginHelper;
import com.scorg.farmaeasy.interfaces.CheckIpConnection;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.dashboard.DashboardData;
import com.scorg.farmaeasy.model.responseModel.dashboard.DashboardResponseModel;
import com.scorg.farmaeasy.model.responseModel.dashboard.ShopList;
import com.scorg.farmaeasy.model.responseModel.intranetcheckconnection.IntranetCheckConnectionResponseModel;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.scorg.farmaeasy.util.Constants.SUCCESS;

public class HomeActivity extends BottomMenuActivity implements HelperResponse, PopupMenu.OnMenuItemClickListener {

    private static final String TAG = "Home";

    private static final int LOGOUT = 1;
    private static final int USER = 2;

    @BindView(R.id.menusButton)
    ImageButton menusButton;

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

    @BindView(R.id.visitingPatientsText)
    TextView visitingPatientsText;
    @BindView(R.id.todaysBirthdayText)
    TextView todaysBirthdayText;

    private Context mContext;
    private DashboardHelper dashboardHelper;
    private List<ShopList> shopList;
    public String mServerPath;
    public Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;
        dashboardHelper = new DashboardHelper(mContext, this);
        dashboardHelper.doGetDashboardData(PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, mContext));
    }

    @SuppressLint("RestrictedApi")
    @OnClick({R.id.menusButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menusButton:
                PopupMenu popup = new PopupMenu(this, menusButton);
                popup.setOnMenuItemClickListener(this);// to implement on click event on items of menu

                popup.getMenu().add(1, USER, 0, PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.USERNAME, mContext)).setIcon(R.drawable.user_icon);
                popup.getMenu().add(1, LOGOUT, 1, "Logout").setIcon(R.drawable.logout_icon);
                if (popup.getMenu() instanceof MenuBuilder) {
                    MenuBuilder m = (MenuBuilder) popup.getMenu();
                    m.setOptionalIconsVisible(true);
                }

                popup.show();
                break;
        }
    }

    @Override
    public void onBottomMenuClick(BottomMenu bottomMenu) {
        if (bottomMenu.getMenuName().equalsIgnoreCase("Home")) {
            Intent intent = new Intent(mContext, BarcodeScannerActivity.class);
            startActivity(intent);
        } else if (bottomMenu.getMenuName().equalsIgnoreCase("Book")) {
            Intent intentObj = new Intent(mContext, BooksActivity.class);
            startActivity(intentObj);
        } else if (bottomMenu.getMenuName().equalsIgnoreCase("Sale")) {
            if (PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mContext).equals("")) {
                CommonMethods.showIPAlertDialog(mContext, mContext.getString(R.string.enteripaddress), new CheckIpConnection() {

                    @Override
                    public void onOkButtonClickListner(String serverPath, Context context, Dialog dialog) {
                        mDialog=dialog;
                        mServerPath=serverPath;
                        IntranetCheckConnectionHelper intranetCheckConnectionHelper = new IntranetCheckConnectionHelper(mContext, HomeActivity.this);
                        intranetCheckConnectionHelper.doIntranetCheckConnectionHelper(mServerPath);
                    }
                });
            } else {
                Intent intentObj = new Intent(mContext, ProductsActivity.class);
                startActivity(intentObj);
            }
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
            if (receivedModel.getCommon().getStatusCode().equals(SUCCESS)) {

                DashboardData dashboardData = receivedModel.getData().getDashboardData();
                shopDetailsText.setText(dashboardData.getShopAddress());

                expiredProductText.setText(String.valueOf(dashboardData.getExpiredProduct()));
                nearExpiryText.setText(String.valueOf(dashboardData.getNearExpiry()));
                todaysChequeText.setText(String.valueOf(dashboardData.getTodayCheque()));
                depositChequeText.setText(String.valueOf(dashboardData.getDepositCheque()));
                pendingOnlinePurchaseText.setText(String.valueOf(dashboardData.getPendingPurchase()));
                pendingOrdersText.setText(String.valueOf(dashboardData.getPendingOrder()));

                visitingPatientsText.setText(String.valueOf(dashboardData.getVisitingPatients()));
                todaysBirthdayText.setText(String.valueOf(dashboardData.getTodaysBirthday()));

                if (shopList == null) {
                    shopList = receivedModel.getData().getShopList();

                    if (shopList != null) {
                        // Last two menu need to confirm
                        if (!shopList.isEmpty()) {
                            //Creating the ArrayAdapter instance having the country list
                            ArrayAdapter<ShopList> aa = new ArrayAdapter<ShopList>(this, R.layout.simple_spinner_item, shopList);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            shopSelection.setAdapter(aa);

                            int selectedPosition = 0;
                            for (ShopList shopL : shopList) {
                                if (shopL.getShopId() == PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, mContext)) {
                                    selectedPosition = shopList.indexOf(shopL);
                                }
                            }

                            shopSelection.setSelection(selectedPosition, false);

                            shopSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    dashboardHelper.doGetDashboardData(shopList.get(position).getShopId());
                                    shopDetailsText.setText(dashboardData.getShopAddress());
                                    PreferencesManager.putInt(PreferencesManager.PREFERENCES_KEY.SHOPID, shopList.get(position).getShopId(), mContext);
                                    PreferencesManager.putString(PreferencesManager.PREFERENCES_KEY.SHOPNAME, shopList.get(position).getShopName(), mContext);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }
                    }
                }

            } else {
                CommonMethods.showToast(mContext, receivedModel.getCommon().getStatusMessage());
            }
        }else if(mOldDataTag.equalsIgnoreCase(Constants.TASK_INTRANET_CHECKCONNECTION)){
            IntranetCheckConnectionResponseModel receivedModel = (IntranetCheckConnectionResponseModel) customResponse;
            if (receivedModel.getCommon().getStatusCode().equals(SUCCESS)) {
                PreferencesManager.putString(PreferencesManager.PREFERENCES_KEY.SERVER_PATH, mServerPath, mContext);
                CommonMethods.Log("mServerPath=>",mServerPath);
                Intent intentObj = new Intent(mContext, ProductsActivity.class);
                startActivity(intentObj);
                mDialog.dismiss();
            }else{
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == LOGOUT) {
            PreferencesManager.clearSharedPref(mContext);
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        } else if (item.getItemId() == USER) {

        }
        return false;
    }
}
