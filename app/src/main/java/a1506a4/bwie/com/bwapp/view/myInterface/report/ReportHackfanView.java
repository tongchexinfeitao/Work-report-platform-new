package a1506a4.bwie.com.bwapp.view.myInterface.report;

import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyLowarBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/8
 * 作用 :
 */

public interface ReportHackfanView extends IView {
    void onReportMyHackfanSucceed(ReportHackfanMyBean bean);

    void onReportLowarHackfanSucceed(ReportHackfanMyLowarBean bean);

    void onReportMyHackfanFaild();

    void onReportLowarHackfanFaild();
}
