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
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PuncheLowerBean;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/3
 * 作用 :
 */

public class PunchSubordinateAdapter extends RecyclerView.Adapter<PunchSubordinateAdapter.MyViewHolder> {
    private Context context;
    private List<PuncheLowerBean.ObjectBean> list;
//    private String refresh = null;

   /* public PunchSubordinateAdapter(Context context, List<PuncheLowerBean.ObjectBean> list, String refresh) {
        this.context = context;
        this.list = list;
        this.refresh = refresh;
    }*/

    public PunchSubordinateAdapter(Context context, List<PuncheLowerBean.ObjectBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.punch_subordinate_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

//        if (TextUtils.isEmpty(refresh)) {
        setHeadImg(holder.subImg);

        switch (list.get(position).getTypeid()) {
            case 1:
                holder.subTitle.setText(list.get(position).getUsername() + "→上下班打卡成功");
                break;
            case 2:
                holder.subTitle.setText(list.get(position).getUsername() + "→出差打卡成功");
                break;
        }
        String address = list.get(position).getAddress();
        //  String[] split = address.split("\\)");
        if (address == null || "".equals(address)) {
            holder.subAddress.setText("未获取到地理位置");
        } else {
            holder.subAddress.setText(address);
        }
        String remarks = list.get(position).getRemarks();
        if (!TextUtils.isEmpty(list.get(position).getRemarks()) && !("null".equals(list.get(position).getRemarks()))) {
            holder.subRemark.setVisibility(View.VISIBLE);
            holder.subRemark.setText("备注: " + remarks);
        } else {
            holder.subRemark.setVisibility(View.GONE);
        }

        String[] split = list.get(position).getChecktime().split(" ");
        holder.subYear.setText(split[0]);
        holder.subTime.setText(split[1]);

        holder.ItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.myOnClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View ItemView;
        private ImageView subImg;
        private TextView subTitle;
        private TextView subTime;
        private final TextView subAddress;
        private final TextView subRemark;
        private final TextView subYear;

        public MyViewHolder(View itemView) {
            super(itemView);
            ItemView = itemView;
            subImg = (ImageView) itemView.findViewById(R.id.subImg);
            subTitle = (TextView) itemView.findViewById(R.id.subTitle);
            subTime = (TextView) itemView.findViewById(R.id.subTime);
            subAddress = (TextView) itemView.findViewById(R.id.subAddress);
            subRemark = (TextView) itemView.findViewById(R.id.subRemark);
            subYear = (TextView) itemView.findViewById(R.id.subYear);
        }
    }


    public void refreshData(List<PuncheLowerBean.ObjectBean> tempList) {
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


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void myOnClick(View v, int position);
    }
}
