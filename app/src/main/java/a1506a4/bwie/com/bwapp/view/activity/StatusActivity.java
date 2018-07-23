package a1506a4.bwie.com.bwapp.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.UserInfo;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.StatusBean;
import a1506a4.bwie.com.bwapp.presenter.notification.StatusPresenter;
import a1506a4.bwie.com.bwapp.view.adapter.StatusAdapter;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.StatusView;

/**
 * Created by Shadow on 2017/11/10.
 */

public class StatusActivity extends Activity implements StatusView {


    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;
    private StatusPresenter statusPresenter;
    private List<StatusBean.ObjectBean> list;
    private StatusAdapter adapter;
    private ImageView iv_back;
    private UserInfo userInfo;
    private StatusBean statusbean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.status_activity);
        initView();
        userInfo = UserInfoManager.getInstance(this).getUserInfo();
        if (userInfo != null && userInfo.getId() != null) {
            statusPresenter.getStatus(userInfo.getId(), getIntent().getStringExtra("mid"), getIntent().getStringExtra("position"));

        }
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                statusPresenter.getStatus(userInfo.getId(), getIntent().getStringExtra("mid"), getIntent().getStringExtra("position"));

                swiper.setRefreshing(false);
            }
        });
    }

    private void initView() {
        statusPresenter = new StatusPresenter(this);
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

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void getStatusSucced(StatusBean bean) {

        list = bean.getObject();
        if (list != null && list.size() > 0) {
            if (adapter != null) {
                adapter.refreshData(list);
            } else {
                adapter = new StatusAdapter(list);
            }

            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void getStatusFailed(String error) {

        Toast.makeText(this, "查看已阅状态失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
