package a1506a4.bwie.com.bwapp.model.bean.ReportBean;

import java.util.List;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLogBean {


    /**
     * code : 200
     * message : success
     * object : [{"id":229,"typeid":8,"reporttype":"其它","parentname":null,"parentphone":null,"studentname":null,"studentphone":null,"sex":null,"amount":null,"duration":null,"site":null,"purpose":null,"content":"123","result":null,"voucherphone":null,"reception":null,"emcee":null,"lecturer":null,"hrrecruiter":null,"recruiter":null,"homevisiting":null,"helpleader":null,"sendtime":"2018-01-01 14:21:05","senduid":"ff8080814bcebd99b3b847ed","sendname":"区域经理程xx","recipientsnum":null,"remarks":null,"orgname":"青岛本部","orgcode":null,"deptname":null,"imagefileArray":null,"index":0,"data":"具体内容：123"}]
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
         * id : 229
         * typeid : 8
         * reporttype : 其它
         * parentname : null
         * parentphone : null
         * studentname : null
         * studentphone : null
         * sex : null
         * amount : null
         * duration : null
         * site : null
         * purpose : null
         * content : 123
         * result : null
         * voucherphone : null
         * reception : null
         * emcee : null
         * lecturer : null
         * hrrecruiter : null
         * recruiter : null
         * homevisiting : null
         * helpleader : null
         * sendtime : 2018-01-01 14:21:05
         * senduid : ff8080814bcebd99b3b847ed
         * sendname : 区域经理程xx
         * recipientsnum : null
         * remarks : null
         * orgname : 青岛本部
         * orgcode : null
         * deptname : null
         * imagefileArray : null
         * index : 0
         * data : 具体内容：123
         */

        private int id;
        private int typeid;
        private String reporttype;
        private Object parentname;
        private Object parentphone;
        private Object studentname;
        private Object studentphone;
        private Object sex;
        private Object amount;
        private Object duration;
        private Object site;
        private Object purpose;
        private String content;
        private Object result;
        private Object voucherphone;
        private Object reception;
        private Object emcee;
        private Object lecturer;
        private Object hrrecruiter;
        private Object recruiter;
        private Object homevisiting;
        private Object helpleader;
        private String sendtime;
        private String senduid;
        private String sendname;
        private Object recipientsnum;
        private Object remarks;
        private String orgname;
        private Object orgcode;
        private Object deptname;
        private Object imagefileArray;
        private int index;
        private String data;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getReporttype() {
            return reporttype;
        }

        public void setReporttype(String reporttype) {
            this.reporttype = reporttype;
        }

        public Object getParentname() {
            return parentname;
        }

        public void setParentname(Object parentname) {
            this.parentname = parentname;
        }

        public Object getParentphone() {
            return parentphone;
        }

        public void setParentphone(Object parentphone) {
            this.parentphone = parentphone;
        }

        public Object getStudentname() {
            return studentname;
        }

        public void setStudentname(Object studentname) {
            this.studentname = studentname;
        }

        public Object getStudentphone() {
            return studentphone;
        }

        public void setStudentphone(Object studentphone) {
            this.studentphone = studentphone;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getDuration() {
            return duration;
        }

        public void setDuration(Object duration) {
            this.duration = duration;
        }

        public Object getSite() {
            return site;
        }

        public void setSite(Object site) {
            this.site = site;
        }

        public Object getPurpose() {
            return purpose;
        }

        public void setPurpose(Object purpose) {
            this.purpose = purpose;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public Object getVoucherphone() {
            return voucherphone;
        }

        public void setVoucherphone(Object voucherphone) {
            this.voucherphone = voucherphone;
        }

        public Object getReception() {
            return reception;
        }

        public void setReception(Object reception) {
            this.reception = reception;
        }

        public Object getEmcee() {
            return emcee;
        }

        public void setEmcee(Object emcee) {
            this.emcee = emcee;
        }

        public Object getLecturer() {
            return lecturer;
        }

        public void setLecturer(Object lecturer) {
            this.lecturer = lecturer;
        }

        public Object getHrrecruiter() {
            return hrrecruiter;
        }

        public void setHrrecruiter(Object hrrecruiter) {
            this.hrrecruiter = hrrecruiter;
        }

        public Object getRecruiter() {
            return recruiter;
        }

        public void setRecruiter(Object recruiter) {
            this.recruiter = recruiter;
        }

        public Object getHomevisiting() {
            return homevisiting;
        }

        public void setHomevisiting(Object homevisiting) {
            this.homevisiting = homevisiting;
        }

        public Object getHelpleader() {
            return helpleader;
        }

        public void setHelpleader(Object helpleader) {
            this.helpleader = helpleader;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getSenduid() {
            return senduid;
        }

        public void setSenduid(String senduid) {
            this.senduid = senduid;
        }

        public String getSendname() {
            return sendname;
        }

        public void setSendname(String sendname) {
            this.sendname = sendname;
        }

        public Object getRecipientsnum() {
            return recipientsnum;
        }

        public void setRecipientsnum(Object recipientsnum) {
            this.recipientsnum = recipientsnum;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        public Object getOrgcode() {
            return orgcode;
        }

        public void setOrgcode(Object orgcode) {
            this.orgcode = orgcode;
        }

        public Object getDeptname() {
            return deptname;
        }

        public void setDeptname(Object deptname) {
            this.deptname = deptname;
        }

        public Object getImagefileArray() {
            return imagefileArray;
        }

        public void setImagefileArray(Object imagefileArray) {
            this.imagefileArray = imagefileArray;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
