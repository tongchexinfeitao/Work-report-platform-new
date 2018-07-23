package a1506a4.bwie.com.bwapp.model.reportModel;

import a1506a4.bwie.com.bwapp.constant.MyApi;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportJobModel {
    public Observable<ReportBean> commitZiXun(String userId, int reporttype, String StudentName,
                                              String StudentPhone, int sex, String result,
                                              String voucherPhone, String reception,
                                              String remarks, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitZiXun(userId, reporttype, StudentName, StudentPhone, sex, result, voucherPhone, reception, remarks, imagefile);
    }

    public Observable<ReportBean> commitJiangZuo(String userId, int reporttype, int amount,
                                                 String site, String result,
                                                 String voucherPhone, String lecturer,
                                                 String remarks, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitJiangZuo(userId, reporttype, amount, site, result, voucherPhone, lecturer, remarks, imagefile);
    }

    public Observable<ReportBean> commitZhaoPin(String userId, int reporttype, int amount,
                                                String site, String result,
                                                String voucherPhone, String hrRecruiter,
                                                String remarks, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitZhaoPin(userId, reporttype, amount, site, result, voucherPhone, hrRecruiter, remarks, imagefile);
    }

    public Observable<ReportBean> commitChuChai(String userId, int reporttype, String duration,
                                                String site, String purpose,
                                                String voucherPhone, String recruiter,
                                                String remarks, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitChuChai(userId, reporttype, duration, site, purpose, voucherPhone, recruiter, remarks, imagefile);
    }

    public Observable<ReportBean> commitJiaFang(String userId, int reporttype, String parentName,
                                                String parentPhone, String StudentName, String result,
                                                String voucherPhone, String homeVisiting,
                                                String remarks, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitJiaFang(userId, reporttype, parentName, parentPhone, StudentName, result, voucherPhone, homeVisiting, remarks, imagefile);
    }

    public Observable<ReportBean> commitBangFu(String userId, int reporttype, String site,
                                               String content, String result,
                                               String voucherPhone, String helpLeader,
                                               String remarks, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitBangFu(userId, reporttype, site, content, result, voucherPhone, helpLeader, remarks, imagefile);
    }

    public Observable<ReportBean> commitHuiYi(String userId, int reporttype, int amount,
                                              String site, String content, String result,
                                              String voucherPhone, String emcee,
                                              String remarks, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitHuiYi(userId, reporttype, amount, site, content, result, voucherPhone, emcee, remarks, imagefile);
    }

    public Observable<ReportBean> commitOther(String userId, int reporttype,
                                              String content, String imagefile) {
        return RetrofitManager.getInstance().create(MyApi.class).commitOther(userId, reporttype, content, imagefile);
    }
}
