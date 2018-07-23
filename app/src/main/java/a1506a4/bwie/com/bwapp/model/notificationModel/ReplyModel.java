package a1506a4.bwie.com.bwapp.model.notificationModel;


import a1506a4.bwie.com.bwapp.constant.NotificationApi;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.ReplyBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class ReplyModel {
    public Observable<ReplyBean> reply(String userId,String mid,String reply) {
        return RetrofitManager.getInstance().create(NotificationApi.class).setMessageReply(userId,mid,reply);
    }
}
