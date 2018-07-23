package a1506a4.bwie.com.bwapp.constant;

import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ImageBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportHackfanMyLowarBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLogBean;
import a1506a4.bwie.com.bwapp.model.bean.ReportBean.ReportMyLowerBean;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.LoginBean;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PunchMyBean;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PuncheLowerBean;
import a1506a4.bwie.com.bwapp.model.bean.punchBean.PunchingBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public interface MyApi {
    //第一次打卡和验证登录
    // String phone, String username, String typeId(打卡类型),
    // String address(经纬度), String remarks(备注)
    @FormUrlEncoded
    @POST("mobileCheckIn/checkIn")
    Observable<LoginBean> Login(@Field("phone") String phone, @Field("username") String username,
                                @Field("typeId") int typeId, @Field("address") String address,
                                @Field("remarks") String remarks);

    //第二次打卡
    //typeId=1&address=139,38&userId=ff808081443d43ee39717198&remarks=测试
    @FormUrlEncoded
    @POST("mobileCheckIn/checkIn")
    Observable<PunchingBean> checkIn(@Field("typeId") Integer typeId, @Field("address") String address, @Field("userId") String userId, @Field("remarks") String remarks);


    //查看自己的打卡记录
    //mobileCheckIn/queryOwnCheckInLog?userId=ff808081443d43ee39717198
    @FormUrlEncoded
    @POST("mobileCheckIn/queryOwnCheckInLog")
    Observable<PunchMyBean> getMyPuncheLog(@Field("userId") String userId);

    //查询下属打卡记录
    @FormUrlEncoded
    @POST("mobileCheckIn/queryOneLowerCheckInLog")
    Observable<PuncheLowerBean> getMyLowerPuncheLog(@Field("userId") String userId);


    //查询我的的汇报记录
    @FormUrlEncoded
    @POST("mobileReport/queryOwnReport")
    Observable<ReportMyLogBean> getMyReportLog(@Field("userId") String userId);

    //查询下级汇报记录//新收汇报
    @FormUrlEncoded
    @POST("mobileReport/querySubordinateReport")
    Observable<ReportMyLowerBean> getMylowerReportLog(@Field("userId") String userId, @Field("positions") String positions);

    //汇报-咨询
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitZiXun(@Field("userId") String userId,
                                       @Field("reporttype") int reporttype,
                                       @Field("studentname") String StudentName,
                                       @Field("studentphone") String StudentPhone,
                                       @Field("sex") int sex,
                                       @Field("result") String result,
                                       @Field("voucherphone") String voucherPhone,
                                       @Field("reception") String reception,
                                       @Field("remarks") String remarks,
                                       @Field("imagefile") String imagefile);

    //汇报-讲座
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitJiangZuo(@Field("userId") String userId,
                                          @Field("reporttype") int reporttype,
                                          @Field("amount") int amount,
                                          @Field("site") String site,
                                          @Field("result") String result,
                                          @Field("voucherphone") String voucherPhone,
                                          @Field("lecturer") String lecturer,
                                          @Field("remarks") String remarks,
                                          @Field("imagefile") String imagefile);

    //汇报-招聘
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitZhaoPin(@Field("userId") String userId,
                                         @Field("reporttype") int reporttype,
                                         @Field("amount") int amount,
                                         @Field("site") String site,
                                         @Field("result") String result,
                                         @Field("voucherphone") String voucherPhone,
                                         @Field("hrrecruiter") String hrRecruiter,
                                         @Field("remarks") String remarks,
                                         @Field("imagefile") String imagefile);

    //汇报-出差
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitChuChai(@Field("userId") String userId,
                                         @Field("reporttype") int reporttype,
                                         @Field("duration") String duration,
                                         @Field("site") String site,
                                         @Field("purpose") String purpose,
                                         @Field("voucherphone") String voucherPhone,
                                         @Field("recruiter") String recruiter,
                                         @Field("remarks") String remarks,
                                         @Field("imagefile") String imagefile);

    //汇报-家纺
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitJiaFang(@Field("userId") String userId,
                                         @Field("reporttype") int reporttype,
                                         @Field("parentname") String parentName,
                                         @Field("parentphone") String parentPhone,
                                         @Field("studentname") String StudentName,
                                         @Field("result") String result,
                                         @Field("voucherphone") String voucherPhone,
                                         @Field("homevisiting") String homeVisiting,
                                         @Field("remarks") String remarks,
                                         @Field("imagefile") String imagefile);

    //汇报-帮扶
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitBangFu(@Field("userId") String userId,
                                        @Field("reporttype") int reporttype,
                                        @Field("site") String site,
                                        @Field("content") String content,
                                        @Field("result") String result,
                                        @Field("voucherphone") String voucherPhone,
                                        @Field("helpleader") String helpLeader,
                                        @Field("remarks") String remarks,
                                        @Field("imagefile") String imagefile);

    //汇报-会议
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitHuiYi(@Field("userId") String userId,
                                       @Field("reporttype") int reporttype,
                                       @Field("amount") int amount,
                                       @Field("site") String site,
                                       @Field("content") String content,
                                       @Field("result") String result,
                                       @Field("voucherphone") String voucherPhone,
                                       @Field("emcee") String emcee,
                                       @Field("remarks") String remarks,
                                       @Field("imagefile") String imagefile);

    //其它
    @FormUrlEncoded
    @POST("mobileReport/submitReport")
    Observable<ReportBean> commitOther(@Field("userId") String userId,
                                       @Field("reporttype") int reporttype,
                                       @Field("content") String content,
                                       @Field("imagefile") String imagefile);

    @FormUrlEncoded
    @POST("mobileReport/queryOwnReport")
    Observable<ReportHackfanMyBean> hackfanMyReport(@Field("userId") String userId,
                                                    @Field("startDate") String startDate,
                                                    @Field("endDate") String endDate,
                                                    @Field("ReportTypeId") int ReportTypeId);

    @FormUrlEncoded
    @POST("mobileReport/querySubordinateReport")
    Observable<ReportHackfanMyLowarBean> hackfanLowarReport(@Field("userId") String userId,
                                                            @Field("startDate") String startDate,
                                                            @Field("endDate") String endDate,
                                                            @Field("typeId") int ReportTypeId);

    //上传图片
    @Multipart
    @POST("mobileUpload/upload?")
    Observable<ImageBean> uploadingImg(@Query("userId") String userId, @Part MultipartBody.Part file);

}
