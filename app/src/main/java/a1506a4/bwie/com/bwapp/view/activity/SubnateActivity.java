package a1506a4.bwie.com.bwapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.MineSendMessageBean;
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import a1506a4.bwie.com.bwapp.view.adapter.SubnateAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Shadow on 2017/11/12.
 */

public class SubnateActivity extends MainActivity {
    private ImageView iv_back;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;
    private MineSendMessageBean.ObjectBean bean;
    private List<MineSendMessageBean.ObjectBean.TopositionlistBean> topositionlist;
    private SubnateAdapter adapter;

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subnate_activity);
        RxBus.getInstance().toObservable(MineSendMessageBean.ObjectBean.class)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MineSendMessageBean.ObjectBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MineSendMessageBean.ObjectBean objectBean) {

                        Log.e(TAG, "onNext: " + bean.getTopositionlist().size());
                        bean = objectBean;
                    }
                });
        initView();
    }

    private void initView() {

        bean = (MineSendMessageBean.ObjectBean) getIntent().getSerializableExtra("bean");
        iv_back = (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        swiper = (SwipeRefreshLayout) findViewById(R.id.swiper);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                swiper.setRefreshing(false);
            }
        });

        topositionlist = bean.getTopositionlist();
        topositionlist = bean.getTopositionlist();
        adapter = new SubnateAdapter(topositionlist);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SubnateAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(SubnateActivity.this, StatusActivity.class);
                intent.putExtra("mid", bean.getId() + "");
                intent.putExtra("position", topositionlist.get(position).getPositionId() + "");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
