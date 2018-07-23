package a1506a4.bwie.com.bwapp.view.myInterface.punch;

import a1506a4.bwie.com.bwapp.model.bean.punchBean.LoginBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by 自信 on 2017/11/1.
 */

public interface LoginRequestView extends IView {
    void onLoginSucceed(LoginBean loginBean);

    void onLoginFaild(String result);
}
