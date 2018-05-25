package com.scorg.farmaeasy.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.product.ExpandableListAdapter;
import com.scorg.farmaeasy.model.responseModel.product.ProductList;
import com.scorg.farmaeasy.model.responseModel.product.ProductResponseModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductFragment extends Fragment {

    @BindView(R.id.productList)
    ExpandableListView productList;

    Unbinder unbinder;

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

        String jsonString = loadJSONFromAsset("productList.json");
        ProductResponseModel productResponseModel = new Gson().fromJson(jsonString, ProductResponseModel.class);

        List<ProductList> productList = productResponseModel.getData().getProductList();

        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, productList);
        // setting list adapter
        this.productList.setAdapter(listAdapter);

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
}