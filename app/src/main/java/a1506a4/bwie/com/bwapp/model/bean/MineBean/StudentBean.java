package a1506a4.bwie.com.bwapp.model.bean.MineBean;

import java.util.List;

/**
 * Created by Shadow on 2017/11/7.
 */

public class StudentBean {


    /**
     * code : 200
     * message : success
     * object : [{"id":1,"studentname":"张三","collegename":"云计算","age":21,"sex":"男","address":"河南","salary":18000,"deptname":"晋中市场部","startworktime":"2017-10-11 19:52:44","company":"苏宁","updatetime":null,"updateid":null},{"id":2,"studentname":"李四","collegename":"大数据","age":28,"sex":"女","address":"河北","salary":13000,"deptname":"","startworktime":"2017-10-31 19:54:13","company":"京东","updatetime":null,"updateid":null},{"id":3,"studentname":"12","collegename":"云计算","age":11,"sex":"男","address":"22","salary":11,"deptname":"晋中市场部","startworktime":"","company":"33","updatetime":null,"updateid":null},{"id":4,"studentname":"赵六","collegename":"大数据","age":24,"sex":"男","address":"河北石家庄","salary":15000,"deptname":"晋中市场部","startworktime":"","company":"北京八维","updatetime":null,"updateid":null}]
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
         * id : 1
         * studentname : 张三
         * collegename : 云计算
         * age : 21
         * sex : 男
         * address : 河南
         * salary : 18000
         * deptname : 晋中市场部
         * startworktime : 2017-10-11 19:52:44
         * company : 苏宁
         * updatetime : null
         * updateid : null
         */

        private int id;
        private String studentname;
        private String collegename;
        private int age;
        private String sex;
        private String address;
        private int salary;
        private String deptname;
        private String startworktime;
        private String company;
        private Object updatetime;
        private Object updateid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public String getCollegename() {
            return collegename;
        }

        public void setCollegename(String collegename) {
            this.collegename = collegename;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getDeptname() {
            return deptname;
        }

        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }

        public String getStartworktime() {
            return startworktime;
        }

        public void setStartworktime(String startworktime) {
            this.startworktime = startworktime;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime = updatetime;
        }

        public Object getUpdateid() {
            return updateid;
        }

        public void setUpdateid(Object updateid) {
            this.updateid = updateid;
        }
    }
}
