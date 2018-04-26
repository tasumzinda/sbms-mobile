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

import org.totalit.sbms.R;

public class ClientManagementHomeFragment extends Fragment{
    private Button openAddNew;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_management_home, container, false);
        openAddNew = v.findViewById(R.id.open_addNewclient_btn);

        openAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddClient(v);
            }
        });
        return v;
    }
 /*   Button.OnClickListener btnFragmentLister = (new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            Fragment fragment = null;
            switch (v.getId()) {
                case R.id.open_addNewclient_btn:
                    fragment = new AddClientFragment();
                    replaceFragment(fragment);
            }
        }
    });
*/
    public void openAddClient(View v){
        Fragment fragment = null;
        fragment = new AddClientFragment();
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
        }
    }
    public void replaceFragment(android.support.v4.app.Fragment rFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, rFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
