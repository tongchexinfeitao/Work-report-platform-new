package a1506a4.bwie.com.bwapp.presenter.mine;

import a1506a4.bwie.com.bwapp.model.bean.VersionBean;
import a1506a4.bwie.com.bwapp.model.mineModel.CheckVersionModel;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.CheckVersionView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/15.
 */

public class CheckVersionPresenter extends IPresenter<CheckVersionView> {

    private CheckVersionModel checkVersionModel;

    public CheckVersionPresenter(CheckVersionView view) {
        super(view);
    }

    @Override
    public void init() {

        checkVersionModel = new CheckVersionModel();
    }

    public void checkVersion(final int version) {
        checkVersionModel.checkVersion(version)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {

                        //
                        if (view != null) {
                            if (versionBean.getCode() == 200) {
                                view.Upgrade(versionBean);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
