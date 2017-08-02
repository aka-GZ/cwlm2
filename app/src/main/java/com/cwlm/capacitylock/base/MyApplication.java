package com.cwlm.capacitylock.base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.tencent.bugly.Bugly;

import cn.jpush.android.api.JPushInterface;


/**
 * @author wok
 * 
 *   自定义Application
 **/
public class MyApplication extends Application {

	private ArrayList<Activity> activities = new ArrayList<Activity>();
	private static MyApplication mApplication = null;


	public static String getUserId() {
		return null;
	}
	public void onCreate() {
		super.onCreate();

		//在使用SDK各组件之前初始化context信息，传入ApplicationContext
		//注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());



		Bugly.init(getApplicationContext(), "8a54b12769", false);
		JPushInterface.init(this);


//        user=(UserObj) PreferencesUtil.getPreferences(getApplicationContext(),"USER");
 //       if(user!=null){
//        AsyncSoapUtils.loginId=CardNumUtil.getCardNum(user.getResHeaCard(), user.getCardNum());
 //       	AsyncSoapUtils.loginId= user.getLoginId();
//        }
//        else{
//        	user=new UserObj();
//        }
//		Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
//		initImageLoader(getApplicationContext());
	}

	
	public static MyApplication getInstance() {
		if (mApplication == null) {
			mApplication = new MyApplication();
		}
		return mApplication;
	}

	private void initImageLoader(Context context) {
		// ImageLoader缓存路径
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				context)
//				.threadPoolSize(3)
//				// 线程池内加载的数量
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.diskCache(new UnlimitedDiskCache(new File(InterfaceFinals.fileDirPath)))
//				// 自定义缓存路径
//				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//				.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout(5s),
//				                                                                    // readTimeout(30s)超时时间
//				.build();// 开始构建
//		ImageLoader.getInstance().init(config);// 全局初始化此配置

		// imageloader使用时初始化，class里配置了。
		// DisplayImageOptions options = new DisplayImageOptions.Builder()
		// .showImageOnLoading(R.drawable.ic_launcher) //设置图片在下载期间显示的图片
		// .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
		// .showImageOnFail(R.drawable.ic_launcher) //设置图片加载/解码过程中错误时候显示的图片
		// .cacheInMemory(true)//设置下载的图片是否缓存在内存中
		// .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
		// .displayer(new RoundedBitmapDisplayer(2))//是否设置为圆角，弧度为多少
		// .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
		// .build();//构建完成

	}
	

	 /** 
    * 向Activity列表中添加Activity对象*/
	public void addActivity(Activity a) {
		activities.add(a);
	}
	  
	
	 /** 
     * Activity关闭时，删除Activity列表中的Activity对象*/  
    public void removeActivity(Activity a){  
    	activities.remove(a);  
    }  
	
	public void onTerminate() {
		super.onTerminate();

		for (Activity activity : activities) {
			activity.finish();
		}
		System.exit(0);  
		//杀死该应用进程  
//	       android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	
}
