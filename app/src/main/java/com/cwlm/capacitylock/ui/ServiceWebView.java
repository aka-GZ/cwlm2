package com.cwlm.capacitylock.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;

/**
 * Created by Zheng on 2017/6/12.
 */

public class ServiceWebView extends BaseActivity {

    WebView web;
    String LoadUrl = "";

    public ServiceWebView() {
        super(R.layout.act_service_web_view);
    }

    @Override
    public void getData() {
        LoadUrl = getIntent().getStringExtra("LoadUrl");
    }


    public void initView() {
        tv_title.setText("协议");
        iv_right.setVisibility(View.INVISIBLE);

        web = (WebView) findViewById(R.id.service_web);

        //声明WebSettings子类
        WebSettings webSettings = web.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
      // web.pauseTimers();  不太理解的属性
        //方式1. 加载一个网页：
        web.loadUrl(LoadUrl);

//
//        //方式2：加载apk包中的html页面
//        web.loadUrl("file:///android_asset/test.html");
//
//        //方式3：加载手机本地的html页面
//        web.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");



//     复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // view.loadUrl(url);
                if (openWithWevView(url) && url.contains("zylm.net")) {//如果是超链接，执行此方法
                    view.loadUrl(url);
                    return true;
                } else if (url.startsWith("tel:")) {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                   // view.loadUrl(url);
                }
                else {
//                 Uri uri = Uri.parse(url); // url为你要链接的地址  百度地图
//                 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                 startActivity(intent);
//                    //ToastUtil.shortShow(ServiceWebView.this,"特殊链接");
////                    view.loadUrl(url);
                }

                return true;


            }
        });
    }
    protected boolean openWithWevView(String url) {//处理判断url的合法性
// TODO Auto-generated method stub
        if (url.startsWith("http:") || url.startsWith("https:")) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        //防止内存溢出方法
        //在 Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空。
        if (web != null) {
            web.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            web.clearHistory();
            ((ViewGroup) web.getParent()).removeView(web);
            web.destroy();
            web = null;
        }
        super.onDestroy();
    }
}
