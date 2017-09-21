package com.cwlm.capacitylock.ui.percenter;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.PredetermineModel;
import com.cwlm.capacitylock.obj.PredetermineObj;
import com.cwlm.capacitylock.ui.MainActivity;

/**
 * Created by akawok on 2017-08-04.
 * 我的预定
 */
public class MyPreParkActivity extends BaseActivity implements View.OnClickListener {

    TextView no_prepark;
    //预约后的布局
    LinearLayout main_ll_appointment_yy, main_ll_navigation_yy;
    TextView main_parkname_yy, main_parkaddress_yy, main_parknumber_yy, main_time_yy, main_cancel_appointment_yy, main_navigation_locklight_yy;

    public MyPreParkActivity() {
        super(R.layout.act_myprepark);
    }


    @Override
    public void getData() {
        if (user.getUserId() != null && !"".equals(user.getUserId())) {
            getDataFromNet(InterfaceFinals.getPredetermine, user.getUserId());
        }
    }

    public void initView() {
        tv_title.setText("我的预定");
        iv_right.setVisibility(View.INVISIBLE);


        //预约后布局
        main_ll_appointment_yy = (LinearLayout) findViewById(R.id.main_ll_appointment_yy);
        main_ll_navigation_yy = (LinearLayout) findViewById(R.id.main_ll_navigation_yy);
        main_parkname_yy = (TextView) findViewById(R.id.main_parkname_yy);
        main_parkaddress_yy = (TextView) findViewById(R.id.main_parkaddress_yy);
        main_parknumber_yy = (TextView) findViewById(R.id.main_parknumber_yy);
        main_time_yy = (TextView) findViewById(R.id.main_time_yy);
        main_navigation_locklight_yy = (TextView) findViewById(R.id.main_navigation_locklight_yy);
        main_cancel_appointment_yy = (TextView) findViewById(R.id.main_cancel_appointment_yy);
        main_ll_navigation_yy.setOnClickListener(this);
        main_cancel_appointment_yy.setOnClickListener(this);


        no_prepark = (TextView) findViewById(R.id.no_prepark);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_cancel_appointment_yy: //取消预订
                if (predetermineObj!=null){
                    final Dialog dialog = new Dialog(MyPreParkActivity.this, R.style.mydialog);
                    dialog.setContentView(R.layout.dialog);
                    TextView dialog_skip = (TextView) dialog.findViewById(R.id.dialog_skip);
                    TextView dialog_go = (TextView) dialog.findViewById(R.id.dialog_go);
                    final TextView dialog_text = (TextView) dialog.findViewById(R.id.dialog_text);
                    dialog_skip.setText("确定");
                    dialog_go.setText("取消");
                    dialog_text.setText("              是否确定取消预约？              ");
                    dialog.show();

                    dialog_skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            getDataFromNet(InterfaceFinals.cancelPredetermine, predetermineObj.getOrderInfoId());
                        }
                    });
                    dialog_go.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    });
                }else{
                    showToast("取消失败，请退出重试");
                }

                break;
            case R.id.main_ll_navigation_yy:  //车位闪灯（室内导航）
                if (predetermineObj!=null){

                    getDataFromNet(InterfaceFinals.lockLight, user.getUserId(), predetermineObj.getRouterId()+"", predetermineObj.getAddr()+"");

                }else{
                    showToast("如果灯未闪烁，请重试");
                }
                break;
            default:
                break;
        }
    }

    PredetermineObj predetermineObj = null; //查询预约信息后赋值，给导航与取消预约使用
    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.getPredetermine:
                no_prepark.setVisibility(View.GONE);
                main_ll_appointment_yy.setVisibility(View.VISIBLE);
                PredetermineObj pObj = ((PredetermineModel) resModel).getObject();
                predetermineObj = pObj;

                main_parkname_yy.setText(pObj.getStopPlaceName());
                main_parkaddress_yy.setText(pObj.getParkAddress());
                main_parknumber_yy.setText("车位编号：" + pObj.getParkNumber());
                main_time_yy.setText(pObj.getReservationDeadline());
                break;
            case InterfaceFinals.cancelPredetermine:
                 main_ll_appointment_yy.setVisibility(View.GONE);
                no_prepark.setVisibility(View.VISIBLE);
                showToast("取消成功");

                break;
            case InterfaceFinals.lockLight:
                main_navigation_locklight_yy.setText("正在闪烁中..");
                showToast("车位接收成功，请寻找闪灯车位");

                break;
            default:
                break;
        }
    }

}
