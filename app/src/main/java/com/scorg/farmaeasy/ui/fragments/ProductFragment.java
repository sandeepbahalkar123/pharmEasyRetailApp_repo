package com.scorg.farmaeasy.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.product.ProductExpandableListAdapter;
import com.scorg.farmaeasy.helpers.batchlist.BatchListHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchListResponseModel;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.ui.activities.PagerActivity;
import com.scorg.farmaeasy.ui.activities.ProductsActivity;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.scorg.farmaeasy.ui.activities.PagerActivity.COLLECTEDPRODUCTSLIST;
import static com.scorg.farmaeasy.ui.activities.PagerActivity.PRODUCTID;
import static com.scorg.farmaeasy.util.Constants.SUCCESS;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductFragment extends Fragment implements HelperResponse {


    private String TAG=this.getClass().getName();
    @BindView(R.id.productList)
    ExpandableListView productListExpand;
    @BindView(R.id.addProducts)
    ImageView addProducts;
    Unbinder unbinder;
    private ArrayList<ProductList> productParentList=new ArrayList<>();;

    public ProductFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        unbinder = ButterKnife.bind(this, rootView);
//        String jsonString = loadJSONFromAsset("productList.json");
//        ProductSearchResponseModel productResponseModel = new Gson().fromJson(jsonString, ProductSearchResponseModel.class);
//        List<ProductList> productChildList = productResponseModel.getData().getProductList();
//        ArrayList<ProductList> productParentList = new ArrayList<>();
//        productParentList.add(productChildList.get(getArguments().getInt(INDEX)));
//
//        ProductExpandableListAdapter listAdapter = new ProductExpandableListAdapter(getContext(),productParentList,  productChildList);
//        // setting list adapter
//        productListExpand.setAdapter(listAdapter);
//        productListExpand.expandGroup(0);

        BatchListHelper batchListHelper = new BatchListHelper(getActivity(), this);
        batchListHelper.doBatchList(getArguments().getString(PRODUCTID));

        return rootView;
    }

    public String loadJSONFromAsset(String fileName) {
        String json;
        try {
            InputStream is = getActivity().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_BATCHLIST)) {
            //After login user navigated to HomeActivity
            BatchListResponseModel receivedModel = (BatchListResponseModel) customResponse;
            if (receivedModel.getCommon().getStatusCode().equals(SUCCESS)) {
                ArrayList<BatchList> productChildList = receivedModel.getData().getBatchList();
                productParentList = getArguments().getParcelableArrayList(COLLECTEDPRODUCTSLIST);
                ProductExpandableListAdapter expandableListAdapter = new ProductExpandableListAdapter(getContext(), productParentList, productChildList);
                // setting list adapter
                productListExpand.setAdapter(expandableListAdapter);
                productListExpand.expandGroup(0);
            } else {
                CommonMethods.showToast(getActivity(), receivedModel.getCommon().getStatusMessage());
            }
        }
    }

    @Override
    public void onParseError(String mOldDataTag, String errorMessage) {
        CommonMethods.showToast(getActivity(), errorMessage);
    }

    @Override
    public void onServerError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(getActivity(), serverErrorMessage);
    }

    @Override
    public void onNoConnectionError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(getActivity(), serverErrorMessage);
    }

    @OnClick(R.id.addProducts)
    public void onViewClicked() {
       CommonMethods.Log(TAG,"addProducts clicked");

        Intent intent = new Intent(getActivity(), ProductsActivity.class);
        intent.putParcelableArrayListExtra(COLLECTEDPRODUCTSLIST,productParentList);
        startActivity(intent);
    }
}