package com.scorg.farmaeasy.bottom_menus;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.util.CommonMethods;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomMenuAdapter extends RecyclerView.Adapter<BottomMenuAdapter.ListViewHolder> {

    private OnBottomMenuClickListener mBottomMenuListClickListener;
    private ArrayList<BottomMenu> bottomMenus;

    BottomMenuAdapter(Context mContext, ArrayList<BottomMenu> bottomMenus) {
        this.bottomMenus = bottomMenus;

        try {
            this.mBottomMenuListClickListener = ((OnBottomMenuClickListener) mContext);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement onBottomMenuListClickListener.");
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_menu_item, parent, false);

        int widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
        int width = (widthPixels * 20) / 100;
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = width;

        itemView.setLayoutParams(layoutParams);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        final BottomMenu bottomMenu = bottomMenus.get(position);
        holder.bottomMenuName.setText(bottomMenu.getMenuName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomMenuListClickListener.onBottomMenuClick(bottomMenu);
            }
        });

        holder.menuBottomIcon.setImageDrawable(bottomMenu.getMenuIcon());

//for app logo
        if (bottomMenu.isAppIcon()) {

//            setMargins(holder.menuBottomIcon, 0, CommonMethods.convertDpToPixel(1), 0, 0);
            holder.bottomMenuName.setVisibility(View.GONE);
            holder.bottomMenuTab.setVisibility(View.GONE);

            if (bottomMenu.getNotificationCount() > 0) {
                holder.showCountTextView.setText(String.valueOf(bottomMenu.getNotificationCount()));
                holder.showCountTextView.setVisibility(View.VISIBLE);
            } else
                holder.showCountTextView.setVisibility(View.GONE);

        } else {

            setMargins(holder.menuBottomIcon, 0, CommonMethods.convertDpToPixel(8), 0, 0);
            holder.showCountTextView.setVisibility(View.GONE);
            if (bottomMenu.isSelected()) {
                holder.bottomMenuTab.setVisibility(View.VISIBLE);
                holder.bottomMenuName.setTextColor(holder.bottomMenuName.getContext().getResources().getColor(R.color.tagColor));
                holder.menuBottomIcon.setColorFilter(ContextCompat.getColor(holder.menuBottomIcon.getContext(), R.color.tagColor), PorterDuff.Mode.MULTIPLY);
            } else {
                holder.bottomMenuTab.setVisibility(View.INVISIBLE);
                holder.bottomMenuName.setTextColor(holder.bottomMenuName.getContext().getResources().getColor(R.color.white));
                holder.menuBottomIcon.setColorFilter(ContextCompat.getColor(holder.menuBottomIcon.getContext(), R.color.white), PorterDuff.Mode.MULTIPLY);
            }
        }

    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    @Override
    public int getItemCount() {
        return bottomMenus.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.spaceView)
        View spaceView;
        @BindView(R.id.menuBottomIcon)
        AppCompatImageView menuBottomIcon;
        @BindView(R.id.showCountTextView)
        TextView showCountTextView;
        @BindView(R.id.bottomMenuName)
        TextView bottomMenuName;
        @BindView(R.id.bottomMenuTab)
        TextView bottomMenuTab;
        @BindView(R.id.menuBottomLayout)
        LinearLayout menuBottomLayout;
        View view;

        ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    public interface OnBottomMenuClickListener {

        void onBottomMenuClick(BottomMenu bottomMenu);

        void onProfileImageClick();
    }

}
