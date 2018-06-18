package com.scorg.farmaeasy.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.preference.PreferencesManager;
import com.scorg.farmaeasy.ui.fragments.DayBookFragment;
import com.scorg.farmaeasy.ui.fragments.SaleBookFragment;
import com.scorg.farmaeasy.ui.fragments.ShortBookFragment;
import com.scorg.farmaeasy.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BooksActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager viewPager;
    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ButterKnife.bind(this);
        mContext = this;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.SHOPNAME, mContext));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String userType = PreferencesManager.getString(PreferencesManager.PREFERENCES_KEY.USERTYPE, mContext);
        if (Constants.USER_TYPE.Owner.equals(userType))
            adapter.addFragment(DayBookFragment.newInstance(), "DAY BOOK");
        adapter.addFragment(ShortBookFragment.newInstance(), "SHORT BOOK");
//        adapter.addFragment(SaleBookFragment.newInstance(), "SALE BOOK");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

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
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
