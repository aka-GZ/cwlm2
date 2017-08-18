package com.cwlm.capacitylock.pay;

import android.view.View;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.obj.PayObj;
import com.google.gson.Gson;

/**
 * Created by Zheng on 2017/8/16.
 */

public class PayActivity extends BaseActivity {

    TextView pay_money,pay_park,pay_success;
    String title;
    String message;
    String json;
    PayObj obj;

    public PayActivity() {
        super(R.layout.act_pay);
    }

    @Override
    public void getData() {

        title = getIntent().getStringExtra("title");
        message = getIntent().getStringExtra("message");
        json = getIntent().getStringExtra("json");

        obj = new Gson().fromJson(json , PayObj.class);

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
