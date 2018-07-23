package a1506a4.bwie.com.bwapp.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.constant.BaseMessage;
import a1506a4.bwie.com.bwapp.constant.ProgressBarHorizontal;
import a1506a4.bwie.com.bwapp.constant.UserInfo;
import a1506a4.bwie.com.bwapp.constant.UserInfoManager;
import a1506a4.bwie.com.bwapp.model.utils.UploadingImageViewUtils;
import a1506a4.bwie.com.bwapp.presenter.report.ReportJobPresenter;
import a1506a4.bwie.com.bwapp.presenter.report.UploadingImgPresenter;
import a1506a4.bwie.com.bwapp.view.circle.CanAddViewImageView;
import a1506a4.bwie.com.bwapp.view.myInterface.report.ReportJobView;
import a1506a4.bwie.com.bwapp.view.myInterface.report.UploadingImgView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/2
 * 作用 :
 */

public class ReportActivity extends Activity implements View.OnClickListener, ReportJobView, UploadingImgView {
    private final static String[] types = {"讲座", "招聘", "出差", "家访", "帮扶", "例会","活动"};

    private final String[] type1 = {"参加人数:", "地点:", "效果:", "证明人及联系电话:", "讲座人电话:", "备注:"};//6
    private final String[] type2 = {"参加人数:", "地点:", "效果:", "证明人及联系电话:", "招聘主管人电话:", "备注:"};//6
    private final String[] type3 = {"需用时长:", "地点:", "目的:", "证明人及联系电话:", "主管人电话:", "备注:"};//6
    private final String[] type4 = {"家长姓名:", "家长联系电话:", "学生姓名:", "效果:", "证明人及联系电话:", "负责家访领导电话:", "备注:"};//7
    private final String[] type5 = {"地点:", "具体内容:", "效果:", "证明人及联系电话:", "帮扶领导电话:", "备注:"};//6
    private final String[] type6 = {"参加人数:", "地点:", "具体内容:", "效果:", "证明人及联系电话:", "会议主持人电话:", "备注:"};//7例会
    private final String[] type7 = {"参加人数:", "地点:", "具体内容:", "效果:", "证明人及联系电话:", "会议主持人电话:", "备注:"};//7活动

    private ArrayList<String[]> list;//保存六种汇报类型的集合
    private String[] strings;
    private LinearLayout layout6;
    private TextView name0;
    private TextView name1;
    private TextView name2;
    private TextView name3;
    private TextView name4;
    private TextView name5;
    private TextView name6;

    private TextView title;
    private ReportJobPresenter presenter;
    private String intent_type;
    private EditText content0;
    private EditText content1;
    private EditText content2;
    private EditText content3;
    private EditText content4;
    private EditText content5;
    private EditText content6;
    private ImageView uploading;
    private UploadingImgPresenter uploadingImgPresenter;

    public String mPublicPhotoPath;
    public static final int REQ_GALLERY = 333;
    public static final int REQUEST_CODE_PICK_IMAGE = 222;
    private PopupWindow mPopupWindow;
    private View popView;
    private CanAddViewImageView mView;
    private Button b1;
    private Button b2;
    private Button b3;
    private boolean canDelete;
    protected StringBuffer ImageName = new StringBuffer();
    private Uri uri;
    public String path;
    int mTargetW;
    int mTargetH;
    private LinearLayout uploadingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setStatusBarColor(getResources().getColor(R.color.lanse));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_from);
        initView();
        initPop();
        //跳转传过来的标题
        String Intent_title = getIntent().getStringExtra("title");
        //跳转传过来的汇报类型
        intent_type = getIntent().getStringExtra("type");
        //设置标题内容
        this.title.setText(Intent_title + "→" + intent_type);
        //判断你传过来的类型是什么?然后设置页面提输入的内容
        for (int i = 0; i < types.length; i++) {
            if (intent_type.equals(types[i])) {
                strings = list.get(i);
            }
        }
        //判断type传过来的类型是哪个,然后根据类型来设置要显示输入框的个数
        if (strings.length == 6) {//6个内容
            layout6.setVisibility(View.GONE);
            name0.setText(strings[0]);
            name1.setText(strings[1]);
            name2.setText(strings[2]);
            name3.setText(strings[3]);
            name4.setText(strings[4]);
            name5.setText(strings[5]);
        } else if (strings.length == 7) {//7个内容
            layout6.setVisibility(View.VISIBLE);
            name0.setText(strings[0]);
            name1.setText(strings[1]);
            name2.setText(strings[2]);
            name3.setText(strings[3]);
            name4.setText(strings[4]);
            name5.setText(strings[5]);
            name6.setText(strings[6]);
        }

        uploadingImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(v);
                return true;
            }
        });
    }

    private void initView() {
        list = new ArrayList<>();
//        list.add(type0);
        list.add(type1);
        list.add(type2);
        list.add(type3);
        list.add(type4);
        list.add(type5);
        list.add(type6);
        list.add(type7);
        Button commit = (Button) findViewById(R.id.commit);//提交按钮
        Button gouBack = (Button) findViewById(R.id.gouBack);//返回按钮

        //标题名字
        title = (TextView) findViewById(R.id.title);
        //输入框

        name0 = (TextView) findViewById(R.id.name0);
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        name4 = (TextView) findViewById(R.id.name4);
        name5 = (TextView) findViewById(R.id.name5);
        name6 = (TextView) findViewById(R.id.name6);
        //第七个输入框的布局,根据传入的类型来判断是否显示
        layout6 = (LinearLayout) findViewById(R.id.layout6);
        //该页面的总布局
        LinearLayout reprotLayout = (LinearLayout) findViewById(R.id.reprotLayout);
        LinearLayout textLayout = (LinearLayout) findViewById(R.id.textLayout);
        uploadingImg = (LinearLayout) findViewById(R.id.uploadingImg);
        uploading = (ImageView) findViewById(R.id.uploading);
        commit.setOnClickListener(this);
        gouBack.setOnClickListener(this);
        reprotLayout.setOnClickListener(this);
        textLayout.setOnClickListener(this);
        uploadingImg.setOnClickListener(this);

        presenter = new ReportJobPresenter(this);
        uploadingImgPresenter = new UploadingImgPresenter(this);

        content0 = (EditText) findViewById(R.id.content0);
        content1 = (EditText) findViewById(R.id.content1);
        content2 = (EditText) findViewById(R.id.content2);
        content3 = (EditText) findViewById(R.id.content3);
        content4 = (EditText) findViewById(R.id.content4);
        content5 = (EditText) findViewById(R.id.content5);
        content6 = (EditText) findViewById(R.id.content6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit://提交
                commit();
                break;
            case R.id.gouBack://返回
                finish();
                break;
            case R.id.reprotLayout:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
            case R.id.textLayout:
                InputMethodManager imm2 = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm2.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
            case R.id.uploadingImg://弹出对话框
                showPopupWindow(v);
                break;
        }
    }

    public void showPopupWindow(View view) {
        setBackgroundAlpha(0.5f);  // 弹出时设置半透明

        //底部弹出
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                getImageFromAlbum();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                startTake();
            }
        });
    }

    @SuppressLint("NewApi")
    public void addGridImage(String path, int mTargetW, int mTargetH) {
//        mView = new ImageView(this);

        canDelete = false;
        mView = new CanAddViewImageView(this);
        mView.setImageDrawable(getDrawable(R.mipmap.ic_launcher));


        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels / 3 - 30 - 30;
        params.height = getResources().getDimensionPixelSize(R.dimen.qqq);


        params.setGravity(Gravity.RIGHT);
        params.setMargins(30, 30, 30, 30);


        mView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mView.setLayoutParams(params);


        Bitmap mBitmap = UploadingImageViewUtils.getSmallBitmap(path, params.width,
                params.height);
        mView.setImageBitmap(mBitmap);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if (canDelete) {
                    showDialog(v1);
                }

            }
        });
    }

    private void showDialog(final View v) {
        AlertDialog.Builder dialog =
                new AlertDialog.Builder(this);

        dialog.setMessage("确定删除此图片？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploading.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tuankaung_tupian));
            }
        });
        dialog.create().show();
    }

    public void initPop() {

        popView = View.inflate(this, R.layout.pop, null);

        b1 = (Button) popView.findViewById(R.id.but1);
        b2 = (Button) popView.findViewById(R.id.but2);
        b3 = (Button) popView.findViewById(R.id.but3);


        mPopupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#f6eaea")));


        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);

        //监听弹出框 消失
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f); //为界面 设置正常透明度
            }
        });
        //取消
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

    }

    /**
     * 设置屏幕的透明度的方法
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    /**
     * 获取相册中的图片
     */
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    private void startTake() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断是否有相机应用
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //创建临时图片文件
            File photoFile = null;
            try {
                photoFile = UploadingImageViewUtils.createPublicImageFile();
                mPublicPhotoPath = photoFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置Action为拍照
            if (photoFile != null) {
                takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                //这里加入flag
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri photoURI = FileProvider.getUriForFile(this, "applicationId.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQ_GALLERY);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mTargetW = mImageView.getWidth();
//        mTargetH = mImageView.getHeight();
        switch (requestCode) {
            //拍照
            case REQ_GALLERY:
                if (resultCode != Activity.RESULT_OK) return;
                uri = Uri.parse(mPublicPhotoPath);
                path = uri.getPath();
                UploadingImageViewUtils.galleryAddPic(mPublicPhotoPath, this);
                if (path != null)
                    uploading.setImageBitmap(BitmapFactory.decodeFile(path));

                File file = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

                UserInfo userInfo = UserInfoManager.getInstance(this).getUserInfo();
                if (!TextUtils.isEmpty(userInfo.getId())) {
                    uploadingImgPresenter.uploadingImg(userInfo.getId(), part);
                    ProgressBarHorizontal.horizontalProgressBar(this);
                } else {
                    BaseMessage.showDialogAndFinish(this, "图片上传失败\n请在考勤打卡\n实名认证", "知道了");
                }
                break;
            //获取相册的图片
            case REQUEST_CODE_PICK_IMAGE:
                if (data == null) return;
                uri = data.getData();
                int sdkVersion = Integer.valueOf(Build.VERSION.SDK);
                if (sdkVersion >= 19) {
                    path = this.uri.getPath();
                    path = UploadingImageViewUtils.getPath_above19(this, this.uri);
                } else {
                    path = UploadingImageViewUtils.getFilePath_below19(this, this.uri);
                }
                if (path != null)
                    uploading.setImageBitmap(BitmapFactory.decodeFile(path));

                File file2 = new File(path);
                RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
                MultipartBody.Part part2 = MultipartBody.Part.createFormData("image", file2.getName(), requestBody2);

                UserInfo userInfo2 = UserInfoManager.getInstance(this).getUserInfo();
                if (!TextUtils.isEmpty(userInfo2.getId())) {
                    uploadingImgPresenter.uploadingImg(userInfo2.getId(), part2);
                    ProgressBarHorizontal.horizontalProgressBar(this);
                } else {
                    BaseMessage.showDialogAndFinish(this, "图片上传失败\n请在考勤打卡\n实名认证", "知道了");
                }
                break;
        }
        addGridImage(path, mTargetW, mTargetH);
    }

    //提交
    private void commit() {
        //判断type传过来的类型是哪个,然后根据类型来设置要显示输入框的个数
        String text0 = content0.getText().toString().trim();
        String text1 = content1.getText().toString().trim();
        String text2 = content2.getText().toString().trim();
        String text3 = content3.getText().toString().trim();
        String text4 = content4.getText().toString().trim();
        String text5 = content5.getText().toString().trim();
        String text6 = content6.getText().toString().trim();//备注输入框
        if (submit()) {
            UserInfo userInfo = UserInfoManager.getInstance(this).getUserInfo();
            if (!TextUtils.isEmpty(userInfo.getId())) {
                switch (intent_type) {
                    case "讲座":
                        try {
                            int amount = Integer.parseInt(text0);
                            presenter.commitJiangZuo(userInfo.getId(), 2, amount, text1, text2, text3, text4, text5, ImageName.toString());
                        } catch (NumberFormatException e) {
                            BaseMessage.showDialog(this, "参加人数\n只能填写数字", "知道了");
                        }
                        break;
                    case "招聘":
                        try {
                            int amount = Integer.parseInt(text0);
                            presenter.commitZhaoPin(userInfo.getId(), 3, amount, text1, text2, text3, text4, text5, ImageName.toString());
                        } catch (NumberFormatException e) {
                            BaseMessage.showDialog(this, "参加人数\n只能填写数字", "知道了");
                        }
                        break;
                    case "出差":
                        presenter.commitChuChai(userInfo.getId(), 4, text0, text1, text2, text3, text4, text5, ImageName.toString());
                        break;
                    case "家访":
                        presenter.commitJiaFang(userInfo.getId(), 5, text0, text1, text2, text3, text4, text5, text6, ImageName.toString());
                        break;
                    case "帮扶":
                        presenter.commitBangFu(userInfo.getId(), 6, text0, text1, text2, text3, text4, text5, ImageName.toString());
                        break;
                    case "例会":
                        try {
                            int amount = Integer.parseInt(text0);
                            presenter.commitHuiYi(userInfo.getId(), 7, amount, text1, text2, text3, text4, text5, text6, ImageName.toString());
                        } catch (NumberFormatException e) {
                            BaseMessage.showDialog(this, "参加人数\n只能填写数字", "知道了");
                        }
                        break;
                    case "活动":
                        try {
                            int amount = Integer.parseInt(text0);
                            presenter.commitHuiYi(userInfo.getId(), 9, amount, text1, text2, text3, text4, text5, text6, ImageName.toString());
                        } catch (NumberFormatException e) {
                            BaseMessage.showDialog(this, "参加人数\n只能填写数字", "知道了");
                        }
                        break;
                }
            } else {
                BaseMessage.showDialog(this, "请在考勤打卡\n实名认证", "知道了");
            }
        }
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onUploadingSucceed(String imgName) {
        ProgressBarHorizontal.dismessProgressBar();
        BaseMessage.showDialog(this, "图片上传成功", "完成");
        if (imgName != null) {
            if (ImageName.length() > 0) {
                ImageName.append("," + imgName);
            } else {
                ImageName.append(imgName);
            }
        }
    }

    @Override
    public void onUploadingFaild() {
        ProgressBarHorizontal.dismessProgressBar();
        BaseMessage.showDialog(this, "图片上传失败", "知道了");
    }

    @Override
    public void onCommitSucceed() {
        BaseMessage.showDialogAndFinish(this, "提交成功", "完成");
        if (ImageName.length() > 0)
            ImageName.delete(0, ImageName.length());
    }

    @Override
    public void onCommitFilad() {
        BaseMessage.showDialog(this, "提交失败\n请稍后再试", "知道了");
    }

    //判断输入框是否为空
    private boolean submit() {
        if (strings.length == 6) {//6个内容
            return editTextIsNull(strings.length);
        } else if (strings.length == 7) {//7个内容
            return editTextIsNull(strings.length);
        }
        return true;
    }

    //判断输入框是否为空
    private boolean editTextIsNull(int length) {
        String content0String = content0.getText().toString().trim();
        if (TextUtils.isEmpty(content0String)) {
            Toast.makeText(this, strings[0] + " 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content1String = content1.getText().toString().trim();
        if (TextUtils.isEmpty(content1String)) {
            Toast.makeText(this, strings[1] + " 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content2String = content2.getText().toString().trim();
        if (TextUtils.isEmpty(content2String)) {
            Toast.makeText(this, strings[2] + " 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content3String = content3.getText().toString().trim();
        if (TextUtils.isEmpty(content3String)) {
            Toast.makeText(this, strings[3] + " 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content4String = content4.getText().toString().trim();
        if (TextUtils.isEmpty(content4String)) {
            Toast.makeText(this, strings[4] + " 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (strings.length == 7) {//7个内容
            String content5String = content5.getText().toString().trim();
            if (TextUtils.isEmpty(content5String)) {
                Toast.makeText(this, strings[5] + " 不能为空", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}