package com.scorg.farmaeasy.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.adapter.product.ProductExpandableListAdapter;
import com.scorg.farmaeasy.helpers.batchlist.BatchListHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchListResponseModel;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.ui.activities.ProductsActivity;
import com.scorg.farmaeasy.util.CommonMethods;
import com.scorg.farmaeasy.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.scorg.farmaeasy.ui.activities.PagerActivity.COLLECTEDPRODUCTSLIST;
import static com.scorg.farmaeasy.ui.activities.PagerActivity.EXISTING_BARCODE_INDEX;
import static com.scorg.farmaeasy.ui.activities.PagerActivity.FROM_BARCODE;
import static com.scorg.farmaeasy.ui.activities.PagerActivity.IS_ALREADYEXISTS;
import static com.scorg.farmaeasy.ui.activities.PagerActivity.PRODUCTID;
import static com.scorg.farmaeasy.util.Constants.SUCCESS;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductFragment extends Fragment implements HelperResponse {


    private static final String TAG = "ProductFragment";
    public static final String PRODUCT_LIST = "product_list";

    @BindView(R.id.productList)
    ExpandableListView productListExpand;
    @BindView(R.id.addProducts)
    ImageView addProducts;
    @BindView(R.id.noRecordsFound)
    TextView noRecordsFound;

    Unbinder unbinder;

    private ArrayList<ProductList> productParentList = new ArrayList<>();
    private BatchListHelper batchListHelper;
    private ProductExpandableListAdapter expandableListAdapter;
    private OnProductFragmentInteraction onProductFragmentInteraction;
    private int lastExpanded = 0;

    public ProductFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProductFragment newInstance(String productId, boolean isFromBarcode, ArrayList<ProductList> productLists) {
        ProductFragment productFragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCTID, productId);
        bundle.putBoolean(FROM_BARCODE, isFromBarcode);
        bundle.putParcelableArrayList(COLLECTEDPRODUCTSLIST, productLists);
        productFragment.setArguments(bundle);
        return productFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        productParentList = getArguments().getParcelableArrayList(COLLECTEDPRODUCTSLIST);
        batchListHelper = new BatchListHelper(getActivity(), this);

        if (!getArguments().getBoolean(FROM_BARCODE))
            batchListHelper.doBatchList(getArguments().getString(PRODUCTID));
        else {
            setMRPValues();
            setProductExpandableListToAdapter(productParentList.size() - 1);
        }

        return rootView;
    }

    private void setProductExpandableListToAdapter(int indexOfLastUpdated) {
        if (expandableListAdapter == null) {
            expandableListAdapter = new ProductExpandableListAdapter(getContext(), productParentList, new ProductExpandableListAdapter.OnItemClickListener() {
                @Override
                public void onQuantityClick(ProductList productList, BatchList batchList) {
                    showInputDialog(productList, batchList);
                }

                @Override
                public void expand(int index) {
                    if (productListExpand.isGroupExpanded(index))
                        productListExpand.collapseGroup(index);
                    else {
                        if (lastExpanded != index)
                            productListExpand.collapseGroup(lastExpanded);
                        productListExpand.expandGroup(index);
                        lastExpanded = index;
                    }
                }

                @Override
                public void removeItem(int index) {
                    productParentList.remove(index);
                    expandableListAdapter.notifyDataSetChanged();
                    onProductFragmentInteraction.setTotalProducts(productParentList.size());
                    onProductFragmentInteraction.setTotalAmount(getTotalAmount(), productParentList);
                    setRecordsMessage();
                }
            });
            // setting list adapter
            productListExpand.setAdapter(expandableListAdapter);
            productListExpand.expandGroup(0);
        } else {
            expandableListAdapter.notifyDataSetChanged();
            if (indexOfLastUpdated != -1) {
                productListExpand.collapseGroup(lastExpanded);
                productListExpand.expandGroup(indexOfLastUpdated);
                productListExpand.smoothScrollToPosition(indexOfLastUpdated);
            }
        }
        onProductFragmentInteraction.setTotalProducts(productParentList.size());
        onProductFragmentInteraction.setTotalAmount(getTotalAmount(), productParentList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        if (mOldDataTag.equalsIgnoreCase(Constants.TASK_BATCHLIST)) {
            // After login user navigated to HomeActivity
            BatchListResponseModel receivedModel = (BatchListResponseModel) customResponse;
            if (receivedModel.getCommon().getStatusCode().equals(SUCCESS)) {
                ArrayList<BatchList> productChildList = receivedModel.getData().getBatchList();
                productParentList.get(productParentList.size() - 1).setBatchList(productChildList);
                setProductExpandableListToAdapter(productParentList.size() - 1);
            }
        }
    }

    private void setRecordsMessage() {
        if (productParentList.isEmpty()) {
            productListExpand.setVisibility(View.GONE);
            noRecordsFound.setVisibility(View.VISIBLE);
        } else {
            noRecordsFound.setVisibility(View.GONE);
            productListExpand.setVisibility(View.VISIBLE);
        }
    }

    private void showInputDialog(ProductList productList, BatchList batchList) {
        CommonMethods.showInputDialog(getContext(), getString(R.string.enter_quantity_message), batchList, new DialogInputListener() {
            @Override
            public void inputValue(int value) {
                batchList.setSaleQTY(value);
                batchList.setMrp(batchList.getSaleRate() / (double) productList.getProdLoosePack() * (double) value);
                expandableListAdapter.notifyDataSetChanged();
                onProductFragmentInteraction.setTotalAmount(getTotalAmount(), productParentList);
            }
        });
    }


    public ArrayList<ProductList> getProducts() {
        return productParentList;
    }

    public interface DialogInputListener {
        void inputValue(int value);
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

    @OnClick(R.id.addProducts)
    public void onViewClicked() {
        CommonMethods.Log(TAG, "addProducts clicked");
        Intent intent = new Intent(getActivity(), ProductsActivity.class);
        intent.putExtra(PRODUCT_LIST, productParentList);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                if (!data.getBooleanExtra(FROM_BARCODE, false)) {
                    productParentList.add((ProductList) data.getParcelableArrayListExtra(COLLECTEDPRODUCTSLIST).get(0));
                    batchListHelper.doBatchList(data.getStringExtra(PRODUCTID));
                } else {

                    if (data.getBooleanExtra(IS_ALREADYEXISTS, false)) {
                        productParentList.clear();
                        productParentList.addAll(data.getParcelableArrayListExtra(COLLECTEDPRODUCTSLIST));
                        int index = data.getIntExtra(EXISTING_BARCODE_INDEX, -1);
                        setProductExpandableListToAdapter(index);
                    } else {
                        productParentList.add((ProductList) data.getParcelableArrayListExtra(COLLECTEDPRODUCTSLIST).get(0));
                        setProductExpandableListToAdapter(productParentList.size() - 1);
                    }

                    setMRPValues();
                }

                setRecordsMessage();
            }
        }
    }

    private void setMRPValues() {
        for (ProductList productList : productParentList) {
            for (BatchList batchList1 : productList.getBatchList()) {
                batchList1.setMrp((batchList1.getSaleRate() / (double) productList.getProdLoosePack()) * (double) batchList1.getSaleQTY());
            }
        }
    }


    private double getTotalAmount() {
        CommonMethods.Log(TAG, "productParentList.size():" + productParentList.size());
        double totalValue = 0.0;
        for (ProductList productList : productParentList) {
            for (BatchList batchList : productList.getBatchList()) {
                totalValue += batchList.getSaleRate() * ((double) batchList.getSaleQTY() / (double) productList.getProdLoosePack());
            }
        }

        return totalValue;
    }

    public interface OnProductFragmentInteraction {
        void setTotalAmount(double amount, ArrayList<ProductList> productLists);

        void setTotalProducts(int size);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProductFragmentInteraction) {
            onProductFragmentInteraction = (OnProductFragmentInteraction) context;
        }
    }
}