package com.cwlm.capacitylock.ui.percenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.ui.LoginActivity;
import com.cwlm.capacitylock.utils.MyUtils;
import com.cwlm.capacitylock.utils.PreferencesUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人中心
 *
 * @author wok
 */
public class PersonInfoCenterActivity extends BaseActivity implements OnClickListener {


    private CircleImageView menu_user_img;
    private LinearLayout menu_reservation_record, menu_reservation, menu_per_center, per_vip_state, menu_certification, menu_complaints ,bind_carnumber;
    private TextView menu_user_name, menu_logout;
    private LinearLayout menu_ll_userbg, per_vip, menu_wallet_top, per_mylock, menu_apply_lock_top;
    private ViewGroup.LayoutParams lp;


    private String downUrl;
    private String verName;
    private String verCode;
    private File file;
    private long exitTime = 0;

    public PersonInfoCenterActivity() {
        super(R.layout.act_mymenu);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_user_img:      //用户头像

//                getPicFromPhoto();                                     //用户换头像方法，新版本调试支持7.0
                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    showToast("已登录!");
                }

//                 //判断用户是否登录
//                 if(TextUtils.isEmpty(MyApplication.getUserId())){
//                     //showToast("请先登录!");
//                     intent.setClass(PersonInfoCenterActivity.this,LoginActivity.class);
//                 }else{
//                     intent.setClass(PersonInfoCenterActivity.this, PersonalCenterActivity.class);
//                 }
//                 startActivity(intent);
//                 overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);

                break;
            case R.id.menu_user_name:      //用户名

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    showToast("已登录!");
                }

                break;
            case R.id.menu_reservation_record: //订单中心


                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(OrderInfoCenterActivity.class);
                }

                break;
            case R.id.menu_reservation:    //我的预定

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(MyPreParkActivity.class);
                }

                break;

            case R.id.iv_right:             //右上角 - 设置
                // 判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(SettingActivity.class);
                }
                break;

            case R.id.per_vip:         //办理月卡

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(BindParkActivity.class);
                }

                break;
            case R.id.menu_wallet_top:         //我的钱包

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(MyWallet_1Activity.class);
                }

				break;
			case R.id.per_mylock:         //我的车位

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(MyLockActivity.class);
				}

				break;
            case R.id.menu_apply_lock_top:         //申请车位锁

                startActivity(ApplyLockActivity.class);

                break;
            case R.id.per_vip_state:         //查询月卡状态

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(VipStateActivity.class);
                }


                break;
            case R.id.bind_carnumber:         //绑定车牌号

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(BindCarNumbleActivity.class);
                }


                break;
//			case R.id.menu_certification:         //实名认证
//
//				//判断用户是否登录
//				if(TextUtils.isEmpty(MyApplication.getUserId())){
//					showToast("请先登录!");
//					intent.setClass(PersonInfoCenterActivity.this,LoginActivity.class);
//				}else{
//					intent.setClass(PersonInfoCenterActivity.this, BindInfoActivity.class);
//				}
//				startActivity(intent);
//				overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
//
//
//				break;
			case R.id.menu_complaints:         //联系我们

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(CommitFeedbackActivity.class);
				}


				break;
            case R.id.menu_per_center:     // 设置 - 更多

                //判断用户是否登录
                if (!MyUtils.isLogin(PersonInfoCenterActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(SettingActivity.class);
                }

                break;

            default:
                break;
        }
    }


    public void initView() {

        tv_title.setText("个人中心");
        iv_right.setImageResource(R.mipmap.setting);
        iv_right.setOnClickListener(this);
        main_title.setBackgroundColor(Color.parseColor("#00000000"));


        menu_reservation_record = (LinearLayout) findViewById(R.id.menu_reservation_record);
        menu_reservation = (LinearLayout) findViewById(R.id.menu_reservation);
        menu_per_center = (LinearLayout) findViewById(R.id.menu_per_center);
        menu_ll_userbg = (LinearLayout) findViewById(R.id.menu_ll_userbg);
        per_vip_state = (LinearLayout) findViewById(R.id.per_vip_state);
        menu_certification = (LinearLayout) findViewById(R.id.menu_certification);
        menu_complaints = (LinearLayout) findViewById(R.id.menu_complaints);
        bind_carnumber = (LinearLayout) findViewById(R.id.bind_carnumber);

        per_vip = (LinearLayout) findViewById(R.id.per_vip);
        menu_wallet_top = (LinearLayout) findViewById(R.id.menu_wallet_top);
        per_mylock = (LinearLayout) findViewById(R.id.per_mylock);
        menu_apply_lock_top = (LinearLayout) findViewById(R.id.menu_apply_lock_top);

        menu_user_img = (CircleImageView) findViewById(R.id.menu_user_img);
        menu_user_name = (TextView) findViewById(R.id.menu_user_name);
        //menu_logout = (TextView) findViewById(R.id.menu_logout);


        //设置图片自适应比例
        int system_width = this.getResources().getDisplayMetrics().widthPixels;
        lp = menu_ll_userbg.getLayoutParams();
        lp.width = system_width;
        lp.height = system_width / 2;
        menu_ll_userbg.setLayoutParams(lp);

        menu_user_img.setOnClickListener(this);
        menu_user_name.setOnClickListener(this);
        menu_reservation_record.setOnClickListener(this);
        menu_reservation.setOnClickListener(this);
        menu_per_center.setOnClickListener(this);
        //menu_logout.setOnClickListener(this);

        per_vip.setOnClickListener(this);
        menu_wallet_top.setOnClickListener(this);
        per_mylock.setOnClickListener(this);
        menu_apply_lock_top.setOnClickListener(this);
        per_vip_state.setOnClickListener(this);
        menu_certification.setOnClickListener(this);
        menu_complaints.setOnClickListener(this);
        bind_carnumber.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        onRestartUser();

        //显示手机号
        if (user == null || TextUtils.isEmpty(user.getPhoneNum())) {
            menu_user_name.setText("未登录");
        } else {
            String PhoneNum = user.getPhoneNum();
            menu_user_name.setText(PhoneNum.substring(0, 3) + "****" + PhoneNum.substring(7));

            try {
                Log.e("头像URL", "-" + PreferencesUtil.getPreferences(PersonInfoCenterActivity.this, "usericon"));
                if (PreferencesUtil.getPreferences(PersonInfoCenterActivity.this, "usericon") != null && MyUtils.isExist((String) PreferencesUtil.getPreferences(PersonInfoCenterActivity.this, "usericon"))) {
                    Bitmap bitmap = BitmapFactory.decodeFile((String) PreferencesUtil.getPreferences(PersonInfoCenterActivity.this, "usericon"));
                    menu_user_img.setImageBitmap(bitmap);
                } else {
                    menu_user_img.setImageResource(R.mipmap.my_icon);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("=================", "======cuowu===========");
            }

        }
        super.onResume();
    }


    //    /**
//     * 双击退出
//     */
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                showToast("再次点击退出程序");
//                exitTime = System.currentTimeMillis();
//            } else {
//                Intent intent = new Intent();
//                intent.setAction("com.molian.qhcg.finishActivity");
//                sendBroadcast(intent);
//            }
//
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


    private void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 0);
    }

    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "test.jpg")));
        startActivityForResult(intent, -1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case -1://-1表示拍照成功
                File file = new File(Environment.getExternalStorageDirectory() + "/test.jpg");
                if (file.exists()) {
                    photoClip(Uri.fromFile(file));
                }
                break;
            case 0:
                if (data != null) {
//                    photoClip(data.getData());
                    photoClip(getImageContentUri(this, uri2File(data.getData())));
                }
                break;
            case 1:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Log.w("test", "data");
                        Bitmap photo = extras.getParcelable("data");
                        menu_user_img.setImageBitmap(photo);
                        saveBitmap(photo, "usericon");
                        //将用户头像存在sharedPreferences里面
                        PreferencesUtil.setPreferences(PersonInfoCenterActivity.this, "usericon", InterfaceFinals.fileDirPath + "usericon");
                        Log.e("未存之前的头像URL", "-" + InterfaceFinals.fileDirPath + "usericon");
                    }
                }
                break;
            default:
                break;
        }

    }

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        //可以选择图片类型，如果是*表明所有类型的图片
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 720);
        intent.putExtra("outputY", 720);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }

    private void saveBitmap(Bitmap bitmap, String bitName) {
        File file = new File(InterfaceFinals.fileDirPath + bitName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File uri2File(Uri uri) {
        File file = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        int actual_image_column_index = actualimagecursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor
                .getString(actual_image_column_index);
        file = new File(img_path);
        return file;
    }

    /**
     * 转化地址为content开头
     *
     * @param context
     * @param imageFile
     * @return
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


}
