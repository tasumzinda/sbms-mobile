package org.totalit.sbms.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.totalit.sbms.ClassImplements.SpinnerOnItemSelectedListener;
import org.totalit.sbms.R;

public class ClientInventoryFragment extends Fragment {

    private TextInputLayout inputLayoutBrand, inputLayoutModel;
    private EditText inputBrand, inputModel;
    private Button saveInventoryBtn, goNext;
    private Spinner inputCategorySpinner, inputNeedMaSpinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_client_inventory, container, false);
        inputLayoutBrand = view.findViewById(R.id.input_layout_brand);
        inputLayoutModel = view.findViewById(R.id.input_layout_model);
        inputBrand = view.findViewById(R.id.input_brand);
        inputModel = view.findViewById(R.id.input_model);
        saveInventoryBtn = view.findViewById(R.id.saveInventory);
        goNext = view.findViewById(R.id.goNext_btn_in_iventory);
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAddContacts(v);
            }
        });
        categoryListener(view);
        needmaintListener(view);
        return view;
    }
    private void categoryListener(View view){
        inputCategorySpinner = view.findViewById(R.id.category_spinner);
        inputCategorySpinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener());
    }
    private void needmaintListener(View view){
        inputNeedMaSpinner = view.findViewById(R.id.category_spinner);
        inputNeedMaSpinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener());
    }
    private void navigateToAddContacts(View view){
        Fragment fragment = null;
        fragment = new AddContactFragment();

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
