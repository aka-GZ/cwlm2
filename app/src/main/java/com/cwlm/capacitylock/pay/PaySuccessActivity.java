package com.cwlm.capacitylock.pay;

import android.view.View;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;

/**
 * Created by Zheng on 2017/8/16.
 */

public class PaySuccessActivity extends BaseActivity {

    TextView paysuccess_money,paysuccess_park,paysuccess_success;

    public PaySuccessActivity() {
        super(R.layout.act_paysuccess);
    }

    @Override
    public void getData() {

    }

    @Override
    public void initView() {
        iv_right.setVisibility(View.INVISIBLE);
        tv_title.setText("支付完成");
        paysuccess_money = (TextView) findViewById(R.id.paysuccess_money);
        paysuccess_park = (TextView) findViewById(R.id.paysuccess_park);
        paysuccess_success = (TextView) findViewById(R.id.paysuccess_success);

        paysuccess_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
