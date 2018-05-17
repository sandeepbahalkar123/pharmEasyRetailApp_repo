package com.scorg.farmaeasy.ui.customesViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.singleton.Application;
import com.scorg.farmaeasy.util.Constants;

/**
 * Created by Sandeep Bahalkar
 */
public class CustomButton extends AppCompatButton {
    private static final String TAG = "Button";
     public CustomButton(Context context) {
        super(context);
    }
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setBackground(Constants.getRectangleDrawable(Constants.BUTTON_COLOR, "#00000000", 2, 10, 10, 10, 10));
        setTextColor(Color.parseColor(Constants.BUTTON_TEXT_COLOR));
        setCustomFont(context, attrs);
    }
    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            setCustomFont(context, attrs);
        }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        String customFont = a.getString(R.styleable.CustomButton_customFontButton);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/" + asset);
        } catch (Exception e) {
            com.scorg.farmaeasy.util.CommonMethods.Log(TAG, "Could not get typeface: " + e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }
}
