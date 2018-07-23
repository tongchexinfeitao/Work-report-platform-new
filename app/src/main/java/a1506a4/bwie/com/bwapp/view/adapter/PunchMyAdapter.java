package a1506a4.bwie.com.bwapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PunchMyBean;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/3
 * 作用 :
 */

public class PunchMyAdapter extends RecyclerView.Adapter<PunchMyAdapter.MyViewHolder> {

    private Context context;
    private List<PunchMyBean.ObjectBean> list;

    public PunchMyAdapter(Context context, List<PunchMyBean.ObjectBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.punch_my_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        setHeadImg(holder.myImg);
        switch (list.get(position).getTypeid()) {
            case 1:
                holder.myType.setText("上下班打卡成功");
                break;
            case 2:
                holder.myType.setText("出差打卡成功");
                break;
        }
        String address = list.get(position).getAddress();
        if (address == null || "".equals(address)) {
            holder.myTitle.setText("未获取到地理位置");
        } else {
            holder.myTitle.setText(address);
        }

        if (!TextUtils.isEmpty(list.get(position).getRemarks()) && !("null".equals(list.get(position).getRemarks()))) {
            holder.myRemark.setVisibility(View.VISIBLE);
            holder.myRemark.setText("备注: " + list.get(position).getRemarks());
        } else {
            holder.myRemark.setVisibility(View.GONE);
        }
        String[] split = list.get(position).getChecktime().split(" ");
        holder.myYear.setText(split[0]);
        holder.myTime.setText(split[1]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onCLick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView myImg;
        private TextView myTitle;
        private TextView myTime;
        private final TextView myType;
        private final TextView myRemark;
        private final TextView myYear;
        private View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            myImg = (ImageView) itemView.findViewById(R.id.myImg);
            myTitle = (TextView) itemView.findViewById(R.id.myTitle);
            myTime = (TextView) itemView.findViewById(R.id.myTime);
            myType = (TextView) itemView.findViewById(R.id.myType);
            myRemark = (TextView) itemView.findViewById(R.id.myRemark);
            myYear = (TextView) itemView.findViewById(R.id.myYear);
        }
    }

    public void refreshData(List<PunchMyBean.ObjectBean> tempList) {
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

    private void setHeadImg(ImageView img) {
        RequestOptions options = new RequestOptions()
                .circleCrop()//加载圆图片
                .error(R.drawable.wode_wodedaka_jilu)//加载错误图片
                .placeholder(R.drawable.wode_wodedaka_jilu)//加载预加载图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置磁盘缓存模式
                .fallback(R.drawable.wode_wodedaka_jilu);//设置备用图片,当图片地址为空的时候显示
        Glide.with(context).setDefaultRequestOptions(options).load(R.drawable.wode_wodedaka_jilu).into(img);
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onCLick(View v, int position);
    }

    public void setOnItemClickListener(PunchMyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
