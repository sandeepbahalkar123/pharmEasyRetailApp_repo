package com.scorg.farmaeasy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.helpers.login.LoginHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.login.LoginResponseModel;
import com.scorg.farmaeasy.ui.customesViews.CustomButton;
import com.scorg.farmaeasy.ui.customesViews.CustomEditText;
import com.scorg.farmaeasy.ui.customesViews.CustomTextView;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;
import com.scorg.farmaeasy.preference.PreferencesManager;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements HelperResponse{
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
                String userId = editTextUserId.getText().toString();
                String password = editTextPassword.getText().toString();
                if (!validate(userId, password)) {
                    LoginHelper loginHelper = new LoginHelper(mContext, this);
                    loginHelper.doLogin(userId, password);
                }
                break;
        }
    }


    private boolean validate(String email, String password) {
        String message = null;
        String enter = getString(R.string.enter);
        if (email.isEmpty()) {
            message = enter + getString(R.string.enter_user_id).toLowerCase(Locale.US);
            editTextUserId.setError(message);
            editTextUserId.requestFocus();

        } else if (password.isEmpty()) {
            message = enter + getString(R.string.enter_password).toLowerCase(Locale.US);
            editTextPassword.setError(message);
            editTextPassword.requestFocus();
        } else if (password.trim().length() < 6) {
            message = getString(R.string.error_too_small_password);
            editTextPassword.setError(message);
            editTextPassword.requestFocus();
        }
        if (message != null) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_LOGIN)) {
            //After login user navigated to HomeActivity
            LoginResponseModel receivedModel = (LoginResponseModel) customResponse;
            PreferencesManager.putString(PreferencesManager.PREFERENCES_KEY.LOGIN_STATUS, Constants.YES, mContext);
             Intent intent = new Intent(mContext, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


//            if (receivedModel.getCommon().isSuccess()) {
//                PreferencesManager.putString(PreferencesManager.PREFERENCES_KEY.LOGIN_STATUS, Constants.YES, getActivity());
//                Intent intent = new Intent(mContext, HomeActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//
//            } else {
//                CommonMethods.showToast(getActivity(), receivedModel.getCommon().getStatusMessage());
//            }
        }
    }

    @Override
    public void onParseError(String mOldDataTag, String errorMessage) {
        CommonMethods.showToast(mContext, errorMessage);
    }

    @Override
    public void onServerError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(mContext, serverErrorMessage);
    }

    @Override
    public void onNoConnectionError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(mContext, serverErrorMessage);
    }
}
