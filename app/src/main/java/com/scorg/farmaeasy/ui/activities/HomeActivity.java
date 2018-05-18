package com.scorg.farmaeasy.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.bottom_menus.BottomMenuActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BottomMenuActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

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
}
