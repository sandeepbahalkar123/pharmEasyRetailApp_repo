package com.scorg.farmaeasy.adapter.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchProductsListAdapter extends RecyclerView.Adapter<SearchProductsListAdapter.ListViewHolder> {

    private final ProductClick productClick;
    private ArrayList<ProductList> productLists;
    private Context mContext;

    public SearchProductsListAdapter(Context mContext, ArrayList<ProductList> productLists, ProductClick productClick) {
        this.productLists = productLists;
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
        ProductList productList = productLists.get(position);
        holder.productname.setText(productList.getProductName());
        holder.totalQTY.setText("QTY : " + String.valueOf(productList.getTotalQTY()));
        holder.rateInfo.setText("Rs "+String.valueOf(productList.getRateInfo()));
        holder.compShortNameShelfNoInfo.setText(productList.getProdCompShortName()+" - "+productList.getShelfNo());
        holder.batchInfo.setText(productList.getBatchInfo()+" Batch");

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClick.onClick(productList.getProductID(),productList.getBatchInfo(),position,productLists.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.totalQTY)
        TextView totalQTY;
        @BindView(R.id.compShortNameShelfNoInfo)
        TextView compShortNameShelfNoInfo;
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
        void onClick(String productId,String totalBatch,int position,ProductList productList);
    }
}