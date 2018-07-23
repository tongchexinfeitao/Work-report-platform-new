package a1506a4.bwie.com.bwapp.model.notificationModel;


import a1506a4.bwie.com.bwapp.constant.NotificationApi;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.LPLBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class GetLPLModel {
    public Observable<LPLBean> getLPL(String userId) {
        return RetrofitManager.getInstance().create(NotificationApi.class).getLowerPositionList(userId);
    }
}
