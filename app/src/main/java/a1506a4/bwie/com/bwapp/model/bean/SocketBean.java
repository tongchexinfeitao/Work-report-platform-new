package a1506a4.bwie.com.bwapp.model.bean;


import com.google.gson.Gson;

import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import rx.Observable;
import rx.functions.Func1;


/**
 * Created by Shadow on 2017/10/24.
 */

public class SocketBean {
    // long m = 0x00000001;
    private static final String TAG = "TAG";
    //根据type码 判断类型
    long type;
    String json;


    public SocketBean(long type, String json) {
        this.type = type;
        this.json = json;
    }

    public long gettype() {
        return type;
    }

    public void settype(long type) {
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public static void sendBean(String json) {

        SocketBean socketBean = new Gson().fromJson(json, SocketBean.class);

        socketBean.setJson(json);
        RxBus.getInstance().post(socketBean);

    }


    public static <T> Observable<T> to(final Class<T> clazz, final long type) {

        Observable<T> observable = RxBus.getInstance().toObservable(SocketBean.class)

                .map(new Func1<SocketBean, T>() {
                    @Override
                    public T call(SocketBean socketBean) {
                        //根据type拿到对应的Bean
                        if (type == socketBean.gettype()) {
                            return new Gson().fromJson(socketBean.getJson(), clazz);
                        }
                        return null;
                    }
                })

                //过滤
                .filter(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(T t) {
                        return t != null;
                    }
                });

        return observable;


    }


    @Override
    public String toString() {
        return "SocketBean{" +
                "type=" + type +
                ", json='" + json + '\'' +
                '}';
    }
}



