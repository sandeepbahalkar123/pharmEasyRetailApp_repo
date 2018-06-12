package com.scorg.farmaeasy.ui.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.product.ProductExpandableListAdapter;
import com.scorg.farmaeasy.adapter.shortbook.ShortBookExpandableListAdapter;
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

    Unbinder unbinder;

    private ArrayList<ShortBookList> mShortBookList = new ArrayList<>();
//    private ShortBookProductsListAdapter mAdapter;
    private int year, month, dayOfMonth;
    private String selected = "";
    private PopupWindow popup;
    private int lastExpanded = -1;

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
        createSortMenu();

        shortBookList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpanded == -1 || lastExpanded != groupPosition) {
                    shortBookList.collapseGroup(lastExpanded);
                }
                lastExpanded = groupPosition;
            }
        });

        return rootView;
    }

    private void createSortMenu() {
        View popupContent = getLayoutInflater().inflate(R.layout.popup, null);

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

        String formatedDate = CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.EEEE) + ",\n" + CommonMethods.getFormattedDate(dayOfMonth + "-" + this.month + "-" + year, Constants.DATE_PATTERN.DD_MM_YYYY, Constants.DATE_PATTERN.DD_MMM_YY);
        fromDateValue.setText(formatedDate);
        toDateValue.setText(formatedDate);
//        Integer shopId = PreferencesManager.getInt(PreferencesManager.PREFERENCES_KEY.SHOPID, getActivity());
        getProducts(selected.isEmpty() ? "" : selected);
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

                    ShortBookExpandableListAdapter listAdapter = new ShortBookExpandableListAdapter(getContext(), mShortBookList);
                    // setting list adapter
                    shortBookList.setAdapter(listAdapter);
//                    shortBookList.expandGroup(0);
                    

                    /*mAdapter = new ShortBookProductsListAdapter(getActivity(), mShortBookList);
                    LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    shortBookList.setLayoutManager(linearlayoutManager);
                    shortBookList.setItemAnimator(new DefaultItemAnimator());
                    shortBookList.setAdapter(mAdapter);*/
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
    @OnClick({R.id.fromDateMainLayout, R.id.sortByButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fromDateMainLayout:

                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

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
        }
    }
}