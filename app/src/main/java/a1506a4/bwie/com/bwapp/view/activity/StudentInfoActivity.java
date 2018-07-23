package a1506a4.bwie.com.bwapp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by Shadow on 2017/11/10.
 */

public class StudentInfoActivity extends Activity {
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_age;
    private TextView tv_address;
    private TextView tv_college;
    private TextView tv_salary;
    private TextView tv_company;
    private TextView tv_startWorkTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);
        initView();
        getData();
    }

    private void getData() {

        //得到传来的学生信息
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String college = intent.getStringExtra("college");
        String age = intent.getStringExtra("age");
        String sex = intent.getStringExtra("sex");
        String address = intent.getStringExtra("address");
        String salary = intent.getStringExtra("salary");
        String company = intent.getStringExtra("company");
        String startworktime = intent.getStringExtra("startworktime");

        //设置文本
        tv_name.setText(name);
        tv_sex.setText(sex);
        tv_age.setText(age);
        tv_address.setText(address);
        tv_college.setText(college);
        tv_salary.setText(salary);
        tv_company.setText(company);
        tv_startWorkTime.setText(startworktime);
    }

    //初始化控件
    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_college = (TextView) findViewById(R.id.tv_college);
        tv_salary = (TextView) findViewById(R.id.tv_salary);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_startWorkTime = (TextView) findViewById(R.id.tv_startWorkTime);
    }
}
