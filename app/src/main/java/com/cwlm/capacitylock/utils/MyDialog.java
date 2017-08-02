package com.cwlm.capacitylock.utils;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwlm.capacitylock.R;


/**
 * Created by Zheng on 2017/8/2.
 */

public class MyDialog{

    Dialog mDialog;

    private AnimationDrawable animationDrawable = null;

    public MyDialog(Context context, String message) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_progress, null);

        TextView text = (TextView) view.findViewById(R.id.progress_message);
        text.setText(message);
        ImageView loadingImage = (ImageView) view.findViewById(R.id.progress_view);
        loadingImage.setImageResource(R.drawable.progress_dialog_loding);
        animationDrawable = (AnimationDrawable)loadingImage.getDrawable();
        if(animationDrawable!=null){
            animationDrawable.setOneShot(false);
            animationDrawable.start();
        }

        mDialog = new Dialog(context,R.style.dialog);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

    }

    public void show() {
        mDialog.show();
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public void dismiss() {
        if(mDialog.isShowing()) {
            mDialog.dismiss();
            animationDrawable.stop();
        }
    }
    public boolean isShowing(){
        if(mDialog.isShowing()) {
            return true;
        }
        return false;
    }


}
