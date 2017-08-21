package com.cwlm.capacitylock.ui.percenter;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;

/**
 * Created by akawok on 2017-08-04.
 * 我的预定
 */
public class MyPreParkActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout my_prepark;
    TextView no_prepark, pre_park_name, start_time, orderinfopoid;
    Button my_prepark_parknumber, cancel_prepark, start_navigation;

    public MyPreParkActivity() {
        super(R.layout.act_myprepark);
    }


    @Override
    public void getData() {
//        getDataFromNet(InterfaceFinals.predetermine, user.getUserId());
    }

    public void initView() {
        tv_title.setText("我的预定");
        iv_right.setVisibility(View.INVISIBLE);

        no_prepark = (TextView) findViewById(R.id.no_prepark);
        pre_park_name = (TextView) findViewById(R.id.pre_park_name);
        start_time = (TextView) findViewById(R.id.start_time);
        my_prepark_parknumber = (Button) findViewById(R.id.my_prepark_parknumber);
        cancel_prepark = (Button) findViewById(R.id.cancel_prepark);
        cancel_prepark.setOnClickListener(this);
        orderinfopoid = (TextView) findViewById(R.id.orderinfopoid);
        my_prepark = (LinearLayout) findViewById(R.id.my_prepark);
        start_navigation = (Button) findViewById(R.id.start_navigation);
        start_navigation.setOnClickListener(this);

        my_prepark.setVisibility(View.GONE);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_prepark:
                //取消预订
                getDataFromNet(InterfaceFinals.cancelPredetermine, orderinfopoid.getText().toString());
                break;
            case R.id.start_navigation:
                //导航
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.predetermine:
                no_prepark.setVisibility(View.GONE);
//                ResultData resultData=JSON.parseObject(msg.obj.toString(),ResultData.class);
//                if ("1".equals(resultData.getStatusCode())){
//                    no_prepark.setVisibility(View.GONE);
//                    OrderInfoPo orderInfoPo=JSON.parseObject(resultData.getObject().toString(),OrderInfoPo.class);
//                    orderinfopoid.setText(orderInfoPo.getOrderInfoId());
//                    pre_park_name.setText(orderInfoPo.getStopPlaceName());
//                    start_time.setText("请您前往停车，保留30分钟，"+orderInfoPo.getCreateTime()+"后开始计费");
//                    my_prepark_parknumber.setText("您已预约的车位:"+orderInfoPo.getFloor()+"层"+orderInfoPo.getParkNumber()+"号");
//                    my_prepark.setVisibility(View.VISIBLE);
//                    end_latitude=Double.parseDouble(orderInfoPo.getLatitude());
//                    end_longitude=Double.parseDouble(orderInfoPo.getLongitude());
//                }else if("2".equals(resultData.getStatusCode())){
//                    my_prepark.setVisibility(View.GONE);
//                }
////                ToastUtil.show(this, JSON.toJSONString(msg.obj));
                break;
            case InterfaceFinals.cancelPredetermine:
//                ResultData resultData1 = JSON.parseObject(msg.obj.toString(), ResultData.class);
//                if ("1".equals(resultData1.getStatusCode())) {
//                    my_prepark.setVisibility(View.GONE);
//                    no_prepark.setVisibility(View.VISIBLE);
//                }
//                ToastUtil.show(this, resultData1.getMess());
                break;
            default:
                break;
        }
    }

}
