package a1506a4.bwie.com.bwapp.view.fragment.reportFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.BaseMessage;
import a1506a4.bwie.com.bwapp.constant.MyDecoration;
import a1506a4.bwie.com.bwapp.constant.UserInfo;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLogBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.presenter.report.ReportMyLogPresenter;
import a1506a4.bwie.com.bwapp.view.activity.ReprotHackfanActivity;
import a1506a4.bwie.com.bwapp.view.adapter.ReportMyAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;
import a1506a4.bwie.com.bwapp.view.myInterface.report.ReprotMyLogView;
import a1506a4.bwie.com.bwapp.view.widget.LowerPositionSelectView;

import static a1506a4.bwie.com.bwapp.constant.BaseMessage.REPORT_MY;

/**
 * 作者: 赵虔
 * 时间: 2017/10/25
 * 类作用:我自己发送的汇报
 */

public class ReportMyFragment extends BaseFragment implements View.OnClickListener, ReprotMyLogView {
    private View view;
    private RelativeLayout myMinuteReport;
    private RecyclerView recyclerView;
    private ReportMyAdapter adapter;
    private SmartRefreshLayout myRefreshLayout;
    private ReportMyLogPresenter presenter;
    private List<ReportMyLogBean.ObjectBean> list;
    private StringBuffer sb = new StringBuffer();
    private LinearLayout reportMyNoContent;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.report_my_fragment, null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            myMinuteReport.setVisibility(View.VISIBLE);
        } else {
            myMinuteReport.setVisibility(View.GONE);
        }

        //验证成功以后保存用户信息的
        UserInfo userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
        if (!TextUtils.isEmpty(userInfo.getId())) {
            //调用接口查询
            presenter.getMyReportLog(userInfo.getId());
        } else {
            reportMyNoContent.setVisibility(View.VISIBLE);
        }

        myRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                UserInfo userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
                if (!TextUtils.isEmpty(userInfo.getId())) {
                    //调用接口查询
                    presenter.getMyReportLog(userInfo.getId());
                }
                myRefreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        presenter.getMyReportLog(userInfo.getId());
    }

    @Override
    protected IPresenter getPresebter() {
        return presenter;
    }

    private void initView(View view) {
        //详细查询按钮的一栏
        myMinuteReport = (RelativeLayout) view.findViewById(R.id.myMinuteReport);
        recyclerView = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MyDecoration(getContext(), LinearLayout.VERTICAL));

        myRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.myRefreshLayout);

        //设置 Header 为 Material样式
        myRefreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));

        myMinuteReport.setOnClickListener(this);

        presenter = new ReportMyLogPresenter(this);
        reportMyNoContent = (LinearLayout) view.findViewById(R.id.reportMyNoContent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myMinuteReport://详细查询按钮
                Intent intent = new Intent(getActivity(), ReprotHackfanActivity.class);
                intent.putExtra("code", REPORT_MY);
                startActivity(intent);
                break;
        }
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onSelectRucceed(ReportMyLogBean bean) {
        list = bean.getObject();
        if (list != null && list.size() > 0) {
            reportMyNoContent.setVisibility(View.GONE);
            if (adapter == null) {
                adapter = new ReportMyAdapter(getContext(), list);
                adapter.setOnItemClickListener(new ReportMyAdapter.onItemClickListener() {
                    @Override
                    public void onCLick(View v, int position) {
                        myReportDialog(position);
                    }
                });
                recyclerView.setAdapter(adapter);
            } else {
                adapter.refreshData(list);
            }
        } else {
            reportMyNoContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSelectFilad() {

    }

    private void myReportDialog(int childPosition) {
        sb.delete(0, sb.length());
        ReportMyLogBean.ObjectBean objectBean = list.get(childPosition);
        switch (objectBean.getReporttype()) {
            case "咨询":
                String studentname = (String) list.get(childPosition).getStudentname();
                String studentphone = (String) list.get(childPosition).getStudentphone();
                String sex = (String) list.get(childPosition).getSex();
                String result = (String) list.get(childPosition).getResult();
                String voucherphone = (String) list.get(childPosition).getVoucherphone();
                String reception = (String) list.get(childPosition).getReception();
                sb.append("学生姓名: " + studentname + "\n\n");
                sb.append("学生联系电话: " + studentphone + "\n\n");
                sb.append("性别: " + sex + "\n\n");
                sb.append("效果: " + result + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone + "\n\n");
                sb.append("接待人及电话: " + reception + "\n\n");
                break;
            case "讲座":
                String amount = (String) list.get(childPosition).getAmount();
                String site = (String) list.get(childPosition).getSite();
                String result1 = (String) list.get(childPosition).getResult();
                String voucherphone1 = (String) list.get(childPosition).getVoucherphone();
                String lecturer = (String) list.get(childPosition).getLecturer();
                sb.append("参加人数: " + amount + "人\n\n");
                sb.append("地点: " + site + "\n\n");
                sb.append("效果: " + result1 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone1 + "\n\n");
                sb.append("讲座人及电话: " + lecturer + "\n\n");
                break;
            case "招聘":
                String amount1 = (String) list.get(childPosition).getAmount();
                String site1 = (String) list.get(childPosition).getSite();
                String result2 = (String) list.get(childPosition).getResult();
                String voucherphone2 = (String) list.get(childPosition).getVoucherphone();
                String hrrecruiter = (String) list.get(childPosition).getHrrecruiter();
                sb.append("参加人数: " + amount1 + "人\n\n");
                sb.append("地点: " + site1 + "\n\n");
                sb.append("效果: " + result2 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone2 + "\n\n");
                sb.append("招聘主管人及电话: " + hrrecruiter + "\n\n");
                break;
            case "出差":
                String duration = (String) list.get(childPosition).getDuration();
                String site2 = (String) list.get(childPosition).getSite();
                String purpose = (String) list.get(childPosition).getPurpose();
                String voucherphone3 = (String) list.get(childPosition).getVoucherphone();
                String recruiter = (String) list.get(childPosition).getRecruiter();
                sb.append("需用时长: " + duration + "\n\n");
                sb.append("地点: " + site2 + "\n\n");
                sb.append("目的: " + purpose + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone3 + "\n\n");
                sb.append("主管人及电话: " + recruiter + "\n\n");
                break;
            case "家访":
                String parentname = (String) list.get(childPosition).getParentname();
                String parentphone = (String) list.get(childPosition).getParentphone();
                String studentname1 = (String) list.get(childPosition).getStudentname();
                String result3 = (String) list.get(childPosition).getResult();
                String voucherphone4 = (String) list.get(childPosition).getVoucherphone();
                String homevisiting = (String) list.get(childPosition).getHomevisiting();
                sb.append("家长姓名: " + parentname + "\n\n");
                sb.append("家长联系电话: " + parentphone + "\n\n");
                sb.append("学生姓名: " + studentname1 + "\n\n");
                sb.append("效果: " + result3 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone4 + "\n\n");
                sb.append("负责家访领导及电话: " + homevisiting + "\n\n");
                break;
            case "帮扶":
                String site3 = (String) list.get(childPosition).getSite();
                String content = (String) list.get(childPosition).getContent();
                String result4 = (String) list.get(childPosition).getResult();
                String voucherphone5 = (String) list.get(childPosition).getVoucherphone();
                String helpleader = (String) list.get(childPosition).getHelpleader();
                sb.append("地点: " + site3 + "\n\n");
                sb.append("具体内容: " + content + "\n\n");
                sb.append("效果: " + result4 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone5 + "\n\n");
                sb.append("帮扶领导及电话: " + helpleader + "\n\n");
                break;
            case "例会":
            case "活动":
                String amount2 = (String) list.get(childPosition).getAmount();
                String site4 = (String) list.get(childPosition).getSite();
                String content1 = (String) list.get(childPosition).getContent();
                String result5 = (String) list.get(childPosition).getResult();
                String voucherphone6 = (String) list.get(childPosition).getVoucherphone();
                String emcee = (String) list.get(childPosition).getEmcee();
                sb.append("参加人数: " + amount2 + "\n\n");
                sb.append("地点: " + site4 + "\n\n");
                sb.append("具体内容: " + content1 + "\n\n");
                sb.append("效果: " + result5 + "\n\n");
                sb.append("证明人及联系电话: " + voucherphone6 + "\n\n");
                sb.append("会议主持人及电话: " + emcee + "\n\n");
                break;
            case "其它":
                String content2 = (String) list.get(childPosition).getContent();
                sb.append("内容: " + content2 + "\n\n");
                break;
        }

        String sendtime = list.get(childPosition).getSendtime();
        sb.append("发送时间: " + sendtime + "\n\n");
        String remarks = (String) list.get(childPosition).getRemarks();
        if (!TextUtils.isEmpty(remarks) && !("null".equals(remarks)))
            sb.append("备注: " + remarks + "\n");

        BaseMessage.itemDiaLog(getContext(), sb.toString());
    }


}
