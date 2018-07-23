package a1506a4.bwie.com.bwapp.constant;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import a1506a4.bwie.com.bwapp.R;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/15
 * 作用 :
 */

public class ProgressBarHorizontal {

    private static AlertDialog alertDialog;

    public static void horizontalProgressBar(Context context) {
        View view = View.inflate(context, R.layout.dialog_uploading, null);
        alertDialog = new AlertDialog.Builder(context, R.style.NoBackGroundDialog)
                .setView(view)
                .setCancelable(false)
                .show();

    }

    public static void dismessProgressBar() {
        alertDialog.dismiss();
    }

    public static boolean progressIsShow() {
        if (alertDialog != null && alertDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }
}
