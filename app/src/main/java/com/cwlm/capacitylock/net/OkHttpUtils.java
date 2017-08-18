package com.cwlm.capacitylock.net;

import android.content.Context;
import android.util.Log;

import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.GetAllStopPlaceModel;
import com.cwlm.capacitylock.model.MyBalanceModel;
import com.cwlm.capacitylock.model.MyLocksModel;
import com.cwlm.capacitylock.model.OrderInfoModel;
import com.cwlm.capacitylock.model.RechargeModel;
import com.cwlm.capacitylock.model.RechargeMonthCardModel;
import com.cwlm.capacitylock.model.SweepNumberModel;
import com.cwlm.capacitylock.model.UserModel;
import com.cwlm.capacitylock.obj.BindCarNumbleObj;
import com.cwlm.capacitylock.utils.MyDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by akawok on 2017-07-28.
 */
public class OkHttpUtils {

    Context ctx;
    int infCode = -1;
    private OkHttpClient okhttp;
    private boolean showProgressDialog = true;
    MyDialog progressDialog = null;


    public OkHttpUtils(Context context, int infCode) {
        this.ctx = context;
        this.infCode = infCode;
    }

    public OkHttpUtils(Context context, int infCode, boolean showProgressDialog) {
        this.ctx = context;
        this.infCode = infCode;
        this.showProgressDialog = showProgressDialog;
    }

    public void GetDataFromNet(String... parms) {


        if (showProgressDialog && progressDialog == null) {
            progressDialog = new MyDialog(ctx, "加载中...");
            progressDialog.show();
        }

        //创建OkHttpClient对象
        okhttp = new OkHttpClient();
        FormBody body;
        switch (infCode) {

            case InterfaceFinals.getAllStopPlace:   //获取所有停车场车辆信息

                body = new FormBody.Builder().build();

                PostRequst(InterfaceFinals.getAllStopPlace_Requst, okhttp, body, GetAllStopPlaceModel.class, InterfaceFinals.getAllStopPlace);

                break;

            case InterfaceFinals.sendSMSNew:   //获取验证码

                body = new FormBody.Builder()
                        .add("toUser", parms[0])
                        .add("type", parms[1])
                        .build();

                PostRequst(InterfaceFinals.sendSMSNew_Requst, okhttp, body, BaseModel.class, InterfaceFinals.sendSMSNew);

                break;
            case InterfaceFinals.login:   //登录

                body = new FormBody.Builder()
                        .add("phoneNum", parms[0])
                        .add("idenCode", parms[1])
                        .build();

                PostRequst(InterfaceFinals.login_Requst, okhttp, body, UserModel.class, InterfaceFinals.login);

                break;
            case InterfaceFinals.myOrder:   //获取我的订单信息

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.myOrder_Requst, okhttp, body, OrderInfoModel.class, InterfaceFinals.myOrder);

                break;
            case InterfaceFinals.predetermine:   //我的预定

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.predetermine_Requst, okhttp, body, BaseModel.class, InterfaceFinals.predetermine);

                break;
            case InterfaceFinals.cancelPredetermine:   //取消预定

                body = new FormBody.Builder()
                        .add("orderInfoId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.cancelPredetermine_Requst, okhttp, body, BaseModel.class, InterfaceFinals.cancelPredetermine);

                break;
            case InterfaceFinals.getCarNumber:   //获取车牌号

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.getCarNumber_Requst, okhttp, body, BindCarNumbleObj.class, InterfaceFinals.getCarNumber);

                break;
            case InterfaceFinals.bindCarNumber:   //绑定车牌号

                body = new FormBody.Builder()
                        .add("carNumber", parms[0])
                        .add("step", parms[1])
                        .build();

                PostRequst(InterfaceFinals.bindCarNumber_Requst, okhttp, body, BaseModel.class, InterfaceFinals.bindCarNumber);

                break;
            case InterfaceFinals.getStopPlaceAllMonthCardPrice:   //获取月卡信息

                body = new FormBody.Builder()
                        .add("stopPlaceId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.getStopPlaceAllMonthCardPrice_Requst, okhttp, body, RechargeMonthCardModel.class, InterfaceFinals.getStopPlaceAllMonthCardPrice);

                break;
            case InterfaceFinals.getOrderInfo:   //app充值下订单

                body = new FormBody.Builder()
                        .add("rechargeMoney", parms[0])
                        .add("payType", parms[1])
                        .build();

                PostRequst(InterfaceFinals.getOrderInfo_Requst, okhttp, body, RechargeModel.class, InterfaceFinals.getOrderInfo);

                break;
            case InterfaceFinals.getAllUserMonthCard:   //获取月卡充值记录

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.getAllUserMonthCard_Requst, okhttp, body, BaseModel.class, InterfaceFinals.getAllUserMonthCard);

                break;
//            case InterfaceFinals.scanCode:   //app扫码停车_新     ----该接口已在扫码界面单独调用
//
//                body = new FormBody.Builder()
//                        .add("userId", parms[0])
//                        .add("param", parms[1])
//                        .add("carNumber", parms[2])
//                        .build();
//
//                PostRequst(InterfaceFinals.scanCode_Requst, okhttp, body, BaseModel.class, InterfaceFinals.scanCode);
//
//                break;
            case InterfaceFinals.cancelPark:   //app取消停车

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .add("rid", parms[1])
                        .add("addr", parms[2])
                        .build();

                PostRequst(InterfaceFinals.cancelPark_Requst, okhttp, body, BaseModel.class, InterfaceFinals.cancelPark);

                break;
            case InterfaceFinals.lockApply:   //申请车位锁

                body = new FormBody.Builder()
                        .add("name", parms[0])
                        .add("phoneNum", parms[1])
                        .add("installAddress", parms[2])
                        .add("remark", parms[3])
                        .build();

                PostRequst(InterfaceFinals.lockApply_Requst, okhttp, body, BaseModel.class, InterfaceFinals.lockApply);

                break;
            case InterfaceFinals.appGetMyBalance:   //app获取余额

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.appGetMyBalance_Requst, okhttp, body, MyBalanceModel.class, InterfaceFinals.appGetMyBalance);

                break;
            case InterfaceFinals.saveAdvice:   //投诉意见

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .add("advice", parms[1])
                        .build();

                PostRequst(InterfaceFinals.saveAdvice_Requst, okhttp, body, BaseModel.class, InterfaceFinals.saveAdvice);

                break;
            case InterfaceFinals.getCarLockUseInformation:   //获取我的车位锁列表

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.getCarLockUseInformation_Requst, okhttp, body, MyLocksModel.class, InterfaceFinals.getCarLockUseInformation);

                break;
            case InterfaceFinals.appBindingUser:   //绑定我的车位锁

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .add("carLockKey", parms[1])
                        .build();

                PostRequst(InterfaceFinals.appBindingUser_Requst, okhttp, body, BaseModel.class, InterfaceFinals.appBindingUser);

                break;
            case InterfaceFinals.updateCarLockState:   //分享我的车位

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .add("carLockId", parms[1])
                        .add("flag", parms[2])
                        .build();

                PostRequst(InterfaceFinals.updateCarLockState_Requst, okhttp, body, BaseModel.class, InterfaceFinals.updateCarLockState);

                break;
            case InterfaceFinals.getSweepNumber:   //主页获取押金和使用次数

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.getSweepNumber_Requst, okhttp, body, SweepNumberModel.class, InterfaceFinals.getSweepNumber);

                break;


            default:

                ((BaseActivity) ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        ((BaseActivity) ctx).showToast("请求失败，请重试");
                    }
                });
                break;
        }
    }

    public void cancel() {
        if (okhttp != null) {
            okhttp.cache();
        }
    }

    public void PostRequst(String url, OkHttpClient okhttp, final FormBody body, final Class<? extends BaseModel> cls, final int inf) {

        final FormBody body2 = body;
        //创建一个Request  post请求
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        //new call
        Call call = okhttp.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                ((BaseActivity) ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        ((BaseActivity) ctx).showToast("请求失败，请重试");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();


                Log.e(" requst ", "|---------------------------------" + "-------------------------------------------------");
                Log.e(" requst ", "|" + body2 + "");
                Log.e("response", "|---------------------------------" + "-------------------------------------------------");
                Log.e("response", "|" + res + "");
                Log.e("response", "|---------------------------------" + "-------------------------------------------------");


                ((BaseActivity) ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }

                        try {
                            final BaseModel model = (BaseModel) new Gson().fromJson(res, cls);

                            if (!res.contains("{")) { //数据返回异常
                                ((BaseActivity) ctx).showToast("数据异常,请重新请求");
                            } else if (model != null && "1".equals(model.getStatusCode())) {
                                model.setInfCode(inf);
                                ((BaseActivity) ctx).onSuccess(model);
                            } else if (model != null) {
                                ((BaseActivity) ctx).onFail(model);
                            } else {
                                ((BaseActivity) ctx).showToast("数据解析异常，请重试");
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            ((BaseActivity) ctx).showToast("数据解析错误");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            ((BaseActivity) ctx).showToast("暂无更新数据");
                        }

                    }
                });
            }
        });
    }


}
