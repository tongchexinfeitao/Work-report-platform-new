package a1506a4.bwie.com.bwapp.view.fragment.punchFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PunchMyBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.presenter.punch.PunchMyPresenter;
import a1506a4.bwie.com.bwapp.view.adapter.PunchMyAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;
import a1506a4.bwie.com.bwapp.view.myInterface.punch.PunchMyView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/2
 * 作用 :
 */

public class PunchMyFragment extends BaseFragment implements PunchMyView {

    private View view;
    private RecyclerView recyclerView;
    private PunchMyAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private PunchMyPresenter presenter;
    private StringBuilder sb = new StringBuilder();
    private List<PunchMyBean.ObjectBean> list;
    private LinearLayout myNoContent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.punch_my_fragment, null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //验证成功以后保存用户信息的
        UserInfo userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
        if (!TextUtils.isEmpty(userInfo.getId())) {
            //调用接口查询
            presenter.getMyPuncheLog(userInfo.getId());
        } else {
            myNoContent.setVisibility(View.VISIBLE);
        }
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                UserInfo userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
                if (!TextUtils.isEmpty(userInfo.getId())) {
                    //调用接口查询
                    presenter.getMyPuncheLog(userInfo.getId());
                }
                refreshLayout.finishRefresh();
            }
        });
    }


    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.myPunchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MyDecoration(getContext(), LinearLayout.VERTICAL));
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        //设置 Header 为 Material样式
        refreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));
        myNoContent = (LinearLayout) view.findViewById(R.id.myNoContent);
        presenter = new PunchMyPresenter(this);
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        presenter.getMyPuncheLog(userInfo.getId());
    }

    @Override
    protected IPresenter getPresebter() {
        return presenter;
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onSelectPunchLogSucceed(PunchMyBean punchMyBean) {
        list = punchMyBean.getObject();
        if (list != null && list.size() > 0) {
            myNoContent.setVisibility(View.GONE);
            if (adapter == null) {
                adapter = new PunchMyAdapter(getContext(), list);
                recyclerView.setAdapter(adapter);
            } else {
                adapter.refreshData(list);
            }
            itemClickDialog();
        } else {
            myNoContent.setVisibility(View.VISIBLE);
        }
    }

    private void itemClickDialog() {
        adapter.setOnItemClickListener(new PunchMyAdapter.onItemClickListener() {
            @Override
            public void onCLick(View v, int position) {
                sb.delete(0, sb.length());

                PunchMyBean.ObjectBean bean = list.get(position);
                String username = bean.getUsername();
                //   String[] split = bean.getAddress().split("\\)");
                String checktime = bean.getChecktime();
                int typeid = bean.getTypeid();
                String remarks = bean.getRemarks();

                sb.append("打卡人: " + username + "\n\n");
                String checktype = null;
                if (typeid == 1) {
                    checktype = "上下班打卡";
                } else if (typeid == 2) {
                    checktype = "出差打卡";
                }
                sb.append("打卡类型: " + checktype + "\n\n");
                if (bean.getAddress() == null || "".equals(bean.getAddress())){
                    sb.append("打卡位置: 未获取到\n\n");
                }else {
                    sb.append("打卡位置: " + bean.getAddress() + "\n\n");
                }

                //  sb.append("经纬度: " + split[0] + ")\n\n");
                sb.append("打卡时间: " + checktime + "\n\n");
                if (!TextUtils.isEmpty(remarks) && !("null".equals(remarks)))
                    sb.append("备注: " + remarks + "\n");

                BaseMessage.itemDiaLog(getContext(), sb.toString());
            }
        });
    }

    @Override
    public void onSelectPunchLogFiald() {
    }
}
