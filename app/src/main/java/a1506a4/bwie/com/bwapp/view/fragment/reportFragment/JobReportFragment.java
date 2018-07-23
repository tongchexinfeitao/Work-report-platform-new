package a1506a4.bwie.com.bwapp.view.fragment.reportFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.view.activity.ConsultActivity;
import a1506a4.bwie.com.bwapp.view.activity.OtherActivity;
import a1506a4.bwie.com.bwapp.view.activity.ReportActivity;

/**
 * 作者: 赵虔
 * 时间: 2017/10/21
 * 类作用:工作汇报
 */

public class JobReportFragment extends Fragment implements View.OnClickListener {
    private static final String[] types = {"咨询", "讲座", "招聘", "出差", "家访", "帮扶", "其他"};
    private View view;
    private Button dayReport;
    private Button meetingReport;
    private Button actionReport;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.report_job_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        dayReport = (Button) view.findViewById(R.id.dayReport);
        meetingReport = (Button) view.findViewById(R.id.meetingReport);
        actionReport = (Button) view.findViewById(R.id.actionReport);
        dayReport.setOnClickListener(this);
        meetingReport.setOnClickListener(this);
        actionReport.setOnClickListener(this);
    }

    //日常例会弹出的类型选择dialog
    private void showDialog() {
        //用来保存用户单选的类型
        View dialogView = View.inflate(getContext(), R.layout.dialog_report_type, null);
        final RadioGroup typeRadioGroup = (RadioGroup) dialogView.findViewById(R.id.typeRadioGroup);
        Button type_commit = (Button) dialogView.findViewById(R.id.type_commit);
        Button type_gouBack = (Button) dialogView.findViewById(R.id.type_gouBack);

        final AlertDialog dialog = new AlertDialog.Builder(getContext(), R.style.NoBackGroundDialog)
                .setView(dialogView)
                .setCancelable(true)
                .show();
        //设置第一个单选框默认选中
        for (int i = 0; i < types.length; i++) {
            RadioButton button = (RadioButton) typeRadioGroup.getChildAt(i);
            if (i == 0) {
                button.setChecked(true);
            } else {
                button.setChecked(false);
            }
        }
        //确定按钮
        type_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < types.length; i++) {
                    RadioButton button = (RadioButton) typeRadioGroup.getChildAt(i);
                    if (button.isChecked()) {
                        if (i == 0) {
                            Intent intent = new Intent(getActivity(), ConsultActivity.class);
                            startActivity(intent);
                        } else if (i == types.length - 1) {
                            Intent intent = new Intent(getActivity(), OtherActivity.class);
                            startActivity(intent);
                        } else {
                            String type = types[i];
                            Intent intent = new Intent(getActivity(), ReportActivity.class);
                            intent.putExtra("title", dayReport.getText());
                            intent.putExtra("type", type);
                            startActivity(intent);
                        }
                        dialog.dismiss();
                    }
                }
            }
        });
        //取消按钮
        type_gouBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dayReport://日汇报
                showDialog();
                break;
            case R.id.meetingReport://例会汇报
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                intent.putExtra("title", meetingReport.getText());
                intent.putExtra("type", "例会");
                startActivity(intent);
                break;
            case R.id.actionReport://活动汇报
                Intent intent2 = new Intent(getActivity(), ReportActivity.class);
                intent2.putExtra("title", actionReport.getText());
                intent2.putExtra("type", "活动");
                startActivity(intent2);
                break;
        }
    }
}
