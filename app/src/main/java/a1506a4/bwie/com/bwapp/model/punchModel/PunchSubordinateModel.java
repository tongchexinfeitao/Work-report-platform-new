package a1506a4.bwie.com.bwapp.model.punchModel;

import a1506a4.bwie.com.bwapp.constant.MyApi;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PuncheLowerBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class PunchSubordinateModel {
    public Observable<PuncheLowerBean> getMyLowerPuncheLog(String userId) {
        return RetrofitManager.getInstance().create(MyApi.class).getMyLowerPuncheLog(userId);
    }
}
