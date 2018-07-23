package a1506a4.bwie.com.bwapp.model.bean.notificationBean;

import java.util.List;

/**
 * Created by Shadow on 2017/11/10.
 */

public class StatusBean {


    /**
     * code : 200
     * message : success
     * object : [{"name":"区域经理程xx","orgname":"青岛本部","statusid":0,"reply":null},{"name":"区域经理赵虔","orgname":"枣庄本部","statusid":0,"reply":null},{"name":"区域经理王浩坤","orgname":"潍坊本部","statusid":1,"reply":null},{"name":"区域经理韦xx","orgname":"泰莱本部","statusid":0,"reply":null},{"name":"区域经理苏xx","orgname":"山东菏泽本部","statusid":0,"reply":null},{"name":"区域经理王xx","orgname":"聊城本部","statusid":0,"reply":null},{"name":"区域经理闵xx","orgname":"滨州本部","statusid":0,"reply":null}]
     */

    private int code;
    private String message;
    private List<ObjectBean> object;

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

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * name : 区域经理程xx
         * orgname : 青岛本部
         * statusid : 0
         * reply : null
         */

        private String name;
        private String orgname;
        private int statusid;
        private Object reply;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        public int getStatusid() {
            return statusid;
        }

        public void setStatusid(int statusid) {
            this.statusid = statusid;
        }

        public Object getReply() {
            return reply;
        }

        public void setReply(Object reply) {
            this.reply = reply;
        }
    }
}
