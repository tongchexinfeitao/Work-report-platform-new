package a1506a4.bwie.com.bwapp.model.mineModel;

import a1506a4.bwie.com.bwapp.constant.NotificationApi;
import a1506a4.bwie.com.bwapp.model.bean.MineBean.StudentBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/7.
 */

public class StudentInfoModel {
    public Observable<StudentBean> getData(String userId){
        return RetrofitManager.getInstance().create(NotificationApi.class).queryGoodNews(userId);
    }
}
