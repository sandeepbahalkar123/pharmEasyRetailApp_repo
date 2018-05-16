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
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setBackground(Constants.getRectangleDrawable(Constants.BUTTON_COLOR, "#00000000", 2, 10, 10, 10, 10));
        setTextColor(Color.parseColor(Constants.BUTTON_TEXT_COLOR));
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = a.getString(R.styleable.CustomTextView_customFont);

        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public void setCustomFont(Context ctx, String asset) {
        Typeface typeface = Application.get(ctx, "fonts/" + asset);
        setTypeface(typeface);
    }
}
