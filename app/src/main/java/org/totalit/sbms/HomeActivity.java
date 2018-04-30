package org.totalit.sbms;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;


import org.totalit.sbms.Database.AbstractDb;
import org.totalit.sbms.Database.InstantiateDb;
import org.totalit.sbms.adapters.ClientAdapter;
import org.totalit.sbms.domain.Client;
import org.totalit.sbms.fragments.AddClientFragment;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Client> clients;
    List<Client> nilClient;
    FloatingActionButton clientMan;
    AbstractDb mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout)findViewById(R.id.layout_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        clientMan = findViewById(R.id.clientMan);
        mdb = InstantiateDb.sbmsDb(this);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navBar);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        drawerLayout.closeDrawers();

                        int id = menuItem.getItemId();
                        Fragment fragment = null;
                        Bundle bundle = new Bundle();
                       // if (id == R.id.menu_clientManagement) {
                         //   fragment = new ClientManagementHomeFragment();
                       // }

                         if (id == R.id.totalitweb) {
                            bundle.putString("url", "http://10.0.2.2:8090/login");
                           // bundle.putString("url", "https://www.facebook.com");
                            fragment = new org.totalit.sbms.fragments.WebViewFragment();
                            fragment.setArguments(bundle);
                        }
                        if (fragment != null) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.frame_layout, fragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.layout_drawer);
                        drawer.closeDrawer(GravityCompat.START);

                        return true;
                    }
                });
                    try {
                        clients = new LoadClientList(mdb).execute().get();

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    if(clients!=null) {
                        recyclerView = findViewById(R.id.recylce_view_list_clients);
                        layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new ClientAdapter(clients);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                  else{
                        nilClient = new ArrayList<>();
                        Client client = new Client();
                        client.setName("No data");
                        nilClient.add(client);
                        recyclerView = findViewById(R.id.recylce_view_list_clients);
                        layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new ClientAdapter(nilClient);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);

                    }
        clientMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddClient(v);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(actionBarDrawerToggle.onOptionsItemSelected(menuItem)){
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void openAddClient(View v){
        Fragment fragments = null;
        fragments = new AddClientFragment();
        if (fragments != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragments);
            ft.commit();
        }
    }

    public class LoadClientList extends AsyncTask<Void, Void, List<Client>> {
        List<Client> client = new ArrayList<>();
        AbstractDb mdb;

        public LoadClientList(AbstractDb mdb) {
            this.mdb = mdb;
        }

        @Override
        protected List<Client> doInBackground(Void... voids) {

            client = mdb.clientRepository().getAll();

            return client;
        }


    }
}
