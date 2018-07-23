package a1506a4.bwie.com.bwapp.presenter.report;

import a1506a4.bwie.com.bwapp.model.reportModel.ReportMyLogModel;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLogBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.report.ReprotMyLogView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLogPresenter extends IPresenter<ReprotMyLogView> {

    private ReportMyLogModel model;

    public ReportMyLogPresenter(ReprotMyLogView view) {
        super(view);
    }

    @Override
    public void init() {
        model = new ReportMyLogModel();
    }


    public void getMyReportLog(String userId ) {
        Observable<ReportMyLogBean> observable = model.getMyReportLog(userId);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportMyLogBean>() {
                    @Override
                    public void accept(ReportMyLogBean bean) throws Exception {
                        if (bean.getCode() == 200) {
                            if (view!=null){
                                view.onSelectRucceed(bean);
                            }
                        } else if (bean.getCode() == 500) {
                            if (view!=null){
                                view.onSelectFilad();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view!=null){
                            view.onSelectFilad();
                        }
                    }
                });
    }
}
