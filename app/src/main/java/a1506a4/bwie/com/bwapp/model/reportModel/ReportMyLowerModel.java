package a1506a4.bwie.com.bwapp.model.reportModel;

import a1506a4.bwie.com.bwapp.constant.MyApi;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLowerBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLowerModel {
    public Observable<ReportMyLowerBean> getMylowerReportLog(String userId,String positions) {
        return RetrofitManager.getInstance().create(MyApi.class).getMylowerReportLog(userId,positions);
    }
}
