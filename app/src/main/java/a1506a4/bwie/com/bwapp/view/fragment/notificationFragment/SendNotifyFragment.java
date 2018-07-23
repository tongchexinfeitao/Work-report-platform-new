package a1506a4.bwie.com.bwapp.view.fragment.notificationFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.LPLBean;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.SendResultBean;
import a1506a4.bwie.com.bwapp.model.utils.ProgressBarUtils;
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.presenter.notification.GetLPLPresenter;
import a1506a4.bwie.com.bwapp.presenter.notification.SendMessagePresenter;
import a1506a4.bwie.com.bwapp.view.adapter.MyRecyclerViewAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.GetLPLView;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.SendMessageView;


/**
 * Created by Shadow on 2017/10/13.
 */

public class SendNotifyFragment extends BaseFragment implements View.OnClickListener, GetLPLView, SendMessageView {

    private View view;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private Button btn_sendAll_notify;
    private TextView tv_selectAll;
    private EditText et_content;
    private TextView tv_warning;
    private RadioButton radio_yes;
    private RadioButton radio_no;
    private boolean isFankui;
    private AlertDialog notifyDialog;
    private GetLPLPresenter getLPLPresenter;
    private SendMessagePresenter sendResultPresenter;
    private List<LPLBean.ObjectBean> list;

    private static final String TAG = "TAG";
    private SwipeRefreshLayout swiper;
    private LinearLayout emptyLinear;
    private View line;
    private AlertDialog loadingDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.send_notification_fragment, container, false);
        loadingDialog = ProgressBarUtils.createLoadingDialog(getContext(), "正在发送···");

        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getId())) {
            getLPLPresenter.getLPL(userInfo.getId());
        }
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (userInfo != null && !TextUtils.isEmpty(userInfo.getId())) {
                    getLPLPresenter.getLPL(userInfo.getId());
                }

                swiper.setRefreshing(false);

            }
        });


    }

    //重写刷新的方法
    @Override
    protected void onRefresh() {
        super.onRefresh();
        getLPLPresenter.getLPL(userInfo.getId());
    }

    @Override
    protected IPresenter getPresebter() {
        return getLPLPresenter;
    }

    private void initView(View view) {

        getLPLPresenter = new GetLPLPresenter(this);
        sendResultPresenter = new SendMessagePresenter(this);

        emptyLinear = (LinearLayout) view.findViewById(R.id.emptyLinear);
        swiper = (SwipeRefreshLayout) view.findViewById(R.id.swiper);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        btn_sendAll_notify = (Button) view.findViewById(R.id.btn_sendAll_notify);
        tv_selectAll = (TextView) view.findViewById(R.id.tv_selectAll);
        btn_sendAll_notify.setOnClickListener(this);
        tv_selectAll.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        //失去焦点关闭键盘
        view.findViewById(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendAll_notify:
                if (adapter != null && adapter.shouldShowDialog()) {
                    showDialog();
                } else {
                    Toast.makeText(getContext(), "请先选择要发送通知的人物", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_selectAll:
                if (adapter != null) {
                    adapter.selectAll();
                }

                break;
            case R.id.btn_send_notification:
                if (!TextUtils.isEmpty(et_content.getText().toString().trim())) {
                    tv_warning.setVisibility(View.GONE);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog);
                    View view = View.inflate(getContext(), R.layout.verify_dialog, null);
                    Button btn_qd = (Button) view.findViewById(R.id.btn_qd);
                    Button btn_qx = (Button) view.findViewById(R.id.btn_qx);
                    final AlertDialog verifyDialog = builder.setView(view).create();
                    verifyDialog.show();

                    btn_qd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //拿到选中的下属职位码
                            String position = getPosition(adapter.getIsSelected());
                            String content = et_content.getText().toString();

                            adapter.resetCheckBox();

                            if (userInfo != null) {
                                loadingDialog.show();
                                sendResultPresenter.sendMessage(userInfo.getId(), content, position);
                            } else {
                                Toast.makeText(getContext(), "未知错误，发送失败", Toast.LENGTH_SHORT).show();
                            }


                            verifyDialog.dismiss();
                            notifyDialog.dismiss();


                        }
                    });

                    btn_qx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.resetCheckBox();
                            verifyDialog.dismiss();
                            notifyDialog.dismiss();
                        }
                    });


                    //关闭小键盘
                    InputMethodManager imm = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                } else {
                    tv_warning.setVisibility(View.VISIBLE);
                }
                break;
           /* case R.id.btn_sendAll_notification:
                if (!TextUtils.isEmpty(et_content.getText().toString().trim())) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("发送通知")
                            .setMessage("你确定要给所有人发送通知吗")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (isFankui) {
                                        notifyDialog.dismiss();
                                        Toast.makeText(getContext(), "逐级发送成功有反馈", Toast.LENGTH_SHORT).show();
                                    } else {
                                        notifyDialog.dismiss();
                                        Toast.makeText(getContext(), "逐级发送成功没有反馈", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create().show();
                    //关闭小键盘
                    InputMethodManager imm = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                } else {
                    tv_warning.setVisibility(View.VISIBLE);
                }
                break;*/
        }
    }

    //根据选中的职位  拿到positionId  拼接字符串

    private String getPosition(HashMap<Integer, Boolean> isSelected) {
        //遍历map  得到选中的position  根据下标拿到code去拼接
        Set<Map.Entry<Integer, Boolean>> entries = isSelected.entrySet();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                String code = list.get(entry.getKey()).getPositioncode();
                builder.append(code + ",");
            }
        }
        return builder.toString();
    }

    private void showDialog() {
        //  isFankui = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog);
        View view = View.inflate(getContext(), R.layout.send_notification_dialog, null);
        RelativeLayout linearLayout = (RelativeLayout) view.findViewById(R.id.notifLayout);
        initview2(view);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        notifyDialog = builder.setView(view).create();
        notifyDialog.show();
     /*   radio_yes.setChecked(true);
        radio_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    isFankui = true;
                } else {
                    isFankui = false;
                }
            }
        });*/
    }

    private void initview2(View view) {
        Button btn_send_notification = (Button) view.findViewById(R.id.btn_send_notification);
        //  Button btn_sendAll_notification = (Button) view.findViewById(R.id.btn_sendAll_notification);
        et_content = (EditText) view.findViewById(R.id.et_content);
        tv_warning = (TextView) view.findViewById(R.id.tv_warning);

//        radio_yes = (RadioButton) view.findViewById(R.id.radio_yes);
//        radio_no = (RadioButton) view.findViewById(R.id.radio_no);

        btn_send_notification.setOnClickListener(this);
        //  btn_sendAll_notification.setOnClickListener(this);
    }


    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void getDataSuccessed(LPLBean bean) {


        list = bean.getObject();
        if (list != null && list.size() > 0) {
            emptyLinear.setVisibility(View.GONE);
            if (adapter != null) {
                adapter.refreshData(list);
            } else {
                adapter = new MyRecyclerViewAdapter(list);

            }
            recyclerView.setAdapter(adapter);

        }


    }

    @Override
    public void getDataFailed(String error) {

    }

    @Override
    public void sendSuccessed(SendResultBean bean) {
        dismissLoadingDialog();
        if (bean.getCode() == 200) {
            //刷新我的通知界面
            RxBus.getInstance().post("refresh");
            Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "发送失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void sendFailed(String error) {
        dismissLoadingDialog();
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }


    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


}
