package com.scorg.farmaeasy.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.scorg.farmaeasy.model.responseModel.addressdetailsdoctordata.DoctorDataResponseModel;
import com.scorg.farmaeasy.model.responseModel.addressdetailsdoctordata.DoctorList;
import com.scorg.farmaeasy.model.responseModel.addressdetailspatientdata.PatientDataResponseModel;
import com.scorg.farmaeasy.model.responseModel.addressdetailspatientdata.PatientList;
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
    AutoCompleteTextView editTextPatientName;
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
    AutoCompleteTextView editTextDoctorName;
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

        editTextPatientName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 2)
                    addressDetailsHelper.doPatientData(s.toString());

            }
        });
        editTextDoctorName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 2)
                    addressDetailsHelper.doPatientData(s.toString());

            }
        });
        editTextPatientAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 2)
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
                if (s.toString().length() > 2)
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
                    editTextPatientAddress.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                }
            }
            break;

            case Constants.TASK_ADDRESSDETAILS_DOCTORADDRESS: {
                DoctorAddressResponseModel doctorAddressResponseModel = (DoctorAddressResponseModel) customResponse;
                if (doctorAddressResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                    // Creating the instance of ArrayAdapter containing list of fruit names
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.sorting_dropdown_item, doctorAddressResponseModel.getData().getAddressList());
                    editTextDoctorClinicAddress.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                }
            }
            break;

            case Constants.TASK_ADDRESSDETAILS_PATIENTDATA: {
                PatientDataResponseModel patientDataResponseModel = (PatientDataResponseModel) customResponse;
                if (patientDataResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                    // Creating the instance of ArrayAdapter containing list of fruit names
                    ArrayAdapter<PatientList> adapter = new ArrayAdapter<PatientList>(getContext(), R.layout.sorting_dropdown_item, patientDataResponseModel.getData().getPatientList());
                    editTextPatientName.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

                    editTextPatientName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            PatientList patientList = patientDataResponseModel.getData().getPatientList().get(position);
                            editTextPatientAddress.setText(patientList.getPatientAddress1());
                            editTextDoctorMobileNo.setText(patientList.getMobileNumberForSMS());
                            editTextPatientGstNo.setText(patientList.getPatientGSTNo());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
            break;

            case Constants.TASK_ADDRESSDETAILS_DOCTORDATA: {
                DoctorDataResponseModel doctorDataResponseModel = (DoctorDataResponseModel) customResponse;
                if (doctorDataResponseModel.getCommon().getStatusCode().equals(SUCCESS)) {
                    // Creating the instance of ArrayAdapter containing list of fruit names
                    ArrayAdapter<DoctorList> adapter = new ArrayAdapter<DoctorList>(getContext(), R.layout.sorting_dropdown_item, doctorDataResponseModel.getData().getDoctorList());
                    editTextDoctorName.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

                    editTextDoctorName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            DoctorList doctorList = doctorDataResponseModel.getData().getDoctorList().get(position);
                            editTextDoctorClinicAddress.setText(doctorList.getDoctorAddress());
                            editTextDoctorMobileNo.setText(doctorList.getMobileNumberForSMS());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
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