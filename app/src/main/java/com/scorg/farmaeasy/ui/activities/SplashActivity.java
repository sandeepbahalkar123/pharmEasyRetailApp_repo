package com.scorg.farmaeasy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.Constants;

public class SplashActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        doLogin();
    }

    private void doLogin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.LOGIN_STATUS, mContext).equals(Constants.YES)) {
                    Intent intentObj = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intentObj);
                } else {
                    Intent intentObj = new Intent(SplashActivity.this, PagerActivity.class);
                    startActivity(intentObj);
                }
                finish();
            }
        }, Constants.TIME_STAMPS.THREE_SECONDS);
    }
}
