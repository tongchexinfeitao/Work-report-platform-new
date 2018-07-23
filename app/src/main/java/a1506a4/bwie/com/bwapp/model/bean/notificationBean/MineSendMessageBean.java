package a1506a4.bwie.com.bwapp.model.bean.notificationBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shadow on 2017/11/6.
 */

public class MineSendMessageBean implements Serializable {


    /**
     * code : 200
     * message : success
     * object : [{"id":29,"content":"123好咯","sendtime":"2017-11-10 14:29:06","sendnum":"ff808081532b32c2bad65790","sendname":"区域经理王浩坤","positionid":"432","positionname":"区域经理","topositions":"426,429,436,427,428,430,431","topositionlist":[{"positionId":"426","positionName":"招办主任"},{"positionId":"429","positionName":"县办主任"},{"positionId":"436","positionName":"区域咨询员"},{"positionId":"427","positionName":"副县级招办主任"},{"positionId":"428","positionName":"县级招办主任"},{"positionId":"430","positionName":"副经理级县办主任"},{"positionId":"431","positionName":"经理级县办主任"}]},{"id":28,"content":"5句可口可乐了了","sendtime":"2017-11-10 14:23:39","sendnum":"ff808081532b32c2bad65790","sendname":"区域经理王浩坤","positionid":"432","positionname":"区域经理","topositions":"426,429,436,427,428,430,431","topositionlist":[{"positionId":"426","positionName":"招办主任"},{"positionId":"429","positionName":"县办主任"},{"positionId":"436","positionName":"区域咨询员"},{"positionId":"427","positionName":"副县级招办主任"},{"positionId":"428","positionName":"县级招办主任"},{"positionId":"430","positionName":"副经理级县办主任"},{"positionId":"431","positionName":"经理级县办主任"}]},{"id":27,"content":"近近景近景","sendtime":"2017-11-10 14:16:35","sendnum":"ff808081532b32c2bad65790","sendname":"区域经理王浩坤","positionid":"432","positionname":"区域经理","topositions":"426,429,436,427,428,430,431","topositionlist":[{"positionId":"426","positionName":"招办主任"},{"positionId":"429","positionName":"县办主任"},{"positionId":"436","positionName":"区域咨询员"},{"positionId":"427","positionName":"副县级招办主任"},{"positionId":"428","positionName":"县级招办主任"},{"positionId":"430","positionName":"副经理级县办主任"},{"positionId":"431","positionName":"经理级县办主任"}]},{"id":26,"content":"可口可乐了","sendtime":"2017-11-10 14:15:08","sendnum":"ff808081532b32c2bad65790","sendname":"区域经理王浩坤","positionid":"432","positionname":"区域经理","topositions":"426,429,436,427,428,430,431","topositionlist":[{"positionId":"426","positionName":"招办主任"},{"positionId":"429","positionName":"县办主任"},{"positionId":"436","positionName":"区域咨询员"},{"positionId":"427","positionName":"副县级招办主任"},{"positionId":"428","positionName":"县级招办主任"},{"positionId":"430","positionName":"副经理级县办主任"},{"positionId":"431","positionName":"经理级县办主任"}]}]
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

    public static class ObjectBean implements Serializable{
        /**
         * id : 29
         * content : 123好咯
         * sendtime : 2017-11-10 14:29:06
         * sendnum : ff808081532b32c2bad65790
         * sendname : 区域经理王浩坤
         * positionid : 432
         * positionname : 区域经理
         * topositions : 426,429,436,427,428,430,431
         * topositionlist : [{"positionId":"426","positionName":"招办主任"},{"positionId":"429","positionName":"县办主任"},{"positionId":"436","positionName":"区域咨询员"},{"positionId":"427","positionName":"副县级招办主任"},{"positionId":"428","positionName":"县级招办主任"},{"positionId":"430","positionName":"副经理级县办主任"},{"positionId":"431","positionName":"经理级县办主任"}]
         */

        private int id;
        private String content;
        private String sendtime;
        private String sendnum;
        private String sendname;
        private String positionid;
        private String positionname;
        private String topositions;
        private List<TopositionlistBean> topositionlist;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getSendnum() {
            return sendnum;
        }

        public void setSendnum(String sendnum) {
            this.sendnum = sendnum;
        }

        public String getSendname() {
            return sendname;
        }

        public void setSendname(String sendname) {
            this.sendname = sendname;
        }

        public String getPositionid() {
            return positionid;
        }

        public void setPositionid(String positionid) {
            this.positionid = positionid;
        }

        public String getPositionname() {
            return positionname;
        }

        public void setPositionname(String positionname) {
            this.positionname = positionname;
        }

        public String getTopositions() {
            return topositions;
        }

        public void setTopositions(String topositions) {
            this.topositions = topositions;
        }

        public List<TopositionlistBean> getTopositionlist() {
            return topositionlist;
        }

        public void setTopositionlist(List<TopositionlistBean> topositionlist) {
            this.topositionlist = topositionlist;
        }

        public static class TopositionlistBean implements Serializable{
            /**
             * positionId : 426
             * positionName : 招办主任
             */

            private String positionId;
            private String positionName;

            public String getPositionId() {
                return positionId;
            }

            public void setPositionId(String positionId) {
                this.positionId = positionId;
            }

            public String getPositionName() {
                return positionName;
            }

            public void setPositionName(String positionName) {
                this.positionName = positionName;
            }
        }
    }
}
