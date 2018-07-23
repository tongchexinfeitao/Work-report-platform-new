package a1506a4.bwie.com.bwapp.constant;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/2
 * 作用 :
 */
public class UserInfo {
    private String id;
    private String name;
    private String orgCode;
    private String orgName;
    private String phone;
    private String position;
    private String positionName;

    public UserInfo() {
    }

    public UserInfo(String id, String name, String orgCode, String orgName, String phone, String position, String positionName) {
        this.id = id;
        this.name = name;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.phone = phone;
        this.position = position;
        this.positionName = positionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
