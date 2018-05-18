package com.scorg.farmaeasy.model.requestModel.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;

public class LoginRequestModel implements CustomResponse {

    @SerializedName("username")
    @Expose
    private String userId;
    @SerializedName("pwd")
    @Expose
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}