package com.scorg.farmaeasy.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
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
public class ShortBookFragment extends Fragment {

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
    @BindView(R.id.sortingSpinner)
    AppCompatSpinner sortingSpinner;
    @BindView(R.id.productslayout)
    LinearLayout productslayout;
    @BindView(R.id.shortBookList)
    RecyclerView shortBookList;

    Unbinder unbinder;

    public ShortBookFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ShortBookFragment newInstance() {
        return new ShortBookFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_short_book, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        List<String> spinnerArray = new ArrayList<String>();

        spinnerArray.add("Sort By");
        spinnerArray.add("Product");
        spinnerArray.add("Company");
        spinnerArray.add("Supplier");
        spinnerArray.add("Quantity");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(), R.layout.sorting_spinner_item, spinnerArray);
        aa.setDropDownViewResource(R.layout.sorting_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sortingSpinner.setAdapter(aa);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}