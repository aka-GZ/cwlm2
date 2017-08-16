package com.cwlm.capacitylock.pay;

import android.view.View;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;

/**
 * Created by Zheng on 2017/8/16.
 */

public class PayActivity extends BaseActivity {

    TextView pay_money,pay_park,pay_success;

    public PayActivity() {
        super(R.layout.act_pay);
    }

    @Override
    public void getData() {

    }

    @Override
    public void initView() {
        iv_right.setVisibility(View.INVISIBLE);
        tv_title.setText("支付");
        pay_money = (TextView) findViewById(R.id.pay_money);
        pay_park = (TextView) findViewById(R.id.pay_park);
        pay_success = (TextView) findViewById(R.id.pay_success);

        pay_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用支付宝支付
            }
        });

    }
}
