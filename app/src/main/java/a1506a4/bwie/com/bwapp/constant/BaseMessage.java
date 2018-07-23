package a1506a4.bwie.com.bwapp.constant;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import a1506a4.bwie.com.bwapp.R;



/**
 * 作者: 赵虔
 * 时间: 2017/10/27
 * 41 市场部咨询员,100 市场部总监,102 市场部咨询室主任,426 市场招办主任,427 市场副县级招办主任,
 * 428 市场县级招办主任,429 市场县办主任,430 市场副经理级县办主任,431 市场经理级县办主任,
 * 432 市场区域经理,433 市场副总监级区域经理,434 市场总监级区域经理,435 县办咨询员,436 区域咨询员
 */

public class BaseMessage {
    public static String NUMBERPHONE = null;//用户手机号
    //验证成功以后保存用户信息的SharedPreferences的name
    public static final String USER_MESSAGE = "UserMessage88888888";
    public static final int REPORT_MY = 10;
    public static final int REPORT_LOWAR = 20;
    private static AlertDialog dialog;


    /**
     * @param activity   上下文
     * @param tipMessage 提示成功或者失败的信息
     * @param buttonText 按钮显示的文字
     */
    public static void showDialog(final Activity activity, String tipMessage, String buttonText) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        View view = View.inflate(activity, R.layout.dialog_succeed, null);
        TextView msg = (TextView) view.findViewById(R.id.message);
        Button dialogButton = (Button) view.findViewById(R.id.dialogButton);
        ImageView img = (ImageView) view.findViewById(R.id.dialogImg);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearLayout);
        layout.setBackgroundResource(R.drawable.backgroud_style01);
        msg.setText(tipMessage);
        dialogButton.setText(buttonText);

        dialog = new AlertDialog.Builder(activity, R.style.NoBackGroundDialog).setView(view).show();
        //弹出的时候让图片旋转并且缩放
        img.animate().rotation(360f)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(2000)
                .scaleX(1f)
                .scaleY(1f)
                .start();

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator rotation = ObjectAnimator.ofFloat(img, "rotation", 360);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 0f, 1.5f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img, "scaleY", 0f, 1.5f, 0f);
        animatorSet.play(rotation).with(scaleX).with(scaleY);

        animatorSet.setDuration(1500).start();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void showDialogAndFinish(final Activity activity, String tipMessage, String buttonText) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        View view = View.inflate(activity, R.layout.dialog_succeed, null);
        TextView msg = (TextView) view.findViewById(R.id.message);
        Button dialogButton = (Button) view.findViewById(R.id.dialogButton);
        ImageView img = (ImageView) view.findViewById(R.id.dialogImg);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearLayout);
        layout.setBackgroundResource(R.drawable.backgroud_style01);
        msg.setText(tipMessage);
        dialogButton.setText(buttonText);

        dialog = new AlertDialog.Builder(activity, R.style.NoBackGroundDialog).setView(view).show();
        //弹出的时候让图片旋转并且缩放
        img.animate().rotation(360f)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(2000)
                .scaleX(1f)
                .scaleY(1f)
                .start();

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator rotation = ObjectAnimator.ofFloat(img, "rotation", 360);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 0f, 1.5f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img, "scaleY", 0f, 1.5f, 0f);
        animatorSet.play(rotation).with(scaleX).with(scaleY);

        animatorSet.setDuration(1500).start();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.finish();
            }
        });
    }


    public static void itemDiaLog(final Context context, String message) {
        View view = View.inflate(context, R.layout.all_dialog_item, null);
        TextView dialogContent = (TextView) view.findViewById(R.id.dialogContent);
        Button dialogCommit = (Button) view.findViewById(R.id.dialogCommit);


        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.NoBackGroundDialog)
                .setView(view).show();

        dialogContent.setText(message);
        dialogCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
