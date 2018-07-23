package a1506a4.bwie.com.bwapp.presenter.notification;

import a1506a4.bwie.com.bwapp.model.bean.notificationBean.StatusBean;
import a1506a4.bwie.com.bwapp.model.notificationModel.StatusModel;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.StatusView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/10.
 */

public class StatusPresenter extends IPresenter<StatusView> {

    private StatusModel statusModel;

    public StatusPresenter(StatusView view) {
        super(view);
    }


    @Override
    public void init() {
        statusModel = new StatusModel();
    }

    public void getStatus(String userId,String mid, String position){

        statusModel.getStatus(userId,mid,position)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StatusBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusBean statusBean) {

                        if(statusBean.getCode()==200&&view!=null){
                            view.getStatusSucced(statusBean);
                        }else {
                            view.getStatusFailed("获取已阅状态失败");
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
