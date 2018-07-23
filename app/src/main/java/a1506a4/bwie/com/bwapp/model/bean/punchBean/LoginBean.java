package a1506a4.bwie.com.bwapp.model.bean.punchBean;

/**
 * Created by 自信 on 2017/11/1.
 */

public class LoginBean {

    /**
     * code : 500
     * message : error
     * object : {"id":"ff8080814969974b6704a119","name":"区域经理吴xx","orgCode":"0016100400","orgName":"枣庄本部","position":"432","phone":"13001077225","positionName":"区域经理"}
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
         * id : ff8080814969974b6704a119
         * name : 区域经理吴xx
         * orgCode : 0016100400
         * orgName : 枣庄本部
         * position : 432
         * phone : 13001077225
         * positionName : 区域经理
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
