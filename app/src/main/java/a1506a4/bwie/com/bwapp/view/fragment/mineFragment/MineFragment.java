package a1506a4.bwie.com.bwapp.view.fragment.mineFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.EventBusBean.UserMessageBean;
import a1506a4.bwie.com.bwapp.model.bean.MineBean.StudentBean;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.presenter.mine.StudentInfoPresenter;
import a1506a4.bwie.com.bwapp.view.activity.StudentInfoActivity;
import a1506a4.bwie.com.bwapp.view.adapter.StudentInfoAdapter;
import a1506a4.bwie.com.bwapp.view.adapter.StudentInfoView;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;


/**
 * Created by Shadow on 2017/10/13.
 */

public class MineFragment extends BaseFragment implements StudentInfoView {

    private View view;
    private ImageView iv_icon;
    private TextView tv_name;
    private TextView tv_type;
    private TextView tv_lh;
    private TextView tv_phone;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;
    private StudentInfoAdapter adapter;
    private StudentInfoPresenter studentInfoPresenter;
    private List<StudentBean.ObjectBean> list;
    private LinearLayout emptyLinear;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //读取用户的信息
        userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
        //如果职位码不是41 100 102 不能查看就业利好
        if (userInfo != null && userInfo.getPosition() != null) {
            int position = Integer.parseInt(userInfo.getPosition());
            if (position == 41 && position == 100 && position == 102) {
                studentInfoPresenter.getStudentInfo(userInfo.getId());
            } else {
                tv_lh.setText("");
            }

        }
        //设置用户姓名  职位  手机号
        if (userInfo.getPositionName() != null && userInfo.getName() != null) {
            tv_name.setText(userInfo.getName());
            tv_phone.setText("联系电话:" + userInfo.getPhone());
            tv_type.setText(userInfo.getPositionName());
        }


        //下拉刷新
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (userInfo != null && userInfo.getPosition() != null) {

                    int position = Integer.parseInt(userInfo.getPosition());
                    if (position == 41 && position == 100 && position == 102) {
                        studentInfoPresenter.getStudentInfo(userInfo.getId());
                    }
                }
                swiper.setRefreshing(false);
            }

        });


    }

    //父类的刷新  全局刷新
    @Override
    protected void onRefresh() {
        super.onRefresh();
        if (userInfo != null && userInfo.getPosition() != null) {

            int position = Integer.parseInt(userInfo.getPosition());
            if (position == 41 && position == 100 && position == 102) {
                studentInfoPresenter.getStudentInfo(userInfo.getId());
            } else {
                tv_lh.setText("");
            }
        }
    }

    private void initView(View view) {
        emptyLinear = (LinearLayout) view.findViewById(R.id.emptyLinear);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_lh = (TextView) view.findViewById(R.id.tv_lh);
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);

        studentInfoPresenter = new StudentInfoPresenter(this);

        //设置默认头像为圆形
      /*  RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(getContext()).load(R.mipmap.logo3_01).apply(options).into(iv_icon);*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        swiper = (SwipeRefreshLayout) view.findViewById(R.id.swiper);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUserMessage(UserMessageBean bean) {
        tv_name.setText(bean.getName());
        tv_phone.setText("联系电话:" + bean.getPhone());
        tv_type.setText(bean.getPosition());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected IPresenter getPresebter() {
        return null;
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void getStudentInfoSucced(StudentBean bean) {


        list = bean.getObject();
        if (list != null && list.size() > 0) {
            emptyLinear.setVisibility(View.GONE);
            if (adapter != null) {
                adapter.refreshData(list);
            } else {
                adapter = new StudentInfoAdapter(list);
            }
            recyclerView.setAdapter(adapter);
        }

        //点击item  把对应的学生信息传到另一个Activity
        adapter.setOnItemClickListener(new StudentInfoAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getContext(), StudentInfoActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("name", list.get(position).getStudentname());
                intent.putExtra("college", list.get(position).getCollegename());
                intent.putExtra("age", list.get(position).getAge());
                intent.putExtra("sex", list.get(position).getSex());
                intent.putExtra("address", list.get(position).getAddress());
                intent.putExtra("salary", list.get(position).getSalary());
                intent.putExtra("company", list.get(position).getCompany());
                intent.putExtra("startworktime;", list.get(position).getStartworktime());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getStudentInfoFailed(String error) {

        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
