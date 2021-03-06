package com.firengy.lesson1;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firengy.lesson1.customview.PagerEnabledSlidingPaneLayout;
import com.firengy.lesson1.fragments.QiushiFragment;
import com.firengy.lesson1.fragments.QiuyouCircleFragment;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SlidingPaneLayout.PanelSlideListener {

    private NavigationView menu;
    private PagerEnabledSlidingPaneLayout slidingPane;

    private List<Fragment> mFragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragments = new LinkedList<Fragment>();
        menu = (NavigationView) findViewById(R.id.menu);
        slidingPane = (PagerEnabledSlidingPaneLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        fragment = QiushiFragment.newInstance("糗事");
        mFragments.add(fragment);
        fragment = QiuyouCircleFragment.newInstance("糗友圈");
        mFragments.add(fragment);

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, mFragments.get(0));
        fragmentTransaction.add(R.id.fragment_container, mFragments.get(1));


        fragmentTransaction
                .hide(mFragments.get(1))
                        //.hide(mFragments.get(2))
                        //.hide(mFragments.get(3))
                .commit();

        slidingPane.setPanelSlideListener(this);
        menu.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_main,menu);
        return true;
    }

    //menu
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.item_qiushi:
                item.setChecked(true);
                fragmentTransaction.show(mFragments.get(0));
                fragmentTransaction.hide(mFragments.get(1));
                break;
            case R.id.item_qiuyoucircle:
                fragmentTransaction.hide(mFragments.get(0));
                fragmentTransaction.show(mFragments.get(1));
                item.setChecked(true);
                break;
            case R.id.item_nearby:
                //fragmentTransaction.show(mFragments.get(2));
                item.setChecked(true);
                break;
            case R.id.item_message:
                //fragmentTransaction.show(mFragments.get(3));
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
        fragmentTransaction.commit();
        //slidingPane.closeDrawers();
        slidingPane.closePane();
        return true;
    }


    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        ViewCompat.setPivotX(menu, 0);
        //ViewCompat.setScaleY(menu, 1 - slideOffset * 0.5f);
        ViewCompat.setTranslationX(menu, -menu.getWidth() / 2 * (1 - slideOffset));

    }

    @Override
    public void onPanelOpened(View panel) {

    }

    @Override
    public void onPanelClosed(View panel) {

    }

    private long exitTime = 0;

    //再按一次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
