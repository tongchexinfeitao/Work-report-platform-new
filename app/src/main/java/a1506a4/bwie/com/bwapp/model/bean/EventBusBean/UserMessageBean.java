package a1506a4.bwie.com.bwapp.model.bean.EventBusBean;

/**
 * Created by 自信 on 2017/11/1.
 */

public class UserMessageBean {
    private String name;//
    private String position;//职位
    private String phone;
    private String userId;

    public UserMessageBean(String name, String position, String phone, String userId) {
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
