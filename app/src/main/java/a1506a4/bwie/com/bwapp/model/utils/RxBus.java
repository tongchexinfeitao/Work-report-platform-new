package a1506a4.bwie.com.bwapp.model.utils;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Shadow on 2017/10/24.
 */

public class RxBus {
    private Subject bus;
    public static RxBus instance;

    //实例化bus
    public RxBus() {
        bus = new SerializedSubject(PublishSubject.create());
    }

    //单例模式
    public static synchronized RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }

    //发送事件
    public void post(Object o) {
        bus.onNext(o);
    }

    //接收事件 转化为Observable

    public <T> Observable<T> toObservable(Class<T> clazz) {
        return bus.ofType(clazz);
    }
}
