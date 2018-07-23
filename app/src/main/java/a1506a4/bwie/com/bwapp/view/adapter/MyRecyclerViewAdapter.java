package a1506a4.bwie.com.bwapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.notificationBean.LPLBean;


/**
 * Created by Shadow on 2017/10/23.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    List<LPLBean.ObjectBean> list;
    HashMap<Integer, Boolean> isSelected;

    public MyRecyclerViewAdapter(List<LPLBean.ObjectBean> list) {
        this.list = list;

        initSelected();
    }

    private void initSelected() {
        isSelected = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            isSelected.put(i, false);
        }

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.checkbox.setText(list.get(position).getPositionname());
        holder.tv_number.setText(position + 1 + ".");
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected.put(position, !isSelected.get(position));
                notifyDataSetChanged();
            }
        });

        holder.checkbox.setChecked(isSelected.get(position));


    }

    public void selectAll() {
        boolean shouldSelectAll = false;
        Set<Map.Entry<Integer, Boolean>> entries = isSelected.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (!entry.getValue()) {
                shouldSelectAll = true;

            }

        }

        if (shouldSelectAll) {
            for (Map.Entry<Integer, Boolean> entry :
                    entries) {
                entry.setValue(true);

            }
        } else {
            for (Map.Entry<Integer, Boolean> entry :
                    entries) {
                entry.setValue(false);

            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox;
        private final TextView tv_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
        }
    }


    public boolean shouldShowDialog() {
        Set<Map.Entry<Integer, Boolean>> entries = isSelected.entrySet();
        for (Map.Entry<Integer, Boolean> entry :
                entries) {
            if (entry.getValue()) {
                return true;
            }
        }
        return false;
    }


    public HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }


    //刷新数据
    public void refreshData(List<LPLBean.ObjectBean> tempList) {
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

    //重新checkBox的状态 为 未选择
    public void resetCheckBox() {
        if (isSelected != null && isSelected.size() > 0) {
            for (int i = 0; i < isSelected.size(); i++) {
                isSelected.put(i, false);
            }
        }
        notifyDataSetChanged();
    }
}
