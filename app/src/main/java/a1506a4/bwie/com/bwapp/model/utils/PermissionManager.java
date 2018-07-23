package a1506a4.bwie.com.bwapp.model.utils;

/**
 * Created by wxn on 2017/10/22.
 *
 *      定位权限工具类
 */

public class PermissionManager {

    /**
     * 请求返回码，用于做请求回调
     */
    public static final int LOCATION_REQUEST_CODE = 100;

    /**
     *
     * @param activity
     * @return  判断你是否拥有定位权限 ，没有就请求权限，并返回false
     */
   /* public static boolean CanLocation(Activity activity){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查是否拥有权限，申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义)
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                activity.requestPermissions(new String[] {
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                }, LOCATION_REQUEST_CODE);
                return false;
            }
            else {
                // 已拥有权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）

                return true;
            }
        }else {
            // 安卓手机版本在5.0时，配置清单中已申明权限，作相应处理，此处正对sdk版本低于23的手机

            return true;
        }



    }*/


}
