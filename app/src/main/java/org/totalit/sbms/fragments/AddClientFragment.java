package org.totalit.sbms.fragments;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.totalit.sbms.ClassImplements.SpinnerOnItemSelectedListener;
import org.totalit.sbms.Database.AbstractDb;
import org.totalit.sbms.Database.AppDatabase;
import org.totalit.sbms.Database.InstantiateDb;
import org.totalit.sbms.R;
import org.totalit.sbms.Utils.DateUtil;
import org.totalit.sbms.Utils.Validations;
import org.totalit.sbms.domain.Client;

import java.util.Date;

public class AddClientFragment extends Fragment {

    private EditText inputName, inputEmail, inputDescription, inputPhone, inputAddress, inputWebsite;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutDescription, inputLayoutPhone, inputLayoutAddress, inputLayoutWebsite;
    private Button saveClient;
    private Spinner branchSpinner;
    String companyOrIndividual;
    RadioGroup clientType;
    RadioButton radioCom, radioInd;


    AbstractDb mdb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addclients,container, false);
              mdb = InstantiateDb.sbmsDb(getActivity().getApplicationContext());

            inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
            inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
            inputLayoutDescription = (TextInputLayout) view.findViewById(R.id.input_layout_description);
            inputLayoutPhone = (TextInputLayout) view.findViewById(R.id.input_layout_phone);
            inputLayoutAddress = (TextInputLayout) view.findViewById(R.id.input_layout_address);
            inputLayoutWebsite = (TextInputLayout) view.findViewById(R.id.input_layout_website);
            inputName = (EditText)view.findViewById(R.id.input_name);
            inputEmail = (EditText)view.findViewById(R.id.input_email);
            inputDescription = (EditText)view.findViewById(R.id.input_description);
            inputPhone = (EditText)view.findViewById(R.id.input_phone);
            inputAddress = (EditText)view.findViewById(R.id.input_address);
            inputWebsite = (EditText)view.findViewById(R.id.input_website);

           clientType = view.findViewById(R.id.client_type_radio_group);
           saveClient = (Button)view.findViewById(R.id.saveClientBtn);
           radioCom = view.findViewById(R.id.radio_company);
           radioInd = view.findViewById(R.id.radio_individual);
           radioCom.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   companyOrIndividual = radioCom.getText().toString();
               }
           });
           radioInd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   companyOrIndividual = radioInd.getText().toString();
               }
           });
          branchListenerr(view);
          saveClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveClientClicked(view);

            }
        });

        return view;
    }



    private void branchListenerr(View view){
        branchSpinner = view.findViewById(R.id.branch_spinner);
        branchSpinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener());
    }

    private void saveClientClicked(View view){
        if ( !validateRadio() | !validateName() | !validateEmail() ) {
            return;
        }

       else {
            Date dateNow = new Date();
            final Client client = new Client();
            client.setName(inputName.getText().toString());
            client.setClientType(companyOrIndividual);
            client.setAddress(inputAddress.getText().toString());
            client.setBranch(branchSpinner.getSelectedItem().toString());
            client.setDescription(inputDescription.getText().toString());
            client.setEmail(inputEmail.getText().toString());
            client.setDescription(inputDescription.getText().toString());
            client.setWebsite(inputWebsite.getText().toString());
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    mdb.clientRepository().insertUser(client);

                }
            });





         Fragment fragment = null;
            Bundle bundle = new Bundle();
            if (branchSpinner.getSelectedItem().equals("Yes")) {
                fragment = new BranchFragment();

            } else {
                fragment = new ProcumentDocsFragment();
            }


            if (fragment != null) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
        }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateRadio(){
        if(clientType.getCheckedRadioButtonId()==-1){
            Toast.makeText(getActivity().getApplicationContext(), "Please Select Client Type", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !Validations.isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }




  private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;

            }
        }
    }
}
