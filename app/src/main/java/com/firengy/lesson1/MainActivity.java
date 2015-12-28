package com.firengy.lesson1;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView menu;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = (NavigationView) findViewById(R.id.menu);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        tabLayout = (TabLayout) findViewById(R.id.table_content);
        pager = (ViewPager) findViewById(R.id.pager);



        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer.setDrawerListener(toggle);

        menu.setNavigationItemSelectedListener(this);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.item_happen:
                break;
            case R.id.item_group:
                break;
        }
        drawer.openDrawer(GravityCompat.START);
        return true;
    }
}
