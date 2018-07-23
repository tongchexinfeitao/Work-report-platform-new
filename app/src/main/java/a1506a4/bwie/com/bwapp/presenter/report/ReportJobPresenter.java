package a1506a4.bwie.com.bwapp.presenter.report;

import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportBean;
import a1506a4.bwie.com.bwapp.model.reportModel.ReportJobModel;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.report.ReportJobView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportJobPresenter extends IPresenter<ReportJobView> {

    private ReportJobModel model;

    public ReportJobPresenter(ReportJobView view) {
        super(view);
    }

    @Override
    public void init() {
        model = new ReportJobModel();
    }

    public void commitZiXun(String userId, int reporttype, String StudentName,
                            String StudentPhone, int sex, String result,
                            String voucherPhone, String reception,
                            String remarks, String imagefile) {
        Observable<ReportBean> observable = model.commitZiXun(userId, reporttype, StudentName, StudentPhone, sex, result, voucherPhone, reception, remarks, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

    public void commitJiangZuo(String userId, int reporttype, int amount,
                               String site, String result,
                               String voucherPhone, String lecturer,
                               String remarks, String imagefile) {
        Observable<ReportBean> observable = model.commitJiangZuo(userId, reporttype, amount, site, result, voucherPhone, lecturer, remarks, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

    public void commitZhaoPin(String userId, int reporttype, int amount,
                              String site, String result,
                              String voucherPhone, String hrRecruiter,
                              String remarks, String imagefile) {
        Observable<ReportBean> observable = model.commitZhaoPin(userId, reporttype, amount, site, result, voucherPhone, hrRecruiter, remarks, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

    public void commitChuChai(String userId, int reporttype, String duration,
                              String site, String purpose,
                              String voucherPhone, String recruiter,
                              String remarks, String imagefile) {
        Observable<ReportBean> observable = model.commitChuChai(userId, reporttype, duration, site, purpose, voucherPhone, recruiter, remarks, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

    public void commitJiaFang(String userId, int reporttype, String parentName,
                              String parentPhone, String StudentName, String result,
                              String voucherPhone, String homeVisiting,
                              String remarks, String imagefile) {
        Observable<ReportBean> observable = model.commitJiaFang(userId, reporttype, parentName, parentPhone, StudentName, result, voucherPhone, homeVisiting, remarks, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

    public void commitBangFu(String userId, int reporttype, String site,
                             String content, String result,
                             String voucherPhone, String helpLeader,
                             String remarks, String imagefile) {
        Observable<ReportBean> observable = model.commitBangFu(userId, reporttype, site, content, result, voucherPhone, helpLeader, remarks, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

    public void commitHuiYi(String userId, int reporttype, int amount,
                            String site, String content, String result,
                            String voucherPhone, String emcee,
                            String remarks, String imagefile) {
        Observable<ReportBean> observable = model.commitHuiYi(userId, reporttype, amount, site, content, result, voucherPhone, emcee, remarks, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

    public void commitOther(String userId, int reporttype, String content, String imagefile) {
        Observable<ReportBean> observable = model.commitOther(userId, reporttype, content, imagefile);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean reportBean) throws Exception {
                        if (reportBean.getCode() == 200) {
                            view.onCommitSucceed();
                        } else if (reportBean.getCode() == 500) {
                            view.onCommitFilad();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onCommitFilad();
                        }
                    }
                });
    }

}
