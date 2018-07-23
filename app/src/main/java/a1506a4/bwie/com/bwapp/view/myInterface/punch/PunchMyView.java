package a1506a4.bwie.com.bwapp.view.myInterface.punch;

import a1506a4.bwie.com.bwapp.model.bean.punchBean.PunchMyBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/6
 * 作用 :
 */

public interface PunchMyView extends IView {
    void onSelectPunchLogSucceed(PunchMyBean punchMyBean);

    void onSelectPunchLogFiald();
}
