package a1506a4.bwie.com.bwapp.presenter.punch;

import a1506a4.bwie.com.bwapp.model.punchModel.LoginModel;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.LoginBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.punch.LoginRequestView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 自信 on 2017/11/1.
 */

public class LoginPresenter extends IPresenter<LoginRequestView> {

    private LoginModel model;

    public LoginPresenter(LoginRequestView view) {
        super(view);
    }

    @Override
    public void init() {
        model = new LoginModel();
    }

    public void checkUserMessage(String phone, String username, int typeId, String address, String remarks) {
        Observable<LoginBean> login = model.Login(phone, username, typeId, address, remarks);

        login.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        int code = loginBean.getCode();
                        if (code == 200) {
                            if (view != null) {
                                view.onLoginSucceed(loginBean);
                            }
                        } else if (code == 500) {
                            if (view != null) {
                                view.onLoginFaild("用户信息\n验证失败");
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onLoginFaild("用户信息\n验证失败");
                        }
                    }
                });
    }
}
