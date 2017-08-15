package com.cwlm.capacitylock.ui.percenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.adapter.OrderInfoAdapter;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.base.MyApplication;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.GetAllStopPlaceModel;
import com.cwlm.capacitylock.model.OrderInfoModel;
import com.cwlm.capacitylock.obj.GetAllStopPlaceObj;
import com.cwlm.capacitylock.obj.OrderInfoObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akawok on 2017-08-04.
 */
public class OrderInfoCenterActivity extends BaseActivity {
    ListView order_list;
    TextView no_order;

    public OrderInfoCenterActivity() {
        super(R.layout.act_orderinfo_center);
    }

    List<OrderInfoObj> list = new ArrayList<OrderInfoObj>();
    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.myOrder:
                list.clear();
                list.addAll(((OrderInfoModel)resModel).getObject());
                OrderInfoAdapter order_list_adapter=new OrderInfoAdapter(this,list);
                order_list.setAdapter(order_list_adapter);
                order_list_adapter.notifyDataSetChanged();
                no_order.setVisibility(View.GONE);

                break;
        }
    }

    @Override
    public void getData() {
      getDataFromNet(InterfaceFinals.myOrder,user.getUserId());
    }

    public void initView() {
        tv_title.setText("订单记录");
        iv_right.setVisibility(View.INVISIBLE);
        order_list= (ListView) findViewById(R.id.order_list);
        no_order= (TextView) findViewById(R.id.no_order);
    }

    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.id_back:
//            finish(true);
//                break;
            default:
                break;
        }
    }

}
