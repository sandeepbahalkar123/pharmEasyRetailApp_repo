package com.scorg.farmaeasy.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.product.ProductList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<ProductList> productList;
    private List<ProductList> productParentList;

    public ProductExpandableListAdapter(Context context, List<ProductList> productParentList, List<ProductList> productList) {
        this._context = context;
        this.productList = productList;
        this.productParentList = productParentList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.productList.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ProductList productList = (ProductList) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.products_childlistitem, null);
            ViewHolderChild viewHolderChild = new ViewHolderChild(convertView);
            convertView.setTag(viewHolderChild);
        }

        ViewHolderChild viewHolderChild = (ViewHolderChild) convertView.getTag();
        viewHolderChild.batchInfo.setText("Batch No : " + productList.getBatchInfo());
        viewHolderChild.editTextQty.setText(String.valueOf(productList.getTotalQTY()));
        viewHolderChild.estimatedPriceValue.setText(String.valueOf(productList.getRateInfo()));
        viewHolderChild.expiryDateInfo.setText(productList.getExpiryDateInfo());
        viewHolderChild.stock.setText("400"); // hardcoded

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.productList
                .size();
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
        viewHolderParent.contentInfo.setText(productList.getContent());

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