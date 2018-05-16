package com.scorg.farmaeasy.singleton;

import android.content.Context;
import android.graphics.Typeface;
import com.scorg.farmaeasy.helpers.database.AppDBHelper;

import java.util.Hashtable;

/**
 * Created by Sandeep Bahalkar
 */
public class Application extends android.app.Application {
    public final String TAG = this.getClass().getName();
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();
    private static String SHOW_UPDATE_DIALOG_ON_SKIPPED = "";

    public static String getShowUpdateDialogOnSkipped() {
        return SHOW_UPDATE_DIALOG_ON_SKIPPED;
    }

    public static void setShowUpdateDialogOnSkipped(String showUpdateDialogOnSkipped) {
        SHOW_UPDATE_DIALOG_ON_SKIPPED = showUpdateDialogOnSkipped;
    }


    public static Typeface get(Context c, String name) {
        synchronized (cache) {
            if (!cache.containsKey(name)) {
                Typeface t = Typeface.createFromAsset(c.getAssets(), "fonts/"
                        + name);
                cache.put(name, t);
            }
            return cache.get(name);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppDBHelper.getInstance(this);
//        new NukeSSLCerts().nuke(); // disable all ssl certificates (dangerous)
    }
}