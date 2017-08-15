package com.cwlm.capacitylock.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.net.OkHttpUtils;
import com.cwlm.capacitylock.obj.UserObj;
import com.cwlm.capacitylock.ui.LoginActivity;
import com.cwlm.capacitylock.ui.MainActivity;
import com.cwlm.capacitylock.ui.percenter.BindCarNumbleActivity;
import com.cwlm.capacitylock.ui.zxing.activity.CaptureActivity;
import com.cwlm.capacitylock.utils.MyDialog;
import com.cwlm.capacitylock.utils.PreferencesUtil;
import com.cwlm.capacitylock.utils.SystemBarTintManager;

import okhttp3.OkHttpClient;

/**
 * @author wok
 * 
 * BaseActivity
 **/
public abstract class BaseActivity extends Activity {


	private int mLayoutId = 0;
	public boolean title = true;
	public boolean bottom = true;
	public boolean mIsShow = true;
	public Toast mToast = null;
	private List<OkHttpUtils> taskList = new ArrayList<OkHttpUtils>();
	
	public ImageView iv_left,iv_right;
	public TextView tv_title;
	public LinearLayout main_title;
	public LinearLayout main_ll_sy,main_ll_xx,main_ll_gr;
	

	public UserObj user;
//------------------------------------------------------------
	
	
	public BaseActivity(int resid) {
		mLayoutId = resid;
	}
	
	/**
	 * 是否显示title、bottom
	 */
	public BaseActivity(int resid , boolean title , boolean bottom) {
		mLayoutId = resid;
		this.title = title;
		this.bottom = bottom;
	}
	

	
	
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		setContentView(mLayoutId);

		user = (UserObj) PreferencesUtil.getPreferences(BaseActivity.this, "User");

		MyApplication.getInstance().addActivity(this);
		getData();
		initTitle();
		initBottom();
		initView();

	}

	/**
	 * 重新加载user对象数据（页面需要时时刷新数据时使用）
	 */
	public void onRestartUser() {
		user = (UserObj) PreferencesUtil.getPreferences(BaseActivity.this, "User");
	}

	/**
	 * 初始化标题栏
	 */
	public void initTitle() {
		if (!title) {
		return;
		}
		// 沉浸式改变状态栏颜色，并且底部栏不沉浸
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			tintManager.setStatusBarTintEnabled(true);
//			tintManager.setStatusBarTintResource(R.color.green);// 通知栏所需颜色
			tintManager.setStatusBarTintResource(R.color.blue);// 通知栏所需颜色
			tintManager.setStatusBarAlpha((float)0);
		}
		
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		tv_title = (TextView) findViewById(R.id.tv_title);
		main_title = (LinearLayout) findViewById(R.id.main_title);
		
		iv_left.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
			}
		});
		iv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BaseActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//回到主页并且销毁其他activity
				startActivity(intent);
				finish();
			}
		});

	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {winParams.flags |= bits;
		} else {winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/**
	 * 初始化底部栏
	 */
	public void initBottom() {
		if (!bottom) {
			return;
		}
//
//		main_ll_sy = (LinearLayout) findViewById(R.id.main_ll_sy);
//		main_ll_xx = (LinearLayout) findViewById(R.id.main_ll_xx);
//		main_ll_gr = (LinearLayout) findViewById(R.id.main_ll_gr);
//
//		main_ll_sy.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				Intent intent = new Intent(BaseActivity.this, MainActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//回到主页并且销毁其他activity
//				startActivity(intent);
////				finish();
//			}
//		});
//		main_ll_xx.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				Intent intent = new Intent(BaseActivity.this, PerCenterActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//回到主页并且销毁其他activity
//				startActivity(intent);
////				finish();
//			}
//		});
//		main_ll_gr.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				Intent intent = new Intent(BaseActivity.this, PerCenterActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//回到主页并且销毁其他activity
//				startActivity(intent);
////				finish();
//			}
//		});
	}
	
	/**
	 * 获取activity页面所需初始数据
	 */
	public abstract void getData();
	
	/**
	 * 网络请求成功返回方法
	 */
	public void onSuccess(BaseModel resModel){
		
	};
	/**
	 * 失败返回 Msg
	 */
	public void onFail(BaseModel resModel){
		if (resModel != null) {
			showToast(resModel.getMess());
		}
	};
	
	/**
	 * 初始化控件，设置监听事件
	 */
	public abstract void initView();
	
	/**
	 * 显示提示框
	 * @param msg
	 */
	public void showToast(String msg){
		if (mToast == null) {
			mToast = Toast.makeText(BaseActivity.this, "", Toast.LENGTH_SHORT);
		}
		mToast.setText(msg);
		mToast.show();
	}

	public void showDialog(String msg){

		View v = getLayoutInflater().inflate(R.layout.base_actvity_dialog , null);
		final Dialog dialog = new Dialog(BaseActivity.this,R.style.mydialog);
		dialog.setContentView(R.layout.base_actvity_dialog);
		dialog.show();
		v.findViewById(R.id.base_dialog_skip).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(CaptureActivity.class);
				if(dialog.isShowing()) {
					dialog.dismiss();
				}
			}
		});
		v.findViewById(R.id.base_dialog_go).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(BindCarNumbleActivity.class);
				if(dialog.isShowing()) {
					dialog.dismiss();
				}
			}
		});
		TextView base_dialog_text = (TextView) v.findViewById(R.id.base_dialog_text);
		base_dialog_text.setText(msg);
	}




	MyDialog progressDialog = null;
	/**
	 * 显示加载框
	 */
	public void showProgressDialog(){

		if (progressDialog == null) {
			progressDialog = new MyDialog(this, "加载中...");
			progressDialog.show();
		}

	}
	/**
	 * 显示加载框
	 * @param msg
	 */
	public void showProgressDialog(String msg){

		if (progressDialog == null) {
			progressDialog = new MyDialog(this, msg + "");
			progressDialog.show();
		}

	}
	/**
	 * 关闭加载框
	 */
	public void cancelProgressDialog(){

		if (progressDialog != null) {
			progressDialog.dismiss();
		}

	}
	
	/**
	 * 网络请求方法
	 * @param infCord
	 * @param params
	 */
	public void getDataFromNet(int infCord, String... params){
		OkHttpUtils http = new OkHttpUtils(BaseActivity.this, infCord);
		http.GetDataFromNet(params);
		taskList.add(http);
	}
	
	/**
	 * 网络请求方法
	 * @param infCord
	 * @param showProgressDialog
	 * @param params
	 */
	public void getDataFromNet(int infCord, boolean showProgressDialog, String... params){
		OkHttpUtils http = new OkHttpUtils(BaseActivity.this, infCord, showProgressDialog);
		http.GetDataFromNet(params);
		taskList.add(http);
	}
	
	/**
	 * 跳转activity
	 * @param cls
	 */
	public void startActivity(Class<?> cls){
		startActivity(cls, null);
	}
    /**
     * 跳转activity并携带数据
     * @param cls
     * @param obj
     */
    public void startActivity(Class<?> cls, Object obj){
		Intent intent = new Intent(this,cls);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		if (obj != null) {
			intent.putExtra("data", (Serializable) obj);
		}
		startActivity(intent);

		overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
//		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	}

	@Override
	public void finish() {
		super.finish();
	}


	/**
	 * 有退出动画的finish
	 */
	public void finish_Anim() {
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
	}

	/**
	 * 给页面返回键添加动画
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			finish_Anim();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	protected void onDestroy() {
		if (taskList != null && !taskList.isEmpty()) {
			for (OkHttpUtils task : taskList) {
				task.cancel();
			}
		}
		MyApplication.getInstance().removeActivity(this);
		super.onDestroy();
	}
}
