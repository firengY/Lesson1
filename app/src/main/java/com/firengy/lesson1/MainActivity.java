package com.firengy.lesson1;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.firengy.lesson1.adapter.QiushiAdapter;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView menu;
    private PagerEnabledSlidingPaneLayout drawer;
    private ActionBarDrawerToggle toggle;
    private TabLayout tabLayout;
    private ViewPager pager;
    public static int LAYOUT = R.layout.activity_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);


        menu = (NavigationView) findViewById(R.id.menu);
        drawer = (PagerEnabledSlidingPaneLayout) findViewById(R.id.drawer);
        tabLayout = (TabLayout) findViewById(R.id.table_content);
        pager = (ViewPager) findViewById(R.id.pager);

        /*toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer.setDrawerListener(toggle);*/

        List<String> titleList = new LinkedList<String>();
        titleList.add("专享");
        titleList.add("视频");
        titleList.add("纯文");
        titleList.add("纯图");
        titleList.add("精华");
        titleList.add("最新");

        QiushiAdapter mAdapter = new QiushiAdapter(
                getSupportFragmentManager(),
                this,
                titleList);

        pager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(pager);

        menu.setNavigationItemSelectedListener(this);
    }

    //三横线
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //menu
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.item_qiushi:
                item.setChecked(true);
                break;
            case R.id.item_qiuyoucircle:
                item.setChecked(true);
                break;
            case R.id.item_nearby:
                item.setChecked(true);
                break;
            case R.id.item_message:
                item.setChecked(true);
                break;
            case R.id.subitem_setting:
                item.setChecked(true);
                break;
            case R.id.subitem_quit:
                item.setChecked(true);
                ActivityCompat.finishAffinity(this);
                break;
        }
        //drawer.closeDrawers();
        drawer.closePane();
        return true;
    }
}
