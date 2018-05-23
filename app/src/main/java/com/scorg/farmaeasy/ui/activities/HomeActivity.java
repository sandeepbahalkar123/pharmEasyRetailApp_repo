package com.scorg.farmaeasy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.bottom_menus.BottomMenu;
import com.scorg.farmaeasy.bottom_menus.BottomMenuActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BottomMenuActivity {

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
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Mediplus Drug Point");
        spinnerArray.add("Om Sai Drug Point");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, spinnerArray);
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        shopSelection.setAdapter(aa);
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
            Intent intentObj = new Intent(mContext, PagerActivity.class);
            startActivity(intentObj);
        } else if (bottomMenu.getMenuName().equalsIgnoreCase("Support")) {
            Intent intentObj = new Intent(mContext, SupportActivity.class);
            startActivity(intentObj);
        }

        super.onBottomMenuClick(bottomMenu);
    }
}
