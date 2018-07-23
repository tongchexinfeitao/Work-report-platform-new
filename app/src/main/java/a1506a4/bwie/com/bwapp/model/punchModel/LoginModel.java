package a1506a4.bwie.com.bwapp.model.punchModel;

import a1506a4.bwie.com.bwapp.constant.MyApi;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.LoginBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by 自信 on 2017/11/1.
 */

public class LoginModel {
    public Observable<LoginBean> Login(String phone, String username, int typeId, String address, String remarks) {
        return RetrofitManager.getInstance().create(MyApi.class).Login(phone, username, typeId, address, remarks);
    }
}
