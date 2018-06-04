package com.scorg.farmaeasy.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.product.BillingProductsListAdapter;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.scorg.farmaeasy.ui.activities.PagerActivity.HOMEDELIVERYFLAG;
import static com.scorg.farmaeasy.ui.activities.PagerActivity.TOTALPRODUCTSLIST;

/**
 * A placeholder fragment containing a simple view.
 */
public class BillingFragment extends Fragment {

    @BindView(R.id.productList)
    RecyclerView productListRecycler;
    @BindView(R.id.quantity)
    TextView quantity;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.quantity_discount)
    TextView quantityDiscount;
    @BindView(R.id.amount_discount)
    TextView amountDiscount;
    @BindView(R.id.netAmount)
    TextView netAmount;
    @BindView(R.id.printButton)
    Button printButton;
    @BindView(R.id.saveButton)
    Button saveButton;
    Unbinder unbinder;
    @BindView(R.id.transactionModeSelection)
    AppCompatSpinner transactionModeSelection;

    private BillingProductsListAdapter mBillingProductsListAdapter;
    private boolean homedeliveryFlag;
    private ArrayList<ProductList> productLists;


    public BillingFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BillingFragment newInstance() {
        return new BillingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_billing_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);

//        String jsonString = loadJSONFromAsset("productList.json");
//        ProductResponseModel productResponseModel = new Gson().fromJson(jsonString, ProductResponseModel.class);
//        List<ProductList> productList = productResponseModel.getData().getProductList();
            homedeliveryFlag = getArguments().getBoolean(HOMEDELIVERYFLAG);

        mBillingProductsListAdapter = new BillingProductsListAdapter(getActivity(), productLists);
        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        productListRecycler.setLayoutManager(linearlayoutManager);
        productListRecycler.setItemAnimator(new DefaultItemAnimator());
        productListRecycler.setAdapter(mBillingProductsListAdapter);

        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Cash");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.mode_spinner_item, spinnerArray);
        transactionModeSelection.setAdapter(spinnerArrayAdapter);


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