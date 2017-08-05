package com.cwlm.capacitylock.ui.percenter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;


/**
 *
 * Created by akawok on 2017-08-05.
 */
public class MyWallet_1Activity extends BaseActivity implements View.OnClickListener {
    TextView accountBalance;
    LinearLayout recharge;


    public MyWallet_1Activity(){
        super(R.layout.act_mywallet);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadMyBalance();
//    }

//    private void loadMyBalance() {
//        Map<String,Object> map= MyApplication.getMap();
//        ZylmOkHttpManager.PostRequest(ZylmRestClient.getAbsoluteUrl(Constants.Strings.request_url59),map,R.id.getBalance,activityHandler);
//    }

    @Override
    public void getData() {

    }

    public void initView() {
        recharge= (LinearLayout) findViewById(R.id.recharge);
        recharge.setOnClickListener(this);
        accountBalance= (TextView) findViewById(R.id.accountBalance);
//        TextPaint tp = accountBalance.getPaint();
//        tp.setFakeBoldText(true);
    }

//    public void loadData(Message msg) {
//        if(msg.what==R.id.getBalance){
//            ResultData resultData= JSON.parseObject(msg.obj.toString(),ResultData.class);
//            if ("1".equals(resultData.getStatusCode())){
//                UnionWeixinUser unionWeixinUser=JSON.parseObject(resultData.getObject().toString(),UnionWeixinUser.class);
//                accountBalance.setText(unionWeixinUser.getAccountBalance().toString());
//            }else if("2".equals(resultData.getStatusCode())){
//                //提示请先登陆
//                ToastUtil.show(MyWallet_1Activity.this,resultData.getMess());
//                Intent intent=new Intent(MyWallet_1Activity.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }else if ("3".equals(resultData.getStatusCode())){
//                ToastUtil.show(MyWallet_1Activity.this,resultData.getMess());
//            }else{
//                ToastUtil.show(this,resultData.getMess());
//            }
//
//        }
//    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recharge:
//                Intent intent=new Intent(MyWallet_1Activity.this,RechargeActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.push_right_out, R.anim.push_left_in);
                showToast("创建订单支付");
                break;
            default:
                break;
        }
    }
}
