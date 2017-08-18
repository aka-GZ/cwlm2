package com.cwlm.capacitylock.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.MyLocksObj;
import com.cwlm.capacitylock.ui.MainActivity;
import com.cwlm.capacitylock.ui.percenter.BindCarNumbleActivity;
import com.cwlm.capacitylock.ui.percenter.MyLockActivity;
import com.cwlm.capacitylock.ui.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by akawok on 2017-08-14.
 */
public class MyLockAdapter extends BaseAdapter {
    private Context context;
    List<MyLocksObj> myLocks;
    MyClickListener listener;

    public MyLockAdapter(Context context, List<MyLocksObj> myLocks ,MyClickListener listener) {
        this.context = context;
        this.myLocks = myLocks;
        this.listener = listener;
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
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_mylock_adapter, null);
            viewHolder = new ViewHolder();

            viewHolder.item_mylock_tv_share= (TextView) view.findViewById(R.id.item_mylock_tv_share);
            viewHolder.item_mylock_time= (TextView) view.findViewById(R.id.item_mylock_time);
            viewHolder.item_mylock_parking_status= (TextView) view.findViewById(R.id.item_mylock_parking_status);
            viewHolder.item_mylock_parking_num= (TextView) view.findViewById(R.id.item_mylock_parking_num);
            viewHolder.item_mylock_parking_lot= (TextView) view.findViewById(R.id.item_mylock_parking_lot);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //State 0分享  1未分享
        if ("0".equals(myLocks.get(position).getState())){
            viewHolder.item_mylock_tv_share.setText(R.string.close_share);
            viewHolder.item_mylock_tv_share.setTextColor(Color.parseColor("#81de65"));
            viewHolder.item_mylock_tv_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    new MyLockActivity().share("1" , myLocks.get(position).getCarLockId());
                    listener.clickListener("1" , myLocks.get(position).getCarLockId());
                }
            });
        }else if ("1".equals(myLocks.get(position).getState())){
            viewHolder.item_mylock_tv_share.setText(R.string.share);
            viewHolder.item_mylock_tv_share.setTextColor(Color.parseColor("#f44747"));
            viewHolder.item_mylock_tv_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    new MyLockActivity().share("0" , myLocks.get(position).getCarLockId());
                    listener.clickListener("0" , myLocks.get(position).getCarLockId());
                }
            });
        }else {

            viewHolder.item_mylock_tv_share.setText("数据请求失败，请重新打开页面");

        }

        String status = "未知";
        //Upordown 0占用1空闲
        if ("0".equals(myLocks.get(position).getUpordown())){
//            status = "占用";
            if(myLocks.get(position).getCarNumber() != null){
                status = myLocks.get(position).getCarNumber();
            }else{
                status = "正在使用中";
            }
        }else if ("1".equals(myLocks.get(position).getUpordown())){
           status = "空闲";
        }else{
            status = "未知";
        }
        viewHolder.item_mylock_parking_status.setText(status);
        viewHolder.item_mylock_parking_num.setText(myLocks.get(position).getParkNumber());
        viewHolder.item_mylock_parking_lot.setText(myLocks.get(position).getStopPlaceName());

        if(myLocks.get(position).getUseTime()==null ||"".equals(myLocks.get(position).getUseTime())){
            viewHolder.item_mylock_time.setText("空闲");
        }else{
            viewHolder.item_mylock_time.setText(myLocks.get(position).getUseTime());
        }
        return view;
    }

     class ViewHolder {
       TextView item_mylock_tv_share,item_mylock_parking_status,item_mylock_parking_num,item_mylock_parking_lot,item_mylock_time;
    }

    //自定义接口，用于回调按钮点击事件到Activity
    public interface MyClickListener{
        public void clickListener(String isShare , String CarLockId);
    }

}