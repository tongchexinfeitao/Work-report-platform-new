package a1506a4.bwie.com.bwapp.model.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by wxn on 2017/10/21.
 *
 * 用于定位的工具类
 */

public class LocationUtil {

    public static double mylongitude = -1.0;// 经度
    public static double mylatitude = -1.0;// 纬度
    public static String myProvince = null;// 省份
    public static String myCity = null;// 城市
    public static String myCounty = null;// 县
    public static String myCityadd = null;// 详细地址

    /**
     * 回调位置的接口定义
     *
     */
    public interface MyLocationListener {
        void myLocatin(double mylongitude, double mylatitude, String province, String city, String street);
    };

    public static MyLocationListener myLocationListener;

    /**
     * 回调位置方法
     *
     * @param myLocationListener
     */
    public static void setMyLocationListener(MyLocationListener myLocationListener) {
        LocationUtil.myLocationListener = myLocationListener;
    }

    /**
     * 获取当前位置
     *
     * @param context
     */
    public static void getLocation(final Context context) {
        //声明LocationClient类
        final LocationClient locationClient = new LocationClient(context);
        // 设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 是否打开GPS
        option.setCoorType("bd09ll"); // 设置返回值的坐标类型
        option.setScanSpan(5000); // 设置定时定位的时间间隔单。位毫秒
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationClient.setLocOption(option);
        // 注册位置监听器
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location == null) {
                    return;
                }
//                Log.e("========", "获取到的定位类型："+location.getLocType());
                if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    // 省市县
                    myProvince = location.getProvince();
                    myCity = location.getCity();
                    myCounty = location.getDistrict();
                    myCityadd = location.getStreet();//详细地址
                    // 经纬度
                    mylongitude = location.getLongitude();

                    myCounty = myCounty+" "+ myCityadd;
//                    Toast.makeText(context,myCityadd,Toast.LENGTH_LONG).show();
                    mylatitude = location.getLatitude();
                    if (myLocationListener != null) {
                        myLocationListener.myLocatin(mylongitude, mylatitude,myProvince, myCity, myCounty);
                        locationClient.stop();
                    }
                }
            }


        });

        locationClient.start();
        /*
         * 当所设的整数值大于等于1000（ms）时，定位SDK内部使用定时定位模式。调用requestLocation(
         * )后，每隔设定的时间，定位SDK就会进行一次定位。如果定位SDK根据定位依据发现位置没有发生变化，就不会发起网络请求，
         * 返回上一次定位的结果；如果发现位置改变，就进行网络请求进行定位，得到新的定位结果。
         * 定时定位时，调用一次requestLocation，会定时监听到定位结果。
         */
        locationClient.requestLocation();
    }






}
