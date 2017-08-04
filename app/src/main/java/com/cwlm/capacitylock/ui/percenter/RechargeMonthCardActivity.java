package com.cwlm.capacitylock.ui.percenter;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.RechargeMonthCardModel;
import com.cwlm.capacitylock.ui.ServiceWebView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akawok on 2017-08-04.
 */
public class RechargeMonthCardActivity extends BaseActivity implements View.OnClickListener {
    Button recharge_now;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;

    LinearLayout id_back, ll_wxpay, ll_alipay;
    TextView charge_agreement ,textView1,textView2,textView3,textView0;
    //GridView recharge_gv;
    CheckBox cb_wxpay, cb_alipay;
    String Checked = "alipay";

    List<Double> money_list;

    public RechargeMonthCardActivity() {
        super(R.layout.act_rechargemonthcard);
    }


    public void getData() {
        String StopPlaceId = getIntent().getStringExtra("data");

        //StopPlaceId = "1";  //测试用
        getDataFromNet(InterfaceFinals.getStopPlaceAllMonthCardPrice , StopPlaceId);

    }

    int Position = -1;
    View OldView = null;

    public void initView() {
        tv_title.setText("充值");
        recharge_now = (Button) findViewById(R.id.recharge_now);
        recharge_now.setOnClickListener(this);
        charge_agreement = (TextView) findViewById(R.id.charge_agreement);
        charge_agreement.setOnClickListener(this);


        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView0 = (TextView) findViewById(R.id.textView0);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView0.setOnClickListener(this);

        cb_wxpay = (CheckBox) findViewById(R.id.cb_wxpay);
        cb_alipay = (CheckBox) findViewById(R.id.cb_alipay);
        ll_wxpay = (LinearLayout) findViewById(R.id.ll_wxpay);
        ll_alipay = (LinearLayout) findViewById(R.id.ll_alipay);
        ll_wxpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked = "wxpay";
                cb_alipay.setChecked(false);
                cb_wxpay.setChecked(true);
            }
        });
        ll_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked = "alipay";
                cb_alipay.setChecked(true);
                cb_wxpay.setChecked(false);
            }
        });

    }

//    private void ChangeNewView(View view , Boolean isNew){
//
//        TextView tv = (TextView) view.findViewById(R.id.recharge_item_tv_1);
//        TextView tv2 = (TextView) view.findViewById(R.id.recharge_item_tv_2);
//        if (isNew) {
//            view.findViewById(R.id.recharge_item_ll).setBackgroundResource(R.drawable.mywallet_recharge_pay_gold);
//            tv.setTextColor(getResources().getColor(R.color.white));
//            tv2.setTextColor(getResources().getColor(R.color.white));
//        }else{
//            view.findViewById(R.id.recharge_item_ll).setBackgroundResource(R.drawable.mywallet_recharge_pay);
//            tv.setTextColor(getResources().getColor(R.color.gray_text_dark));
//            tv2.setTextColor(getResources().getColor(R.color.gray_text_dark));
//        }
//    }
public void selected(int i) {
    try {
        for (int j = 0; j < 4; j++) {
            if (i == j) {
                ((TextView) findViewById((Integer) R.id.class.getField("textView" + j).getInt(new R.id()))).setBackgroundResource(R.drawable.mywallet_recharge_pay_gold);
                ((TextView) findViewById((Integer) R.id.class.getField("textView" + j).getInt(new R.id()))).setTextColor(getResources().getColor(R.color.white));
            } else {
                ((TextView) findViewById((Integer) R.id.class.getField("textView" + j).getInt(new R.id()))).setBackgroundResource(R.drawable.mywallet_recharge_pay);
                ((TextView) findViewById((Integer) R.id.class.getField("textView" + j).getInt(new R.id()))).setTextColor(getResources().getColor(R.color.text_gary));
            }
        }
        Position = i;

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge_now:
                if ("alipay".equals(Checked)) {
//                    // 订单
//                    String orderInfo = getOrderInfo("车位", "地锁租用费用", Constants.Strings.recharge_money);
//                    // 对订单做RSA 签名
//                    String sign = sign(orderInfo);


                    if (Position != -1) {

                        getDataFromNet(InterfaceFinals.getOrderInfo , money_list.get(Position).toString() , "会员卡办理");

                    } else {
                        showToast("请选择卡类型");

                    }


                } else if ("wxpay".equals(Checked)) {
                    showToast("目前暂支持支付宝支付");
                }


                break;
            case R.id.textView1:
                selected(1);
                break;
            case R.id.textView2:
                selected(2);
                break;
            case R.id.textView3:
                selected(3);
                break;
            case R.id.textView0:
                selected(0);
                break;
            case R.id.charge_agreement:
                Intent intent1=new Intent();
                intent1.setClass(RechargeMonthCardActivity.this, ServiceWebView.class);
                intent1.putExtra("LoadUrl",InterfaceFinals.agreement);
                startActivity(intent1);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
                break;
        }
    }

    RechargeMonthCardModel.ObjectBean obj = null;

    @Override
    public void loadData(Message msg) {
        switch (msg.what) {
            case SDK_PAY_FLAG: {
                PayResult payResult = new PayResult((String) msg.obj);
                // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                String resultInfo = payResult.getResult();
                String resultStatus = payResult.getResultStatus();
                // ToastUtil.show(RechargeActivity.this,resultStatus);
                // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                if (TextUtils.equals(resultStatus, "9000")) {
                    Map<String, Object> map = MyApplication.getMap();//stopPlaceCardPriceType （1表示月卡，2表示季卡，3表示半年卡，4表示年卡）
                    String stopPlaceCardPriceType = "";
                    if (Position == 0) {
                        stopPlaceCardPriceType = "1";
                    } else if (Position == 1) {
                        stopPlaceCardPriceType = "2";
                    } else if (Position == 2) {
                        stopPlaceCardPriceType = "3";
                    } else if (Position == 3) {
                        stopPlaceCardPriceType = "4";
                    }
                    map.put("stopPlaceCardPriceType", stopPlaceCardPriceType);
                    map.put("stopPlaceId", obj.getStopPlaceId());
                    map.put("payMoney", money_list.get(Position));
                    ZylmOkHttpManager.PostRequest(ZylmRestClient.getAbsoluteUrl(Constants.Strings.request_url88), map, R.id.success_rechargemonthcard, activityHandler);
                } else {
                    // 判断resultStatus 为非“9000”则代表可能支付失败
                    // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                    if (TextUtils.equals(resultStatus, "8000")) {

                    } else {
                        // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                        setResult(400);
                        finish();
                    }
                }
                break;
            }
            case SDK_CHECK_FLAG: {
                ToastUtil.show(this, "检查结果为：" + msg.obj);
                break;
            }
            case R.id.success_rechargemonthcard:

                CaptureData cdmodel = new Gson().fromJson(msg.obj.toString(), CaptureData.class);

                if ("1".equals(cdmodel.getStatusCode())) {

                    ToastUtil.shortShow(this, "充值成功");
                    Intent intent = new Intent(RechargeMonthCardActivity.this, VipStateActivity.class);
                    startActivity(intent);
                } else{
                    showToast(cdmodel.getMess().toString());
                    }

                finish();
                break;
            case R.id.rechargemonthcard: //获取月卡信息
                RechargeMonthCardModel rmcmmodel = new Gson().fromJson(msg.obj.toString(), RechargeMonthCardModel.class);

                if ("1".equals(rmcmmodel.getStatusCode())) {

                    obj = rmcmmodel.getObject();
                    money_list = new ArrayList<Double>();
                    money_list.clear();
                    money_list.add(obj.getMonthPrice());
                    money_list.add(obj.getQuarterPrice());
                    money_list.add(obj.getHalfYearPrice());
                    money_list.add(obj.getYearPrice());
                    if (obj.getMonthPrice()!=0){
                        textView0.setVisibility(View.VISIBLE);
                        textView0.setText("月卡 " + obj.getMonthPrice() + "元" );
                    }
                    if (obj.getQuarterPrice()!=0){
                        textView1.setVisibility(View.VISIBLE);
                        textView1.setText("季卡 " + obj.getQuarterPrice() + "元" );
                    }
                    if (obj.getHalfYearPrice()!=0){
                        textView2.setVisibility(View.VISIBLE);
                        textView2.setText("半年卡 " + obj.getHalfYearPrice() + "元" );
                    }
                    if (obj.getYearPrice()!=0){
                        textView3.setVisibility(View.VISIBLE);
                        textView3.setText("年卡 " + obj.getYearPrice() + "元" );
                    }
                    //RechargeMonthCardAdapter adapter = new RechargeMonthCardAdapter(RechargeMonthCardActivity.this, money_list);
//                    recharge_gv.setAdapter(adapter);
//                    recharge_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                            if (OldView == null){
//                                OldView = view;
//                                ChangeNewView(view , true);
//                            }else{
//                                ChangeNewView(OldView , false);
//                                ChangeNewView(view , true);
//                                OldView = view;
//                            }
//                            Position = position;
//                        }
//                    });
                } else {
                    showToast(rmcmmodel.getMess().toString());
                }
                break;
            case R.id.recharge: //获取支付宝订单
                RechargeModel model = new Gson().fromJson(msg.obj.toString(), RechargeModel.class);

                if ("1".equals(model.getStatusCode())) {

                    final String payInfo = model.getMap().getPayInfo();
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // 构造PayTask 对象
                            PayTask alipay = new PayTask(RechargeMonthCardActivity.this);
                            // 调用支付接口，获取支付结果
                            String result = alipay.pay(payInfo);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            Message.obtain(activityHandler, SDK_PAY_FLAG, result).sendToTarget();
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                } else {
                    showToast(model.getMess().toString());
                }
                break;
            default:
                break;
        }
    }
}
