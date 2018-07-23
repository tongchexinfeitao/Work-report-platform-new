package a1506a4.bwie.com.bwapp.model.mineModel;

import a1506a4.bwie.com.bwapp.constant.CheckVersionApi;
import a1506a4.bwie.com.bwapp.model.bean.VersionBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/7.
 */

public class CheckVersionModel {
    public Observable<VersionBean> checkVersion(int version) {
        return RetrofitManager.getInstance().create(CheckVersionApi.class).checkVersion(version);
    }
}
