package com.cwlm.capacitylock.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.MyLocksObj;

import java.io.Serializable;
import java.util.List;

/**
 * Created by akawok on 2017-08-14.
 */
public class MyLockAdapter extends BaseAdapter {
    private Context context;
    List<MyLocksObj> myLocks;

    public MyLockAdapter(Context context, List<MyLocksObj> myLocks) {
        this.context = context;
        this.myLocks = myLocks;
    }

    @Override
    public int getCount() {
       if(myLocks==null){
           return 0;
       }else{
           return myLocks.size();
       }
    }

    @Override
    public Object getItem(int position) {
        if (myLocks==null){
            return null;
        }else{
            return myLocks.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_mylock_adapter, null);
            viewHolder = new ViewHolder();

            viewHolder.item_mylock_tv_time= (TextView) view.findViewById(R.id.item_mylock_tv_time);
            viewHolder.item_mylock_parking_status= (TextView) view.findViewById(R.id.item_mylock_parking_status);
            viewHolder.item_mylock_parking_num= (TextView) view.findViewById(R.id.item_mylock_parking_num);
            viewHolder.item_mylock_parking_lot= (TextView) view.findViewById(R.id.item_mylock_parking_lot);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.item_mylock_tv_time.setOnClickListener(new MyListener(position));

        String status = "未知";
        //Upordown 0占用1空闲
        if ("0".equals(myLocks.get(position).getUpordown())){
            status = "占用";
        }else if ("1".equals(myLocks.get(position).getUpordown())){
           status = "空闲";
        }else{
            status = "未知";
        }
        viewHolder.item_mylock_parking_status.setText(status);
        viewHolder.item_mylock_parking_num.setText(myLocks.get(position).getParkNumber());
        viewHolder.item_mylock_parking_lot.setText(myLocks.get(position).getStopPlaceName());
        return view;
    }
    static class ViewHolder {
       TextView item_mylock_tv_time,item_mylock_parking_status,item_mylock_parking_num,item_mylock_parking_lot;

    }
    private class MyListener implements View.OnClickListener {
        Integer mPosition;
        public MyListener(Integer mPosition) {
            super();
            this.mPosition = mPosition;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_mylock_tv_time:

//                    MyLocksObj obj = myLocks.get(mPosition);
//                    Intent i = new Intent(context , RentalTimeActivity.class);
//                    i.putExtra("data" , (Serializable) obj);
//                    context.startActivity(i);

                    break;
            }
        }
//
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            if (isChecked){
//                parkDetailActivity.getRouterIds().add(carLocks.get(mPosition).getRouterId());
//                parkDetailActivity.getAddrs().add(carLocks.get(mPosition).getAddr());
//            }else{
//                parkDetailActivity.getRouterIds().remove(carLocks.get(mPosition).getRouterId());
//                parkDetailActivity.getAddrs().remove(carLocks.get(mPosition).getAddr());
//            }
//        }
    }
}