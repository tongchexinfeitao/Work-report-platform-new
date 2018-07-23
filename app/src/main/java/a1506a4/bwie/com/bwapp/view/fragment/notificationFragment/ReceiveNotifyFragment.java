package a1506a4.bwie.com.bwapp.view.fragment.notificationFragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.NotificationBean;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.ReceiveMessageBean;
import a1506a4.bwie.com.bwapp.model.utils.ProgressBarUtils;
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.presenter.notification.ReceiveMessagePresenter;
import a1506a4.bwie.com.bwapp.presenter.notification.ReplyPresenter;
import a1506a4.bwie.com.bwapp.view.activity.MainActivity;
import a1506a4.bwie.com.bwapp.view.adapter.NotificationAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.ReceiveMessageView;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.ReplyView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Shadow on 2017/10/13.
 */

public class ReceiveNotifyFragment extends BaseFragment implements ReceiveMessageView, ReplyView {

    private static final String TAG = "TAG";
    private View view;
    private RecyclerView recyclerView;
    private TextView htv_title;
    private EditText et_feedback;
    private View line;
    private Button btn_commit;
    private Button btn_cancel;
    private AlertDialog notificationDialog;
    private SwipeRefreshLayout swiper;
    private NotificationAdapter adapter;
    private ReceiveMessagePresenter receiveMessagePresenter;
    private List<ReceiveMessageBean.ObjectBean> list;
    public static int count;
    private ReplyPresenter replyPresenter;
    private LinearLayout emptyLinear;
    private Button btn_fankui;
    private AlertDialog loadingDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notification_receive_fragment, container, false);
        loadingDialog = ProgressBarUtils.createLoadingDialog(getContext(), "正在回复···");
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();

        if (userInfo != null) {
            receiveMessagePresenter.receiveMessage(userInfo.getId());
        }


        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // TODO: 2017/11/3  刷新收到的通知  再次请求接口
                if (userInfo != null) {
                    receiveMessagePresenter.receiveMessage(userInfo.getId());
                }
                swiper.setRefreshing(false);
            }
        });


        RxBus.getInstance().toObservable(NotificationBean.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NotificationBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NotificationBean notificationBean) {

                        showNotification(notificationBean.getContent());
                        receiveMessagePresenter.receiveMessage(userInfo.getId());
                    }
                });


    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        receiveMessagePresenter.receiveMessage(userInfo.getId());
    }

    @Override
    protected IPresenter getPresebter() {
        return receiveMessagePresenter;
    }

    private void initView(View view) {

        emptyLinear = (LinearLayout) view.findViewById(R.id.emptyLinear);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swiper = (SwipeRefreshLayout) view.findViewById(R.id.swiper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        receiveMessagePresenter = new ReceiveMessagePresenter(this);
        replyPresenter = new ReplyPresenter(this);

    }

    private void showDialog(View view, final int position) {


        if (notificationDialog != null && notificationDialog.isShowing()) {
            notificationDialog.dismiss();
            notificationDialog = null;
        }

        View view2 = View.inflate(getContext(), R.layout.receive_notification_dialog, null);
        Button btn_qd = (Button) view2.findViewById(R.id.btn_qd);
        btn_fankui = (Button) view2.findViewById(R.id.btn_fankui);
        TextView htv_time = (TextView) view2.findViewById(R.id.htv_time);
        TextView htv_title = (TextView) view2.findViewById(R.id.htv_title);
        TextView htv_content = (TextView) view2.findViewById(R.id.htv_content);
        TextView tv_name = (TextView) view2.findViewById(R.id.tv_name);
        TextView tv_position = (TextView) view2.findViewById(R.id.tv_position);


        htv_time.setText(list.get(position).getSendtime());

        htv_title.setText("通知");
        htv_content.setText(list.get(position).getContent());
        tv_name.setText(list.get(position).getSendname());
        tv_position.setText(list.get(position).getPositionname());

        notificationDialog = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog)
                .setView(view2)
                .show();
        notificationDialog.setCanceledOnTouchOutside(true);
        //点击确定 对话框消失
        btn_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notificationDialog.dismiss();
            }
        });

        //回复
        btn_fankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationDialog.dismiss();
                showFeedbackDialog(list.get(position).getId());
            }
        });
    }

    private void showFeedbackDialog(final int mid) {
        View view = View.inflate(getContext(), R.layout.feedback_dialog, null);
        htv_title = (TextView) view.findViewById(R.id.htv_title);
        et_feedback = (EditText) view.findViewById(R.id.et_feedback);
        btn_commit = (Button) view.findViewById(R.id.btn_commit);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        final AlertDialog dialog = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog)
                .setView(view)
                .create();
        dialog.show();
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = et_feedback.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(getContext(), "回复内容不能为空", Toast.LENGTH_SHORT).show();
                } else if (text.length() < 5) {
                    Toast.makeText(getContext(), "内容不得少于5个字", Toast.LENGTH_SHORT).show();
                } else {
                    loadingDialog.show();
                    //请求回复的接口
                    replyPresenter.reply(userInfo.getId(), mid + "", text);


                    //关闭小键盘
                    InputMethodManager imm = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    dialog.dismiss();
                    notificationDialog.dismiss();

                }


            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭小键盘
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                dialog.dismiss();
                notificationDialog.dismiss();
            }
        });


    }

    private void showNotification(String content) {


        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(getContext())
                .setContentTitle("通知")
                .setContentText(content)
                .setNumber(10)
                //.setLargeIcon()
                .setSmallIcon(R.mipmap.logo2)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(getDefalutIntent())
                .setTicker("你收到一条新通知")
                .build();

        notification.flags = Notification.FLAG_AUTO_CANCEL;

        assert manager != null;

        manager.notify(1, notification);

    }

    public PendingIntent getDefalutIntent() {


        //   Intent intent = new Intent(Intent.ACTION_MAIN);
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra("flag", "notify");

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }


    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void getMessageSuccessed(ReceiveMessageBean bean) {


        list = bean.getObject();

        if (list != null && list.size() > 0) {
            emptyLinear.setVisibility(View.GONE);
            if (adapter != null) {
                adapter.refreshData(list);
            } else {
                adapter = new NotificationAdapter(list);
            }
            recyclerView.setAdapter(adapter);
        }


        //item点击  红点消失    请求接口 改变该消息的状态为已读
        adapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {


            @Override
            public void onClick(final View view, int position, ImageView imageView) {


                if (list.get(position).getStatusid() == 0) {
                    //请求接口 改变查阅状态
                    receiveMessagePresenter.changeStatus(userInfo.getId(), list.get(position).getId());

                    //设置本地list 的状态变为已读
                    list.get(position).setStatusid(1);
                    adapter.notifyDataSetChanged();
                }

                showDialog(view, position);

            }
        });

        //初始化 count
        count = 0;
        //拿到list中未读消息的数量
        if (list != null && list.size() > 0) {

            for (ReceiveMessageBean.ObjectBean o : list) {
                if (o.getStatusid() == 0) {
                    count++;
                }

            }

            //发送给QdgeView.setNumber
            RxBus.getInstance().post(count);
        }


    }

    @Override
    public void getMessageFailed(String error) {

    }

    @Override
    public void changeStatus(int code) {
        if (code == 200) {
            count--;
            RxBus.getInstance().post(count);
        }
    }

    @Override
    public void replySuccessed(Boolean result) {
        loadingDialog.dismiss();
        if (result) {
            Toast.makeText(getContext(), "回复成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "回复失败", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void replyError(String error) {
        loadingDialog.dismiss();
    }
}
