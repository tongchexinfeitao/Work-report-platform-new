package a1506a4.bwie.com.bwapp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import a1506a4.bwie.com.bwapp.R;

/**
 * 作者: 赵虔
 * 时间: 2017/10/24
 * 类作用:根据汇报类型查询汇报,左侧显示汇报类型的列表
 */

public class ReportLeftTypeAdapter extends BaseAdapter {


    private Context context;
    private String[] types;


    private int selectId = 0;

    public ReportLeftTypeAdapter(Context context, String[] types) {
        this.context = context;
        this.types = types;
    }

    public void setSelected(int pos) {
        this.selectId = pos;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return types.length;
    }

    @Override
    public Object getItem(int position) {
        return types[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.report_select_left_item, null);

            holder.type_text = (TextView) convertView.findViewById(R.id.typeName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.type_text.setText(types[position]);


        if (position == selectId) {
            holder.type_text.setTextColor(Color.parseColor("#ffffff"));
            convertView.setBackgroundColor(Color.parseColor("#4D8DC5"));
        } else {
            holder.type_text.setTextColor(Color.parseColor("#333333"));
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        return convertView;
    }

    class ViewHolder {
        TextView type_text;
    }
}
