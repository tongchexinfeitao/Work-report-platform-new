package a1506a4.bwie.com.bwapp.presenter.report;

import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLowerBean;
import a1506a4.bwie.com.bwapp.model.reportModel.ReportMyLowerModel;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.report.ReportMyLowerLogView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLowerPresenter extends IPresenter<ReportMyLowerLogView> {

    private ReportMyLowerModel model;

    public ReportMyLowerPresenter(ReportMyLowerLogView view) {
        super(view);
    }

    @Override
    public void init() {
        model = new ReportMyLowerModel();
    }

    public void getMylowerReportLog(String userId, String positions ) {
        Observable<ReportMyLowerBean> observable = model.getMylowerReportLog(userId,positions);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportMyLowerBean>() {
                    @Override
                    public void accept(ReportMyLowerBean reportMyLowerBean) throws Exception {
                        if (reportMyLowerBean.getCode() == 200 || reportMyLowerBean.getCode() == 500) {
                            if (view != null) {
                                view.onSelectLoverLogSucceed(reportMyLowerBean);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onSelectLoverLogFaild();
                        }
                    }
                });
    }
}
