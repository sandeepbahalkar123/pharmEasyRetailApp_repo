package com.scorg.farmaeasy.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.scorg.farmaeasy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @BindView(R.id.searchTextView)
    AppCompatEditText searchTextView;
    @BindView(R.id.clearButton)
    ImageButton clearButton;
    @BindView(R.id.searchView)
    View searchView;
    @BindView(R.id.searchBackButton)
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

    }


    @OnClick({R.id.clearButton, R.id.searchBackButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clearButton:
                searchTextView.setText("");
                break;
            case R.id.searchBackButton:
                if (searchTextView.getText().toString().isEmpty()) {
                    searchView.setVisibility(View.GONE);
                    toolbar.setVisibility(View.VISIBLE);
                } else searchTextView.setText("");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                toolbar.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
                searchTextView.requestFocus();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

}
