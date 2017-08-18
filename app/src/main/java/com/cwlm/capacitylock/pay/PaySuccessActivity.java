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

public class PaySuccessActivity extends BaseActivity {

    TextView paysuccess_money,paysuccess_park,paysuccess_success;

    String title;
    String message;
    String json;
    PayObj obj;

    public PaySuccessActivity() {
        super(R.layout.act_paysuccess);
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
        tv_title.setText("支付完成");
        paysuccess_money = (TextView) findViewById(R.id.paysuccess_money);
        paysuccess_park = (TextView) findViewById(R.id.paysuccess_park);
        paysuccess_success = (TextView) findViewById(R.id.paysuccess_success);

        if (obj != null){
            paysuccess_money.setText(obj.getMoney());
            paysuccess_park.setText(obj.getPark());

        }else {
            showToast("订单可能推送异常，请至我的订单查询");
        }
        paysuccess_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
