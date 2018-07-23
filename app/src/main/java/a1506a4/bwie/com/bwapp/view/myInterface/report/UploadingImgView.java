package a1506a4.bwie.com.bwapp.view.myInterface.report;

import a1506a4.bwie.com.bwapp.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/14
 * 作用 :
 */

public interface UploadingImgView extends IView {
    void onUploadingSucceed(String imgName);

    void onUploadingFaild();
}
