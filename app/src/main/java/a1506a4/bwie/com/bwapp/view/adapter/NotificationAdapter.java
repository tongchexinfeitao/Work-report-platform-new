package a1506a4.bwie.com.bwapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.ReceiveMessageBean;

/**
 * Created by Shadow on 2017/10/24.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    List<ReceiveMessageBean.ObjectBean> list;

    public NotificationAdapter(List<ReceiveMessageBean.ObjectBean> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (list.get(position).getContent().length() > 6) {
            holder.tv_message.setText(list.get(position).getContent().substring(0, 7) + "...");
        } else {
            holder.tv_message.setText(list.get(position).getContent());
        }

        holder.tv_name.setText("发送人：" + list.get(position).getSendname());
        holder.tv_position.setText("职位：" + list.get(position).getPositionname());
        holder.tv_time.setText("发送时间：" + list.get(position).getSendtime());

        if (list.get(position).getStatusid() == 0) {
            holder.iv_point.setVisibility(View.VISIBLE);
        } else {
            holder.iv_point.setVisibility(View.INVISIBLE);
        }
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v, position, holder.iv_point);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_message;
        private TextView tv_time;
        private TextView tv_position;
        private TextView tv_replied;
        private TextView tv_name;
        private final RelativeLayout relative;
        private ImageView iv_point;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            tv_replied = (TextView) itemView.findViewById(R.id.tv_replied);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_position = (TextView) itemView.findViewById(R.id.tv_position);
            relative = (RelativeLayout) itemView.findViewById(R.id.relative);
            iv_point = (ImageView) itemView.findViewById(R.id.iv_point);

        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position, ImageView image);
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener2) {
        this.mOnItemClickListener = OnItemClickListener2;
    }


    public void refreshData(List<ReceiveMessageBean.ObjectBean> tempList) {
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
