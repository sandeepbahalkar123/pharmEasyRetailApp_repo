package com.scorg.farmaeasy.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.requestModel.sale.Billing;
import com.scorg.farmaeasy.model.requestModel.sale.Details;
import com.scorg.farmaeasy.model.requestModel.sale.SaleRequestModel;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.ui.fragments.AddressDetailsFragment;
import com.scorg.farmaeasy.ui.fragments.BillingFragment;
import com.scorg.farmaeasy.ui.fragments.ProductFragment;
import com.scorg.farmaeasy.util.CommonMethods;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerActivity extends AppCompatActivity implements ProductFragment.OnProductFragmentInteraction {

    public static final String PRODUCTID = "productid";
    public static final String COLLECTEDPRODUCTSLIST = "collectedproductslist";
    public static final String FROM_HOME_ACTIVITY = "from_home_activity";
    public static final String TOTALPRODUCTSLIST = "totalproductslist";
    public static final String HOMEDELIVERYFLAG = "homedeliveryflag";
    private static final String TAG = "PagerActivity";

    @BindView(R.id.fixedBottomHomeDelLayout)
    RelativeLayout fixedBottomHomeDelLayout;
    @BindView(R.id.fixedBottomLayout)
    LinearLayout fixedBottomLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
    }

    private void callApi(Billing billing) {
        SaleRequestModel saleRequestModel = new SaleRequestModel();
        saleRequestModel.setProductList(productFragment.getProducts());
        saleRequestModel.setDetails(addressDetailsFragment.getDetails());
        billing.setHomeDelivery(homeDeliveryCheckbBox.isChecked());
        saleRequestModel.setBilling(billing);

        // call Api
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
        DecimalFormat precision = new DecimalFormat("#0.00");
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
                batchListamount += batchList.getSaleRate() * batchList.getSaleQTY();
            }
            productList1.setIndividualProductTotalBatchQty(batchListqty);
            productList1.setIndividualProductTotalBatchAmount(batchListamount);
        }

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
