package org.totalit.sbms.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.totalit.sbms.Database.AbstractDb;
import org.totalit.sbms.Database.InstantiateDb;
import org.totalit.sbms.R;
import org.totalit.sbms.adapters.ClientAdapter;
import org.totalit.sbms.domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

public class ClientManagementHomeFragment extends Fragment{
     private Button openAddNew;
     RecyclerView recyclerView;
     RecyclerView.Adapter adapter;
     RecyclerView.LayoutManager layoutManager;
     List<Client> clients;
     List<Client> myList;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_client_management_home, container, false);
        openAddNew = v.findViewById(R.id.open_addNewclient_btn);


        new Thread(new Runnable() {
            @Override
            public void run() {
                clients = new ArrayList<>();
              Client client = new Client();
              client.setName("Roy");
              client.setDescription("Kanaz");
              clients.add(client);

                recyclerView = v.findViewById(R.id.recylce_view_list_clients);
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new ClientAdapter(clients);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        }).start();
        openAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddClient(v);
            }
        });
        return v;
    }

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
