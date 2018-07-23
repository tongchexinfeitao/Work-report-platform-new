package a1506a4.bwie.com.bwapp.model;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Shadow on 2017/10/13.
 */

public interface IModel {
    Observable<ResponseBody> register();

}
