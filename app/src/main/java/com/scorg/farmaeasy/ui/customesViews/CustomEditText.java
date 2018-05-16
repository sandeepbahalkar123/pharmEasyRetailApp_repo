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

/**
 * Created by Sandeep Bahalkar
 */
public class CustomEditText extends AppCompatEditText {
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

        setTextColor(Color.parseColor(Constants.TEXT_COLOR));
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