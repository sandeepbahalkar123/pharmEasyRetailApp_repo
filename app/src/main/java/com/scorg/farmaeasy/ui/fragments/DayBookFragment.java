package com.scorg.farmaeasy.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.daybook.DayBookParticularListAdapter;
import com.scorg.farmaeasy.helpers.daybook.DayBookHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.daybook.DayBookList;
import com.scorg.farmaeasy.model.responseModel.daybook.DayBookResponseModel;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.scorg.farmaeasy.util.Constants.SUCCESS;

/**
 * A placeholder fragment containing a simple view.
 */
public class DayBookFragment extends Fragment implements HelperResponse, DatePickerDialog.OnDateSetListener {


    Unbinder unbinder;
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
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

    private ArrayList<DayBookList> mDayBookList = new ArrayList<>();
    private DayBookParticularListAdapter mAdapter;

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
        View rootView = inflater.inflate(R.layout.fragment_daybook, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",\n" + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        toDateValue.setText(formatedDate);
        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
        DayBookHelper loginHelper = new DayBookHelper(getActivity(), this);
        loginHelper.doDayBook(shopId, month + "/" + dayOfMonth + "/" + year, month + "/" + dayOfMonth + "/" + year);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_DAYBOOK)) {
            //After login user navigated to HomeActivity
            DayBookResponseModel dayBookResponseModel = (DayBookResponseModel) customResponse;
            if (dayBookResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                if (dayBookResponseModel.getData().getOpeningBal().getDbAmount() != 0)
                    openingbalnaceDebitValue.setText("" + dayBookResponseModel.getData().getOpeningBal().getDbAmount());
                else
                    openingbalnaceDebitValue.setText("");

                if (dayBookResponseModel.getData().getOpeningBal().getCrAmount() != 0)
                    openingbalnaceCreditValue.setText("" + dayBookResponseModel.getData().getOpeningBal().getCrAmount());
                else
                    openingbalnaceCreditValue.setText("");

                if (dayBookResponseModel.getData().getTotal().getDbAmount() != 0)
                    totalDebitValue.setText("" + dayBookResponseModel.getData().getTotal().getDbAmount());
                else
                    totalDebitValue.setText("");

                if (dayBookResponseModel.getData().getTotal().getCrAmount() != 0)
                    totalCreditValue.setText("" + dayBookResponseModel.getData().getTotal().getCrAmount());
                else
                    totalCreditValue.setText("");

                if (dayBookResponseModel.getData().getClosingBal().getDbAmount() != 0)
                    closingBalnceDebitValue.setText("" + dayBookResponseModel.getData().getClosingBal().getDbAmount());
                else
                    closingBalnceDebitValue.setText("");

                if (dayBookResponseModel.getData().getClosingBal().getCrAmount() != 0)
                    closingBalnceCreditValue.setText("" + dayBookResponseModel.getData().getClosingBal().getCrAmount());
                else
                    closingBalnceCreditValue.setText("");

                mDayBookList = dayBookResponseModel.getData().getDayBookList();
                mAdapter = new DayBookParticularListAdapter(getActivity(), mDayBookList);
                LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearlayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

            } else
                CommonMethods.showToast(getContext(), dayBookResponseModel.getCommon().getStatusMessage());
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",\n" + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        toDateValue.setText(formatedDate);
        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
        DayBookHelper loginHelper = new DayBookHelper(getActivity(), this);
        loginHelper.doDayBook(shopId, month + "/" + dayOfMonth + "/" + year, month + "/" + dayOfMonth + "/" + year);
    }

    @OnClick(R.id.fromDateMainLayout)
    public void onViewClicked() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}