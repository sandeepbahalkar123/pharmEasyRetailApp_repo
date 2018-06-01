package com.scorg.farmaeasy.adapter.product;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.model.responseModel.productsearch.ProductList;
import com.scorg.farmaeasy.util.CommonMethods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<ProductList> productParentList;

    public ProductExpandableListAdapter(Context context, ArrayList<ProductList> productParentList) {
        this._context = context;
        this.productParentList = productParentList;
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
        viewHolderChild.batchInfo.setText("Batch No : " + batchList.getBatchNumber());
        viewHolderChild.editTextQty.setText("0");
        viewHolderChild.estimatedPriceValue.setText(" Rs "+String.valueOf(batchList.getSaleRate()));
        viewHolderChild.expiryDateInfo.setText(batchList.getProdCompShortName()+" - "+batchList.getExpiry()+" - ");
        viewHolderChild.packingType.setText(batchList.getProdpack());
        viewHolderChild.stock.setText(String.valueOf(batchList.getClosingStock())); // hardcoded

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
        viewHolderParent.productname.setText(productList.getProductName());
        viewHolderParent.contentInfo.setText("XYZ");

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

        ViewHolderParent(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderChild {
        @BindView(R.id.batchInfo)
        TextView batchInfo;
        @BindView(R.id.editTextQty)
        EditText editTextQty;
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

        ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }

}