package a1506a4.bwie.com.bwapp.view.myInterface.mine;

import a1506a4.bwie.com.bwapp.model.bean.notificationBean.MineSendMessageBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface MineMessage extends IView {
    void getMineMessageSucced(MineSendMessageBean bean);
    void getMineMessageFailed(String error);
}
