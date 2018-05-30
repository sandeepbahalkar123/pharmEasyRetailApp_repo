package com.scorg.farmaeasy.adapter.shortbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.responseModel.shortbook.ShortBookList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortBookExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<ShortBookList> shortBookList;
    private List<ShortBookList> productParentList;

    public ShortBookExpandableListAdapter(Context context, List<ShortBookList> productParentList, List<ShortBookList> shortBookList) {
        this._context = context;
        this.shortBookList = shortBookList;
        this.productParentList = productParentList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.shortBookList.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ShortBookList shortBookList = (ShortBookList) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.shortbook_child_listitems, null);
            ViewHolderChild viewHolderChild = new ViewHolderChild(convertView);
            convertView.setTag(viewHolderChild);
        }

        ViewHolderChild viewHolderChild = (ViewHolderChild) convertView.getTag();
        viewHolderChild.productname.setText("Batch No : " + shortBookList.getProdName());
        viewHolderChild.avlStock.setText(String.valueOf(shortBookList.getAvailableStock()));
        viewHolderChild.compShortName.setText(shortBookList.getAccShortName());
        viewHolderChild.noOfQtyTabs.setText(String.valueOf(shortBookList.getSchemeQuantity()));
        viewHolderChild.totalQTY.setText(String.valueOf(shortBookList.getSchemeQuantity()));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.shortBookList
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
        ShortBookList shortBookList = (ShortBookList) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_shortbook_listitems, null);

            ViewHolderParent viewHolderParent = new ViewHolderParent(convertView);
            convertView.setTag(viewHolderParent);
        }

        ViewHolderParent viewHolderParent = (ViewHolderParent) convertView.getTag();
        viewHolderParent.productname.setText(shortBookList.getProdName());
        viewHolderParent.avlStock.setText(String.valueOf(shortBookList.getAvailableStock()));
        viewHolderParent.compShortName.setText(shortBookList.getAccShortName());
        viewHolderParent.noOfQty.setText(String.valueOf(shortBookList.getOrderQuantity()));
//        viewHolderParent.unit.setText();
//        viewHolderParent.totalQTY.setText(shortBookList.getOrderQuantity());


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
        @BindView(R.id.totalQTY)
        TextView totalQTY;
        @BindView(R.id.compShortName)
        TextView compShortName;
        @BindView(R.id.noOfQty)
        TextView noOfQty;
        @BindView(R.id.avlStock)
        TextView avlStock;

        ViewHolderParent(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderChild {
        @BindView(R.id.tabletIcon)
        ImageView tabletIcon;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.totalQTY)
        TextView totalQTY;
        @BindView(R.id.noOfQtyTabs)
        TextView noOfQtyTabs;
        @BindView(R.id.avlStockStrips)
        TextView avlStockStrips;
        @BindView(R.id.compShortName)
        TextView compShortName;
        @BindView(R.id.noOfQty)
        TextView noOfQty;
        @BindView(R.id.avlStock)
        TextView avlStock;

        ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }
}