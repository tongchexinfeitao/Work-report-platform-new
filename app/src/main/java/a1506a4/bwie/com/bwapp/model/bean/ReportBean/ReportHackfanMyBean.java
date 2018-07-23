package a1506a4.bwie.com.bwapp.model.bean.ReportBean;

import java.util.List;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/9
 * 作用 :
 */

public class ReportHackfanMyBean {

    /**
     * code : 200
     * message : success
     * object : [{"id":75,"index":0,"orgname":"枣庄本部","reception":"兔兔","remarks":"涂涂乐","reporttype":"咨询","result":"兔兔","sendname":"区域经理赵虔","sendtime":"2017-11-09 16:21:05","senduid":"ff8080814969974b6704a129","sex":"男","studentname":"急急急急","studentphone":"急急急","typeid":1,"voucherphone":"在我们"},{"amount":"1","id":30,"index":0,"lecturer":"就来找我","orgname":"枣庄本部","remarks":"历经磨难","reporttype":"讲座","result":"555","sendname":"区域经理赵虔","sendtime":"2017-11-08 15:24:34","senduid":"ff8080814969974b6704a129","site":"1111","typeid":2},{"amount":"22","hrrecruiter":"我下午","id":77,"index":0,"orgname":"枣庄本部","remarks":"谭咏麟","reporttype":"招聘","result":"2555","sendname":"区域经理赵虔","sendtime":"2017-11-09 16:23:02","senduid":"ff8080814969974b6704a129","site":"22","typeid":3,"voucherphone":"8888"},{"duration":"6669","id":25,"index":0,"orgname":"枣庄本部","purpose":"55555","recruiter":"兔兔看看","remarks":"凸透镜快乐快乐","reporttype":"出差","sendname":"区域经理赵虔","sendtime":"2017-11-08 15:07:32","senduid":"ff8080814969974b6704a129","site":"566555","typeid":4},{"homevisiting":"咯某托","id":79,"index":0,"orgname":"枣庄本部","parentname":"模具他","parentphone":"默默看","remarks":"弄哭了","reporttype":"家访","result":"涂抹哦哦","sendname":"区域经理赵虔","sendtime":"2017-11-09 16:24:00","senduid":"ff8080814969974b6704a129","studentname":"落寞","typeid":5,"voucherphone":"同模"},{"content":"涂涂乐","helpleader":"据统计哪里","id":80,"index":0,"orgname":"枣庄本部","remarks":"涂涂乐","reporttype":"帮扶","result":"铝土矿","sendname":"区域经理赵虔","sendtime":"2017-11-09 16:24:21","senduid":"ff8080814969974b6704a129","site":"咯可怜","typeid":6,"voucherphone":"落寞哦"},{"amount":"10","content":"季度会议总结","emcee":"李县办:13366668888","id":11,"index":0,"orgname":"枣庄本部","remarks":"完成","reporttype":"会议","result":"全满完成","sendname":"区域经理赵虔","sendtime":"2017-11-08 08:21:04","senduid":"ff8080814969974b6704a129","site":"第三报告厅","typeid":7},{"content":"测试测试","id":45,"index":0,"orgname":"枣庄本部","reporttype":"其它","sendname":"区域经理赵虔","sendtime":"2017-11-09 13:49:36","senduid":"ff8080814969974b6704a129","typeid":8}]
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
         * id : 75
         * index : 0
         * orgname : 枣庄本部
         * reception : 兔兔
         * remarks : 涂涂乐
         * reporttype : 咨询
         * result : 兔兔
         * sendname : 区域经理赵虔
         * sendtime : 2017-11-09 16:21:05
         * senduid : ff8080814969974b6704a129
         * sex : 男
         * studentname : 急急急急
         * studentphone : 急急急
         * typeid : 1
         * voucherphone : 在我们
         * amount : 1
         * lecturer : 就来找我
         * site : 1111
         * hrrecruiter : 我下午
         * duration : 6669
         * purpose : 55555
         * recruiter : 兔兔看看
         * homevisiting : 咯某托
         * parentname : 模具他
         * parentphone : 默默看
         * content : 涂涂乐
         * helpleader : 据统计哪里
         * emcee : 李县办:13366668888
         */

        private int id;
        private int index;
        private String orgname;
        private String reception;
        private String remarks;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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
    }
}
