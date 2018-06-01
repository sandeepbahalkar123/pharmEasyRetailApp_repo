package com.scorg.farmaeasy.model.requestModel.sale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;

import java.util.ArrayList;

public class SaleRequestModel {

    @SerializedName("productList")
    @Expose
    private ArrayList<ProductList> productParentList = new ArrayList<>();

    public ArrayList<ProductList> getProductParentList() {
        return productParentList;
    }

    public void setProductParentList(ArrayList<ProductList> productParentList) {
        this.productParentList = productParentList;
    }
}
