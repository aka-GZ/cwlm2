package com.cwlm.capacitylock.ui.percenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.utils.MyUtils;

/**
 * 提交意见建议
 *
 * @author akawok 2017/08/12
 */
public class CommitFeedbackActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_commit , commit_phone;
    private EditText id_commit_content;

    public CommitFeedbackActivity() {
        super(R.layout.act_commit_feedback);
    }


    @Override
    public void getData() {

    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infcode = resModel.getInfCode();
        switch (infcode){
            case InterfaceFinals.saveAdvice:

                showToast("提交成功，请耐心等待~");

                break;
        }
    }

    public void initView() {
        tv_title.setText("提交建议");

        ll_commit = (LinearLayout) findViewById(R.id.ll_commit);
        commit_phone = (LinearLayout) findViewById(R.id.commit_phone);
        id_commit_content = (EditText) findViewById(R.id.id_commit_content);

        ll_commit.setOnClickListener(this);
        commit_phone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 提交
            case R.id.ll_commit: //提交
                String text = id_commit_content.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    showToast("内容不能为空哦");
                    return;
                }
                getDataFromNet(InterfaceFinals.saveAdvice , user.getUserId() , text);
                break;
            case R.id.commit_phone:  // 拨打手机号

                MyUtils.call(this, "18672194971");

                break;

            default:
                break;
        }
    }


}
