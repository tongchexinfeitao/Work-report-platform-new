package a1506a4.bwie.com.bwapp.view.fragment.punchFragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.app.MyApplication;
import a1506a4.bwie.com.bwapp.constant.BaseMessage;
import a1506a4.bwie.com.bwapp.model.bean.EventBusBean.LocationBean;
import a1506a4.bwie.com.bwapp.model.bean.EventBusBean.UserMessageBean;
import a1506a4.bwie.com.bwapp.model.bean.RefreshBean;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.LoginBean;
import a1506a4.bwie.com.bwapp.model.utils.GpsUtil;
import a1506a4.bwie.com.bwapp.model.utils.LocationUtil;
import a1506a4.bwie.com.bwapp.model.utils.ProgressBarUtils;
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import a1506a4.bwie.com.bwapp.presenter.punch.LoginPresenter;
import a1506a4.bwie.com.bwapp.presenter.punch.PunchingPresenter;
import a1506a4.bwie.com.bwapp.view.IView;
import a1506a4.bwie.com.bwapp.view.myInterface.punch.LoginRequestView;
import a1506a4.bwie.com.bwapp.view.myInterface.punch.PunchingView;

import static a1506a4.bwie.com.bwapp.constant.BaseMessage.USER_MESSAGE;
import static a1506a4.bwie.com.bwapp.view.activity.MainActivity.NETWORK_STATE_BOOLEAN;
import static a1506a4.bwie.com.bwapp.view.activity.MainActivity.NETWORK_STATE_NAME;
import static android.content.Context.MODE_PRIVATE;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/2
 * 作用 :
 */

public class PunchingFragment extends Fragment implements View.OnClickListener, IView, PunchingView, LoginRequestView {
    private static final int GO_TO_SETTING_REQUEST_CODE = 105;
    private EditText remark;
    private TextView location;
    private boolean canLocation = false;
    private PunchingPresenter punchingPresenter;
    private View view;
    private SharedPreferences userNameShared;
    private LoginPresenter loginPresenter;
    private SharedPreferences userMessage;


    private long lastTime = 0;
    private AlertDialog loadingDialog;
    private AlertDialog nameDialog;
    private TextView location01;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.punching_fragment, null);
        loadingDialog = ProgressBarUtils.createLoadingDialog(getContext(), "正在打卡···");
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //保存用户输入的姓名
        userNameShared = getActivity().getSharedPreferences("UserName", MODE_PRIVATE);
        //验证成功以后保存用户信息
        userMessage = getActivity().getSharedPreferences(USER_MESSAGE, MODE_PRIVATE);
        //判断用户是否开启定位权限
        int i = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        if (i == PackageManager.PERMISSION_GRANTED) {
            canLocation = true;
        } else {
            canLocation = false;
        }

        //检查网络并获取定位的位置
        checkNewworkAndLocation();
    }

    //检查网络并获取定位的位置,不向服务器发送数据
    public void checkNewworkAndLocation() {
        //先检查是否有定位的权限
        if (canLocation) {
            //检查是否有网络
            boolean aBoolean = getActivity().getSharedPreferences(NETWORK_STATE_NAME, MODE_PRIVATE)
                    .getBoolean(NETWORK_STATE_BOOLEAN, false);
            if (aBoolean) {
                LocationUtil.getLocation(MyApplication.getContext());
                LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                    @Override
                    public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                location01.setText(s + ":");
                                location.setText(s1 + s2);
                            }
                        });
                    }
                });
            }
        } else {
            location.setText("定位功能未开启。");
        }
    }

    private void initView() {
        remark = (EditText) view.findViewById(R.id.remark);//备注
        RelativeLayout punchLeft = (RelativeLayout) view.findViewById(R.id.punchLeft);
        RelativeLayout punchRight = (RelativeLayout) view.findViewById(R.id.punchRight);
        //定位成功以后显示的位置
        location = (TextView) view.findViewById(R.id.location);
        //省或市
        location01 = (TextView) view.findViewById(R.id.location01);
        //定位图标和显示定位位置的布局
        LinearLayout locationLayout = (LinearLayout) view.findViewById(R.id.locationLayout);
        //总布局
        RelativeLayout ralativeLayout = (RelativeLayout) view.findViewById(R.id.ralativeLayout);
        locationLayout.setOnClickListener(this);
        punchLeft.setOnClickListener(this);
        punchRight.setOnClickListener(this);
        ralativeLayout.setOnClickListener(this);
        punchingPresenter = new PunchingPresenter(this);
        loginPresenter = new LoginPresenter(this);
    }

    //第一次进入定位权限申请成功以后会发送当前的定位,这里用来接受定位
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setLocation(LocationBean bean) {
        if (!TextUtils.isEmpty(bean.getProvince())) {
            //接收到的内容不为空,证明activity权限已开,并且权限回调已经成功发送消息
            canLocation = true;
            location01.setText(bean.getProvince() + ":");
            location.setText(bean.getCity() + bean.getStreet());
        }
    }

    @Override
    public void onClick(View v) {
        long clickTime = System.currentTimeMillis();

        switch (v.getId()) {
            case R.id.punchLeft://上下班打卡
                loadingDialog.show();
                //判断GPS功能打开没有,如果没打开弹出提示
                if (!GpsUtil.isOPen(MyApplication.getContext())) {
                    Toast.makeText(getActivity(), "请开启您的GPS定位功能,然后重启软件!", Toast.LENGTH_LONG).show();
                    return;
                }

                if ((clickTime - lastTime) >= 5000) {
                    lastTime = clickTime;
                    punch(1);
                } else {
                    loadingDialog.dismiss();
                    Toast.makeText(getActivity(), "打卡频率过快,请稍后再试...", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.punchRight://出差打卡
                loadingDialog.show();
                if (!GpsUtil.isOPen(MyApplication.getContext())) {
                    Toast.makeText(getActivity(), "请开启您的GPS定位功能,然后重启软件!", Toast.LENGTH_LONG).show();
                    return;
                }

                if ((clickTime - lastTime) >= 5000) {
                    lastTime = clickTime;
                    punch(2);
                } else {
                    loadingDialog.dismiss();
                    Toast.makeText(getActivity(), "打卡频率过快,请稍后再试...", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.locationLayout://点击定位的图标,重新定位
                checkNewworkAndLocation();
                break;
            case R.id.ralativeLayout:
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
        }
    }

    //定位,打卡
    private void punch(final int typeId) {
        //先检查是否有定位的权限
        if (canLocation) {
            //检查是否有网络
            boolean aBoolean = getActivity().getSharedPreferences(NETWORK_STATE_NAME, MODE_PRIVATE)
                    .getBoolean(NETWORK_STATE_BOOLEAN, false);
            if (aBoolean) {
                //然后判断是否拿到用户的手机号
                if (!TextUtils.isEmpty(BaseMessage.NUMBERPHONE)) {
                    //拿到手机号以后,判断用户姓名是否为空,为空弹出输入框让用户输入真实姓名
                    if (TextUtils.isEmpty(userNameShared.getString("UserNameKey", null))) {
                        View view = View.inflate(getContext(), R.layout.dialog_username, null);
                        nameDialog = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog)
                                .setView(view).setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        dialog.dismiss();
                                        if (loadingDialog.isShowing()) {
                                            loadingDialog.dismiss();
                                        }
                                    }
                                }).show();

                        final EditText nameEditText = (EditText) view.findViewById(R.id.userName);
                        Button name_commit = (Button) view.findViewById(R.id.name_commit);
                        Button name_gouBack = (Button) view.findViewById(R.id.name_gouBack);

                        //确定按钮
                        name_commit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //如果姓名输入框不为空,就进行打卡和验证信息
                                if (!TextUtils.isEmpty(nameEditText.getText().toString().trim())) {
                                    //关闭小键盘
                                    InputMethodManager imm = (InputMethodManager)
                                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                                    nameDialog.dismiss();
                                    //请求接口验证用户信息并打卡
                                    LocationUtil.getLocation(MyApplication.getContext());
                                    LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                                        @Override
                                        public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                                            location01.setText(s + ":");
                                            location.setText(s1 + s2);
                                            loginPresenter.checkUserMessage(BaseMessage.NUMBERPHONE, nameEditText.getText().toString().trim(), typeId, v + "," + v1, remark.getText().toString().trim());
                                        }
                                    });
                                } else {
                                    BaseMessage.showDialog(getActivity(), "姓名不能为空", "知道了");
                                }
                            }
                        });
                        //取消按钮
                        name_gouBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                InputMethodManager imm = (InputMethodManager)
                                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                                nameDialog.dismiss();
                                if (loadingDialog.isShowing()) {
                                    loadingDialog.dismiss();
                                }
                            }
                        });

                    } else {//用户姓名不为空就直接打卡
                        LocationUtil.getLocation(MyApplication.getContext());
                        LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                            @Override
                            public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        location01.setText(s + ":");
                                        location.setText(s1 + s2);
                                        //调用接口项服务器回传数据
                                        punchingPresenter.checkIn(userMessage.getString("id", null), typeId, v + "," + v1, remark.getText().toString().trim());
                                    }
                                });
                            }
                        });
                    }
                } else {//没有拿到手机号提示用户
//                    new AlertDialog.Builder(getContext())
//                            .setTitle("目前只支持移动和联通用户:")
//                            .setMessage("未识别到您卡一的手机号码,请把已登记注册的手机卡放到手机卡槽一,然后再重试;如果还不能使用,请联系管理人员确认个人信息,或到营业厅更换手机卡,换卡时请把手机号写入到手机卡中!")
//                            .setPositiveButton("知道了", null)
//                            .show();
                    if (TextUtils.isEmpty(userNameShared.getString("UserNameKey", null))) {
                        View view = View.inflate(getContext(), R.layout.dialog_usernam_and_phone, null);
                        nameDialog = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog)
                                .setView(view).setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        dialog.dismiss();
                                        if (loadingDialog.isShowing()) {
                                            loadingDialog.dismiss();
                                        }
                                    }
                                }).show();

                        final EditText nameEditText = (EditText) view.findViewById(R.id.userName);
                        final EditText phoneEditText = (EditText) view.findViewById(R.id.phone);
                        Button name_commit = (Button) view.findViewById(R.id.name_commit);
                        Button name_gouBack = (Button) view.findViewById(R.id.name_gouBack);

                        //确定按钮
                        name_commit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //如果姓名输入框不为空,就进行打卡和验证信息
                                if (!TextUtils.isEmpty(nameEditText.getText().toString().trim()) && !TextUtils.isEmpty(phoneEditText.getText().toString().trim())) {
                                    //关闭小键盘
                                    InputMethodManager imm = (InputMethodManager)
                                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                                    nameDialog.dismiss();
                                    //请求接口验证用户信息并打卡
                                    LocationUtil.getLocation(MyApplication.getContext());
                                    LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                                        @Override
                                        public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                                            location01.setText(s + ":");
                                            location.setText(s1 + s2);

                                            String phoneNumber = phoneEditText.getText().toString();
                                            loginPresenter.checkUserMessage(phoneNumber, nameEditText.getText().toString().trim(), typeId, v + "," + v1, remark.getText().toString().trim());
                                        }
                                    });
                                } else {
                                    BaseMessage.showDialog(getActivity(), "姓名与电话不能为空", "知道了");
                                }
                            }
                        });
                        //取消按钮
                        name_gouBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                InputMethodManager imm = (InputMethodManager)
                                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                                nameDialog.dismiss();
                                if (loadingDialog.isShowing()) {
                                    loadingDialog.dismiss();
                                }
                            }
                        });

                    } else {//用户姓名不为空就直接打卡
                        LocationUtil.getLocation(MyApplication.getContext());
                        LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                            @Override
                            public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        location01.setText(s + ":");
                                        location.setText(s1 + s2);
                                        //调用接口项服务器回传数据
                                        punchingPresenter.checkIn(userMessage.getString("id", null), typeId, v + "," + v1, remark.getText().toString().trim());
                                    }
                                });
                            }
                        });
                    }
                }
            } else {
                Toast.makeText(getActivity(), "请检查您的网络", Toast.LENGTH_LONG).show();
            }
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("定位权限未打开:")
                    .setMessage("为了正常使用打卡功能,请打开定位权限!")
                    .setPositiveButton("打开", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goToSetting();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断回传吗是否一致
        if (requestCode == GO_TO_SETTING_REQUEST_CODE) {
            //检查用户是否打开定位权限
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //重启页面,完成定位
                getActivity().recreate();
                //重启页面的动画,避免用户看到重启时的闪屏
                getActivity().overridePendingTransition(R.anim.enter_alpha, R.anim.exit_alpha);
            } else {
                Toast.makeText(getActivity(), "定位权限未打开,请重新设置权限", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 跳转到设置权限页面
     */
    private void goToSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, GO_TO_SETTING_REQUEST_CODE);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onPunchSucceed() {
        loadingDialog.dismiss();
        remark.setText("");
        BaseMessage.showDialog(getActivity(), "打卡成功", "完成");                                        //调用接口项服务器回传数据
    }

    @Override
    public void onPunchFaild() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

        BaseMessage.showDialog(getActivity(), "打卡失败", "知道了");
    }


    @Override
    public void onLoginSucceed(LoginBean loginBean) {
        loadingDialog.dismiss();
        //第一次验证登录打卡成功以后把备注框设置为空
        remark.setText("");
        //保存姓名
        userNameShared.edit().putString("UserNameKey", loginBean.getObject().getName()).commit();
        //保存职位信息
        String positionName = loginBean.getObject().getPositionName();
        userMessage.edit().putString("positionName", positionName).commit();

        //把信息传给我的信息页面
        EventBus.getDefault().post(new UserMessageBean(userNameShared.getString("UserNameKey", null), positionName, loginBean.getObject().getPhone(), loginBean.getObject().getId()));

        //然后把用户的信息保存
        userMessage.edit().putString("id", loginBean.getObject().getId())
                .putString("name", loginBean.getObject().getName())
                .putString("orgCode", loginBean.getObject().getOrgCode())
                .putString("orgName", loginBean.getObject().getOrgName())
                .putString("phone", loginBean.getObject().getPhone())
                .putString("position", loginBean.getObject().getPosition())
                .putString("positionName", loginBean.getObject().getPositionName())
                .commit();

        //用户验证通过以后完成打卡
        LocationUtil.getLocation(MyApplication.getContext());
        LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
            @Override
            public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        location01.setText(s + ":");
                        location.setText(s1 + s2);
                        BaseMessage.showDialog(getActivity(), "信息验证成功\n\n打卡成功", "完成");                                        //调用接口项服务器回传数据
                    }
                });
            }
        });

        //把信息传给我的打卡页面
        RxBus.getInstance().post(new RefreshBean(RefreshBean.REFRESH_ALL_FRAGMENT));

    }

    @Override
    public void onLoginFaild(String result) {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        BaseMessage.showDialog(getActivity(), result, "知道了");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
