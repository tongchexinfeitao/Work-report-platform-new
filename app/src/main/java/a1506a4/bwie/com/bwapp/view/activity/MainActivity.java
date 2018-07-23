package a1506a4.bwie.com.bwapp.view.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.BaseMessage;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.EventBusBean.LocationBean;
import a1506a4.bwie.com.bwapp.model.bean.VersionBean;
import a1506a4.bwie.com.bwapp.model.utils.DownloadUtils;
import a1506a4.bwie.com.bwapp.model.utils.LocationUtil;
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import a1506a4.bwie.com.bwapp.presenter.mine.CheckVersionPresenter;
import a1506a4.bwie.com.bwapp.view.adapter.MyViewPagerAdapter;
import a1506a4.bwie.com.bwapp.view.circle.NoScrollViewPager;
import a1506a4.bwie.com.bwapp.view.fragment.mineFragment.MineFragment;
import a1506a4.bwie.com.bwapp.view.fragment.notificationFragment.NotificationFragment;
import a1506a4.bwie.com.bwapp.view.fragment.punchFragment.PunchFragment;
import a1506a4.bwie.com.bwapp.view.fragment.reportFragment.ReportFragment;
import a1506a4.bwie.com.bwapp.view.myInterface.CheckVersionView;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import rx.Observer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, CheckVersionView {
    private static final int GO_TO_SETTING_REQUEST_CODE = 125;
    //危险权限组
    String[] permissionGroup = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA
    };
    public static int REQUEST_CODE_PERMISSION = 0x00099;
    private RadioButton btn_punch_clock;
    private RadioButton btn_report;
    private RadioButton btn_notification;
    private RadioButton btn_mine;
    private NoScrollViewPager viewpager;
    private List<Fragment> list;
    //用来保存网络状态的SharedPreferences()的名字
    public static final String NETWORK_STATE_NAME = "NETWORK_STATE_NAME";
    //保存是否有网络的boolean值
    public static final String NETWORK_STATE_BOOLEAN = "NETWORK_STATE_BOOLEAN";
    //跳转网络设置的请求码
    public static final int SET_NETWORK_REQUEST = 106;
    private RelativeLayout networkLayout;
    private SharedPreferences networkSP;
    private RadioGroup radioGroup;
    private NetworkBroadcastReceiver broadcastReceiver;
    public QBadgeView qBadgeView;
    private Button button;

    private CheckVersionPresenter checkVersionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
//        getWindow().setStatusBarColor(getResources().getColor(R.color.lanse));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        initView();

        viewpager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), list));

        //注册监听网络状态的广播
        IntentFilter intent = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        broadcastReceiver = new NetworkBroadcastReceiver();
        this.registerReceiver(broadcastReceiver, intent);
        //动态申请权限
        requestPermission(permissionGroup, 116);
        //点击事件
        onClickListener();
    }

    private void onClickListener() {

        //设置radioGroup监听,选中的radioButton颜色变红
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton button = (RadioButton) radioGroup.getChildAt(i);
                    if (button.isChecked()) {
                        button.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        button.setTextColor(getResources().getColor(R.color.titleColor2));
                    }
                }
            }
        });
    }

    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION);
            } else {
//                permissionFail(REQUEST_CODE_PERMISSION);
                showTipsDialog();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断是不是跳转网络设置的请求码
        if (requestCode == SET_NETWORK_REQUEST || requestCode == GO_TO_SETTING_REQUEST_CODE) {
            //判断当前是否有网
            if (networkSP.getBoolean(NETWORK_STATE_BOOLEAN, false)) {
                //重启页面,完成定位
                recreate();
                //重启页面的动画,避免用户看到重启时的闪屏
                overridePendingTransition(R.anim.enter_alpha, R.anim.exit_alpha);
            }
        }
    }

    private void initView() {
        //检测版本是否为最新
        checkVersionPresenter = new CheckVersionPresenter(this);
        checkVersionPresenter.checkVersion(getVersionCode());
        //初始化控件
        btn_punch_clock = (RadioButton) findViewById(R.id.btn_punch_clock);
        btn_report = (RadioButton) findViewById(R.id.btn_report);
        btn_notification = (RadioButton) findViewById(R.id.btn_notification);
        btn_mine = (RadioButton) findViewById(R.id.btn_mine);
        button = (Button) findViewById(R.id.button);
        networkLayout = (RelativeLayout) findViewById(R.id.networkLayout);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        viewpager = (NoScrollViewPager) findViewById(R.id.ReportViewPager);


        //未读消息小红点
        qBadgeView = new QBadgeView(this);
        //绑定view
        qBadgeView.bindTarget(button);
        RxBus.getInstance().toObservable(Integer.class)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    //设置小红点number
                    @Override
                    public void onNext(Integer integer) {
                        qBadgeView.setBadgeNumber(integer);
                    }
                });

        //点击小红点  跳转到通知
        qBadgeView.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                viewpager.setCurrentItem(2);
            }
        });

        //不能滑动viewpager
        viewpager.setScroll(false);
        //预加载
        viewpager.setOffscreenPageLimit(10);
        //没有网络的时候显示的提示栏

        list = new ArrayList<>();
        initData();
        //保存网络状态的SharedPreferences
        networkSP = getSharedPreferences(NETWORK_STATE_NAME, MODE_PRIVATE);

        btn_notification.setOnClickListener(this);
        btn_report.setOnClickListener(this);
        btn_punch_clock.setOnClickListener(this);
        btn_mine.setOnClickListener(this);
        networkLayout.setOnClickListener(this);

    }

    private void initData() {
        list.add(new PunchFragment());
        list.add(new ReportFragment());
        list.add(new NotificationFragment());
        list.add(new MineFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_punch_clock:
                viewpager.setCurrentItem(0);
                break;
            case R.id.btn_report:
                viewpager.setCurrentItem(1);

                break;
            case R.id.btn_notification:
                viewpager.setCurrentItem(2);
                break;
            case R.id.btn_mine:
                viewpager.setCurrentItem(3);

                break;
            case R.id.networkLayout://显示没有网络的横幅,点击跳转设置页面
                Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                startActivityForResult(intent, SET_NETWORK_REQUEST);
                break;
        }


    }

    @Override
    public Context context() {
        return this;
    }

    //更新版本的回调
    @Override
    public void Upgrade(final VersionBean bean) {

        //自定义一个对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.NoBackGroundDialog);
        View view = View.inflate(this, R.layout.version_upgrade, null);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button btn_qd = (Button) view.findViewById(R.id.btn_qd);
        Button btn_qx = (Button) view.findViewById(R.id.btn_qx);
        btn_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下载新的app
                new DownloadUtils(MainActivity.this).download(bean.getObject(), "八维工作汇报平台");
                alertDialog.dismiss();
            }
        });
        btn_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


    }


    //用来检测网络的广播
    class NetworkBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    networkSP.edit().putBoolean(NETWORK_STATE_BOOLEAN, true).commit();
                    //有网的时候提示没网的标题影藏
                    networkLayout.setVisibility(View.GONE);
                } else {
                    networkSP.edit().putBoolean(NETWORK_STATE_BOOLEAN, false).commit();
                    //没网的时候显示没网的标题栏
                    networkLayout.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        unregisterReceiver(broadcastReceiver);
        if (list != null) {
            list.clear();
            list = null;
        }
    }


    @Override
    public void onBackPressed() {

        if (viewpager.getCurrentItem() == 0) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.cry)
                    .setTitle("退出程序")
                    .setMessage("您真的要退出了么")
                    .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("再玩会儿", null).create().show();

        } else {
            viewpager.setCurrentItem(0);
            btn_punch_clock.setChecked(true);
        }
    }


    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    public boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    public static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    public void showTipsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("警告:")
                .setMessage("当前应用缺少必要权限，无法正常使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    public void startAppSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, GO_TO_SETTING_REQUEST_CODE);
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        String nativePhoneNumber;
        //获取手机号
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        nativePhoneNumber = telephonyManager.getLine1Number();
        if (nativePhoneNumber.contains("+86")) {
            nativePhoneNumber = nativePhoneNumber.substring(3, nativePhoneNumber.length());
        }

        BaseMessage.NUMBERPHONE = nativePhoneNumber;
        // 第一次获取到权限，请求定位
        LocationUtil.getLocation(this);
        LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
            @Override
            public void myLocatin(double mylongitude, double mylatitude, final String province, final String city, final String street) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new LocationBean(province, city, street));
                    }
                });
            }
        });
        //读取用户的信息
        UserInfoManager.getInstance(this).getUserInfo();
    }

    /**
     * 权限获取失败
     *
     * @param requestCode
     */
    public void permissionFail(int requestCode) {
        //动态申请权限
        requestPermission(permissionGroup, 118);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //点击通知，程序杀死时打开 跳转到收到的通知界面
        getNotify(getIntent());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        //
        getNotify(intent);
        setIntent(intent);
    }

    private void getNotify(Intent intent) {
        //拿到这个value跳转到fragment
        String value = intent.getStringExtra("flag");
        //将value置空
        intent.putExtra("flag", "");
        if (!TextUtils.isEmpty(value)) {
            switch (value) {
                case "notify":

                    //跳转到通知
                    viewpager.setCurrentItem(2);
                    btn_notification.setChecked(true);
                    //发送一个值
                    RxBus.getInstance().post("receive");
                    break;
            }
        }
    }

    // TODO: 2017/11/15 进入主界面  检查版本号


    //拿到当前版本号
    private int getVersionCode() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
