package a1506a4.bwie.com.bwapp.view.myInterface.report;

import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLowerBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public interface ReportMyLowerLogView extends IView {
    void onSelectLoverLogSucceed(ReportMyLowerBean reportMyLowerBean);

    void onSelectLoverLogFaild();
}
