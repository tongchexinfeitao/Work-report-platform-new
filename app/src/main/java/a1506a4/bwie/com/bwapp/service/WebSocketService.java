package a1506a4.bwie.com.bwapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

import a1506a4.bwie.com.bwapp.constant.UserInfo;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.RefreshBean;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.NotificationBean;
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Shadow on 2017/10/24.
 */

public class WebSocketService extends Service {

    private static final String TEST_URL = "ws://111.205.104.180/bwiePush/websocket";
    private static final String REAL_URL = "ws://39.104.49.156/bwiePush/websocket";
    private static final String WEBSOCKT_URL = TEST_URL;
    URI uri = URI.create(WEBSOCKT_URL);
    public WebSocketClient webSocketClient;

    private static final String TAG = "TAG";


    @Override
    public void onCreate() {
        super.onCreate();
        initRefresh();
    }

    private void initRefresh() {
        CompositeSubscription subscription = new CompositeSubscription();
        Observable<RefreshBean> observable = RxBus.getInstance().toObservable(RefreshBean.class);
        Subscription id = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RefreshBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(RefreshBean bean) {
                        UserInfo userInfo = UserInfoManager.getInstance(WebSocketService.this).getUserInfo();
                        if (userInfo != null && !TextUtils.isEmpty(userInfo.getId())) {
                            webSocketClient.send("sid:" + userInfo.getId());
                        }
                    }
                });
        //保存订阅者
        subscription.add(id);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //当第一次进入时  创建webSocketClient并进行连接    websocket关闭就再次创建webSocketClient 进行连接
        if (webSocketClient == null || webSocketClient != null && webSocketClient.isClosed()) {


            webSocketClient = new WebSocketClient(uri) {
                //连接成功的时候
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                    UserInfo userInfo = UserInfoManager.getInstance(WebSocketService.this).getUserInfo();
                    if (userInfo != null && !TextUtils.isEmpty(userInfo.getId())) {
                        webSocketClient.send("sid:" + userInfo.getId());
                    }

                }

                //收到消息  或者发消息
                @Override
                public void onMessage(String json) {


                    NotificationBean bean = new Gson().fromJson(json, NotificationBean.class);
                    RxBus.getInstance().post(bean);

                    // SocketBean.sendBean(json);


                }

                //关闭
                @Override
                public void onClose(int i, String s, boolean b) {


                }

                //错误
                @Override
                public void onError(Exception e) {


                }
            };

            this.webSocketClient.connect();


        }


        //当进程被杀死的时候  service会重新创建
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webSocketClient != null) {
            webSocketClient.close(100, "service close");
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
