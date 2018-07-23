package a1506a4.bwie.com.bwapp.model.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by Shadow on 2017/11/20.
 */

public class ProgressBarUtils {

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static AlertDialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.progress_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.NoBackGroundDialog);
        AlertDialog loadingDialog = builder.setView(v).create();
        loadingDialog.setCanceledOnTouchOutside(false);
        return loadingDialog;

    }
}
