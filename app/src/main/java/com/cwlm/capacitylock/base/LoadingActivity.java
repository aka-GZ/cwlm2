package com.cwlm.capacitylock.base;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.ui.MainActivity;
import com.cwlm.capacitylock.utils.PreferencesUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 加载页
 *
 * @author wok
 */
public class LoadingActivity extends BaseActivity {

    private ImageView loadingba;
    private ViewPager viewPager;

    private int StartNum = 0; // app启动次数


    public LoadingActivity() {
        super(R.layout.act_loading, false, false);
    }

    public void initTitle() {
    }

    Message message = new Message();

    @Override
    public void getData() {


    }

    public void initView() {
        loadingba = (ImageView) findViewById(R.id.loadingba);
        viewPager = (ViewPager) findViewById(R.id.view_pager);


        // 检查网络是否打开
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取代表联网状态的NetWorkInfo对象
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            // 获取当前的网络连接是否可用
            if (!networkInfo.isAvailable() && !networkInfo.isConnected()) {
                showToast("网络连接异常，请检查网络状态");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("网络连接异常，请检查网络状态")
                        .setCancelable(false)
                        .setPositiveButton("确定", new OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                            }
                        }).create().show();
            }
        } else {
            showToast("网络连接异常，请检查网络状态");
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog
                    .setTitle("网络连接异常，请检查网络状态")
                    .setCancelable(false)
                    .setPositiveButton("确定",
                            new OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    finish();
                                }
                            }).create().show();
        }


        StartNum = PreferencesUtil.getStartNumPreferences(LoadingActivity.this, "startnum");//获取启动次数
        PreferencesUtil.setStartNumPreferences(LoadingActivity.this, "startnum", StartNum + 1);//启动次数加一

        Log.e("StartNum", StartNum + "");
        if (StartNum <= 0) {


            List<Integer> startImages = new ArrayList<Integer>();
            startImages.add(R.mipmap.loading_1);
            startImages.add(R.mipmap.loading_2);
            startImages.add(R.mipmap.loading_3);
            startImages.add(R.mipmap.loading_4);
            loadingba.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setAdapter(new InnerImageAdapter(this, startImages));
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    //当引导页在最后一页时出发方法
                    if (position == viewPager.getAdapter().getCount() - 1) {
                        message.what = 1;
                        mHandler.sendMessageDelayed(message, 2000);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } else {
            loadingba.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            message.what = 1;
            mHandler.sendMessageDelayed(message, 2000);
        }
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Intent i = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 引导页适配器
     */
    public class InnerImageAdapter extends PagerAdapter {
        protected List<Integer> imageIdList;
        protected Context mContext;

        public InnerImageAdapter(Context mContext, List<Integer> imageIdList) {
            this.imageIdList = imageIdList;
            this.mContext = mContext;
        }

        @Override
        public final Object instantiateItem(ViewGroup container, int position) {
            //String mItem = imageIdList.get(position);
            ImageView imageView = new ImageView(mContext);
            //Bitmap bm = BitmapFactory.decodeStream(mContext.getAssets().open(mItem));
            imageView.setImageResource(imageIdList.get(position));
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView, layoutParams);
            return imageView;
        }

        @Override
        public final void destroyItem(ViewGroup container, int position,
                                      Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public int getCount() {
            return imageIdList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
