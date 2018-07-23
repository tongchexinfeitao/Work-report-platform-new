package a1506a4.bwie.com.bwapp.model.bean.ReportBean;

import java.util.List;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLowerBean {

    /**
     * code : 200
     * message : success
     * object : [{"name":"县办主任张xx","reportlogview":[{"id":4,"typeid":1,"reporttype":"咨询","parentname":null,"parentphone":null,"studentname":null,"studentphone":null,"sex":"男","amount":null,"duration":null,"site":null,"purpose":null,"content":null,"result":"很好","voucherphone":null,"reception":"接待人电话:13366668888","emcee":null,"lecturer":null,"hrrecruiter":null,"recruiter":null,"homevisiting":null,"helpleader":null,"sendtime":"2017-11-08 08:16:32","senduid":"bdb278f55501504c9d9574d4","sendname":"县办主任张xx","recipientsnum":null,"remarks":"完成","orgname":"市中区"}]}]
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
         * name : 县办主任张xx
         * reportlogview : [{"id":4,"typeid":1,"reporttype":"咨询","parentname":null,"parentphone":null,"studentname":null,"studentphone":null,"sex":"男","amount":null,"duration":null,"site":null,"purpose":null,"content":null,"result":"很好","voucherphone":null,"reception":"接待人电话:13366668888","emcee":null,"lecturer":null,"hrrecruiter":null,"recruiter":null,"homevisiting":null,"helpleader":null,"sendtime":"2017-11-08 08:16:32","senduid":"bdb278f55501504c9d9574d4","sendname":"县办主任张xx","recipientsnum":null,"remarks":"完成","orgname":"市中区"}]
         */

        private String name;
        private List<ReportlogviewBean> reportlogview;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ReportlogviewBean> getReportlogview() {
            return reportlogview;
        }

        public void setReportlogview(List<ReportlogviewBean> reportlogview) {
            this.reportlogview = reportlogview;
        }

        public static class ReportlogviewBean {
            /**
             * id : 4
             * typeid : 1
             * reporttype : 咨询
             * parentname : null
             * parentphone : null
             * studentname : null
             * studentphone : null
             * sex : 男
             * amount : null
             * duration : null
             * site : null
             * purpose : null
             * content : null
             * result : 很好
             * voucherphone : null
             * reception : 接待人电话:13366668888
             * emcee : null
             * lecturer : null
             * hrrecruiter : null
             * recruiter : null
             * homevisiting : null
             * helpleader : null
             * sendtime : 2017-11-08 08:16:32
             * senduid : bdb278f55501504c9d9574d4
             * sendname : 县办主任张xx
             * recipientsnum : null
             * remarks : 完成
             * orgname : 市中区
             */

            private int id;
            private int typeid;
            private String reporttype;
            private Object parentname;
            private Object parentphone;
            private Object studentname;
            private Object studentphone;
            private String sex;
            private Object amount;
            private Object duration;
            private Object site;
            private Object purpose;
            private Object content;
            private String result;
            private Object voucherphone;
            private String reception;
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
            private String remarks;
            private String orgname;

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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
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

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public Object getVoucherphone() {
                return voucherphone;
            }

            public void setVoucherphone(Object voucherphone) {
                this.voucherphone = voucherphone;
            }

            public String getReception() {
                return reception;
            }

            public void setReception(String reception) {
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

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getOrgname() {
                return orgname;
            }

            public void setOrgname(String orgname) {
                this.orgname = orgname;
            }
        }
    }
}
