package a1506a4.bwie.com.bwapp.view.myInterface.notification;

import a1506a4.bwie.com.bwapp.model.bean.notificationBean.ReceiveMessageBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/7.
 */

public interface ReceiveMessageView extends IView {
    void getMessageSuccessed(ReceiveMessageBean bean);

    void getMessageFailed(String error);

    void changeStatus(int code);
}
