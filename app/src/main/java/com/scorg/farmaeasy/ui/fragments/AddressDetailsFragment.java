package com.scorg.farmaeasy.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.helpers.addressdetails.AddressDetailsHelper;
import com.scorg.farmaeasy.interfaces.CustomResponse;
import com.scorg.farmaeasy.interfaces.HelperResponse;
import com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress.DoctorAddressResponseModel;
import com.scorg.farmaeasy.model.responseModel.addressdetailscommonaddress.PatientAddressResponseModel;
import com.scorg.farmaeasy.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.scorg.farmaeasy.util.Constants.SUCCESS;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddressDetailsFragment extends Fragment implements HelperResponse {

    @BindView(R.id.patientName)
    TextView patientName;
    @BindView(R.id.editTextPatientName)
    EditText editTextPatientName;
    @BindView(R.id.patientAddress)
    TextView patientAddress;
    @BindView(R.id.editTextPatientAddress)
    AutoCompleteTextView editTextPatientAddress;
    @BindView(R.id.patientMobileNo)
    TextView patientMobileNo;
    @BindView(R.id.editTextPatientMobileNo)
    EditText editTextPatientMobileNo;
    @BindView(R.id.patientGstNo)
    TextView patientGstNo;
    @BindView(R.id.editTextPatientGstNo)
    EditText editTextPatientGstNo;
    @BindView(R.id.doctorName)
    TextView doctorName;
    @BindView(R.id.editTextDoctorName)
    EditText editTextDoctorName;
    @BindView(R.id.doctorClinicAddress)
    TextView doctorClinicAddress;
    @BindView(R.id.editTextDoctorClinicAddress)
    AutoCompleteTextView editTextDoctorClinicAddress;
    @BindView(R.id.doctorMobileNo)
    TextView doctorMobileNo;
    @BindView(R.id.editTextDoctorMobileNo)
    EditText editTextDoctorMobileNo;
    Unbinder unbinder;

    private AddressDetailsHelper addressDetailsHelper;

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
        unbinder = ButterKnife.bind(this, rootView);
        addressDetailsHelper = new AddressDetailsHelper(getContext(), this);

        editTextPatientAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty())
                    addressDetailsHelper.doPatientAddress(s.toString());

            }
        });

        editTextDoctorClinicAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty())
                    addressDetailsHelper.doDoctorAddress(s.toString());

            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(String mOldDataTag, CustomResponse customResponse) {
        switch (mOldDataTag) {
            case Constants.TASK_ADDRESSDETAILS_PATIENTADDRESS: {
                PatientAddressResponseModel doctorAddressResponseModel = (PatientAddressResponseModel) customResponse;
                if (doctorAddressResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                    // Creating the instance of ArrayAdapter containing list of fruit names
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.sorting_dropdown_item, doctorAddressResponseModel.getData().getAddressList());
                    editTextPatientAddress.setThreshold(1);//will start working from first character
                    editTextPatientAddress.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    editTextPatientAddress.showDropDown();
                }
            }

            break;

            case Constants.TASK_ADDRESSDETAILS_DOCTORADDRESS: {
                DoctorAddressResponseModel doctorAddressResponseModel = (DoctorAddressResponseModel) customResponse;
                if (doctorAddressResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                    // Creating the instance of ArrayAdapter containing list of fruit names
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.sorting_dropdown_item, doctorAddressResponseModel.getData().getAddressList());
                    editTextDoctorClinicAddress.setThreshold(1);//will start working from first character
                    editTextDoctorClinicAddress.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    editTextDoctorClinicAddress.showDropDown();
                }
            }
            break;

        }
    }

    @Override
    public void onParseError(String mOldDataTag, String errorMessage) {

    }

    @Override
    public void onServerError(String mOldDataTag, String serverErrorMessage) {

    }

    @Override
    public void onNoConnectionError(String mOldDataTag, String serverErrorMessage) {

    }
}