package a1506a4.bwie.com.bwapp.model.reportModel;

import a1506a4.bwie.com.bwapp.constant.MyApi;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyLowarBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/8
 * 作用 :
 */

public class ReportHackfanModel {
    public Observable<ReportHackfanMyBean> hackfanMyReport(String userId,
                                                           String startDate,
                                                           String endDate,
                                                           int ReportTypeId) {
        return RetrofitManager.getInstance().create(MyApi.class).hackfanMyReport(userId, startDate, endDate, ReportTypeId);
    }

    public Observable<ReportHackfanMyLowarBean> hackfanLowarReport(String userId,
                                                                   String startDate,
                                                                   String endDate,
                                                                   int ReportTypeId) {
        return RetrofitManager.getInstance().create(MyApi.class).hackfanLowarReport(userId, startDate, endDate, ReportTypeId);
    }
}
