package com.cwlm.capacitylock.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.CarLockObj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016-10-11.
 */
public class NearCarLockAdapter extends BaseAdapter {
    Context context;
    List<CarLockObj> carLocks = new ArrayList<CarLockObj>();

    public NearCarLockAdapter(Context context, List<CarLockObj> carLocks) {
        this.context = context;
        this.carLocks = carLocks;
    }

    @Override
    public int getCount() {
        return carLocks.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return carLocks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_error_carlocks, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.carnumber = (TextView) view.findViewById(R.id.carnumber);
            viewHolder.carlock_time = (TextView) view.findViewById(R.id.carlock_time);
            viewHolder.go = (Button) view.findViewById(R.id.go);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (position == 0){
            viewHolder.carnumber.setText("停车位编号");
            viewHolder.carlock_time.setText("可用时间");
        }else{

            if (carLocks.size() == 0){
                viewHolder.carnumber.setText("附近暂无可用车位");
                viewHolder.carlock_time.setText("无");
            }else{

                viewHolder.carnumber.setText(carLocks.get(position-1).getParkNumber());
                viewHolder.carlock_time.setText("全天");
            }
        }
//        viewHolder.title.setText(carLocks.get(position).getLockName());
        //viewHolder.go.setOnClickListener(new MyListener(position));
        return view;
    }

    static class ViewHolder {
        TextView title, carnumber ,carlock_time;
        Button go;
    }
//    private class MyListener implements View.OnClickListener {
//
//        int mPosition;
//
//        public MyListener(int mPosition) {
//
//            super();
//
//            this.mPosition = mPosition;
//
//        }
//
//        @Override
//        public void onClick(final View v) {
//            long cTime = Calendar.getInstance().getTimeInMillis();
//            if (cTime - Constants.LAST_TIME > Constants.MIN_CLICK_TIME) {
//                Constants.LAST_TIME = cTime;
//            } else {
//                return;
//            }
//            switch (v.getId()){
//                case R.id.go:
//                    Intent intent=new Intent();
//                    intent.putExtra("carlock", JSON.toJSONString(carLocks.get(mPosition)));
//                    Activity activity = (Activity)context;
//                    activity.setResult(3,intent);
//                    activity.finish();
//                    break;
//            }
//        }
//    }
}
