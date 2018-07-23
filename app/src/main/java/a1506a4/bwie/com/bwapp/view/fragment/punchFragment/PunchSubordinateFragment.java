package a1506a4.bwie.com.bwapp.view.fragment.punchFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.BaseMessage;
import a1506a4.bwie.com.bwapp.constant.MyDecoration;
import a1506a4.bwie.com.bwapp.constant.UserInfo;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PuncheLowerBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.presenter.punch.PunchSubordinatePresenter;
import a1506a4.bwie.com.bwapp.view.adapter.PunchSubordinateAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;
import a1506a4.bwie.com.bwapp.view.myInterface.punch.punchSubordianateView;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/2
 * 作用 :
 */

public class PunchSubordinateFragment extends BaseFragment implements punchSubordianateView {

    private View view;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private PunchSubordinatePresenter presenter;
    private PunchSubordinateAdapter adapter;
    private EditText seekText;
    private List<PuncheLowerBean.ObjectBean> list;
    private List<PuncheLowerBean.ObjectBean> copyList;
    private StringBuilder sb = new StringBuilder();
    private LinearLayout lowarNoContent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.punch_subordinate_fragment, null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        copyList = new ArrayList<>();
        //验证成功以后保存用户信息的
        UserInfo userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
        if (!TextUtils.isEmpty(userInfo.getId())) {
            //调用接口查询
            presenter.selectMyLowerPuncheLog(userInfo.getId());
        } else {
            lowarNoContent.setVisibility(View.VISIBLE);
            seekText.setVisibility(View.GONE);
        }
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                //调用接口查询
                UserInfo userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
                if (!TextUtils.isEmpty(userInfo.getId())) {
                    //调用接口查询
                    presenter.selectMyLowerPuncheLog(userInfo.getId());
                }
                refreshLayout.finishRefresh();
            }
        });

        seekText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (submit()) {
                    //判断是否查询到了数据
                    boolean isSelect = false;
                    copyList.clear();
                    String seekTextString = seekText.getText().toString().trim();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getUsername().contains(seekTextString)) {
                            isSelect = true;
                            copyList.add(list.get(i));
                        }
                    }
                    //所有到结果
                    if (isSelect) {
                        adapter.refreshData(copyList);
                        itemClickDialog();
                    } else {
                        //没有所有到结果
                        try {
                            Thread.sleep(200);
                            Toast.makeText(getActivity(), "没有搜索到该用户", Toast.LENGTH_LONG).show();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    //调用接口查询
                    UserInfo userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
                    if (!TextUtils.isEmpty(userInfo.getId())) {
                        //调用接口查询
                        presenter.selectMyLowerPuncheLog(userInfo.getId());
                    }
                }
            }
        });
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        presenter.selectMyLowerPuncheLog(userInfo.getId());
    }

    @Override
    protected IPresenter getPresebter() {
        return presenter;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.subordinateRV);
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        //设置 Header 为 Material样式
        refreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MyDecoration(getContext(), LinearLayout.VERTICAL));
        presenter = new PunchSubordinatePresenter(this);
        seekText = (EditText) view.findViewById(R.id.seekText);
        lowarNoContent = (LinearLayout) view.findViewById(R.id.lowarNoContent);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onSelectSucceed(PuncheLowerBean puncheLowerBean) {
        list = puncheLowerBean.getObject();
        if (list != null && list.size() > 0) {
            lowarNoContent.setVisibility(View.GONE);
            seekText.setVisibility(View.VISIBLE);
            if (adapter == null) {
                adapter = new PunchSubordinateAdapter(getContext(), list);
                recyclerView.setAdapter(this.adapter);
            } else {
                adapter.refreshData(list);
            }
            itemClickDialog();
        } else {
            lowarNoContent.setVisibility(View.VISIBLE);
            seekText.setVisibility(View.GONE);
        }
    }

    private void itemClickDialog() {
        adapter.setOnItemClickListener(new PunchSubordinateAdapter.OnItemClickListener() {
            @Override
            public void myOnClick(View v, int position) {
                //关闭输入法
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                //清空StringBuffer
                sb.delete(0, sb.length());

                PuncheLowerBean.ObjectBean bean = list.get(position);
                String username = bean.getUsername();
                //String[] split = bean.getAddress().split("\\)");
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
                if (bean.getAddress() == null || "".equals(bean.getAddress())) {
                    sb.append("打卡位置: 未获取到地理位置\n\n");
                } else {
                    sb.append("打卡位置: " + bean.getAddress() + "\n\n");
                }
                //sb.append("经纬度: " + split[0] + ")\n\n");
                sb.append("打卡时间: " + checktime + "\n\n");
                if (!TextUtils.isEmpty(remarks) && !("null".equals(remarks)))
                    sb.append("备注: " + remarks + "\n");

                BaseMessage.itemDiaLog(getContext(), sb.toString());
            }
        });
    }

    @Override
    public void onSelectFaild() {

    }

    private boolean submit() {
        String seekTextString = seekText.getText().toString().trim();
        if (TextUtils.isEmpty(seekTextString)) {
            return false;
        }
        return true;
    }
}
