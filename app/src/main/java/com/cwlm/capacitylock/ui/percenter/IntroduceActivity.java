package com.cwlm.capacitylock.ui.percenter;


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
	
	@Override
	public void finish() {
		finish_Anim();
	}
}
