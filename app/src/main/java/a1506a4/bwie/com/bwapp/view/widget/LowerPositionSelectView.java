package a1506a4.bwie.com.bwapp.view.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.UserInfo;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.LPLBean;
import a1506a4.bwie.com.bwapp.presenter.notification.GetLPLPresenter;
import a1506a4.bwie.com.bwapp.view.adapter.MyRecyclerViewAdapter;
import a1506a4.bwie.com.bwapp.view.myInterface.notification.GetLPLView;

/**
 * Created by hasee on 2018/1/1.
 */

public class LowerPositionSelectView extends FrameLayout implements View.OnClickListener, GetLPLView {
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private Button btn_sendAll_notify;
    private TextView tv_selectAll;
    private GetLPLPresenter getLPLPresenter;
    private List<LPLBean.ObjectBean> list;
    private LinearLayout emptyLinear;


    public LowerPositionSelectView(@NonNull Context context) {
        super(context);
    }

    public LowerPositionSelectView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LowerPositionSelectView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = inflate(context, R.layout.lower_position_select_view, this);
        getLPLPresenter = new GetLPLPresenter(this);
        emptyLinear = (LinearLayout) view.findViewById(R.id.emptyLinear);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        btn_sendAll_notify = (Button) view.findViewById(R.id.btn_sendAll_notify);
        tv_selectAll = (TextView) view.findViewById(R.id.tv_selectAll);
        btn_sendAll_notify.setOnClickListener(this);
        tv_selectAll.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        initData();
    }

    private void initData() {
        final UserInfo userInfo = UserInfoManager.getInstance(getContext()).getUserInfo();
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getId())) {
            getLPLPresenter.getLPL(userInfo.getId());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendAll_notify:
                //拿到选中的下属职位码
                String position = getPosition(adapter.getIsSelected());
                if (onSelectClickListener != null) {
                    onSelectClickListener.onSelectClick(position);
                }
                break;
            case R.id.tv_selectAll:
                if (adapter != null) {
                    adapter.selectAll();
                }
                break;
        }
    }

    //根据选中的职位  拿到positionId  拼接字符串

    private String getPosition(HashMap<Integer, Boolean> isSelected) {
        //遍历map  得到选中的position  根据下标拿到code去拼接
        Set<Map.Entry<Integer, Boolean>> entries = isSelected.entrySet();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                String code = list.get(entry.getKey()).getPositioncode();
                builder.append(code + ",");
            }
        }
        return builder.toString();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void getDataSuccessed(LPLBean bean) {
        list = bean.getObject();
        if (list != null && list.size() > 0) {
            emptyLinear.setVisibility(View.GONE);
            if (adapter != null) {
                adapter.refreshData(list);
            } else {
                adapter = new MyRecyclerViewAdapter(list);

            }
            recyclerView.setAdapter(adapter);
        } else {
            emptyLinear.setVisibility(VISIBLE);
        }
    }

    @Override
    public void getDataFailed(String error) {

    }


    private OnSelectClickListener onSelectClickListener;

    public void refreshData() {
        if (list == null || list.size() == 0) {
            initData();
        }
    }

    public interface OnSelectClickListener {
        void onSelectClick(String positions);
    }

    public void setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
    }
}
