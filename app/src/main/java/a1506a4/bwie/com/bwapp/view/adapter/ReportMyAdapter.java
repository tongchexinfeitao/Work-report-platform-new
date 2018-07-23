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
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLogBean;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/8
 * 作用 :
 */

public class ReportMyAdapter extends RecyclerView.Adapter<ReportMyAdapter.MyReportViewHolder> {
    private Context context;
    private List<ReportMyLogBean.ObjectBean> list;

    public ReportMyAdapter(Context context, List<ReportMyLogBean.ObjectBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.report_my_child_item, parent, false);
        return new MyReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyReportViewHolder holder, final int position) {
        holder.time.setText(list.get(position).getSendtime());
        holder.myChildType.setText(list.get(position).getReporttype());
        //地点
        String site = (String) list.get(position).getSite();
        //效果
        String result = (String) list.get(position).getResult();

        if (list.get(position).getReporttype().equals("其它")) {
            holder.msg.setText("内容 :" + (String) list.get(position).getContent());
        } else {
            holder.msg.setText(!TextUtils.isEmpty(site) && !"null".equals(site) ? "地点: " + site : "效果: " + result);
        }
        setHeadImg(holder.headImg);

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

    class MyReportViewHolder extends RecyclerView.ViewHolder {
        ImageView headImg;
        TextView time;
        TextView msg;
        TextView myChildType;

        public MyReportViewHolder(View itemView) {
            super(itemView);
            myChildType = (TextView) itemView.findViewById(R.id.myChildType);
            headImg = (ImageView) itemView.findViewById(R.id.myChildHeadImg);
            msg = (TextView) itemView.findViewById(R.id.myChildMsg);
            time = (TextView) itemView.findViewById(R.id.myChildTiem);
        }
    }


    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onCLick(View v, int position);
    }

    public void setOnItemClickListener(ReportMyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

    public void refreshData(List<ReportMyLogBean.ObjectBean> tempList) {
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
