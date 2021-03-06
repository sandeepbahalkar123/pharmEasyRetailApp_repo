package com.scorg.farmaeasy.singleton;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

/**
 * Created by Sandeep Bahalkar
 */

public class Device {

    private  final String TAG = this.getClass().getName();
    private Context context;
    private WindowManager windowManager;

    public Device(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public Device(Context context) {
        this.context = context;
    }

    /**
     * Create a static method to get instance.
     */

    public static Device getInstance(Context context) {

        return new Device(context);
    }

    public static Device getInstance(WindowManager windowManager) {

        return new Device(windowManager);
    }

    public String getDensity() {

        String density = Constants.HDPI;

        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                density = Constants.LDPI;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                density = Constants.MDPI;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                density = Constants.HDPI;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                density = Constants.XHDPI;
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                density = Constants.XXHDPI;
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                density = Constants.XXXHDPI;
                break;
        }

        CommonMethods.Log(TAG, density);

        return density;
    }

    public String getDeviceType() {
        String what = "";
        boolean tabletSize = context.getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            what = Constants.TABLET;
        } else {
            what = Constants.PHONE;
        }
        return what;
    }

    public String getDeviceId() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getOSVersion() {
        return Build.VERSION.RELEASE + "(" + Build.BRAND + ")";
    }

    public String getOS() {
        return "Android";
    }
}
