package a1506a4.bwie.com.bwapp.model.bean.punchBean;

import java.util.List;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/6
 * 作用 :
 */

public class PunchMyBean {


    /**
     * code : 200
     * message : success
     * object : [{"id":17,"address":"(116.305959,40.046501)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:53:54","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":2,"statusid":null},{"id":16,"address":"(116.305959,40.046501)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:53:52","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":15,"address":"(116.305934,40.046516)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:52:39","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":14,"address":"(116.305934,40.046516)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:52:37","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":2,"statusid":null},{"id":13,"address":"(116.305934,40.046516)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:52:34","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":12,"address":"(116.305934,40.046516)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:52:30","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":11,"address":"(116.305924,40.046534)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:50:04","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":10,"address":"(116.305933,40.046521)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:47:37","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":9,"address":"(116.305959,40.046522)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:45:35","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":2,"statusid":null},{"id":8,"address":"(116.305959,40.046522)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:45:32","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":7,"address":"(116.305959,40.046522)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:45:11","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":6,"address":"(116.30628,40.046596)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:41:58","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":5,"address":"(116.306357,40.046634)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:41:00","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":3,"address":"(116.306265,40.046574)北京市海淀区唐家岭东路","checktime":"2017-11-06 15:39:10","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":"","typeid":1,"statusid":null},{"id":2,"address":"(116.304879,40.048817)北京市海淀区东北旺南路26号-2幢-平房102-b","checktime":"2017-11-06 15:37:22","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":null,"typeid":1,"statusid":null},{"id":1,"address":"(116.304879,40.048817)北京市海淀区东北旺南路26号-2幢-平房102-b","checktime":"2017-11-06 15:31:25","userid":"ff8080814969974b6704a129","username":"区域经理赵虔","remarks":null,"typeid":1,"statusid":null}]
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
         * id : 17
         * address : (116.305959,40.046501)北京市海淀区唐家岭东路
         * checktime : 2017-11-06 15:53:54
         * userid : ff8080814969974b6704a129
         * username : 区域经理赵虔
         * remarks :
         * typeid : 2
         * statusid : null
         */

        private int id;
        private String address;
        private String checktime;
        private String userid;
        private String username;
        private String remarks;
        private int typeid;
        private Object statusid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getChecktime() {
            return checktime;
        }

        public void setChecktime(String checktime) {
            this.checktime = checktime;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public Object getStatusid() {
            return statusid;
        }

        public void setStatusid(Object statusid) {
            this.statusid = statusid;
        }
    }
}
