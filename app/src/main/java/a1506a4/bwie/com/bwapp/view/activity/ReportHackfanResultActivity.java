package a1506a4.bwie.com.bwapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.BaseMessage;
import a1506a4.bwie.com.bwapp.constant.MyDecoration;
import a1506a4.bwie.com.bwapp.constant.UserInfo;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyLowarBean;
import a1506a4.bwie.com.bwapp.presenter.report.ReportHackfanPresenter;
import a1506a4.bwie.com.bwapp.view.adapter.ReportLowarHackfanAdapter;
import a1506a4.bwie.com.bwapp.view.adapter.ReportMyHackfanAdapter;
import a1506a4.bwie.com.bwapp.view.myInterface.report.ReportHackfanView;

import static a1506a4.bwie.com.bwapp.constant.BaseMessage.REPORT_LOWAR;
import static a1506a4.bwie.com.bwapp.constant.BaseMessage.REPORT_MY;

public class ReportHackfanResultActivity extends AppCompatActivity implements View.OnClickListener, ReportHackfanView {

    private RecyclerView recyclerView;
    private int selectCode;
    private ReportHackfanPresenter presenter;
    private static final String[] types = {"咨询", "讲座", "招聘", "出差", "家访", "帮扶", "例会" ,"其它","活动"};
    private int reportType;
    private TextView selectTime;
    private TextView selectTitle;
    private StringBuilder sb = new StringBuilder();
    private ImageView selectResultNoContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_report_select_reslut);
        initView();

        Intent intent = getIntent();
        reportType = intent.getIntExtra("reportType", 1);
        String startTime = intent.getStringExtra("startTime");
        String endTime = intent.getStringExtra("endTime");
        selectCode = intent.getIntExtra("selectCode", REPORT_MY);
        selectTime.setText(startTime + "  一  " + endTime);
        selectTitle.setText(types[reportType - 1] + "  查询结果");
        UserInfo userInfo = UserInfoManager.getInstance(this).getUserInfo();
        if (!TextUtils.isEmpty(userInfo.getId())) {
            if (selectCode == REPORT_MY) {
                presenter.hackfanMyReport(userInfo.getId(), startTime, endTime, reportType);
            } else if (selectCode == REPORT_LOWAR) {
                presenter.hackfanLowarReport(userInfo.getId(), startTime, endTime, reportType);
            }
        } else {
            selectResultNoContent.setVisibility(View.VISIBLE);
        }

    }

    private void initView() {
        selectTime = (TextView) findViewById(R.id.selectTime);
        selectTitle = (TextView) findViewById(R.id.selectTitle);
        ImageView resultGoback = (ImageView) findViewById(R.id.resultGoback);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayout.VERTICAL));
        resultGoback.setOnClickListener(this);
        presenter = new ReportHackfanPresenter(this);
        selectResultNoContent = (ImageView) findViewById(R.id.selectResultNoContent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resultGoback:
                finish();
                break;
        }
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onReportMyHackfanSucceed(ReportHackfanMyBean bean) {
        final List<ReportHackfanMyBean.ObjectBean> list = bean.getObject();
        if (list != null && list.size() > 0) {
            selectResultNoContent.setVisibility(View.GONE);
            ReportMyHackfanAdapter adapter = new ReportMyHackfanAdapter(this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new ReportMyHackfanAdapter.onItemClickListener() {
                @Override
                public void onCLick(View v, int position) {
                    myHackfanDialog(position, list);
                }
            });
        } else {
            selectResultNoContent.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onReportLowarHackfanSucceed(ReportHackfanMyLowarBean bean) {
        final List<ReportHackfanMyLowarBean.ObjectBean> list = bean.getObject();
        if (list != null && list.size() > 0) {
            selectResultNoContent.setVisibility(View.GONE);
            ReportLowarHackfanAdapter adapter = new ReportLowarHackfanAdapter(this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new ReportLowarHackfanAdapter.onItemClickListener() {
                @Override
                public void onCLick(View v, int position) {
                    lowarHackfanDialog(position, list);
                }
            });
        } else {
            selectResultNoContent.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onReportMyHackfanFaild() {
        BaseMessage.showDialogAndFinish(this, "没有\n该时间段的记录", "知道了");
    }

    @Override
    public void onReportLowarHackfanFaild() {
        BaseMessage.showDialogAndFinish(this, "没有\n该时间段的记录", "知道了");
    }

    private void myHackfanDialog(int position, List<ReportHackfanMyBean.ObjectBean> list) {
        sb.delete(0, sb.length());
        ReportHackfanMyBean.ObjectBean bean = list.get(position);
        switch (types[reportType - 1]) {
            case "咨询":
                String studentname = bean.getStudentname();
                String studentphone = bean.getStudentphone();
                String sex = bean.getSex();
                String result = bean.getResult();
                String voucherphone = bean.getVoucherphone();
                String reception = bean.getReception();
                sb.append("学生姓名: " + studentname + "\n\n");
                sb.append("学生联系电话: " + studentphone + "\n\n");
                sb.append("性别: " + sex + "\n\n");
                sb.append("效果: " + result + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone + "\n\n");
                sb.append("接待人及电话: " + reception + "\n\n");
                break;
            case "讲座":
                String amount = bean.getAmount();
                String site = bean.getSite();
                String result1 = bean.getResult();
                String voucherphone1 = bean.getVoucherphone();
                String lecturer = bean.getLecturer();
                sb.append("参加人数: " + amount + "人\n\n");
                sb.append("地点: " + site + "\n\n");
                sb.append("效果: " + result1 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone1 + "\n\n");
                sb.append("讲座人及电话: " + lecturer + "\n\n");
                break;
            case "招聘":
                String amount1 = bean.getAmount();
                String site1 = bean.getSite();
                String result2 = bean.getResult();
                String voucherphone2 = bean.getVoucherphone();
                String hrrecruiter = bean.getHrrecruiter();
                sb.append("参加人数: " + amount1 + "人\n\n");
                sb.append("地点: " + site1 + "\n\n");
                sb.append("效果: " + result2 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone2 + "\n\n");
                sb.append("招聘主管人及电话: " + hrrecruiter + "\n\n");
                break;
            case "出差":
                String duration = bean.getDuration();
                String site2 = bean.getSite();
                String purpose = bean.getPurpose();
                String voucherphone3 = bean.getVoucherphone();
                String recruiter = bean.getRecruiter();
                sb.append("需用时长: " + duration + "\n\n");
                sb.append("地点: " + site2 + "\n\n");
                sb.append("目的: " + purpose + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone3 + "\n\n");
                sb.append("主管人及电话: " + recruiter + "\n\n");
                break;
            case "家访":
                String parentname = bean.getParentname();
                String parentphone = bean.getParentphone();
                String studentname1 = bean.getStudentname();
                String result3 = bean.getResult();
                String voucherphone4 = bean.getVoucherphone();
                String homevisiting = bean.getHomevisiting();
                sb.append("家长姓名: " + parentname + "\n\n");
                sb.append("家长联系电话: " + parentphone + "\n\n");
                sb.append("学生姓名: " + studentname1 + "\n\n");
                sb.append("效果: " + result3 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone4 + "\n\n");
                sb.append("负责家访领导及电话: " + homevisiting + "\n\n");
                break;
            case "帮扶":
                String site3 = bean.getSite();
                String content = bean.getContent();
                String result4 = bean.getResult();
                String voucherphone5 = bean.getVoucherphone();
                String helpleader = bean.getHelpleader();
                sb.append("地点: " + site3 + "\n\n");
                sb.append("具体内容: " + content + "\n\n");
                sb.append("效果: " + result4 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone5 + "\n\n");
                sb.append("帮扶领导及电话: " + helpleader + "\n\n");
                break;
            case "会议":
                String amount2 = bean.getAmount();
                String site4 = bean.getSite();
                String content1 = bean.getContent();
                String result5 = bean.getResult();
                String voucherphone6 = bean.getVoucherphone();
                String emcee = bean.getEmcee();
                sb.append("参加人数: " + amount2 + "\n\n");
                sb.append("地点: " + site4 + "\n\n");
                sb.append("具体内容: " + content1 + "\n\n");
                sb.append("效果: " + result5 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone6 + "\n\n");
                sb.append("会议主持人及电话: " + emcee + "\n\n");
                break;
            case "其它":
                String content2 = bean.getContent();
                sb.append("内容: " + content2 + "\n\n");
                break;
        }

        String sendtime = bean.getSendtime();
        sb.append("发送时间: " + sendtime + "\n\n");
        String remarks = bean.getRemarks();
        if (!TextUtils.isEmpty(remarks) && !("null".equals(remarks)))
            sb.append("备注: " + remarks + "\n");

        BaseMessage.itemDiaLog(this, sb.toString());
    }

    private void lowarHackfanDialog(int position, List<ReportHackfanMyLowarBean.ObjectBean> list) {
        sb.delete(0, sb.length());
        ReportHackfanMyLowarBean.ObjectBean bean = list.get(position);
        String sendname = bean.getSendname();
        String sendtime = bean.getSendtime();
        sb.append("发送人: " + sendname + "\n\n");
        sb.append("发送时间: " + sendtime + "\n\n");

        switch (types[reportType - 1]) {
            case "咨询":
                String studentname = bean.getStudentname();
                String studentphone = bean.getStudentphone();
                String sex = bean.getSex();
                String result = bean.getResult();
                String voucherphone = bean.getVoucherphone();
                String reception = bean.getReception();
                sb.append("学生姓名: " + studentname + "\n\n");
                sb.append("学生联系电话: " + studentphone + "\n\n");
                sb.append("性别: " + sex + "\n\n");
                sb.append("效果: " + result + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone + "\n\n");
                sb.append("接待人及电话: " + reception + "\n\n");
                break;
            case "讲座":
                String amount = bean.getAmount();
                String site = bean.getSite();
                String result1 = bean.getResult();
                String voucherphone1 = bean.getVoucherphone();
                String lecturer = bean.getLecturer();
                sb.append("参加人数: " + amount + "人\n\n");
                sb.append("地点: " + site + "\n\n");
                sb.append("效果: " + result1 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone1 + "\n\n");
                sb.append("讲座人及电话: " + lecturer + "\n\n");
                break;
            case "招聘":
                String amount1 = bean.getAmount();
                String site1 = bean.getSite();
                String result2 = bean.getResult();
                String voucherphone2 = bean.getVoucherphone();
                String hrrecruiter = bean.getHrrecruiter();
                sb.append("参加人数: " + amount1 + "人\n\n");
                sb.append("地点: " + site1 + "\n\n");
                sb.append("效果: " + result2 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone2 + "\n\n");
                sb.append("招聘主管人及电话: " + hrrecruiter + "\n\n");
                break;
            case "出差":
                String duration = bean.getDuration();
                String site2 = bean.getSite();
                String purpose = bean.getPurpose();
                String voucherphone3 = bean.getVoucherphone();
                String recruiter = bean.getRecruiter();
                sb.append("需用时长: " + duration + "\n\n");
                sb.append("地点: " + site2 + "\n\n");
                sb.append("目的: " + purpose + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone3 + "\n\n");
                sb.append("主管人及电话: " + recruiter + "\n\n");
                break;
            case "家访":
                String parentname = bean.getParentname();
                String parentphone = bean.getParentphone();
                String studentname1 = bean.getStudentname();
                String result3 = bean.getResult();
                String voucherphone4 = bean.getVoucherphone();
                String homevisiting = bean.getHomevisiting();
                sb.append("家长姓名: " + parentname + "\n\n");
                sb.append("家长联系电话: " + parentphone + "\n\n");
                sb.append("学生姓名: " + studentname1 + "\n\n");
                sb.append("效果: " + result3 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone4 + "\n\n");
                sb.append("负责家访领导及电话: " + homevisiting + "\n\n");
                break;
            case "帮扶":
                String site3 = bean.getSite();
                String content = bean.getContent();
                String result4 = bean.getResult();
                String voucherphone5 = bean.getVoucherphone();
                String helpleader = bean.getHelpleader();
                sb.append("地点: " + site3 + "\n\n");
                sb.append("具体内容: " + content + "\n\n");
                sb.append("效果: " + result4 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone5 + "\n\n");
                sb.append("帮扶领导及电话: " + helpleader + "\n\n");
                break;
            case "例会":
            case "活动":
                String amount2 = bean.getAmount();
                String site4 = bean.getSite();
                String content1 = bean.getContent();
                String result5 = bean.getResult();
                String voucherphone6 = bean.getVoucherphone();
                String emcee = bean.getEmcee();
                sb.append("参加人数: " + amount2 + "\n\n");
                sb.append("地点: " + site4 + "\n\n");
                sb.append("具体内容: " + content1 + "\n\n");
                sb.append("效果: " + result5 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone6 + "\n\n");
                sb.append("会议主持人及电话: " + emcee + "\n\n");
                break;
            case "其它":
                String content2 = bean.getContent();
                sb.append("内容: " + content2 + "\n\n");
                break;
        }

        String remarks = bean.getRemarks();
        if (!TextUtils.isEmpty(remarks) && !("null".equals(remarks)))
            sb.append("备注: " + remarks + "\n");

        BaseMessage.itemDiaLog(this, sb.toString());
    }

}
