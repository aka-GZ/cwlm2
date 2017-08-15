package com.cwlm.capacitylock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;


/**
 *
 */
public class RechargeAdapter extends BaseAdapter {
    private Context context;
    String[] money_list;

    public RechargeAdapter(Context context, String[] money_list) {
        this.context = context;
        this.money_list = money_list;
    }

    @Override
    public int getCount() {
        if (money_list == null) {
            return 0;
        } else {
            return money_list.length;
        }
    }

    @Override
    public Object getItem(int position) {
        if (money_list == null) {
            return null;
        } else {
            return money_list[position];
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
            view = LayoutInflater.from(context).inflate(R.layout.item_recharge, null);
            viewHolder = new ViewHolder();
            viewHolder.recharge_item_ll = (LinearLayout) view.findViewById(R.id.recharge_item_ll);
            viewHolder.recharge_item_tv_1 = (TextView) view.findViewById(R.id.recharge_item_tv_1);
            viewHolder.recharge_item_tv_2 = (TextView) view.findViewById(R.id.recharge_item_tv_2);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.recharge_item_ll.setBackgroundResource(R.drawable.mywallet_recharge_pay);
//        viewHolder.recharge_item_tv_1.setTextColor(context.getResources().getColor(R.color.background));
//        viewHolder.recharge_item_tv_2.setTextColor(context.getResources().getColor(R.color.background));
        viewHolder.recharge_item_tv_2.setText(money_list[position]);


        return view;
    }

    class ViewHolder {
        TextView recharge_item_tv_1,recharge_item_tv_2;
        LinearLayout recharge_item_ll;
    }
}
