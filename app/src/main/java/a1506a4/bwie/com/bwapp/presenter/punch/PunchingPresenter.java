package a1506a4.bwie.com.bwapp.presenter.punch;

import a1506a4.bwie.com.bwapp.model.punchModel.PunchModel;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PunchingBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.punch.PunchingView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public class PunchingPresenter extends IPresenter<PunchingView> {

    private PunchModel model;

    public PunchingPresenter(PunchingView view) {
        super(view);
    }


    @Override
    public void init() {
        model = new PunchModel();
    }

    //打卡
    public void checkIn(String userId, int typeId, String address, String remarks) {
        Observable<PunchingBean> observable = model.checkIn(userId, typeId, address, remarks);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PunchingBean>() {
                    @Override
                    public void accept(PunchingBean punchingBean) throws Exception {
                        int code = punchingBean.getCode();
                        if (code == 200) {
                            if (view != null) {
                                view.onPunchSucceed();
                            }
                        } else if (code == 500) {
                            if (view != null) {
                                view.onPunchFaild();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onPunchFaild();
                        }
                    }
                });
    }

}
