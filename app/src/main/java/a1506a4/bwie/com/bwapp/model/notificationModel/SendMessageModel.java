package a1506a4.bwie.com.bwapp.model.notificationModel;

import a1506a4.bwie.com.bwapp.constant.NotificationApi;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.MineSendMessageBean;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.SendResultBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class SendMessageModel {

    public Observable<SendResultBean> getData(String userId,String content,String position){

        return RetrofitManager.getInstance().create(NotificationApi.class).submitMessage(userId,content,position);

    }
    public Observable<MineSendMessageBean> getMineMessage(String userId){

        return RetrofitManager.getInstance().create(NotificationApi.class).querySendMessageLog(userId);

    }


}
