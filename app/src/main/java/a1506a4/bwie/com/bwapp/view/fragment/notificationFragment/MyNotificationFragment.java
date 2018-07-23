package a1506a4.bwie.com.bwapp.view.fragment.notificationFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.MineSendMessageBean;
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.presenter.notification.MineMessagePresenter;
import a1506a4.bwie.com.bwapp.presenter.notification.StatusPresenter;
import a1506a4.bwie.com.bwapp.view.activity.SubnateActivity;
import a1506a4.bwie.com.bwapp.view.adapter.MyNotificationAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;
import a1506a4.bwie.com.bwapp.view.myInterface.mine.MineMessage;
import rx.Observer;

/**
 * 作者: 王浩坤
 * 时间: 2017/10/25
 * 类作用:我自己发送的通知
 */

public class MyNotificationFragment extends BaseFragment implements MineMessage {

    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;
    private MineMessagePresenter mineMessagePresenter;
    private MyNotificationAdapter adapter;
    private List<MineSendMessageBean.ObjectBean> list;
    private AlertDialog notificationDialog;
    private StatusPresenter statusPresenter;
    private LinearLayout emptyLinear;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.notification_my_fragment, null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
        if (userInfo != null) {
            mineMessagePresenter.mineSendMessage(userInfo.getId());
        }

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: 2017/11/3  刷新 我的通知  再次请求接口
                if (userInfo != null) {
                    mineMessagePresenter.mineSendMessage(userInfo.getId());
                }
                swiper.setRefreshing(false);
            }
        });


        RxBus.getInstance().toObservable(String.class)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {

                        if ("refresh".equals(s) && userInfo != null) {
                            mineMessagePresenter.mineSendMessage(userInfo.getId());
                        }
                    }
                });
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        mineMessagePresenter.mineSendMessage(userInfo.getId());
    }

    @Override
    protected IPresenter getPresebter() {
        return mineMessagePresenter;
    }

    //初始化控件
    private void initView(View view) {
        mineMessagePresenter = new MineMessagePresenter(this);

        emptyLinear = (LinearLayout) view.findViewById(R.id.emptyLinear);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        swiper = (SwipeRefreshLayout) view.findViewById(R.id.swiper);
    }

    @Override
    public Context context() {
        return getContext();
    }

    //成功拿到数据
    @Override
    public void getMineMessageSucced(MineSendMessageBean bean) {

        list = bean.getObject();
        if (list != null && list.size() > 0) {
            emptyLinear.setVisibility(View.GONE);
            if (adapter != null) {
                adapter.refreshData(list);
            } else {
                adapter = new MyNotificationAdapter(list);

            }
            recyclerView.setAdapter(adapter);
        }


        //item点击显示详细信息对话框
        adapter.setOnItemClickListener(new MyNotificationAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                showDialog(v, position);
            }
        });

    }

    @Override
    public void getMineMessageFailed(String error) {
    }


    private void showDialog(View view, final int position) {

        if (notificationDialog != null && notificationDialog.isShowing()) {
            notificationDialog.dismiss();
            notificationDialog = null;
        }
        View view2 = View.inflate(getContext(), R.layout.notification_dialog, null);
        Button btn_qd = (Button) view2.findViewById(R.id.btn_qd);
        Button btn_status = (Button) view2.findViewById(R.id.btn_status);
        TextView tv_time = (TextView) view2.findViewById(R.id.htv_time);
        TextView tv_title = (TextView) view2.findViewById(R.id.htv_title);
        TextView htv_content = (TextView) view2.findViewById(R.id.htv_content);
        TextView tv_name = (TextView) view2.findViewById(R.id.tv_name);
        TextView tv_position = (TextView) view2.findViewById(R.id.tv_position);

        //设置文本内容
        tv_title.setText("通知");
        tv_time.setText(list.get(position).getSendtime());
        htv_content.setText(list.get(position).getContent());
        tv_name.setText(list.get(position).getSendname());
        tv_position.setText(list.get(position).getPositionname());

        //创建对话框
        notificationDialog = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog)
                .setView(view2)
                .show();
        notificationDialog.setCanceledOnTouchOutside(true);
        btn_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //点击确定按钮 消失
                notificationDialog.dismiss();
            }
        });
        //点击查看下属的已阅状态
        btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationDialog.dismiss();
                //把mid  通知的id   positionid  职位的id 传到另一个页面  去请求接口
                MineSendMessageBean.ObjectBean bean = list.get(position);
                //RxBus.getInstance().post(bean);
                Intent intent = new Intent(getContext(), SubnateActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);


            }
        });
    }


}
