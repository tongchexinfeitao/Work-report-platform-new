package a1506a4.bwie.com.bwapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.MineSendMessageBean;

/**
 * Created by Shadow on 2017/11/7.
 */

public class MyNotificationAdapter extends RecyclerView.Adapter<MyNotificationAdapter.MyViewHolder> {
    List<MineSendMessageBean.ObjectBean> list;

    public MyNotificationAdapter(List<MineSendMessageBean.ObjectBean> list) {
        this.list = list;
    }


    @Override
    public MyNotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_notification_item, parent, false);

        return new MyNotificationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyNotificationAdapter.MyViewHolder holder, final int position) {


        if (list.get(position).getContent().length() > 6) {
            holder.tv_content.setText(list.get(position).getContent().substring(0, 7) + "...");
        } else
            holder.tv_content.setText(list.get(position).getContent());
        holder.tv_time.setText("发送时间：" + list.get(position).getSendtime());
        holder.tv_name.setText("发送人：" + list.get(position).getSendname() + "   ");
        holder.tv_position.setText("职位：" + list.get(position).getPositionname());

        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v, position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_time;
        private TextView tv_content;
        private TextView tv_position;
        private LinearLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_position = (TextView) itemView.findViewById(R.id.tv_position);
            relative = (LinearLayout) itemView.findViewById(R.id.relative);
        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.mOnItemClickListener = onItemClickListener2;
    }

    public void refreshData(List<MineSendMessageBean.ObjectBean> tempList) {
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
