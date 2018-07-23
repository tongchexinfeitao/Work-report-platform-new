package a1506a4.bwie.com.bwapp.model.bean.ReportBean;

import java.util.List;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/9
 * 作用 :
 */

public class ReportHackfanMyLowarBean {


    /**
     * code : 200
     * message : success
     * object : [{"id":72,"index":0,"orgname":"海淀区","reception":"13511122222","reporttype":"咨询","result":"效果","sendname":"县办主任丁原","sendtime":"2017-11-09 15:24:02","senduid":"ff808081426926a39ba25469","sex":"男","studentname":"张三","studentphone":"13012345111","typeid":1,"voucherphone":"证明人123777"},{"amount":"10","id":47,"index":0,"lecturer":"13511122223","orgname":"海淀区","reporttype":"讲座","result":"效果","sendname":"县办主任丁原","sendtime":"2017-11-09 15:07:55","senduid":"ff808081426926a39ba25469","site":"北京","typeid":2,"voucherphone":"证明人123777"},{"amount":"10","hrrecruiter":"13511122223","id":48,"index":0,"orgname":"海淀区","reporttype":"招聘","result":"效果","sendname":"县办主任丁原","sendtime":"2017-11-09 15:08:02","senduid":"ff808081426926a39ba25469","site":"北京","typeid":3,"voucherphone":"证明人123777"},{"duration":"3天","id":49,"index":0,"orgname":"海淀区","purpose":"目的","recruiter":"13511122223","reporttype":"出差","sendname":"县办主任丁原","sendtime":"2017-11-09 15:08:08","senduid":"ff808081426926a39ba25469","site":"北京","typeid":4,"voucherphone":"证明人123777"},{"homevisiting":"13511122223","id":50,"index":0,"orgname":"海淀区","parentname":"家长","parentphone":"112324244","reporttype":"家访","result":"效果","sendname":"县办主任丁原","sendtime":"2017-11-09 15:08:13","senduid":"ff808081426926a39ba25469","site":"北京","studentname":"stu","typeid":5,"voucherphone":"证明人123777"},{"content":"112323","helpleader":"13511122223","id":51,"index":0,"orgname":"海淀区","reporttype":"帮扶","result":"效果","sendname":"县办主任丁原","sendtime":"2017-11-09 15:08:17","senduid":"ff808081426926a39ba25469","site":"北京","typeid":6,"voucherphone":"证明人123777"},{"amount":"10","content":"123","emcee":"1","id":17,"index":0,"orgname":"海淀区","remarks":"","reporttype":"会议","result":"1","sendname":"县办主任丁原","sendtime":"2017-11-08 14:42:23","senduid":"ff808081426926a39ba25469","site":"1","typeid":7},{"content":"adsfjh","id":57,"index":0,"orgname":"海淀区","reporttype":"其它","sendname":"县办主任丁原","sendtime":"2017-11-09 15:23:06","senduid":"ff808081426926a39ba25469","typeid":8}]
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
         * id : 72
         * index : 0
         * orgname : 海淀区
         * reception : 13511122222
         * reporttype : 咨询
         * result : 效果
         * sendname : 县办主任丁原
         * sendtime : 2017-11-09 15:24:02
         * senduid : ff808081426926a39ba25469
         * sex : 男
         * studentname : 张三
         * studentphone : 13012345111
         * typeid : 1
         * voucherphone : 证明人123777
         * amount : 10
         * lecturer : 13511122223
         * site : 北京
         * hrrecruiter : 13511122223
         * duration : 3天
         * purpose : 目的
         * recruiter : 13511122223
         * homevisiting : 13511122223
         * parentname : 家长
         * parentphone : 112324244
         * content : 112323
         * helpleader : 13511122223
         * emcee : 1
         * remarks :
         */

        private int id;
        private int index;
        private String orgname;
        private String reception;
        private String reporttype;
        private String result;
        private String sendname;
        private String sendtime;
        private String senduid;
        private String sex;
        private String studentname;
        private String studentphone;
        private int typeid;
        private String voucherphone;
        private String amount;
        private String lecturer;
        private String site;
        private String hrrecruiter;
        private String duration;
        private String purpose;
        private String recruiter;
        private String homevisiting;
        private String parentname;
        private String parentphone;
        private String content;
        private String helpleader;
        private String emcee;
        private String remarks;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        public String getReception() {
            return reception;
        }

        public void setReception(String reception) {
            this.reception = reception;
        }

        public String getReporttype() {
            return reporttype;
        }

        public void setReporttype(String reporttype) {
            this.reporttype = reporttype;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getSendname() {
            return sendname;
        }

        public void setSendname(String sendname) {
            this.sendname = sendname;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public String getStudentphone() {
            return studentphone;
        }

        public void setStudentphone(String studentphone) {
            this.studentphone = studentphone;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getVoucherphone() {
            return voucherphone;
        }

        public void setVoucherphone(String voucherphone) {
            this.voucherphone = voucherphone;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getLecturer() {
            return lecturer;
        }

        public void setLecturer(String lecturer) {
            this.lecturer = lecturer;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getHrrecruiter() {
            return hrrecruiter;
        }

        public void setHrrecruiter(String hrrecruiter) {
            this.hrrecruiter = hrrecruiter;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getRecruiter() {
            return recruiter;
        }

        public void setRecruiter(String recruiter) {
            this.recruiter = recruiter;
        }

        public String getHomevisiting() {
            return homevisiting;
        }

        public void setHomevisiting(String homevisiting) {
            this.homevisiting = homevisiting;
        }

        public String getParentname() {
            return parentname;
        }

        public void setParentname(String parentname) {
            this.parentname = parentname;
        }

        public String getParentphone() {
            return parentphone;
        }

        public void setParentphone(String parentphone) {
            this.parentphone = parentphone;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHelpleader() {
            return helpleader;
        }

        public void setHelpleader(String helpleader) {
            this.helpleader = helpleader;
        }

        public String getEmcee() {
            return emcee;
        }

        public void setEmcee(String emcee) {
            this.emcee = emcee;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
