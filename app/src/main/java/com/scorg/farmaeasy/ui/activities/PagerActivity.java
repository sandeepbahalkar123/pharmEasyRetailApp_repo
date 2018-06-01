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
import com.scorg.farmaeasy.ui.fragments.AddressDetailsFragment;
import com.scorg.farmaeasy.ui.fragments.BillingFragment;
import com.scorg.farmaeasy.ui.fragments.ProductFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerActivity extends AppCompatActivity {
    public static final String INDEX="index";
    public static final String PRODUCTID = "productid";
    public static final String COLLECTEDPRODUCTSLIST = "collectedproductslist";
    public static final String FROM_HOME_ACTIVITY = "from_home_activity";


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

   /* private MenuItem item;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(PagerActivity.this, ProductsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        item = menu.getItem(0);
        return true;
    }*/

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
                } else {
                    fixedBottomAmtLayout.setVisibility(View.VISIBLE);
                    fixedBottomHomeDelLayout.setVisibility(View.GONE);
                }

//                item.setVisible(tab.getPosition() == 0);
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

        ProductFragment productFragment = ProductFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCTID,getIntent().getStringExtra(PRODUCTID));
        bundle.putParcelableArrayList(COLLECTEDPRODUCTSLIST,getIntent().getParcelableArrayListExtra(COLLECTEDPRODUCTSLIST));
        productFragment.setArguments(bundle);
        adapter.addFragment(productFragment, "Product");

        adapter.addFragment(AddressDetailsFragment.newInstance(), "Add Details");

        adapter.addFragment(BillingFragment.newInstance(), "Billing");

        viewPager.setAdapter(adapter);
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
