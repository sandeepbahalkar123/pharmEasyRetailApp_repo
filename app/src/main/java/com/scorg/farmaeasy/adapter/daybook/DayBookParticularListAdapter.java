package com.scorg.farmaeasy.adapter.daybook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.daybook.DayBookList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayBookParticularListAdapter extends RecyclerView.Adapter<DayBookParticularListAdapter.ListViewHolder> {


    private ArrayList<DayBookList> dayBookLists;
    private Context mContext;

    public class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.particularName)
        TextView particularName;
        @BindView(R.id.debitValue)
        TextView debitValue;
        @BindView(R.id.creditValue)
        TextView creditValue;
        View view;

        public ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;

        }
    }


    public DayBookParticularListAdapter(Context mContext, ArrayList<DayBookList> dayBookLists) {
        this.dayBookLists = dayBookLists;
        this.mContext = mContext;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_daybook_listitems, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        DayBookList dayBookList = dayBookLists.get(position);
        holder.particularName.setText(dayBookList.getParticularName());
        if(dayBookList.getDbAmount()!=0)
            holder.debitValue.setText(""+dayBookList.getDbAmount());
        else
            holder.debitValue.setText("");
        if(dayBookList.getCrAmount()!=0)
            holder.creditValue.setText(""+dayBookList.getCrAmount());
        else
            holder.creditValue.setText("");
    }

    @Override
    public int getItemCount() {
        return dayBookLists.size();
    }
}