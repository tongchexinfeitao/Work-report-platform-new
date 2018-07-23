package a1506a4.bwie.com.bwapp.model.bean;

/**
 * Created by Shadow on 2017/11/7.
 */

public class RefreshBean {
    private int code;
    public static final int REFRESH_ALL_FRAGMENT = 1;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RefreshBean(int code) {
        this.code = code;
    }
}
