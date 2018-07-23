package a1506a4.bwie.com.bwapp.model.punchModel;

import a1506a4.bwie.com.bwapp.constant.MyApi;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PunchingBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public class PunchModel {
    public Observable<PunchingBean> checkIn(String userId, int typeId, String address, String remarks) {
        return RetrofitManager.getInstance().create(MyApi.class).checkIn(typeId, address, userId,remarks);
    }
}
