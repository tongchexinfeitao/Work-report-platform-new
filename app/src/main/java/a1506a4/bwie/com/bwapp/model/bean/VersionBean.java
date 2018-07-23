package a1506a4.bwie.com.bwapp.model.bean;

/**
 * Created by Shadow on 2017/11/15.
 */

public class VersionBean {


    /**
     * code : 200
     * message : 需要升级
     * object : http://111.205.104.180/bwie.1.apk
     */

    private int code;
    private String message;
    private String object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
