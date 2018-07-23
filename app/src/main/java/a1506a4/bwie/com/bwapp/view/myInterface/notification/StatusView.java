package a1506a4.bwie.com.bwapp.view.myInterface.notification;

import a1506a4.bwie.com.bwapp.model.bean.notificationBean.StatusBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/10.
 */

public interface StatusView extends IView{

    void getStatusSucced(StatusBean bean);
    void getStatusFailed(String error);
}
