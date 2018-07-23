package a1506a4.bwie.com.bwapp.view.activity;


import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.BaseMessage;
import a1506a4.bwie.com.bwapp.view.adapter.ReportLeftTypeAdapter;

import static a1506a4.bwie.com.bwapp.constant.BaseMessage.REPORT_MY;
import static a1506a4.bwie.com.bwapp.constant.BaseMessage.REPORT_LOWAR;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/2
 * 作用 :查询
 */

public class ReprotHackfanActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private EditText startTime, endTime;
    public static int TYPE_POSITION = 1;

    private static final String[] TYPES = {"咨询", "讲座", "招聘", "出差", "家访", "帮扶", "例会" ,"其它","活动"};

    private ListView listView;

    private ReportLeftTypeAdapter adapter;
    private boolean canSearch = false;

    private Calendar now = Calendar.getInstance();
    private DatePickerDialog dpd = DatePickerDialog.newInstance(
            ReprotHackfanActivity.this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
    );
    private String start;
    private String end;
    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_select_reprot);
        initView();
        code = getIntent().getIntExtra("code", REPORT_MY);
        adapter = new ReportLeftTypeAdapter(this, TYPES);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelected(position);
                TYPE_POSITION = position + 1;

            }
        });

    }


    private void initView() {
        listView = (ListView) findViewById(R.id.type_switch_listView);

        Button beginSelect = (Button) findViewById(R.id.beginSelect);
        Button selectTime = (Button) findViewById(R.id.selectTime);

        startTime = (EditText) findViewById(R.id.start_date);
        endTime = (EditText) findViewById(R.id.end_date);
        ImageView gouback = (ImageView) findViewById(R.id.reportGoback);

        dpd.setStartTitle("开始日期");
        dpd.setEndTitle("结束日期");
        beginSelect.setOnClickListener(this);
        selectTime.setOnClickListener(this);
        gouback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectTime:
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.beginSelect:
                if (canSearch) {
                    if (!(TextUtils.isEmpty(start) && TextUtils.isEmpty(end))) {
                        Intent intent = new Intent(ReprotHackfanActivity.this, ReportHackfanResultActivity.class);
                        intent.putExtra("reportType", TYPE_POSITION);
                        intent.putExtra("startTime", start);
                        intent.putExtra("endTime", end);
                        if (code == REPORT_MY) {
                            intent.putExtra("selectCode", REPORT_MY);
                        } else if (code == REPORT_LOWAR) {
                            intent.putExtra("selectCode", REPORT_LOWAR);
                        }
                        startActivity(intent);

                        TYPE_POSITION = 1;
                        adapter.setSelected(TYPE_POSITION - 1);
                    }
                } else {
                    Toast.makeText(ReprotHackfanActivity.this, "您还没有选择日期", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reportGoback:
                finish();
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {

        start = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        end = yearEnd + "-" + (monthOfYearEnd + 1) + "-" + dayOfMonthEnd;
        if (year == yearEnd) {
            if (monthOfYear == monthOfYearEnd) {
                if (dayOfMonth <= dayOfMonthEnd) {
                    startTime.setText(start);
                    endTime.setText(end);

                    canSearch = true;

                } else {
                    BaseMessage.showDialog(this, "结束日期不能\n大于开始日期", "知道了");
                }
            } else if (monthOfYear < monthOfYearEnd) {
                startTime.setText(start);
                endTime.setText(end);
                canSearch = true;
            } else {
                BaseMessage.showDialog(this, "结束日期不能\n大于开始日期", "知道了");
            }
        } else if (year < yearEnd) {
            startTime.setText(start);
            endTime.setText(end);
            canSearch = true;
        } else {
            BaseMessage.showDialog(this, "结束日期不能\n大于开始日期", "知道了");
        }
    }
}