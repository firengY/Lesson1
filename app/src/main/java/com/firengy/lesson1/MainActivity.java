package com.firengy.lesson1;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView menu;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = (NavigationView) findViewById(R.id.menu);
        drawer = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer.setDrawerListener(toggle);
        menu.setNavigationItemSelectedListener(this);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return false;
    }
}
