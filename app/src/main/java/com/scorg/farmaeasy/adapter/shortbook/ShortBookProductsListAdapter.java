package com.scorg.farmaeasy.adapter.shortbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortBookProductsListAdapter extends RecyclerView.Adapter<ShortBookProductsListAdapter.ListViewHolder> {


    private ArrayList<ShortBookList> shortBookLists;
    private Context mContext;

    public class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.totalQTY)
        TextView totalQTY;
        @BindView(R.id.compShortName)
        TextView compShortName;
        @BindView(R.id.noOfQty)
        TextView noOfQty;
        @BindView(R.id.compShortCodelayout)
        RelativeLayout compShortCodelayout;
        @BindView(R.id.mainlayout)
        LinearLayout mainlayout;
        View view;

        public ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;

        }
    }


    public ShortBookProductsListAdapter(Context mContext, ArrayList<ShortBookList> shortBookLists) {
        this.shortBookLists = shortBookLists;
        this.mContext = mContext;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_shortbook_listitems, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ShortBookList shortBookList = shortBookLists.get(position);
        holder.productname.setText(shortBookList.getProdName());
        holder.totalQTY.setText(String.valueOf(shortBookList.getOrderQuantity()));
        holder.compShortName.setText(shortBookList.getProdCompShortName());
        holder.noOfQty.setText(shortBookList.getProdLoosePack()+" "+shortBookList.getProdpacktype());
    }

    @Override
    public int getItemCount() {
        return shortBookLists.size();
    }
}