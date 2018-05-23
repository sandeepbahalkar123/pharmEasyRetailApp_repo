package com.scorg.farmaeasy.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class DayBookFragment extends Fragment {

    @BindView(R.id.fromDateValue)
    TextView fromDateValue;
    @BindView(R.id.fromDateMainLayout)
    LinearLayout fromDateMainLayout;
    @BindView(R.id.toDateValue)
    TextView toDateValue;
    @BindView(R.id.toDateMainLayout)
    LinearLayout toDateMainLayout;
    @BindView(R.id.selectDatelayout)
    LinearLayout selectDatelayout;
    @BindView(R.id.particularlayout)
    LinearLayout particularlayout;
    @BindView(R.id.openingbalnaceDebitValue)
    TextView openingbalnaceDebitValue;
    @BindView(R.id.openingbalnaceCreditValue)
    TextView openingbalnaceCreditValue;
    @BindView(R.id.topLayout)
    LinearLayout topLayout;
    @BindView(R.id.cashsaleDebitValue)
    TextView cashsaleDebitValue;
    @BindView(R.id.cashsaleCreditValue)
    TextView cashsaleCreditValue;
    @BindView(R.id.creditsaleDebitValue)
    TextView creditsaleDebitValue;
    @BindView(R.id.creditsaleCreditValue)
    TextView creditsaleCreditValue;
    @BindView(R.id.creditcardsaleDebitValue)
    TextView creditcardsaleDebitValue;
    @BindView(R.id.creditcardsaleCreditValue)
    TextView creditcardsaleCreditValue;
    @BindView(R.id.cashPurchaseDebitValue)
    TextView cashPurchaseDebitValue;
    @BindView(R.id.cashPurchaseCreditValue)
    TextView cashPurchaseCreditValue;
    @BindView(R.id.creditPurchaseDebitValue)
    TextView creditPurchaseDebitValue;
    @BindView(R.id.creditPurchaseCreditValue)
    TextView creditPurchaseCreditValue;
    @BindView(R.id.mainlayout)
    LinearLayout mainlayout;
    @BindView(R.id.totalDebitValue)
    TextView totalDebitValue;
    @BindView(R.id.totalCreditValue)
    TextView totalCreditValue;
    @BindView(R.id.closingBalnceDebitValue)
    TextView closingBalnceDebitValue;
    @BindView(R.id.closingBalnceCreditValue)
    TextView closingBalnceCreditValue;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;
    Unbinder unbinder;

    public DayBookFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DayBookFragment newInstance() {
        return new DayBookFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_day_book, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}