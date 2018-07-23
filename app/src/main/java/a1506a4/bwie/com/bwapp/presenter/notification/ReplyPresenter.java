package a1506a4.bwie.com.bwapp.presenter.notification;

import a1506a4.bwie.com.bwapp.model.bean.notificationBean.ReplyBean;
import a1506a4.bwie.com.bwapp.model.notificationModel.ReplyModel;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.ReplyView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/6.
 */

public class ReplyPresenter extends IPresenter<ReplyView> {

    private static final String TAG = "TAG";
    private ReplyModel replyModel;

    public ReplyPresenter(ReplyView view) {
        super(view);
    }


    @Override
    public void init() {
        replyModel = new ReplyModel();
    }

    public void reply(String userId, String mid, String reply) {
        replyModel.reply(userId, mid, reply)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReplyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReplyBean bean) {
                        if (view != null && bean.getCode() == 200) {
                            view.replySuccessed(true);
                        } else {
                            view.replySuccessed(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        view.replyError("未知错误，请稍候再试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
