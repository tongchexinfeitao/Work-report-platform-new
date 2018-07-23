package a1506a4.bwie.com.bwapp.model.bean.notificationBean;

import java.util.List;

/**
 * Created by Shadow on 2017/11/6.
 */

public class LPLBean {


    /**
     * code : 200
     * message : success
     * object : [{"positioncode":"102","positionname":"咨询主任"},{"positioncode":"41","positionname":"咨询员"},{"positioncode":"432","positionname":"区域经理"},{"positioncode":"434","positionname":"总监级区域经理"},{"positioncode":"433","positionname":"副总监级区域经理"},{"positioncode":"436","positionname":"区域咨询员"},{"positioncode":"429","positionname":"县办主任"},{"positioncode":"431","positionname":"经理级县办主任"},{"positioncode":"430","positionname":"副经理级县办主任"},{"positioncode":"426","positionname":"招办主任"},{"positioncode":"428","positionname":"县级招办主任"},{"positioncode":"427","positionname":"副县级招办主任"}]
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
         * positioncode : 102
         * positionname : 咨询主任
         */

        private String positioncode;
        private String positionname;

        public String getPositioncode() {
            return positioncode;
        }

        public void setPositioncode(String positioncode) {
            this.positioncode = positioncode;
        }

        public String getPositionname() {
            return positionname;
        }

        public void setPositionname(String positionname) {
            this.positionname = positionname;
        }
    }
}
