package a1506a4.bwie.com.bwapp.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import static a1506a4.bwie.com.bwapp.constant.BaseMessage.USER_MESSAGE;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/2
 * 作用 :
 */

public class UserInfoManager {
    private static UserInfo userInfo;
    private static Context context;
    private static UserInfoManager userInfoManager;

    public UserInfoManager(Context mActivity) {
        this.context = mActivity;
    }

    public static UserInfo getUserInfo() {
        if (userInfo == null || TextUtils.isEmpty(userInfo.getId())) {
            SharedPreferences sp = context.getSharedPreferences(USER_MESSAGE, Context.MODE_PRIVATE);
            String id = sp.getString("id", null);
            String name = sp.getString("name", null);
            String orgCode = sp.getString("orgCode", null);
            String orgName = sp.getString("orgName", null);
            String phone = sp.getString("phone", null);
            String position = sp.getString("position", null);
            String positionName = sp.getString("positionName", null);

            userInfo = new UserInfo(id, name, orgCode, orgName, phone, position, positionName);
        }
        return userInfo;
    }

    public static synchronized UserInfoManager getInstance(Context context) {
        if (userInfoManager == null) {
            synchronized (UserInfoManager.class) {
                if (userInfoManager == null) {
                    userInfoManager = new UserInfoManager(context);
                }
            }
        }
        return userInfoManager;
    }

    //刷新信息
    public static UserInfo refreshUserInfo() {
        userInfo = null;
        return getUserInfo();
    }
}
