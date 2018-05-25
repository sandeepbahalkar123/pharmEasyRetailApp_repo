package com.scorg.farmaeasy.model.responseModel.batch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("batchList")
    @Expose
    private List<BatchList> batchList = null;

    public List<BatchList> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<BatchList> batchList) {
        this.batchList = batchList;
    }

}