package com.cwlm.capacitylock.ui.percenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.adapter.RechargeAdapter;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.obj.RechargeObj;
import com.cwlm.capacitylock.pay.PayResult;
import com.cwlm.capacitylock.ui.ServiceWebView;

import java.util.Map;

/**
 * 充值
 * Created by akawok on 2017-03-23.
 */
public class RechargeActivity extends BaseActivity implements View.OnClickListener {
    Button recharge_now;
//    // 商户PID
//    public static final String PARTNER = "2088021739597205";
//    // 商户收款账号
//    public static final String SELLER = "zylm_net@163.com";
//    // 商户私钥，pkcs8格式
//    public String RSA_PRIVATE= "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKapDgR4kjBXR5dCt5+jjIa7OOIpt9uUtTIoBwKrAcFaUYIR6j797h7SdOXifHqwsGjQEJ6vFxWTrca/2VomQ3aLvOIBGXEy8QkuKkN6rBeFRFoM4D7SqdWhJTML20AjF5tIrz4YFrktP3TcZY9K5FdUBW5qkucgufy4b5PDW0IbAgMBAAECgYBm2K6AzdMRJ9tTGeXtCuV+g2zwDGScieFjiVYCLHuR0WW5Scrvgo5+0Ms4zhKZOA41MCkBc/Vf6Fl17BB6VGCOeSAKiINLtdTANoWJzSR4Mj6XJwvydiqsUGRCCsik7VWOHqbYPY1dF2h2SFAYch+f6BDZEr8fslvCPZBBVWpqoQJBANkJKwY09Nx8HhYi+FS2ezxRVF2k83cI1oG1Za9yra92qvXWVLx9Yr/Yy7FoZjr3QybAHbJssljxZNL3y7IMHgsCQQDElKmWvFPRuoCq5xQjHZaFLfuQtu07TR9V0c/CfiIvTPuDeUr2WTSR9x+on079VBKG1YJsm2UjRET3Yntoh8YxAkEAjTE8ryYGYeXRAN+xJBMu6teSBfXIeZjjS7DBvTjiqzKNF69eot0DQAF+yiQ71YKEShJTf37dK6WmzORtvJ/LbwJBAKXdjc5KryqaA7Cv8zTB5VwP9WAgiMBtjU/7DSLdDHj+qoU/VXnQwOOcMRE1AhAL3On5wudioo6ELvDRHrGPRjECQGe5udYrPd/L2Yl5k10cHgGtwl1ig5LzpNaGA1J07DZBbCjHfvoWsARN+DafI3PEQF4sU3SBuZArhcAzESwdz/E=";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    LinearLayout id_back,ll_wxpay,ll_alipay;
    TextView charge_agreement;
    GridView recharge_gv;
    CheckBox cb_wxpay,cb_alipay;
    String Checked = "alipay";

    String[] money_list = new String[]{"10","30","50","100","150","200"};
    RechargeAdapter adapter = new RechargeAdapter(RechargeActivity.this , money_list);

    public RechargeActivity() {
        super(R.layout.act_recharge);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.getRechargeOrderInfo://app下订单返回参数
                RechargeObj model = (RechargeObj) resModel;


                final String payInfo = model.getObject();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(RechargeActivity.this);
                        // 调用支付接口，获取支付结果
//                            String result = alipay.pay(payInfo, true);
                        Map<String, String> result = alipay.payV2(payInfo, true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
        }
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

                showToast("支付成功");
                finish();

            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                showToast("支付失败");
            }
        };
    };

    int Position = -1;
    View OldView = null;
    public void initView() {
        tv_title.setText("充值");
        iv_right.setVisibility(View.INVISIBLE);
        recharge_now= (Button) findViewById(R.id.recharge_now);
        recharge_now.setOnClickListener(this);
        recharge_gv = (GridView) findViewById(R.id.recharge_gv);
        charge_agreement= (TextView) findViewById(R.id.charge_agreement);
        charge_agreement.setOnClickListener(this);

        adapter = new RechargeAdapter(RechargeActivity.this , money_list);
        recharge_gv.setAdapter(adapter);
        recharge_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (OldView == null){
                    OldView = view;
                    ChangeNewView(view , true);
                }else{
                    ChangeNewView(OldView , false);
                    ChangeNewView(view , true);
                    OldView = view;
                }
                Position = position;
            }
        });


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

    private void ChangeNewView(View view , Boolean isNew){

        TextView tv = (TextView) view.findViewById(R.id.recharge_item_tv_1);
        TextView tv2 = (TextView) view.findViewById(R.id.recharge_item_tv_2);
        if (isNew) {
            view.findViewById(R.id.recharge_item_ll).setBackgroundResource(R.drawable.mywallet_recharge_pay_gold);
            tv.setTextColor(getResources().getColor(R.color.white));
            tv2.setTextColor(getResources().getColor(R.color.white));
        }else{
            view.findViewById(R.id.recharge_item_ll).setBackgroundResource(R.drawable.mywallet_recharge_pay);
            tv.setTextColor(getResources().getColor(R.color.text_gary));
            tv2.setTextColor(getResources().getColor(R.color.text_gary));
        }
    }
//    private void ChangeOldView(View oldView){
//
//        oldView.findViewById(R.id.recharge_item_ll).setBackgroundResource(R.drawable.mywallet_recharge_pay);
//        TextView tv = (TextView)oldView.findViewById(R.id.recharge_item_tv_1);
//        tv.setTextColor(getResources().getColor(R.color.background));
//        TextView tv2 = (TextView)oldView.findViewById(R.id.recharge_item_tv_2);
//        tv2.setTextColor(getResources().getColor(R.color.background));
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recharge_now:
                if ("alipay".equals(Checked)) {


                    if (Position != -1) {

                        getDataFromNet(InterfaceFinals.getRechargeOrderInfo , user.getUserId() , money_list[Position].toString());

                    }else{
                        showToast("请选择金额");
                    }


                }else if("wxpay".equals(Checked)){
                    showToast("目前暂支持支付宝支付");
                }
                break;
            case R.id.charge_agreement:
                Intent intent1=new Intent();
                intent1.setClass(RechargeActivity.this, ServiceWebView.class);
                intent1.putExtra("LoadUrl", InterfaceFinals.agreement);
                startActivity(intent1);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
                break;
        }
    }
}
