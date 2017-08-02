package com.cwlm.capacitylock.ui.percenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.base.MyApplication;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-04-park_duration1.
 */
public class OrderInfoCenterActivity extends BaseActivity{
    LinearLayout id_back;
    ListView order_list;
    TextView no_order;

    public OrderInfoCenterActivity(){
        super(R.layout.orderinfo_center_activity);
    }


    @Override
    public void getData() {

    }

    public void initView() {
        tv_title.setText("我的订单");
        iv_right.setVisibility(View.INVISIBLE);
        if (TextUtils.isEmpty(MyApplication.getUserId())){
//            Intent intent1=new Intent(this, LoginActivity.class);
//            startActivity(intent1);
        }

        order_list= (ListView) findViewById(R.id.order_list);
        no_order= (TextView) findViewById(R.id.no_order);
        if ("数据等于空"!= null){
            order_list.setVisibility(View.GONE);no_order.setVisibility(View.VISIBLE);
        }
    }



}
