package com.scorg.farmaeasy.model.requestModel.sale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;

import java.util.List;

public class SaleRequestModel {

    @SerializedName("productList")
    @Expose
    private List<ProductList> productList = null;
    @SerializedName("details")
    @Expose
    private Details details;

    public List<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductList> productList) {
        this.productList = productList;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

}