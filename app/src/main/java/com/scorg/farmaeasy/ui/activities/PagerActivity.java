package com.scorg.farmaeasy.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.helpers.sale.SaleHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.requestModel.sale.Billing;
import com.scorg.farmaeasy.model.requestModel.sale.SaleRequestModel;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.model.responseModel.sale.SaleResponseModel;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.ui.fragments.AddressDetailsFragment;
import com.scorg.farmaeasy.ui.fragments.BillingFragment;
import com.scorg.farmaeasy.ui.fragments.ProductFragment;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.scorg.farmaeasy.util.Constants.SUCCESS;

public class PagerActivity extends AppCompatActivity implements ProductFragment.OnProductFragmentInteraction, BillingFragment.PagerActivityInteraction, HelperResponse {

    public static final String PRODUCTID = "productid";
    public static final String COLLECTEDPRODUCTSLIST = "collectedproductslist";
    public static final String FROM_HOME_ACTIVITY = "from_home_activity";
    private static final String TAG = "PagerActivity";

    private Context mContext;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fixedBottomHomeDelLayout)
    RelativeLayout fixedBottomHomeDelLayout;
    @BindView(R.id.fixedBottomLayout)
    LinearLayout fixedBottomLayout;

    @BindView(R.id.coachmark)
    ImageView coachmark;

    @BindView(R.id.container)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.totalUnits)
    TextView totalUnits;
    @BindView(R.id.totalAmount)
    TextView totalAmount;
    @BindView(R.id.fixedBottomAmtLayout)
    LinearLayout fixedBottomAmtLayout;
    @BindView(R.id.homeDeliveryCheckbBox)
    CheckBox homeDeliveryCheckbBox;

    private ProductFragment productFragment;
    private AddressDetailsFragment addressDetailsFragment;
    private BillingFragment billingFragment;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private int[] tabIcons = {
            R.drawable.product,
            R.drawable.details,
            R.drawable.billing
    };

    private int[] tabIconsSelected = {
            R.drawable.product_selected,
            R.drawable.details_selected,
            R.drawable.billing_selected
    };

    private String[] titles = {
            "Product",
            "Add Details",
            "Billing"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        mContext = this;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titles[0]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        setupViewPager();

        viewPager.setOffscreenPageLimit(3);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                ImageView tabIconOne = customView.findViewById(R.id.icon);
                tabIconOne.setImageResource(tabIconsSelected[tab.getPosition()]);
                tabIconOne.setBackgroundResource(R.drawable.selected_back);
                getSupportActionBar().setTitle(titles[tab.getPosition()]);

                if (tab.getPosition() == 2) {
                    fixedBottomAmtLayout.setVisibility(View.GONE);
                    fixedBottomHomeDelLayout.setVisibility(View.VISIBLE);
                    billingFragment.setProducts(productFragment.getProducts());
                    CommonMethods.hideKeyboard(PagerActivity.this);
                    if (isProductQTYValid())
                        addressDetailsValidation();
                    else {
                        setPagerPosition(0);
                        CommonMethods.showToast(mContext, mContext.getString(R.string.please_select_atleast_single_product_quantity));
                    }
                } else if (tab.getPosition() == 1) {

                    fixedBottomAmtLayout.setVisibility(View.VISIBLE);
                    fixedBottomHomeDelLayout.setVisibility(View.GONE);

                    if (!isProductQTYValid()) {
                        setPagerPosition(0);
                        CommonMethods.showToast(mContext, mContext.getString(R.string.please_select_atleast_single_product_quantity));
                    }
                } else {
                    fixedBottomAmtLayout.setVisibility(View.VISIBLE);
                    fixedBottomHomeDelLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                ImageView tabIconOne = customView.findViewById(R.id.icon);
                tabIconOne.setImageResource(tabIcons[tab.getPosition()]);
                tabIconOne.setBackgroundResource(R.drawable.deselected_back);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        coachmarkFun();
    }

    private void coachmarkFun() {
        if (!PreferencesManager.getBoolean(Constants.COACHMARK_SAVE, mContext)) {
            coachmark.setVisibility(View.VISIBLE);
            PreferencesManager.putBoolean(Constants.COACHMARK_SAVE, true, mContext);
        } else coachmark.setVisibility(View.GONE);
        coachmark.setOnClickListener(v -> coachmark.setVisibility(View.GONE));
    }

    private boolean isProductQTYValid() {
        boolean isValid = false;
        for (ProductList productList : productFragment.getProducts()) {
            for (BatchList batchList : productList.getBatchList()) {
                if (batchList.getSaleQTY() > 0)
                    isValid = true;
            }
        }
        return isValid;
    }

    private void addressDetailsValidation() {
        if (addressDetailsFragment.getDetails().getPatient().getPatientName() == null) {
            CommonMethods.showToast(mContext, mContext.getString(R.string.please_enter_patient_name));
            setPagerPosition(1);
        } else if (addressDetailsFragment.getDetails().getPatient().getPatientName().equals("")) {
            CommonMethods.showToast(mContext, mContext.getString(R.string.please_enter_patient_name));
            setPagerPosition(1);
        } else if (addressDetailsFragment.getDetails().getDoctor().getDoctorName() == null) {
            CommonMethods.showToast(mContext, mContext.getString(R.string.please_enter_doctor_name));
            setPagerPosition(1);
        } else if (addressDetailsFragment.getDetails().getDoctor().getDoctorName().equals("")) {
            CommonMethods.showToast(mContext, mContext.getString(R.string.please_enter_doctor_name));
            setPagerPosition(1);
        }
    }

    private void setPagerPosition(int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(position);
            }
        }, 50);
    }

    @Override
    public void callApi(Billing billing) {
        double maxDiscountLimit = Double.parseDouble(PreferencesManager.getString(Constants.DISCOUNT_LIMIT, mContext));

        if (billingFragment.getDiscountValue() <= maxDiscountLimit) {
            SaleRequestModel saleRequestModel = addressDetailsFragment.getDetails();
            saleRequestModel.setProductList(productFragment.getProducts());
            billing.setIsHomeDelivery(homeDeliveryCheckbBox.isChecked());
            saleRequestModel.setBilling(billing);

            // call Api
            SaleHelper saleHelper = new SaleHelper(mContext, this);
            saleHelper.doPostSaleData(saleRequestModel);
        } else
            CommonMethods.showToast(mContext, mContext.getString(R.string.dicountValidation) + maxDiscountLimit);

    }


    private void setupTabIcons() {

        View tabOne = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ImageView tabIconOne = tabOne.findViewById(R.id.icon);
        tabIconOne.setImageResource(tabIconsSelected[0]);
        tabIconOne.setBackgroundResource(R.drawable.selected_back);
        View leftViewOne = tabOne.findViewById(R.id.leftView);
        leftViewOne.setVisibility(View.INVISIBLE);
        View rightViewOne = tabOne.findViewById(R.id.rightView);
        tabs.getTabAt(0).setCustomView(tabOne);

        View tabTwo = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ImageView tabIconTwo = tabTwo.findViewById(R.id.icon);
        tabIconTwo.setImageResource(tabIcons[1]);
        tabIconTwo.setBackgroundResource(R.drawable.deselected_back);
        View leftViewTwo = tabTwo.findViewById(R.id.leftView);
        View rightViewTwo = tabTwo.findViewById(R.id.rightView);
        tabs.getTabAt(1).setCustomView(tabTwo);

        View tabThree = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ImageView tabIconThree = tabThree.findViewById(R.id.icon);
        tabIconThree.setImageResource(tabIcons[2]);
        tabIconThree.setBackgroundResource(R.drawable.deselected_back);
        View leftViewThree = tabThree.findViewById(R.id.leftView);
        View rightViewThree = tabThree.findViewById(R.id.rightView);
        rightViewThree.setVisibility(View.INVISIBLE);
        tabs.getTabAt(2).setCustomView(tabThree);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        productFragment = ProductFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCTID, getIntent().getStringExtra(PRODUCTID));
        bundle.putParcelableArrayList(COLLECTEDPRODUCTSLIST, getIntent().getParcelableArrayListExtra(COLLECTEDPRODUCTSLIST));
        productFragment.setArguments(bundle);
        adapter.addFragment(productFragment, "Product");

        addressDetailsFragment = AddressDetailsFragment.newInstance();
        adapter.addFragment(addressDetailsFragment, "Add Details");

        billingFragment = BillingFragment.newInstance();
        adapter.addFragment(billingFragment, "Billing");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void setTotalAmount(double amount, ArrayList<ProductList> productLists) {
        DecimalFormat precision;
        if (amount != 0.0) {
            precision = new DecimalFormat("##,##,###.00");
        } else {
            precision = new DecimalFormat("##,##,###0.00");
        }
        totalAmount.setText(getString(R.string.total_with_rs) + " " + precision.format(amount));
        CommonMethods.Log(TAG, "productLists.size()>>>>>>" + productLists.size());
        getIndividualProductTotalBatchDetails(productLists);
    }

    @Override
    public void setTotalProducts(int size) {
        totalUnits.setText(String.valueOf(size) + " " + getString(R.string.products));

    }

    private void getIndividualProductTotalBatchDetails(ArrayList<ProductList> productList) {
        Integer batchListqty;
        double batchListamount;
        for (ProductList productList1 : productList) {
            batchListqty = 0;
            batchListamount = 0.0;
            for (BatchList batchList : productList1.getBatchList()) {
                batchListqty += batchList.getSaleQTY();
                batchListamount += batchList.getSaleRate() * ((double) batchList.getSaleQTY() / (double) productList1.getProdLoosePack());
            }
            productList1.setIndividualProductTotalBatchQty(batchListqty);
            productList1.setIndividualProductTotalBatchAmount(batchListamount);
        }

    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equals(Constants.TASK_POST_SALE)) {
            SaleResponseModel saleResponseModel = (SaleResponseModel) customResponse;
            if (saleResponseModel.getCommon().getStatusCode().equals(SUCCESS))
                showInputDialog(saleResponseModel.getCommon().getStatusMessage());
            else
                CommonMethods.showToast(mContext, saleResponseModel.getCommon().getStatusMessage());
        }
    }

    public void showInputDialog(String message) {
        final Dialog dialog = new Dialog(mContext);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.message_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView messageText = ((TextView) dialog.findViewById(R.id.textViewDialogMessage));
        messageText.setText("Bill Status\n\n" + message);

        dialog.findViewById(R.id.button_ok).setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(mContext, HomeActivity.class);
            startActivity(intent);
            finishAffinity();
        });

        dialog.show();

    }

    @Override
    public void onParseError(String mOldDataTag, String errorMessage) {

    }

    @Override
    public void onServerError(String mOldDataTag, String serverErrorMessage) {

    }

    @Override
    public void onNoConnectionError(String mOldDataTag, String serverErrorMessage) {

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


}
