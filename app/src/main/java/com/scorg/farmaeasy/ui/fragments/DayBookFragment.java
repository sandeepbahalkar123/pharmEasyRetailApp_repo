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
import android.widget.ImageButton;
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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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

    @BindView(R.id.backLines)
    LinearLayout backLines;

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
    @BindView(R.id.noRecordsFound)
    TextView noRecordsFound;

    @BindView(R.id.leftButton)
    ImageButton leftButton;
    @BindView(R.id.rightButton)
    ImageButton rightButton;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private String dateSelected = "";
    private String dateCurrent = "";

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
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        dateSelected = dayOfMonth + "-" + month + "-" + year;
        dateCurrent = dayOfMonth + "-" + month + "-" + year;

        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ", " + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
        DayBookHelper loginHelper = new DayBookHelper(getActivity(), this);
        loginHelper.doDayBook(shopId, month + "/" + dayOfMonth + "/" + year, month + "/" + dayOfMonth + "/" + year);

        setButtonVisibility();
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
            DecimalFormat precision = new DecimalFormat("#.00");
            if (dayBookResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                if (dayBookResponseModel.getData().getOpeningBal().getDbAmount() != 0)
                    openingbalnaceDebitValue.setText("" + precision.format(dayBookResponseModel.getData().getOpeningBal().getDbAmount()));
                else
                    openingbalnaceDebitValue.setText("0.00");

                if (dayBookResponseModel.getData().getOpeningBal().getCrAmount() != 0)
                    openingbalnaceCreditValue.setText("" + precision.format(dayBookResponseModel.getData().getOpeningBal().getCrAmount()));
//                else
//                    openingbalnaceCreditValue.setText("0.00");

                if (dayBookResponseModel.getData().getTotal().getDbAmount() != 0)
                    totalDebitValue.setText("" + precision.format(dayBookResponseModel.getData().getTotal().getDbAmount()));
                else
                    totalDebitValue.setText("0.00");

                if (dayBookResponseModel.getData().getTotal().getCrAmount() != 0)
                    totalCreditValue.setText("" + precision.format(dayBookResponseModel.getData().getTotal().getCrAmount()));
                else
                    totalCreditValue.setText("0.00");

                if (dayBookResponseModel.getData().getClosingBal().getDbAmount() != 0)
                    closingBalnceDebitValue.setText("" + precision.format(dayBookResponseModel.getData().getClosingBal().getDbAmount()));
                else
                    closingBalnceDebitValue.setText("0.00");

                if (dayBookResponseModel.getData().getClosingBal().getCrAmount() != 0)
                    closingBalnceCreditValue.setText("" + precision.format(dayBookResponseModel.getData().getClosingBal().getCrAmount()));
//                else
//                    closingBalnceCreditValue.setText("0.00");

                if (dayBookResponseModel.getData().getDayBookList().size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noRecordsFound.setVisibility(View.GONE);
                    backLines.setVisibility(View.VISIBLE);
                    ArrayList<DayBookList> mDayBookList = dayBookResponseModel.getData().getDayBookList();
                    DayBookParticularListAdapter mAdapter = new DayBookParticularListAdapter(getActivity(), mDayBookList);
                    LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearlayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    backLines.setVisibility(View.GONE);
                    noRecordsFound.setVisibility(View.VISIBLE);
                }

            } else {
                recyclerView.setVisibility(View.GONE);
                noRecordsFound.setVisibility(View.VISIBLE);
                backLines.setVisibility(View.GONE);
                openingbalnaceDebitValue.setText("0.00");
                totalDebitValue.setText("0.00");
                totalCreditValue.setText("0.00");
                closingBalnceDebitValue.setText("0.00");
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        dateSelected = dayOfMonth + "-" + month + "-" + year;
        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ", " + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
        DayBookHelper loginHelper = new DayBookHelper(getActivity(), this);
        loginHelper.doDayBook(shopId, month + "/" + dayOfMonth + "/" + year, month + "/" + dayOfMonth + "/" + year);

        setButtonVisibility();
    }

    @OnClick({R.id.leftButton, R.id.rightButton, R.id.fromDateMainLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftButton: {
                Calendar calendar = getAddedDateResult(-1);

                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                dateSelected = dayOfMonth + "-" + month + "-" + year;

                String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ", " + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
                fromDateValue.setText(formatedDate);
                Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
                DayBookHelper loginHelper = new DayBookHelper(getActivity(), this);
                loginHelper.doDayBook(shopId, month + "/" + dayOfMonth + "/" + year, month + "/" + dayOfMonth + "/" + year);

                setButtonVisibility();
            }
            break;
            case R.id.rightButton: {
                Calendar calendar = getAddedDateResult(1);

                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                dateSelected = dayOfMonth + "-" + month + "-" + year;

                String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ", " + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
                fromDateValue.setText(formatedDate);
                Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
                DayBookHelper loginHelper = new DayBookHelper(getActivity(), this);
                loginHelper.doDayBook(shopId, month + "/" + dayOfMonth + "/" + year, month + "/" + dayOfMonth + "/" + year);

                setButtonVisibility();
            }
            break;
            case R.id.fromDateMainLayout: {
                Calendar calendar = getAddedDateResult(0);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
            break;
        }
    }

    private void setButtonVisibility() {
        if (dateCurrent.equalsIgnoreCase(dateSelected))
            rightButton.setVisibility(View.INVISIBLE);
        else rightButton.setVisibility(View.VISIBLE);
    }

    private Calendar getAddedDateResult(int days) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(dateSelected));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar;
    }
}