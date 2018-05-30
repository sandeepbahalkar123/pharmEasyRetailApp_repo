package com.scorg.farmaeasy.adapter.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.product.ProductList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillingProductsListAdapter extends RecyclerView.Adapter<BillingProductsListAdapter.ListViewHolder> {

    private List<ProductList> shortBookLists;
    private Context mContext;

    public BillingProductsListAdapter(Context mContext, List<ProductList> shortBookLists) {
        this.shortBookLists = shortBookLists;
        this.mContext = mContext;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_billing_products_listitems, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ProductList shortBookList = shortBookLists.get(position);
        holder.productName.setText(shortBookList.getProductName());
        holder.quantity.setText(String.valueOf(shortBookList.getTotalQTY()));
        holder.amount.setText(String.valueOf(shortBookList.getRateInfo()));
    }

    @Override
    public int getItemCount() {
        return shortBookLists.size();
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