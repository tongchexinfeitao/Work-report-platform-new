package a1506a4.bwie.com.bwapp.presenter.mine;

import a1506a4.bwie.com.bwapp.model.mineModel.StudentInfoModel;
import a1506a4.bwie.com.bwapp.model.bean.MineBean.StudentBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.adapter.StudentInfoView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/7.
 */

public class StudentInfoPresenter extends IPresenter<StudentInfoView> {

    private StudentInfoModel studentInfoModel;

    public StudentInfoPresenter(StudentInfoView view) {
        super(view);
    }

    @Override
    public void init() {
        studentInfoModel = new StudentInfoModel();
    }

    public void getStudentInfo(String userId) {
        studentInfoModel.getData(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StudentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StudentBean studentBean) {

                        if (view != null && studentBean.getCode() == 200) {
                            view.getStudentInfoSucced(studentBean);
                        } else {
                            view.getStudentInfoFailed("获取就业利好失败");
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
