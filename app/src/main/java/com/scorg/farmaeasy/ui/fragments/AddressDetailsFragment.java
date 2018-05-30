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
public class AddressDetailsFragment extends Fragment {

    public AddressDetailsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddressDetailsFragment newInstance() {
        return new AddressDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_details, container, false);

        return rootView;
    }
}