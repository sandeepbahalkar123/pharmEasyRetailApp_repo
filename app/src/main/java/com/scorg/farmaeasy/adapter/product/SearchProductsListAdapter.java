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

public class SearchProductsListAdapter extends RecyclerView.Adapter<SearchProductsListAdapter.ListViewHolder> {

    private final ProductClick productClick;
    private List<ProductList> shortBookLists;
    private Context mContext;

    public SearchProductsListAdapter(Context mContext, List<ProductList> shortBookLists, ProductClick productClick) {
        this.shortBookLists = shortBookLists;
        this.mContext = mContext;
        this.productClick = productClick;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_products_listitem, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ProductList shortBookList = shortBookLists.get(position);
        holder.productname.setText(shortBookList.getProductName());
        holder.totalQTY.setText("QTY : " + String.valueOf(shortBookList.getTotalQTY()));
        holder.rateInfo.setText(String.valueOf(shortBookList.getRateInfo()));

        holder.expiryDateInfo.setText(String.valueOf(shortBookList.getExpiryDateInfo()));
        holder.batchInfo.setText(String.valueOf(shortBookList.getBatchInfo()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClick.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shortBookLists.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.totalQTY)
        TextView totalQTY;
        @BindView(R.id.expiryDateInfo)
        TextView expiryDateInfo;
        @BindView(R.id.rateInfo)
        TextView rateInfo;
        @BindView(R.id.batchInfo)
        TextView batchInfo;

        View view;

        ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    public interface ProductClick {
        void onClick(int shortBookList);
    }
}