package com.scorg.farmaeasy.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.product.SearchProductsListAdapter;
import com.scorg.farmaeasy.helpers.productsearch.ProductSearchHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductSearchResponseModel;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;
import com.scorg.farmaeasy.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.scorg.farmaeasy.ui.activities.PagerActivity.COLLECTEDPRODUCTSLIST;
import static com.scorg.farmaeasy.ui.activities.PagerActivity.PRODUCTID;
import static com.scorg.farmaeasy.ui.fragments.ProductFragment.PRODUCT_LIST;
import static com.scorg.farmaeasy.util.Constants.SUCCESS;

public class ProductsActivity extends AppCompatActivity implements HelperResponse, SearchProductsListAdapter.ProductClick {

    public static final String PRODUCT_BARCODE = "product_barcode";
    private Context mContext;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.productList)
    RecyclerView productListRecycler;
    @BindView(R.id.searchTextView)
    AppCompatEditText searchTextView;
    @BindView(R.id.clearButton)
    ImageButton clearButton;
    @BindView(R.id.searchView)
    View searchView;
    @BindView(R.id.searchBackButton)
    ImageButton backButton;
    @BindView(R.id.noRecordsFound)
    TextView noRecordsFound;
    @BindView(R.id.barcodeImg)
    ImageView barcodeImg;

    private String searchedString = "";
    private ArrayList<ProductList> productList = new ArrayList<>();
    private ArrayList<ProductList> existingProductList;
    private SearchProductsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        mContext = ProductsActivity.this;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        existingProductList = getIntent().getParcelableArrayListExtra(PRODUCT_LIST);

        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        productListRecycler.setLayoutManager(linearlayoutManager);
        productListRecycler.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new SearchProductsListAdapter(mContext, this.productList, this, searchedString);
        productListRecycler.setAdapter(mAdapter);

        ProductSearchHelper productSearchHelper = new ProductSearchHelper(mContext, this);
        productSearchHelper.doProductSearch("", 0, true);

        searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ProductsActivity.this.productList.clear();
                mAdapter.notifyDataSetChanged();

                if (s.toString().length() > 3)
                    productSearchHelper.doProductSearch(s.toString(), 0, false);
                else if (s.toString().isEmpty()) productSearchHelper.doProductSearch("", 0, false);

                searchedString = s.toString();
            }
        });


        productListRecycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearlayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (searchedString.length() > 3)
                    productSearchHelper.doProductSearch(searchedString, page, false);
                else productSearchHelper.doProductSearch("", page, false);
            }
        });
    }

    @OnClick({R.id.clearButton, R.id.searchBackButton, R.id.barcodeImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clearButton:
                searchTextView.setText("");
                break;
            case R.id.searchBackButton:
                if (searchTextView.getText().toString().isEmpty()) {
                    searchView.setVisibility(View.GONE);
                    toolbar.setVisibility(View.VISIBLE);
                    searchTextView.setEnabled(false);
                    CommonMethods.hideKeyboard(this);
                } else searchTextView.setText("");

               /* if (searchTextView.getText().toString().isEmpty())
                    onBackPressed();
                else searchTextView.setText(""); */

                break;

            case R.id.barcodeImg:
                // Please set here for Barcode opening logic
                Intent intent = new Intent(mContext, BarcodeScannerActivity.class);
                startActivityForResult(intent, 11);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 11) {
                String barcodeString = data.getStringExtra(PRODUCT_BARCODE);
                CommonMethods.showToast(mContext, barcodeString);
            }
        }
    }

    @Override
    public void onClick(String productId, String totalBatch, int position, ProductList productList) {
        ArrayList<ProductList> totalProductList = new ArrayList<>();
        totalProductList.add(productList);

        if (getIntent().getBooleanExtra(PagerActivity.FROM_HOME_ACTIVITY, false)) {
            if (!totalBatch.equalsIgnoreCase("0")) {
                Intent intent = new Intent(mContext, PagerActivity.class);
                intent.putExtra(PRODUCTID, productId);
                intent.putParcelableArrayListExtra(COLLECTEDPRODUCTSLIST, totalProductList);
                startActivity(intent);
            } else
                CommonMethods.showToast(mContext, mContext.getString(R.string.nobatchavailable));

        } else {
            if (!totalBatch.equalsIgnoreCase("0")) {
                if (!isAvailable(productList)) {
                    Intent intent = new Intent();
                    intent.putExtra(PRODUCTID, productId);
                    intent.putParcelableArrayListExtra(COLLECTEDPRODUCTSLIST, totalProductList);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else CommonMethods.showToast(mContext, mContext.getString(R.string.product_already_exist));
            } else
                CommonMethods.showToast(mContext, mContext.getString(R.string.nobatchavailable));
        }
    }

    private boolean isAvailable(ProductList productList) {
        boolean isAvailable = false;
        for (ProductList productList1 : existingProductList) {
            if (productList.getProductID().equals(productList1.getProductID()))
                isAvailable = true;
        }
        return isAvailable;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                toolbar.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
                searchTextView.setEnabled(true);
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

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {

        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_PRODUCT_SEARCH)) {
            //After login user navigated to HomeActivity
            ProductSearchResponseModel receivedModel = (ProductSearchResponseModel) customResponse;
            if (receivedModel.getCommon().getStatusCode().equals(SUCCESS)) {
                productList.addAll(receivedModel.getData().getProductList());
                mAdapter.notifyDataSetChanged();
                productListRecycler.setVisibility(View.VISIBLE);
                noRecordsFound.setVisibility(View.GONE);
            } else {
                if (productList.isEmpty()) {
                    productListRecycler.setVisibility(View.GONE);
                    noRecordsFound.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onParseError(String mOldDataTag, String errorMessage) {
        CommonMethods.showToast(mContext, errorMessage);
    }

    @Override
    public void onServerError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(mContext, serverErrorMessage);
    }

    @Override
    public void onNoConnectionError(String mOldDataTag, String serverErrorMessage) {
        CommonMethods.showToast(mContext, serverErrorMessage);
    }

}
