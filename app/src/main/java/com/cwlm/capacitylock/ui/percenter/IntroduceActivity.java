package com.cwlm.capacitylock.ui.percenter;


import android.view.KeyEvent;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;

/**
 * 企业介绍
 * akawok 2017-08-09
 */
public class IntroduceActivity extends BaseActivity {

	public IntroduceActivity() {
		super(R.layout.act_introduce);
	}


	@Override
	public void getData() {

	}

	public void initView() {
		tv_title.setText("联系我们");
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//			finish_Anim();
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
}
