package com.scorg.farmaeasy.model.requestModel.addressdetailssearchtext;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;

public class AddressDetailsRequestModel implements CustomResponse {


    @SerializedName("searchString")
    @Expose
    private String searchString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}