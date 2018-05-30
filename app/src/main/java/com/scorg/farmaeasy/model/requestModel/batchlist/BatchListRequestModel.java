package com.scorg.farmaeasy.model.requestModel.batchlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.interfaces.CustomResponse;

public class BatchListRequestModel implements CustomResponse {


    @SerializedName("productID")
    @Expose
    private String productID;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}