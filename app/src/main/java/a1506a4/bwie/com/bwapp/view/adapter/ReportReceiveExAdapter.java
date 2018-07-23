package a1506a4.bwie.com.bwapp.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLowerBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLowerBean.ObjectBean.ReportlogviewBean;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public class ReportReceiveExAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ReportMyLowerBean.ObjectBean> groupList;
    private List<ReportMyLowerBean.ObjectBean.ReportlogviewBean> childList;
    private String[] group;
    private ReportlogviewBean[][] child;


    public ReportReceiveExAdapter(Context context, List<ReportMyLowerBean.ObjectBean> groupList) {
        this.context = context;
        this.groupList = groupList;
        initData();
    }

    private void initData() {
        group = new String[groupList.size()];
        child = new ReportlogviewBean[groupList.size()][];

        for (int i = 0; i < group.length; i++) {
            //一级列表的数据
            group[i] = groupList.get(i).getName();

            //模拟实例化二级列表的集合
            childList = groupList.get(i).getReportlogview();

            //创建二维数组的二级数据集合,也就是[][]  的第二个[]的数据
            ReportlogviewBean[] childStr = new ReportlogviewBean[childList.size()];
            for (int y = 0; y < childStr.length; y++) {
                childStr[y] = this.childList.get(y);
            }
            //把数据添加到二级列表
            child[i] = childStr;
        }
    }

    @Override
    public int getGroupCount() {
        return group.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.report_receive_group_item, null);
            viewHolder = new GroupViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.receiveGroupName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(group[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.report_receive_child_item, null);
            viewHolder = new ChildViewHolder();
            viewHolder.headImg = (ImageView) convertView.findViewById(R.id.receiveChildHeadImg);
            viewHolder.type = (TextView) convertView.findViewById(R.id.receiveChildType);
            viewHolder.time = (TextView) convertView.findViewById(R.id.receiveChildTiem);
            viewHolder.msg = (TextView) convertView.findViewById(R.id.receiveChildMsg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.type.setText(child[groupPosition][childPosition].getReporttype());
        viewHolder.time.setText(child[groupPosition][childPosition].getSendtime());

        //地点
        String site = (String) child[groupPosition][childPosition].getSite();
        //效果
        String result = child[groupPosition][childPosition].getResult();
        viewHolder.msg.setText((!TextUtils.isEmpty(site)) ? "地点 :" + site : "效果 :" + result);

        if ("其它".equals(child[groupPosition][childPosition].getReporttype())) {
            viewHolder.msg.setText("内容 :" + (String) child[groupPosition][childPosition].getContent());
        } else {
            viewHolder.msg.setText(!TextUtils.isEmpty(site) && !"null".equals(site) ? "地点: " + site : "效果: " + result);
        }
        setHeadImg(viewHolder.headImg);
        return convertView;
    }


    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    class GroupViewHolder {
        TextView name;
    }

    class ChildViewHolder {
        ImageView headImg;
        TextView type;
        TextView time;
        TextView msg;
    }

    public void refreshData(List<ReportMyLowerBean.ObjectBean> tempList) {
        if (tempList != null && tempList.size() >= 0) {
            if (groupList != null) {
                groupList.clear();
                childList.clear();
                childList = null;
                groupList.addAll(tempList);
                initData();
            } else {
                groupList = tempList;
            }
        }else{
            if (groupList != null) {
                groupList.clear();
                if(childList!=null){
                    groupList.clear();
                }
            }
            initData();
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
}
