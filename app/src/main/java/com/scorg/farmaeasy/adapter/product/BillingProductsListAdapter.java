package com.scorg.farmaeasy.adapter.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillingProductsListAdapter extends RecyclerView.Adapter<BillingProductsListAdapter.ListViewHolder> {

    private ArrayList<ProductList> productLists;
    private Context mContext;
    DecimalFormat precision = new DecimalFormat("#0.00");

    public BillingProductsListAdapter(Context mContext, ArrayList<ProductList> productLists) {
        this.productLists = productLists;
        this.mContext = mContext;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_billing_products_listitems, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ProductList productList = productLists.get(position);
        holder.productName.setText(productList.getProductName());
        holder.quantity.setText(String.valueOf(productList.getIndividualProductTotalBatchQty()));
        holder.amount.setText(precision.format(productList.getIndividualProductTotalBatchAmount()));
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.amount)
        TextView amount;

        ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}