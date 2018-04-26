package org.totalit.sbms;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


import org.totalit.sbms.fragments.AddClientFragment;
import org.totalit.sbms.fragments.ClientManagementHomeFragment;
import org.totalit.sbms.fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout)findViewById(R.id.layout_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                        if (id == R.id.menu_clientManagement) {
                            fragment = new ClientManagementHomeFragment();
                        }

                        else if (id == R.id.totalitweb) {
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

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(actionBarDrawerToggle.onOptionsItemSelected(menuItem)){
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
