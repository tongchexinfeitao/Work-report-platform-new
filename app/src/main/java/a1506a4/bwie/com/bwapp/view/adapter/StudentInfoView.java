package a1506a4.bwie.com.bwapp.view.adapter;

import a1506a4.bwie.com.bwapp.model.bean.MineBean.StudentBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface StudentInfoView extends IView {

    void getStudentInfoSucced(StudentBean bean);

    void getStudentInfoFailed(String error);
}
