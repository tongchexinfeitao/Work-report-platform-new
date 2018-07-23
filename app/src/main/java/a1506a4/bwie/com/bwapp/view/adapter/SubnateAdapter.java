package a1506a4.bwie.com.bwapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.MineSendMessageBean;

/**
 * Created by Shadow on 2017/11/10.
 */

public class SubnateAdapter extends RecyclerView.Adapter<SubnateAdapter.MyViewHolder> {

    List<MineSendMessageBean.ObjectBean.TopositionlistBean> list;

    public SubnateAdapter(List<MineSendMessageBean.ObjectBean.TopositionlistBean> topositionlist) {
        this.list = topositionlist;
    }

    @Override
    public SubnateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subnate_item, parent, false);
        return new SubnateAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubnateAdapter.MyViewHolder holder, final int position) {

        holder.tv_position.setText(list.get(position).getPositionName());
        holder.tv_position.setOnClickListener(new View.OnClickListener() {
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
        private TextView tv_position;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_position = (TextView) itemView.findViewById(R.id.tv_positionName);

        }
    }


    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.mOnItemClickListener = onItemClickListener2;
    }


}
