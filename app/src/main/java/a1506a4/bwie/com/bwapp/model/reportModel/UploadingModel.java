package a1506a4.bwie.com.bwapp.model.reportModel;


import a1506a4.bwie.com.bwapp.constant.MyApi;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ImageBean;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/14
 * 作用 :
 */

public class UploadingModel {
    public Observable<ImageBean> uploading(String userId, MultipartBody.Part file) {
        return RetrofitManager.getInstance().create(MyApi.class).uploadingImg(userId, file);
    }
}
