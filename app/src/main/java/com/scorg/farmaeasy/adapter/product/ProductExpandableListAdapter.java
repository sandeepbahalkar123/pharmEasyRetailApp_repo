package com.scorg.farmaeasy.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.ui.fragments.ProductFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductExpandableListAdapter extends BaseExpandableListAdapter {

    private final OnItemClickListener onItemClickListener;
    private Context _context;
    private ArrayList<ProductList> productParentList;

    public ProductExpandableListAdapter(Context context, ArrayList<ProductList> productParentList, OnItemClickListener onItemClickListener) {
        this._context = context;
        this.productParentList = productParentList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.productParentList.get(groupPosition).getBatchList().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final BatchList batchList = (BatchList) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.products_childlistitem, null);
            ViewHolderChild viewHolderChild = new ViewHolderChild(convertView);
            convertView.setTag(viewHolderChild);
        }
        ViewHolderChild viewHolderChild = (ViewHolderChild) convertView.getTag();
        viewHolderChild.batchInfo.setText("B No : " + batchList.getBatchNumber());
        viewHolderChild.editTextQty.setText(String.valueOf(batchList.getSaleQTY()));
        viewHolderChild.estimatedPriceValue.setText(" Rs " + String.valueOf(batchList.getSaleRate()));
        viewHolderChild.expiryDateInfo.setText(batchList.getProdCompShortName() + " - " + batchList.getExpiry() + " - ");
        viewHolderChild.packingType.setText(batchList.getProdpack());
        viewHolderChild.stock.setText(String.valueOf(batchList.getClosingStock())); // hardcoded
        viewHolderChild.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onQuantityClick(batchList);
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.productParentList.get(groupPosition).getBatchList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.productParentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.productParentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ProductList productList = (ProductList) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.products_parentlistitem, null);

            ViewHolderParent viewHolderParent = new ViewHolderParent(convertView);
            convertView.setTag(viewHolderParent);
        }
        ViewHolderParent viewHolderParent = (ViewHolderParent) convertView.getTag();

        viewHolderParent.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.removeItem(groupPosition);
            }
        });

        if (productList.isLongPressed())
            viewHolderParent.deleteButton.setVisibility(View.VISIBLE);
        else viewHolderParent.deleteButton.setVisibility(View.INVISIBLE);

        viewHolderParent.productname.setText(productList.getProductName());
        viewHolderParent.contentInfo.setText(productList.getDrugInfo());
        viewHolderParent.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                productList.setLongPressed(!productList.isLongPressed());
                notifyDataSetChanged();
                return true;
            }
        });

        viewHolderParent.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.expand(groupPosition);
            }
        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolderParent {
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.contentInfo)
        TextView contentInfo;
        @BindView(R.id.qty)
        TextView qty;
        @BindView(R.id.stock)
        TextView stock;
        @BindView(R.id.deleteButton)
        ImageButton deleteButton;
        View view;

        ViewHolderParent(View view) {
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    static class ViewHolderChild {
        @BindView(R.id.batchInfo)
        TextView batchInfo;
        @BindView(R.id.editTextQty)
        TextView editTextQty;
        @BindView(R.id.stock)
        TextView stock;
        @BindView(R.id.expiryDateInfo)
        TextView expiryDateInfo;
        @BindView(R.id.packingType)
        TextView packingType;
        @BindView(R.id.estimatedPrice)
        TextView estimatedPrice;
        @BindView(R.id.estimatedPriceValue)
        TextView estimatedPriceValue;
        @BindView(R.id.mainLayout)
        LinearLayout mainLayout;


        ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onQuantityClick(BatchList batchList);
        void expand(int index);
        void removeItem(int index);
    }

}