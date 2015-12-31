package com.firengy.lesson1.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firengy.lesson1.R;
import com.firengy.lesson1.adapter.QiushiAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QiushiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QiushiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "qiushi";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public QiushiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param text Parameter 1.
     * @return A new instance of fragment QiushiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QiushiFragment newInstance(String text) {
        QiushiFragment fragment = new QiushiFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_qiushi, container, false);

        TabLayout tabLayout = (TabLayout) ret.findViewById(R.id.table_content);
        ViewPager pager = (ViewPager) ret.findViewById(R.id.pager);


        List<String> titleList = new LinkedList<String>();

        titleList.add("专享");
        titleList.add("视频");
        titleList.add("纯文");
        titleList.add("纯图");
        //titleList.add("精华");
        titleList.add("最新");

        List<String> typeList = new LinkedList<String>();

        typeList.add("suggest");
        typeList.add("video");
        typeList.add("text");
        typeList.add("image");
        typeList.add("latest");


        QiushiAdapter adapter = new QiushiAdapter(
                getChildFragmentManager(),
                titleList,
                typeList);

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        return ret;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text = getArguments().getString(TITLE);

    }
}
