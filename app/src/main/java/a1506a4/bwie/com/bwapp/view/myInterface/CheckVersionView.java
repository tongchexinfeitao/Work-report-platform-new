package a1506a4.bwie.com.bwapp.view.myInterface;

import a1506a4.bwie.com.bwapp.model.bean.VersionBean;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/11/15.
 */

public interface CheckVersionView extends IView {
    void Upgrade(VersionBean bean);
}
