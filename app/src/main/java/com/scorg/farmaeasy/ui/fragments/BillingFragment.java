package com.scorg.farmaeasy.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.product.BillingProductsListAdapter;
import com.scorg.farmaeasy.model.requestModel.sale.Billing;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class BillingFragment extends Fragment {

    @BindView(R.id.productList)
    RecyclerView productListRecycler;
    @BindView(R.id.printButton)
    Button printButton;
    @BindView(R.id.saveButton)
    Button saveButton;
    Unbinder unbinder;
    @BindView(R.id.transactionModeSelection)
    AppCompatSpinner transactionModeSelection;
    @BindView(R.id.percentEditText)
    EditText percentEditText;
    @BindView(R.id.totalAmountValue)
    TextView totalAmountValue;
    @BindView(R.id.amountDiscountValue)
    TextView amountDiscountValue;
    @BindView(R.id.netAmountValue)
    TextView netAmountValue;

    DecimalFormat precision = new DecimalFormat("#0.00");

    private PagerActivityInteraction pagerActivityInteraction;
    private double totalAmount;

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

        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        productListRecycler.setLayoutManager(linearlayoutManager);
        productListRecycler.setItemAnimator(new DefaultItemAnimator());

        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Cash");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.mode_spinner_item, spinnerArray);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.select_mode_spinner_item);
        transactionModeSelection.setAdapter(spinnerArrayAdapter);

        percentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty())
                    calculateDiscount(Double.parseDouble(s.toString()));
                else calculateDiscount(0);
            }
        });

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void setProducts(ArrayList<ProductList> productList) {
//        double maxDiscountLimit = Double.parseDouble(PreferencesManager.getString(Constants.DISCOUNT_LIMIT, getContext()));
//        percentEditText.setText(precision.format(maxDiscountLimit));

        totalAmount = 0;
        BillingProductsListAdapter mAdapter = new BillingProductsListAdapter(getContext(), productList);
        productListRecycler.setAdapter(mAdapter);
        for (ProductList productL : productList)
            totalAmount += productL.getIndividualProductTotalBatchAmount();

        calculateDiscount(Double.parseDouble(percentEditText.getText().toString()));
    }

    private void calculateDiscount(double discountValue) {
        double totalDiscountedValue = discountValue * totalAmount / 100;
        double netAmount = totalAmount - totalDiscountedValue;
        totalAmountValue.setText(precision.format(totalAmount));
        amountDiscountValue.setText(precision.format(totalDiscountedValue));
        netAmountValue.setText(precision.format(netAmount));
    }

    @OnClick({R.id.printButton, R.id.saveButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.printButton:
                callApi("print");
                break;
            case R.id.saveButton:
                callApi("save");
                break;
        }
    }

    private void callApi(String billingType) {
        Billing billing = new Billing();
        billing.setBillingType(billingType);
        billing.setDiscount(percentEditText.getText().toString());
        billing.setTotal(totalAmountValue.getText().toString());
        billing.setNetAmt(netAmountValue.getText().toString());
        billing.setTrasactionMode((String) transactionModeSelection.getSelectedItem());

        pagerActivityInteraction.callApi(billing);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PagerActivityInteraction) {
            pagerActivityInteraction = (PagerActivityInteraction) context;
        }
    }

    public double getDiscountValue() {
        if (percentEditText.getText().toString().isEmpty())
            return 0.0;
        else return Double.parseDouble(percentEditText.getText().toString());
    }

    public interface PagerActivityInteraction {
        void callApi(Billing billing);
    }
}