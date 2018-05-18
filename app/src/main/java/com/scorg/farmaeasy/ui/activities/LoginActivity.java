package com.scorg.farmaeasy.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.ui.customesViews.CustomButton;
import com.scorg.farmaeasy.ui.customesViews.CustomEditText;
import com.scorg.farmaeasy.ui.customesViews.CustomTextView;
import com.scorg.farmaeasy.util.CommonMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.editTextUserId)
    CustomEditText editTextUserId;
    @BindView(R.id.editTextPassword)
    CustomEditText editTextPassword;
    @BindView(R.id.btnOtp)
    CustomTextView btnOtp;
    @BindView(R.id.btn_login)
    CustomButton btnLogin;
    private Context mContext;
    private static final String TAG="LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
        ButterKnife.bind(this);

//        editTextPassword.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final int DRAWABLE_LEFT = 0;
//                final int DRAWABLE_TOP = 1;
//                final int DRAWABLE_RIGHT = 2;
//                final int DRAWABLE_BOTTOM = 3;
//
//                if(event.getAction() == MotionEvent.ACTION_UP) {
//                    if(event.getRawX() >= (editTextPassword.getRight() - editTextPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                        // your action here
//                        CommonMethods.Log(TAG,"Eye icon clicked");
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
    }

    @OnClick({R.id.btnOtp, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOtp:
                break;
            case R.id.btn_login:
                break;
        }
    }




}
