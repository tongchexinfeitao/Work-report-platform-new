package a1506a4.bwie.com.bwapp.view.myInterface.notification;

import a1506a4.bwie.com.bwapp.model.bean.notificationBean.SendResultBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface SendMessageView extends IView {

    void sendSuccessed(SendResultBean bean);

    void sendFailed(String error);
}
