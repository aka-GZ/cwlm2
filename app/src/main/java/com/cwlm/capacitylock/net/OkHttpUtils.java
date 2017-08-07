package com.cwlm.capacitylock.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;

import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.GetAllStopPlaceModel;
import com.cwlm.capacitylock.obj.UserObj;
import com.cwlm.capacitylock.ui.LoginActivity;
import com.cwlm.capacitylock.ui.MainActivity;
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
    private boolean showProgressDialog = false;
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

                PostRequst(InterfaceFinals.login_Requst, okhttp, body, UserObj.class, InterfaceFinals.login);

                break;
            case InterfaceFinals.myOrder:   //获取我的订单信息

                body = new FormBody.Builder()
                        .add("userId", parms[0])
                        .build();

                PostRequst(InterfaceFinals.myOrder_Requst, okhttp, body, BaseModel.class, InterfaceFinals.myOrder);

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

                PostRequst(InterfaceFinals.getCarNumber_Requst, okhttp, body, BaseModel.class, InterfaceFinals.getCarNumber);

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

                PostRequst(InterfaceFinals.getStopPlaceAllMonthCardPrice_Requst, okhttp, body, BaseModel.class, InterfaceFinals.getStopPlaceAllMonthCardPrice);

                break;
            case InterfaceFinals.getOrderInfo:   //app充值下订单

                body = new FormBody.Builder()
                        .add("rechargeMoney", parms[0])
                        .add("payType", parms[1])
                        .build();

                PostRequst(InterfaceFinals.getOrderInfo_Requst, okhttp, body, BaseModel.class, InterfaceFinals.getOrderInfo);

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

                PostRequst(InterfaceFinals.scanCode_Requst, okhttp, body, BaseModel.class, InterfaceFinals.scanCode);

                break;


            default:
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
                        progressDialog.dismiss();
                        ((BaseActivity) ctx).showToast("请求失败，请重试");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();

                if (progressDialog != null) {
                    progressDialog.dismiss();
                }

                Log.e(" requst ", "|---------------------------------" + "-------------------------------------------------");
                Log.e(" requst ", "|" + body2 + "");
                Log.e("response", "|---------------------------------" + "-------------------------------------------------");
                Log.e("response", "|" + res + "");
                Log.e("response", "|---------------------------------" + "-------------------------------------------------");


                try {
                    final BaseModel model = (BaseModel) new Gson().fromJson(res, cls);

                    ((BaseActivity) ctx).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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
                        }
                    });
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    ((BaseActivity) ctx).showToast("数据解析错误");
                } catch (Exception e) {
                    e.printStackTrace();
                    ((BaseActivity) ctx).showToast("暂无更新数据");
                }
            }
        });
    }


}
