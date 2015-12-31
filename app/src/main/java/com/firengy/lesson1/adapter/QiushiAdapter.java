package com.firengy.lesson1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.firengy.lesson1.fragments.ZhuanXiangFragment;

import java.util.List;

/**
 * Created by firengy
 * on 15-12-28.
 * Email: 18811372352@163.com
 */
public class QiushiAdapter extends FragmentPagerAdapter {
    private List<String> textList;
    private List<String> typeList;

    public QiushiAdapter(FragmentManager fm) {
        super(fm);
    }

    public QiushiAdapter(FragmentManager fm,List<String> textList,List<String> typeList) {
        super(fm);

        this.textList = textList;
        this.typeList = typeList;
    }

    @Override
    public Fragment getItem(int position) {
        return ZhuanXiangFragment.newInstance(typeList.get(position));
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
