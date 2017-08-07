package com.cwlm.capacitylock.ui.scan;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.obj.CaptureData_CancelObj;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017-05-09.
 */
public class CancelActivity extends BaseActivity {


    LinearLayout ll_cancel_price_normal , ll_cancel_price_timeout , ll_cancel_remind_text;
    TextView cancel_stop_time, cancel_carlock_num, cancel_price_normal, cancel_price_timeout, cancel_timer, cancel_btn, cancel_btn_back;

    private int recLen = 120;
    Timer timer = new Timer();

    //String userId = "14939765019480474413";
    String rid = "";
    String addr = "";
    private CaptureData_CancelObj result;

    public CancelActivity() {
        super(R.layout.act_cancel);
    }

    public void getData() {

        String data = getIntent().getStringExtra("data");

        result = new Gson().fromJson(data, CaptureData_CancelObj.class);

        rid = "";
        addr = "";
        rid = result.getObject().getRouterId();
        addr = result.getObject().getAddr();

        timer.schedule(task, 0, 1000);       // timeTask
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.cancelPark:

                showToast("取消成功");
                finish_Anim();

                break;
        }
    }

    public void initView() {
        cancel_stop_time = (TextView) findViewById(R.id.cancel_stop_time);
        cancel_carlock_num = (TextView) findViewById(R.id.cancel_carlock_num);
        cancel_price_normal = (TextView) findViewById(R.id.cancel_price_normal);
        cancel_price_timeout = (TextView) findViewById(R.id.cancel_price_timeout);
        cancel_timer = (TextView) findViewById(R.id.cancel_timer);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);
        cancel_btn_back = (TextView) findViewById(R.id.cancel_btn);


        cancel_stop_time.setText("全天");
        cancel_carlock_num.setText(result.getObject().getParkNumber());
        cancel_price_normal.setText(result.getObject().getStopPrice() + "元/时");
        cancel_price_timeout.setText(result.getObject().getTimeout_price() + "元/时");

        findViewById(R.id.cancel_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Anim();
            }
        });


        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String url = Constants.web_url + "/CarLock/stopplace/cancelPark?userId="+userId+"&rid="+rid+"&addr="+addr;

                getDataFromNet(InterfaceFinals.cancelPark ,true , user.getUserId() , rid , addr);


            }
        });


    }



    Boolean isCancel = false;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    cancel_timer.setText(recLen + " s");
                    if (recLen < 0) {
                        timer.cancel();
                        isCancel = true;
                        cancel_timer.setText("已超过两分钟无法取消停车");
                        cancel_btn.setVisibility(View.GONE);
                    }
            }
        }
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            recLen--;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!isCancel) {
            timer.cancel();
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }


        int totalHeight = 0;

        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }



}








