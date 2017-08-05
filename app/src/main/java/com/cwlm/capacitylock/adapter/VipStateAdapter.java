package com.cwlm.capacitylock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.VipStateObj;

import java.util.List;

/**
 *
 */
public class VipStateAdapter extends BaseAdapter {
    private Context context;
    List<VipStateObj> list;

    public VipStateAdapter(Context context, List<VipStateObj> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (list == null) {
            return null;
        } else {
            return list.get(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.item_vipstate, null);
            viewHolder = new ViewHolder();
            viewHolder.vipstate_item_tv_1 = (TextView) view.findViewById(R.id.vipstate_item_tv_1);
            viewHolder.vipstate_item_tv_2 = (TextView) view.findViewById(R.id.vipstate_item_tv_2);
            viewHolder.vipstate_item_tv_3 = (TextView) view.findViewById(R.id.vipstate_item_tv_3);
            viewHolder.vipstate_item_tv_4 = (TextView) view.findViewById(R.id.vipstate_item_tv_4);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.recharge_item_ll.setBackgroundResource(R.drawable.mywallet_recharge_pay);
//        viewHolder.recharge_item_tv_1.setTextColor(context.getResources().getColor(R.color.background));
//        viewHolder.recharge_item_tv_2.setTextColor(context.getResources().getColor(R.color.background));
        viewHolder.vipstate_item_tv_1.setText("月卡ID:  " + list.get(position).getMonthCardId());
        viewHolder.vipstate_item_tv_2.setText("停车场:  " + list.get(position).getStopPlaceName());
        viewHolder.vipstate_item_tv_3.setText("开始时间:  " + list.get(position).getStartTime());
        viewHolder.vipstate_item_tv_4.setText("到期时间:  " + list.get(position).getDeadLine());


        return view;
    }

    class ViewHolder {
        TextView vipstate_item_tv_1 ,vipstate_item_tv_2 ,vipstate_item_tv_3,vipstate_item_tv_4;
    }
}
