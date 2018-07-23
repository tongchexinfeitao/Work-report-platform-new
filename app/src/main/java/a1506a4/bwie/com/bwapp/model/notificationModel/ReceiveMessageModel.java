package a1506a4.bwie.com.bwapp.model.notificationModel;

import a1506a4.bwie.com.bwapp.constant.NotificationApi;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.ChangeStatusBean;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.ReceiveMessageBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class ReceiveMessageModel {

    public Observable<ReceiveMessageBean> getData(String userId){

        return RetrofitManager.getInstance().create(NotificationApi.class).queryReceiveMessageLog(userId);

    }
    public Observable<ChangeStatusBean> changeStatus(String userId,int messageId){

        return RetrofitManager.getInstance().create(NotificationApi.class).changeMessageStatus(userId,messageId);

    }



}
