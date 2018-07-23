package a1506a4.bwie.com.bwapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.model.bean.MineBean.StudentBean;


/**
 * Created by Shadow on 2017/10/23.
 */

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.MyViewHolder> {

    List<StudentBean.ObjectBean> list;

    public StudentInfoAdapter(List<StudentBean.ObjectBean> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_info_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_college.setText("移动通信学院");
        holder.tv_salary.setText(list.get(position).getSalary() + "");

        holder.tv_studentSex.setText(list.get(position).getSex());

        holder.tv_studentAge.setText(list.get(position).getAge() + "");
        holder.tv_studentName.setText(list.get(position).getStudentname());
        holder.linear.setOnClickListener(new View.OnClickListener() {
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
        private ImageView iv_icon;
        private TextView tv_studentName;
        private TextView tv_college;
        private TextView tv_dept;
        private TextView tv_salary;
        private TextView tv_studentSex;
        private TextView tv_studentAge;
        private LinearLayout linear;

        public MyViewHolder(View itemView) {
            super(itemView);
            linear = (LinearLayout) itemView.findViewById(R.id.linear);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_studentName = (TextView) itemView.findViewById(R.id.tv_studentName);
            tv_studentSex = (TextView) itemView.findViewById(R.id.tv_studentSex);
            tv_studentAge = (TextView) itemView.findViewById(R.id.tv_studentAge);
            tv_college = (TextView) itemView.findViewById(R.id.tv_college);
            tv_dept = (TextView) itemView.findViewById(R.id.tv_dept);
            tv_salary = (TextView) itemView.findViewById(R.id.tv_salary);


        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.mOnItemClickListener = onItemClickListener2;
    }


    public void refreshData(List<StudentBean.ObjectBean> tempList) {
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
