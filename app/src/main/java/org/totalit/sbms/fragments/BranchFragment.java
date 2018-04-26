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

import org.totalit.sbms.R;

public class BranchFragment extends Fragment {
    private TextInputLayout inputLayoutName, inputLayoutAddress;
    private EditText inputname, inputaddress;
    private Button saveBranch, goNext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_branch, container, false);
        inputLayoutName = view.findViewById(R.id.input_layout_branch_name);
        inputLayoutAddress = view.findViewById(R.id.input_layout_branch_address);
        inputname = view.findViewById(R.id.input_branch_name);
        inputaddress = view.findViewById(R.id.input_branch_address);
        saveBranch = view.findViewById(R.id.saveBranch);
        goNext = view.findViewById(R.id.goNextBtn);
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            navigateToAddInventory(v);
            }
        });

        return view;
    }

    private void navigateToAddInventory(View view){
        Fragment fragment = null;
        fragment = new ProcumentDocsFragment();

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
