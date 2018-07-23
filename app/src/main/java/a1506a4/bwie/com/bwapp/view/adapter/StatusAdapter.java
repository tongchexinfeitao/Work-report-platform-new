package a1506a4.bwie.com.bwapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.StatusBean;

/**
 * Created by Shadow on 2017/11/10.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {

    List<StatusBean.ObjectBean> list;

    public StatusAdapter(List<StatusBean.ObjectBean> list) {
        this.list = list;
    }

    @Override
    public StatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status, parent, false);
        return new StatusAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusAdapter.MyViewHolder holder, int position) {

        holder.tv_name.setText("姓名：" + list.get(position).getName());
        holder.tv_orgName.setText("部门：" + list.get(position).getOrgname());
        if (list.get(position).getStatusid() == 0) {
            holder.tv_status.setText("是否已阅：未阅");
        } else {
            holder.tv_status.setText("是否已阅：已阅");
            if (list.get(position).getReply() != null) {
                holder.tv_reply.setText("回复内容：" + list.get(position).getReply());
            }
        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_orgName;
        private TextView tv_reply;
        private TextView tv_status;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_orgName = (TextView) itemView.findViewById(R.id.tv_orgName);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_reply = (TextView) itemView.findViewById(R.id.tv_reply);

        }
    }

    public void refreshData(List<StatusBean.ObjectBean> tempList) {
        if (tempList != null && tempList.size() > 0) {
            if (list != null) {
                list.clear();
                list.addAll(tempList);
            } else {
                list = tempList;
            }
        }
        notifyDataSetChanged();
    }
}
