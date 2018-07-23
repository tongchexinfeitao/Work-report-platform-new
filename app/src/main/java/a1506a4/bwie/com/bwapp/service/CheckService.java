package a1506a4.bwie.com.bwapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Shadow on 2017/10/25.
 */

public class CheckService extends Service {
    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //与服务器进行连接
        final Intent intent1 = new Intent(this,WebSocketService.class);
        //每隔5秒连接一次
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                startService(intent1);
            }
        },1000,5000);



        //如服务被关闭  自动创建
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁timer
        if(timer!=null){
            timer.cancel();
        }
    }
}
