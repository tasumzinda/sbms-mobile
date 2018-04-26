package org.totalit.sbms.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.totalit.sbms.R;

public class ProcumentDocsFragment extends Fragment {

    private CheckBox appLetter, companyProfile, certOfinc, mou, crFouthten, vatReg, itfCert, tradeLicense, tracebleRef;
    private Button saveDocuments;

    String appL = "false";
    String comProf = "false";
    String certOf = "false";
    String memoOf = "false";
    String crFour = "false";
    String vatR = "false";
    String itfCertificate = "false";
    String tradeLi = "false";
    String traceR = "false";
    private EditText otherDocs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_procurement_documents,container, false);
        otherDocs = view.findViewById(R.id.input_other);
        saveDocuments = view.findViewById(R.id.saveDocuments);
        saveDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAddInventory(v);
            }
        });

        return view;
    }

    private void checkBoxListerner(View view){
        appLetter = view.findViewById(R.id.app_letter);
        companyProfile = view.findViewById(R.id.com_profile);
        certOfinc = view.findViewById(R.id.cert_of_inc);
        mou = view.findViewById(R.id.mou);
        crFouthten = view.findViewById(R.id.cr14);
        vatReg = view.findViewById(R.id.vat);
        itfCert = view.findViewById(R.id.itf);
        tradeLicense = view.findViewById(R.id.trade_license);
        tracebleRef = view.findViewById(R.id.trace_ref);
        if(appLetter.isChecked()){
            appL = "true";
        }
        if(companyProfile.isChecked()){
            comProf = "true";
        }
        if(certOfinc.isChecked()){
            certOf = "true";
        }
        if(mou.isChecked()){
            memoOf = "true";
        }
        if(crFouthten.isChecked()){
            crFour = "true";
        }
        if(vatReg.isChecked()){
            vatR = "true";
        }
        if(itfCert.isChecked()){
            itfCertificate = "true";
        }
        if(tradeLicense.isChecked()){
            tradeLi = "true";
        }
        if(tracebleRef.isChecked()){
            traceR = "true";
        }
    }
    private void navigateToAddInventory(View view){
        Fragment fragment = null;
        fragment = new ClientInventoryFragment();

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
