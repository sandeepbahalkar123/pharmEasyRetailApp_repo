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
import android.widget.ImageView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.ui.fragments.AddDetailsFragment;
import com.scorg.farmaeasy.ui.fragments.BillingFragment;
import com.scorg.farmaeasy.ui.fragments.ProductFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerActivity extends AppCompatActivity {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;
    private TabLayout tabLayout;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titles[0]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                ImageView tabIconOne = customView.findViewById(R.id.icon);
                tabIconOne.setImageResource(tabIconsSelected[tab.getPosition()]);
                tabIconOne.setBackgroundResource(R.drawable.selected_back);
                getSupportActionBar().setTitle(titles[tab.getPosition()]);
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
        tabLayout.getTabAt(0).setCustomView(tabOne);

        View tabTwo = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ImageView tabIconTwo = tabTwo.findViewById(R.id.icon);
        tabIconTwo.setImageResource(tabIcons[1]);
        tabIconTwo.setBackgroundResource(R.drawable.deselected_back);
        View leftViewTwo = tabTwo.findViewById(R.id.leftView);
        View rightViewTwo = tabTwo.findViewById(R.id.rightView);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        View tabThree = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ImageView tabIconThree = tabThree.findViewById(R.id.icon);
        tabIconThree.setImageResource(tabIcons[2]);
        tabIconThree.setBackgroundResource(R.drawable.deselected_back);
        View leftViewThree = tabThree.findViewById(R.id.leftView);
        View rightViewThree = tabThree.findViewById(R.id.rightView);
        rightViewThree.setVisibility(View.INVISIBLE);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ProductFragment.newInstance(), "Product");
        adapter.addFragment(AddDetailsFragment.newInstance(), "Add Details");
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
