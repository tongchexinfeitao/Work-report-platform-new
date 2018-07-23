package a1506a4.bwie.com.bwapp.model.bean.notificationBean;

/**
 * Created by Shadow on 2017/11/6.
 */

public class SendResultBean {

    /**
     * code : 200
     * message : success
     * object : {"id":"ff808081443d43ee39717198","name":"总监张xx","orgCode":"00161000","orgName":"山东基地","position":"100","phone":"13012300212","positionName":"市场部总监"}
     */

    private int code;
    private String message;
    private ObjectBean object;

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

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * id : ff808081443d43ee39717198
         * name : 总监张xx
         * orgCode : 00161000
         * orgName : 山东基地
         * position : 100
         * phone : 13012300212
         * positionName : 市场部总监
         */

        private String id;
        private String name;
        private String orgCode;
        private String orgName;
        private String position;
        private String phone;
        private String positionName;

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

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }
    }
}
