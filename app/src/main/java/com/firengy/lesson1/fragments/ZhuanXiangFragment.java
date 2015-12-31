package com.firengy.lesson1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firengy.lesson1.QsbkService;
import com.firengy.lesson1.R;
import com.firengy.lesson1.adapter.ZhuanXiangAdapter;
import com.firengy.lesson1.entity.ZhuanXiangItem;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZhuanXiangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZhuanXiangFragment extends Fragment implements Callback<ZhuanXiangItem> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String TITLE = "param1";
    private static final String TYPE = "type";

    private static final String TAG = "ZhuanXiangFragment";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String type;

    private Call<ZhuanXiangItem> call;
    private ZhuanXiangAdapter adapter;
    private int page;

    public ZhuanXiangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ZhuanXiangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZhuanXiangFragment newInstance( String type) {
        ZhuanXiangFragment fragment = new ZhuanXiangFragment();
        Bundle args = new Bundle();
        //args.putString(TITLE, title);
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取arguments传过来的值，即网络接口注解部分：image/latest/
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_zhuan_xiang, container, false);
        PullToRefreshListView listView = (PullToRefreshListView) ret.findViewById(R.id.list_zhuanxiang);

        adapter = new ZhuanXiangAdapter(getContext());

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setAdapter(adapter);
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QsbkService service = build.create(QsbkService.class);
        call = service.getList(type, page);
        call.enqueue(this);

        //listView.setOnItemClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                //adapter.addAll();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        return ret;
    }

    @Override
    public void onResponse(Response<ZhuanXiangItem> response, Retrofit retrofit) {

        adapter.addAll(response.body().getItems());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Log.d(TAG, "onFailure: " + t.toString());
    }

}
