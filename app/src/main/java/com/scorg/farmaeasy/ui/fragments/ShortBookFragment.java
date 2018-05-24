package com.scorg.farmaeasy.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShortBookFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

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
        init();

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

    private void init() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",\n" + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        toDateValue.setText(formatedDate);
        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fromDateMainLayout)
    public void onViewClicked() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",\n" + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        toDateValue.setText(formatedDate);
        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
    }
}