package a1506a4.bwie.com.bwapp.model.bean.EventBusBean;

/**
 * 作者: 赵虔
 * 时间: 2017/10/23
 * 类作用:用户同意定位权限以后使用EventBus传给打卡fragment页面的定位信息
 */

public class LocationBean {
    private String province;//省
    private String city;//市
    private String street;//县(区)

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public LocationBean(String province, String city, String street) {
        this.province = province;
        this.city = city;
        this.street = street;
    }
}
