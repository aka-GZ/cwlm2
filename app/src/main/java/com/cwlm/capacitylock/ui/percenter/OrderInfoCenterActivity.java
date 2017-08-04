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
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.base.MyApplication;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.GetAllStopPlaceModel;
import com.cwlm.capacitylock.obj.GetAllStopPlaceObj;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by akawok on 2017-08-04.
 */
public class OrderInfoCenterActivity extends BaseActivity {
    ListView order_list;
    TextView no_order;
//    List<OrderInfo> list = new ArrayList<OrderInfo>();

    public OrderInfoCenterActivity() {
        super(R.layout.act_orderinfo_center);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.myOrder:
//                list.clear();
//                list.addAll(((GetAllStopPlaceModel) resModel).getObject());
//                for (int i = 0; i < list.size(); i++) {
//                    Bundle mBundle = new Bundle();
//                    mBundle.putInt("id", i);
//                    AddBaiduMarker(list.get(i).getLatitude(), list.get(i).getLongitude(), mBundle);
//                }


                break;
        }
    }

    @Override
    public void getData() {
//      getDataFromNet(InterfaceFinals.myOrder,user.getUserId());
        getDataFromNet(InterfaceFinals.myOrder,"15009468415885658379");
    }

    public void initView() {
        tv_title.setText("订单记录");
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

//    public void loadData(Message msg) {
//        switch (msg.what){
//            case R.id.getOrderInfos:
////                ToastUtil.show(this, JSON.toJSONString(msg.obj));
//                ResultData resultData= JSON.parseObject(msg.obj.toString(),ResultData.class);
//                if ("1".equals(resultData.getStatusCode())){
//                    List<OrderInfoPo> orderInfos=JSON.parseArray(resultData.getObject().toString(),OrderInfoPo.class);
//                    Order_List_Adapter order_list_adapter=new Order_List_Adapter(this,orderInfos);
//                    order_list.setAdapter(order_list_adapter);
//                    order_list_adapter.notifyDataSetChanged();
//                    no_order.setVisibility(View.GONE);
//                }
//                break;
//            default:
//                break;
//        }

//    }
}
