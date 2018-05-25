package com.scorg.farmaeasy.ui.fragments;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.daybook.DayBookParticularListAdapter;
import com.scorg.farmaeasy.adapter.shortbook.ShortBookProductsListAdapter;
import com.scorg.farmaeasy.helpers.daybook.DayBookHelper;
import com.scorg.farmaeasy.helpers.shortbook.ShortBookHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.daybook.DayBookList;
import com.scorg.farmaeasy.model.responseModel.daybook.DayBookResponseModel;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookList;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookResponseModel;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShortBookFragment extends Fragment implements HelperResponse{

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

    Unbinder unbinder;

    private ArrayList<ShortBookList> mShortBookList = new ArrayList<>();
    private ShortBookProductsListAdapter mAdapter;

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

        init();

        return rootView;
    }


    private void init(){
        String fromDate = "5/24/2018";
        String toDate = fromDate;
        String orderBy= "Qunatity";
        ShortBookHelper shortBookHelper = new ShortBookHelper(getActivity(), this);
        shortBookHelper.doShortBook(orderBy,fromDate,toDate);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_SHORTBOOK)) {
            //After login user navigated to HomeActivity
            ShortBookResponseModel shortBookResponseModel = (ShortBookResponseModel) customResponse;
            if (shortBookResponseModel.getCommon().getSuccess()) {
                mShortBookList=shortBookResponseModel.getData().getShortBookList();
                mAdapter = new ShortBookProductsListAdapter(getActivity(),mShortBookList);
                LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearlayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

            } else {
                CommonMethods.showToast(getActivity(), shortBookResponseModel.getCommon().getStatusMessage());
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