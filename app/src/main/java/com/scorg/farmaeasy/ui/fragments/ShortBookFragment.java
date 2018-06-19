package com.scorg.farmaeasy.ui.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.shortbook.ShortBookExpandableListAdapter;
import com.scorg.farmaeasy.helpers.shortbook.ShortBookHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookList;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookResponseModel;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

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

public class ShortBookFragment extends Fragment implements HelperResponse, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.fromDateValue)
    TextView fromDateValue;
    @BindView(R.id.fromDateMainLayout)
    LinearLayout fromDateMainLayout;

    @BindView(R.id.sortByButton)
    Button sortByButton;
    @BindView(R.id.sortLayout)
    RelativeLayout sortLayout;

    @BindView(R.id.productslayout)
    LinearLayout productslayout;
    @BindView(R.id.shortBookList)
    ExpandableListView shortBookList;
    @BindView(R.id.noRecordsFound)
    TextView noRecordsFound;

    @BindView(R.id.leftButton)
    ImageButton leftButton;
    @BindView(R.id.rightButton)
    ImageButton rightButton;

    Unbinder unbinder;

    private ArrayList<ShortBookList> mShortBookList = new ArrayList<>();
    private int year, month, dayOfMonth;
    private String selected = "";
    private PopupWindow popup;
    private int lastExpanded = -1;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private String dateSelected = "";
    private String dateCurrent = "";

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
        init();
        return rootView;
    }

    private void init() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",  " + CommonMethods.getFormattedDate(dayOfMonth + "-" + month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        getProducts("");
        createSortMenu();

        dateSelected = dayOfMonth + "-" + month + "-" + year;
        dateCurrent = dayOfMonth + "-" + month + "-" + year;

        shortBookList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpanded == -1 || lastExpanded != groupPosition) {
                    shortBookList.collapseGroup(lastExpanded);
                }
                lastExpanded = groupPosition;
            }
        });

        setButtonVisibility();
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

    private void createSortMenu() {
        View popupContent = getLayoutInflater().inflate(R.layout.popup, null);

        LinearLayout mainLayout = popupContent.findViewById(R.id.mainLayout);
        sortLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                sortLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = mainLayout.getLayoutParams();
                layoutParams.width = sortLayout.getWidth();
                mainLayout.setLayoutParams(layoutParams);
            }
        });

        Button productButton = popupContent.findViewById(R.id.productButton);
        productButton.setOnClickListener(v -> {
            selected = productButton.getText().toString();
            getProducts(selected);
            popup.dismiss();
        });

        Button companyButton = popupContent.findViewById(R.id.companyButton);
        companyButton.setOnClickListener(v -> {
            selected = companyButton.getText().toString();
            getProducts(selected);
            popup.dismiss();
        });

        Button supplierButton = popupContent.findViewById(R.id.supplierButton);
        supplierButton.setOnClickListener(v -> {
            selected = supplierButton.getText().toString();
            getProducts(selected);
            popup.dismiss();
        });

        Button quantityButton = popupContent.findViewById(R.id.quantityButton);
        quantityButton.setOnClickListener(v -> {
            selected = quantityButton.getText().toString();
            getProducts(selected);
            popup.dismiss();
        });

        popup = new PopupWindow();
        //popup should wrap content view
        popup.setWindowLayoutMode(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        //set content and background
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.setContentView(popupContent);
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        popup.dismiss();
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month + 1;
        this.dayOfMonth = dayOfMonth;

        dateSelected = dayOfMonth + "-" + this.month + "-" + year;

        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",  " + CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        getProducts(selected.isEmpty() ? "" : selected);

        setButtonVisibility();
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_SHORTBOOK)) {
            //After login user navigated to HomeActivity
            ShortBookResponseModel shortBookResponseModel = (ShortBookResponseModel) customResponse;
            if (shortBookResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                if (shortBookResponseModel.getData().getShortBookList().size() > 0) {
                    shortBookList.setVisibility(View.VISIBLE);
                    noRecordsFound.setVisibility(View.GONE);
                    mShortBookList = shortBookResponseModel.getData().getShortBookList();

                    ShortBookExpandableListAdapter listAdapter = new ShortBookExpandableListAdapter(getContext(), new ShortBookExpandableListAdapter.OnChildClickListener() {
                        @Override
                        public void onClick(int groupPosition) {
                            shortBookList.collapseGroup(groupPosition);
                        }
                    }, mShortBookList);
                    // setting list adapter
                    shortBookList.setAdapter(listAdapter);
                } else {
                    shortBookList.setVisibility(View.GONE);
                    noRecordsFound.setVisibility(View.VISIBLE);
                }

            } else {
                shortBookList.setVisibility(View.GONE);
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

    @SuppressLint("RestrictedApi")
    @OnClick({R.id.fromDateMainLayout, R.id.sortByButton, R.id.leftButton, R.id.rightButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fromDateMainLayout: {
                Calendar calendar = getAddedDateResult(0);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
            break;
            case R.id.sortByButton:
                if (popup.isShowing()) {
                    popup.dismiss();
                } else {
                    //Show the PopupWindow anchored to the button we
                    //pressed. It will be displayed below the button
                    //if there's room, otherwise above.
                    popup.showAsDropDown(sortLayout);
                }
                break;

            case R.id.leftButton: {
                Calendar calendar = getAddedDateResult(-1);

                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                this.year = year;
                this.month = month;
                this.dayOfMonth = dayOfMonth;

                dateSelected = dayOfMonth + "-" + month + "-" + year;

                String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",  " + CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
                fromDateValue.setText(formatedDate);
                getProducts(selected.isEmpty() ? "" : selected);

                setButtonVisibility();
            }
            break;
            case R.id.rightButton: {
                Calendar calendar = getAddedDateResult(1);

                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                this.year = year;
                this.month = month;
                this.dayOfMonth = dayOfMonth;

                dateSelected = dayOfMonth + "-" + month + "-" + year;

                String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",  " + CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
                fromDateValue.setText(formatedDate);
                getProducts(selected.isEmpty() ? "" : selected);

                setButtonVisibility();
            }
            break;
        }
    }
}