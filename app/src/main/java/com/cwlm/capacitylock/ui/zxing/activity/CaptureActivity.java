package com.cwlm.capacitylock.ui.zxing.activity;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.ui.LoginActivity;
import com.cwlm.capacitylock.ui.MainActivity;
import com.cwlm.capacitylock.ui.scan.CancelActivity;
import com.cwlm.capacitylock.ui.scan.ErrorActivity;
import com.cwlm.capacitylock.ui.zxing.camera.CameraManager;
import com.cwlm.capacitylock.ui.zxing.decoding.CaptureActivityHandler;
import com.cwlm.capacitylock.ui.zxing.decoding.InactivityTimer;
import com.cwlm.capacitylock.ui.zxing.decoding.RGBLuminanceSource;
import com.cwlm.capacitylock.ui.zxing.view.ViewfinderView;
import com.cwlm.capacitylock.utils.MyDialog;
import com.cwlm.capacitylock.utils.PreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Initial the camera
 * 
 * @author zhangguoyu
 * 
 */
public class CaptureActivity extends BaseActivity implements Callback {

	private ImageView btnLight;
	private ImageView btnOpenImage;
	private boolean playBeep;
	private boolean vibrate;
	private boolean hasSurface;
	private String characterSet;
	private boolean ifOpenLight = false;//判断是否开启闪光灯
	private MediaPlayer mediaPlayer;
	private ViewfinderView viewfinderView;
	private CaptureActivityHandler handler;
	private Vector<BarcodeFormat> decodeFormats;
	private InactivityTimer inactivityTimer;
	private static final float BEEP_VOLUME = 0.10f;


	private OkHttpClient okhttp;
	MyDialog progressDialog = null;

	public CaptureActivity() {
		super(R.layout.act_capture);
	}

//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//		}
//		setContentView(R.layout.act_capture);
//		CameraManager.init(getApplication());
//		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
//		btnLight = (TextView) findViewById(R.id.btn_light);
//		btnOpenImage = (TextView) findViewById(R.id.btn_openimg);
////		btnLight.getBackground().setAlpha(50);
////		btnOpenImage.getBackground().setAlpha(50);
//		btnLight.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				IfOpenLight();
//			}
//		});
//		btnOpenImage.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				pickPictureFromAblum();
//			}
//		});
//
//
//		hasSurface = false;
//		inactivityTimer = new InactivityTimer(this);
//		setListener();
//	}

	@Override
	public void getData() {

	}

	@Override
	public void initView() {
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		btnLight = (ImageView) findViewById(R.id.btn_light);
		btnOpenImage = (ImageView) findViewById(R.id.btn_openimg);
//		btnLight.getBackground().setAlpha(50);
//		btnOpenImage.getBackground().setAlpha(50);
		btnLight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish_Anim();
			}
		});
		btnOpenImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				pickPictureFromAblum();  扫描图片二维码，暂时无此功能
				IfOpenLight();
			}
		});


		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		setListener();
	}

	/**
	 * 注册事件
	 */
	private void setListener() {
		((TextView) findViewById(R.id.tv_title)).setText("扫码停车");
		((ImageView) findViewById(R.id.iv_right)).setVisibility(View.INVISIBLE);
		((ImageView) findViewById(R.id.iv_left)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	/**
	 * Handler scan result
	 * @param result
	 * @param barcode
	 * 扫描获取结果 (因为扫码activity只接受网络请求，而不解析，所以重新添加okhttp请求)
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		String resultString = result.getText();
		// FIXME
		if (resultString.equals("")) {
			Toast.makeText(CaptureActivity.this, "扫描失败!", Toast.LENGTH_SHORT)
					.show();
		} else {

			//创建OkHttpClient对象
			okhttp = new OkHttpClient();
			FormBody body = new FormBody.Builder()
					.add("userId", user.getUserId())
					.add("param", resultString)
					.add("carNumber", user.getCarNumber())
					.build();
			Request request = new Request.Builder()
					.url(InterfaceFinals.scanCode_Requst)
					.post(body)
					.build();

			if (progressDialog == null) {
				progressDialog = new MyDialog(this, "加载中...");
				progressDialog.show();
			}
			//new call
			Call call = okhttp.newCall(request);
			//请求加入调度
			call.enqueue(new okhttp3.Callback() {
				@Override
				public void onFailure(Call call, IOException e) {

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							progressDialog.dismiss();
							showToast("请求失败，请重试");
						}
					});
				}

				@Override
				public void onResponse(Call call, final Response response) throws IOException {
					final String res = response.body().string();

					if (progressDialog != null) {
						progressDialog.dismiss();
					}

					Log.e("response", "|---------------------------------" + "-------------------------------------------------");
					Log.e("response", "|" + res + "");
					Log.e("response", "|---------------------------------" + "-------------------------------------------------");

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							BaseModel result = new Gson().fromJson(res, BaseModel.class);
							//非业主扫码成功  &  业主扫码成功
							if("2".equals(result.getStatusCode())||"1".equals(result.getStatusCode())){
								Intent intent=new Intent(CaptureActivity.this,CancelActivity.class);
								intent.putExtra("data",res);
								startActivity(intent);
								finish();
							}
							//当前车位不可用,推荐附近停车位
							else if("4".equals(result.getStatusCode())||"5".equals(result.getStatusCode())){
								Intent intent=new Intent(CaptureActivity.this,ErrorActivity.class);
								intent.putExtra("freelock",res);
								startActivity(intent);
								finish();
							}
							//车锁主扫非自己车位且未交押金（如果交了押金将会返回 2 ）
							else if("16".equals(result.getStatusCode())){

								//未交押金跳转交押金界面
								startActivity(PayDepositActivity.class);
								finish();

							}
							//黑名单用户
							else if("6".equals(result.getStatusCode())){

								//清除登录信息
								PreferencesUtil.clearPreferences(CaptureActivity.this , "User");
								startActivity(LoginActivity.class);
								finish();

							}else{
								showToast(result.getMess());
								//跳回主页
								startActivity(MainActivity.class);
								finish();
							}


						}
					});
				}
			});
		}
	}


	/*
	 * 获取带二维码的相片进行扫描
	 */
	public void pickPictureFromAblum() {
		Intent mIntent = new Intent(
				Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		startActivityForResult(mIntent, 1);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent) 对相册获取的结果进行分析
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();

				Result resultString = scanningImage1(picturePath);
				if (resultString == null) {
					Toast.makeText(getApplicationContext(), "解析错误，请选择正确的二维码图片", Toast.LENGTH_LONG).show();
				} else {

					String resultImage = resultString.getText();
					if (resultImage.equals("")) {

						Toast.makeText(CaptureActivity.this, "扫描失败",Toast.LENGTH_SHORT).show();
					} else {

						Intent resultIntent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("result", resultImage);
						resultIntent.putExtras(bundle);
						CaptureActivity.this.setResult(RESULT_OK, resultIntent);
					}

					CaptureActivity.this.finish();
				}

				break;

			default:
				break;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 解析QR图内容
	 *
	 * @return
	 */
	// 解析QR图片
	private Result scanningImage1(String picturePath) {

		if (TextUtils.isEmpty(picturePath)) {
			return null;
		}

		Map<DecodeHintType, String> hints1 = new Hashtable<DecodeHintType, String>();
		hints1.put(DecodeHintType.CHARACTER_SET, "utf-8");

		// 获得待解析的图片
		Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
		RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		Result result;
		try {

			result =  reader.decode(bitmap1, (Hashtable<DecodeHintType, String>) hints1);
			return result;
		} catch (NotFoundException e) {
			Toast.makeText(CaptureActivity.this, "解析错误，请选择正确的二维码图片",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (ChecksumException e) {
			Toast.makeText(CaptureActivity.this, "解析错误，请选择正确的二维码图片",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (FormatException e) {
			Toast.makeText(CaptureActivity.this, "解析错误，请选择正确的二维码图片",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		return null;
	}

	// 是否开启闪光灯
	public void IfOpenLight() {

		if (ifOpenLight){
			//关闪光灯
			CameraManager.get().closeLight();
//			btnLight.setText(getString(R.string.str_open_light));
			ifOpenLight = false;
		}else{
			//开闪光灯
			CameraManager.get().openLight();
//			btnLight.setText(getString(R.string.str_close_light));
			ifOpenLight = true;

		}
//		ifOpenLight++;
//
//		switch (ifOpenLight % 2) {
//		case 0:
//			//关闪光灯
//			CameraManager.get().closeLight();
//			btnLight.setText(getString(R.string.str_open_light));
//			break;
//		case 1:
//			//开闪光灯
//			CameraManager.get().openLight();
//			btnLight.setText(getString(R.string.str_close_light));
//			break;
//		default:
//			break;
//		}
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this,decodeFormats,characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {

			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.bi);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}