package com.scorg.farmaeasy.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.model.requestModel.daybook.DayBookRequestModel;

import java.io.IOException;
import java.io.InputStream;

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
        String json = loadJSONFromAsset("batchList.json");
//        new Gson().fromJson(json, DayBookRequestModel.class);
        return rootView;
    }

    public String loadJSONFromAsset(String fileName) {
        String json;
        try {
            InputStream is = getActivity().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}