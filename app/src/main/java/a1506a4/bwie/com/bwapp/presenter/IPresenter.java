package a1506a4.bwie.com.bwapp.presenter;

import android.content.Context;

import a1506a4.bwie.com.bwapp.app.MyApplication;
import a1506a4.bwie.com.bwapp.view.IView;

/**
 * Created by Shadow on 2017/10/13.
 */

public abstract class IPresenter<T extends IView> {

    public T view;

    public IPresenter(T view) {
        this.view = view;
        init();
    }

    public abstract void init();

    public Context getContext() {
        if (view != null && view.context() != null) {
            return view.context();
        }
        return MyApplication.getContext();
    }

    public void onDestroy() {
        if (view != null) {
            view = null;
        }
    }
}
