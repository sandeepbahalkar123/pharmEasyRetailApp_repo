package com.scorg.farmaeasy.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorg.farmaeasy.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class BillingFragment extends Fragment {

    public BillingFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BillingFragment newInstance() {
        return new BillingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_billing_details, container, false);

        return rootView;
    }
}