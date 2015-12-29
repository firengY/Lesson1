package com.firengy.lesson1.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.firengy.lesson1.fragments.BlankFragment;

import java.util.List;

/**
 * Created by firengy
 * on 15-12-28.
 * Email: 18811372352@163.com
 */
public class QiushiAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<String> textList;

    public QiushiAdapter(FragmentManager fm) {
        super(fm);
    }

    public QiushiAdapter(FragmentManager fm, Context context, List<String> textList) {
        super(fm);
        this.context = context;
        this.textList = textList;
    }

    @Override
    public Fragment getItem(int position) {
        return BlankFragment.newInstance(textList.get(position));
    }

    @Override
    public int getCount() {
        return textList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return textList.get(position);
    }
}
