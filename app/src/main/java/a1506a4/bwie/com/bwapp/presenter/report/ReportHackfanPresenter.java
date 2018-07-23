package a1506a4.bwie.com.bwapp.presenter.report;

import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyLowarBean;
import a1506a4.bwie.com.bwapp.model.reportModel.ReportHackfanModel;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.report.ReportHackfanView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/8
 * 作用 :
 */

public class ReportHackfanPresenter extends IPresenter<ReportHackfanView> {

    private ReportHackfanModel model;

    public ReportHackfanPresenter(ReportHackfanView view) {
        super(view);
    }

    @Override
    public void init() {
        model = new ReportHackfanModel();
    }

    public void hackfanMyReport(String userId, String startDate, String endDate, int ReportTypeId) {
        Observable<ReportHackfanMyBean> observable = model.hackfanMyReport(userId, startDate, endDate, ReportTypeId);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportHackfanMyBean>() {
                    @Override
                    public void accept(ReportHackfanMyBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onReportMyHackfanSucceed(reportBean);
                        } else if (reportBean.getCode() == 500) {
                            view.onReportMyHackfanFaild();
                        }
                    }
                });
    }

    public void hackfanLowarReport(String userId, String startDate, String endDate, int ReportTypeId) {
        Observable<ReportHackfanMyLowarBean> observable = model.hackfanLowarReport(userId, startDate, endDate, ReportTypeId);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportHackfanMyLowarBean>() {
                    @Override
                    public void accept(ReportHackfanMyLowarBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            if (view != null) {
                                view.onReportLowarHackfanSucceed(reportBean);
                            }
                        } else if (reportBean.getCode() == 500) {
                            if (view != null) {
                                view.onReportLowarHackfanFaild();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onReportLowarHackfanFaild();
                        }
                    }
                });
    }
}
