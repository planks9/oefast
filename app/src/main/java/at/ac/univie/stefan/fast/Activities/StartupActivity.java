package at.ac.univie.stefan.fast.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import at.ac.univie.stefan.fast.DataBase.AppDatabasePersonData;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.Fragments.ExportDataFragment;
import at.ac.univie.stefan.fast.Fragments.PersonManagementFragment;
import at.ac.univie.stefan.fast.Fragments.SettingsFragment;
import at.ac.univie.stefan.fast.R;

/**
 * Created by Stefan on 14.04.2018.
 */

public class StartupActivity extends AppCompatActivity {


    FragmentManager fragmentManager;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseCreator.createnewDataBasePersonData(getApplicationContext());
        DataBaseCreator.createnewDataBase(getApplicationContext());
        setContentView(R.layout.navigation_drawer);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentplaceholder,new PersonManagementFragment()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                Fragment fragmentClass =null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragmentClass = new PersonManagementFragment();
                        break;
                    case R.id.nav_exportieren:
                        fragmentClass = new ExportDataFragment();
                        break;
                    case R.id.nav_settings:
                        fragmentClass = new SettingsFragment();
                        break;
                    default:
                        fragmentClass = new SettingsFragment();
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentplaceholder,fragmentClass).commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
