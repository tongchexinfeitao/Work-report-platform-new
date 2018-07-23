package a1506a4.bwie.com.bwapp.view.myInterface.notification;

import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/7.
 */

public interface ReplyView extends IView {
    void replySuccessed(Boolean result);
    void replyError(String  error);
}
