package a1506a4.bwie.com.bwapp.presenter.punch;

import a1506a4.bwie.com.bwapp.model.punchModel.PunchSubordinateModel;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PuncheLowerBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.myInterface.punch.punchSubordianateView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public class PunchSubordinatePresenter extends IPresenter<punchSubordianateView> {


    private PunchSubordinateModel model;
    private Observable<PuncheLowerBean> observable;

    public PunchSubordinatePresenter(punchSubordianateView view) {
        super(view);
    }


    @Override
    public void init() {
        model = new PunchSubordinateModel();
    }

    //打卡
    public void selectMyLowerPuncheLog(String userId) {
        observable = model.getMyLowerPuncheLog(userId);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PuncheLowerBean>() {
                    @Override
                    public void accept(PuncheLowerBean puncheLowerBean) throws Exception {
                        if (puncheLowerBean.getCode() == 200) {
                            if (view != null) {
                                view.onSelectSucceed(puncheLowerBean);
                            }
                        } else if (puncheLowerBean.getCode() == 500) {
                            if (view != null) {
                                view.onSelectFaild();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onSelectFaild();
                        }
                    }
                });
    }
}
