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
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyBean;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/8
 * 作用 :
 */

public class ReportMyHackfanAdapter extends RecyclerView.Adapter<ReportMyHackfanAdapter.MyHackfanViewHolder> {
    private Context context;
    private List<ReportHackfanMyBean.ObjectBean> list;

    public ReportMyHackfanAdapter(Context context, List<ReportHackfanMyBean.ObjectBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHackfanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.punch_my_item, parent, false);
        return new MyHackfanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHackfanViewHolder holder, final int position) {
        setHeadImg(holder.myImg);

        holder.myType.setText("类型: " + list.get(position).getReporttype());

        //地点
        String site = (String) list.get(position).getSite();
        //效果
        String result = list.get(position).getResult();

        if (list.get(position).getReporttype().equals("其它")) {
            holder.myTitle.setText("内容 :" + (String) list.get(position).getContent());
        } else {
            holder.myTitle.setText(!TextUtils.isEmpty(site) && !"null".equals(site) ? "地点: " + site : "效果: " + result);
        }

        if (!TextUtils.isEmpty(list.get(position).getRemarks()) && !("null".equals(list.get(position).getRemarks()))) {
            holder.myRemark.setVisibility(View.VISIBLE);
            holder.myRemark.setText("备注: " + list.get(position).getRemarks());
        } else {
            holder.myRemark.setVisibility(View.GONE);
        }

        holder.myTime.setText(list.get(position).getSendtime());

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

    class MyHackfanViewHolder extends RecyclerView.ViewHolder {
        private ImageView myImg;
        private TextView myTitle;
        private TextView myTime;
        private TextView myType;
        private TextView myRemark;
        private View itemView;

        public MyHackfanViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            myImg = (ImageView) itemView.findViewById(R.id.myImg);
            myTitle = (TextView) itemView.findViewById(R.id.myTitle);
            myTime = (TextView) itemView.findViewById(R.id.myTime);
            myType = (TextView) itemView.findViewById(R.id.myType);
            myRemark = (TextView) itemView.findViewById(R.id.myRemark);
        }
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

    public void setOnItemClickListener(ReportMyHackfanAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
