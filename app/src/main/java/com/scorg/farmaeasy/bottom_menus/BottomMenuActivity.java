package com.scorg.farmaeasy.bottom_menus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.scorg.farmaeasy.R;

import java.util.ArrayList;


@SuppressWarnings("unused")
@SuppressLint("Registered")
public class BottomMenuActivity extends AppCompatActivity implements BottomMenuAdapter.OnBottomMenuClickListener {

    RecyclerView recyclerView;
    private BottomMenuAdapter bottomMenuAdapter;
    private int mPosition;
    private ColorGenerator mColorGenerator;
    private Context mContext;
    private String[] mMenuNames = {"Home", "Book", "AppLogo", "Sale", "Support"};
    private FrameLayout mFrame;
    private String activityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.bottom_menu_activity);
        mContext = this;
        init();
    }

    public void init() {
        mFrame = (FrameLayout) findViewById(R.id.activityView);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        createBottomMenu();
    }

    public void createBottomMenu() {
        ArrayList<BottomMenu> mBottomMenuList = new ArrayList<>();
        BottomMenu homeMenu = new BottomMenu();
        homeMenu.setAppIcon(false);
        homeMenu.setMenuIcon(ContextCompat.getDrawable(mContext, R.drawable.home_menu));
        homeMenu.setSelected(true);
        homeMenu.setMenuName("Home");

        mBottomMenuList.add(homeMenu);

        BottomMenu bookMenu = new BottomMenu();
        bookMenu.setAppIcon(false);
        bookMenu.setMenuIcon(ContextCompat.getDrawable(mContext, R.drawable.book_menu));
        bookMenu.setSelected(false);
        bookMenu.setMenuName("Book");

        mBottomMenuList.add(bookMenu);

        BottomMenu logoMenu = new BottomMenu();
        logoMenu.setAppIcon(true);
        logoMenu.setMenuIcon(ContextCompat.getDrawable(mContext, R.drawable.logo_menu));
        logoMenu.setSelected(false);
        logoMenu.setMenuName("AppLogo");

        mBottomMenuList.add(logoMenu);

        BottomMenu saleMenu = new BottomMenu();
        saleMenu.setAppIcon(false);
        saleMenu.setMenuIcon(ContextCompat.getDrawable(mContext, R.drawable.sale_menu));
        saleMenu.setSelected(false);
        saleMenu.setMenuName("Sale");

        mBottomMenuList.add(saleMenu);

        BottomMenu supportMenu = new BottomMenu();
        supportMenu.setAppIcon(false);
        supportMenu.setMenuIcon(ContextCompat.getDrawable(mContext, R.drawable.support_menu));
        supportMenu.setSelected(false);
        supportMenu.setMenuName("Support");

        mBottomMenuList.add(supportMenu);

        bottomMenuAdapter = new BottomMenuAdapter(this, mBottomMenuList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, mMenuNames.length));
        recyclerView.setAdapter(bottomMenuAdapter);
    }


    @Override
    public void onBottomMenuClick(BottomMenu bottomMenu) {

    }

    @Override
    public void onProfileImageClick() {

    }

    public void doNotifyDataSetChanged() {
        bottomMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void setContentView(int layoutResID) {
        mFrame.removeAllViews();
        View.inflate(this, layoutResID, mFrame);
    }

    @Override
    public void setContentView(View view) {
        mFrame.removeAllViews();
        mFrame.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mFrame.removeAllViews();
        mFrame.addView(view, params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        mFrame.addView(view, params);
    }
}