package com.scorg.farmaeasy.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.shortbook.ShortBookProductsListAdapter;
import com.scorg.farmaeasy.helpers.shortbook.ShortBookHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookList;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookResponseModel;
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

import static com.scorg.farmaeasy.util.Constants.SUCCESS;

/**
 * A placeholder fragment containing a simple view.
 */

public class ShortBookFragment extends Fragment implements HelperResponse, DatePickerDialog.OnDateSetListener {

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.noRecordsFound)
    TextView noRecordsFound;

    Unbinder unbinder;


    private ArrayList<ShortBookList> mShortBookList = new ArrayList<>();
    private ShortBookProductsListAdapter mAdapter;
    private int year, month, dayOfMonth;

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
        View rootView = inflater.inflate(R.layout.fragment_shortbook, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",\n" + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        toDateValue.setText(formatedDate);

        getProducts("");

        List<String> spinnerArray = new ArrayList<String>();

        spinnerArray.add("Sort By");
        spinnerArray.add("Products");
        spinnerArray.add("Company");
        spinnerArray.add("Supplier");
        spinnerArray.add("Quantity");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(), R.layout.sorting_spinner_item, spinnerArray);
        aa.setDropDownViewResource(R.layout.sorting_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sortingSpinner.setAdapter(aa);

        sortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    getProducts(spinnerArray.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return rootView;
    }

    private void getProducts(String sortBy) {

//        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());

        ShortBookHelper shortBookHelper = new ShortBookHelper(getActivity(), this);
        shortBookHelper.doShortBook(month + "/" + dayOfMonth + "/" + year, month + "/" + dayOfMonth + "/" + year, sortBy);
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
        this.year = year;
        this.month = month + 1;
        this.dayOfMonth = dayOfMonth;

        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",\n" + CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        toDateValue.setText(formatedDate);
//        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
        getProducts(sortingSpinner.getSelectedItemPosition() == 0 ? "" : sortingSpinner.getSelectedItem().toString());
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_SHORTBOOK)) {
            //After login user navigated to HomeActivity
            ShortBookResponseModel shortBookResponseModel = (ShortBookResponseModel) customResponse;
            if (shortBookResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                if(shortBookResponseModel.getData().getShortBookList().size()>0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noRecordsFound.setVisibility(View.GONE);
                    mShortBookList = shortBookResponseModel.getData().getShortBookList();
                    mAdapter = new ShortBookProductsListAdapter(getActivity(), mShortBookList);
                    LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearlayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }else {
                    recyclerView.setVisibility(View.GONE);
                    noRecordsFound.setVisibility(View.VISIBLE);
                }

            } else {
                recyclerView.setVisibility(View.GONE);
                noRecordsFound.setVisibility(View.VISIBLE);
//                CommonMethods.showToast(getActivity(), shortBookResponseModel.getCommon().getStatusMessage());
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
}