package com.cwlm.capacitylock.ui.percenter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.MyBalanceModel;
import com.cwlm.capacitylock.obj.MyBalanceObj;


/**
 *我的钱包
 * Created by akawok on 2017-08-05.
 */
public class MyWallet_1Activity extends BaseActivity implements View.OnClickListener {
    TextView accountBalance ,mywallet_deposit_tv;
    LinearLayout recharge , mywallet_deposit_rl , mywallet_redpacket_ll;


    public MyWallet_1Activity(){
        super(R.layout.act_mywallet);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromNet(InterfaceFinals.appGetMyBalance , user.getUserId());
    }


    @Override
    public void getData() {

    }

    public void initView() {
        tv_title.setText("我的钱包");
        iv_right.setVisibility(View.INVISIBLE);
        recharge= (LinearLayout) findViewById(R.id.recharge);
        mywallet_deposit_rl= (LinearLayout) findViewById(R.id.mywallet_deposit_rl);
        mywallet_redpacket_ll= (LinearLayout) findViewById(R.id.mywallet_redpacket_ll);
        accountBalance = (TextView) findViewById(R.id.accountBalance);
        mywallet_deposit_tv = (TextView) findViewById(R.id.mywallet_deposit_tv);

        recharge.setOnClickListener(this);
        mywallet_deposit_rl.setOnClickListener(this);
//        TextPaint tp = accountBalance.getPaint(); 加粗字体
//        tp.setFakeBoldText(true);
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infcode = resModel.getInfCode();
        switch(infcode){
            case InterfaceFinals.appGetMyBalance:

                MyBalanceObj obj = ((MyBalanceModel)resModel).getObject();
                accountBalance.setText(obj.getAccountBalance() + "");

                break;

        }
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recharge:
                startActivity(RechargeActivity.class);
                break;
            default:
                break;
        }
    }
}
