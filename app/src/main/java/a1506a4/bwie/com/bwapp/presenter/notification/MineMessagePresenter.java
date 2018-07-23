package a1506a4.bwie.com.bwapp.presenter.notification;

import a1506a4.bwie.com.bwapp.model.notificationModel.SendMessageModel;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.MineSendMessageBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.mine.MineMessage;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/6.
 */

public class MineMessagePresenter extends IPresenter<MineMessage> {

    private static final String TAG = "TAG";
    private SendMessageModel sendMessageModel;

    public MineMessagePresenter(MineMessage view) {
        super(view);
    }

    @Override
    public void init() {
        sendMessageModel = new SendMessageModel();
    }

    public void mineSendMessage(String userId) {

        sendMessageModel.getMineMessage(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MineSendMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MineSendMessageBean mineSendMessageBean) {

                        if (view != null && mineSendMessageBean.getCode() == 200) {
                            view.getMineMessageSucced(mineSendMessageBean);
                        } else {
                            view.getMineMessageFailed("获取我的通知失败");
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
