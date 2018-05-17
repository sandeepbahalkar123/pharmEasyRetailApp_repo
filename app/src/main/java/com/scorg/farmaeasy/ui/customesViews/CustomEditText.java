package com.scorg.farmaeasy.ui.customesViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.singleton.Application;
import com.scorg.farmaeasy.util.Constants;
import com.scorg.farmaeasy.util.CommonMethods;
/**
 * Created by Sandeep Bahalkar
 */
public class CustomEditText extends AppCompatEditText {
    private static final String TAG = "EditText";
    public CustomEditText(Context context) {
        super(context);
    }
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

        setTextColor(Color.parseColor(Constants.TEXT_COLOR));
        setCustomFont(context, attrs);
    }

      public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = a.getString(R.styleable.CustomTextView_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/" + asset);
        } catch (Exception e) {
           CommonMethods.Log(TAG, "Could not get typeface: " + e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }
}