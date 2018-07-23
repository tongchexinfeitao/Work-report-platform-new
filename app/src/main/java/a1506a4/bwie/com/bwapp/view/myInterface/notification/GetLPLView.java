package a1506a4.bwie.com.bwapp.view.myInterface.notification;

import a1506a4.bwie.com.bwapp.model.bean.notificationBean.LPLBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface GetLPLView extends IView {
    void getDataSuccessed(LPLBean bean);

    void getDataFailed(String error);
}
