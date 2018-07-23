package a1506a4.bwie.com.bwapp.view.myInterface.punch;

import a1506a4.bwie.com.bwapp.model.bean.punchBean.PuncheLowerBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public interface punchSubordianateView extends IView {
    void onSelectSucceed(PuncheLowerBean puncheLowerBean);

    void onSelectFaild();
}
