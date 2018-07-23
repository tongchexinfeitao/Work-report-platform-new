package a1506a4.bwie.com.bwapp.model.notificationModel;


import a1506a4.bwie.com.bwapp.constant.NotificationApi;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.StatusBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class StatusModel {
    public Observable<StatusBean> getStatus(String userId,String mid, String position) {
        return RetrofitManager.getInstance().create(NotificationApi.class).queryOneSendMessageLog(userId,mid, position);
    }
}
