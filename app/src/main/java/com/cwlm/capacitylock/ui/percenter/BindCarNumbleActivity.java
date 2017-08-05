package com.cwlm.capacitylock.ui.percenter;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.ui.LoginActivity;
import com.cwlm.capacitylock.utils.MyUtils;


/**
 * Created by akawok on 2017-08-04.
 */
public class BindCarNumbleActivity extends BaseActivity implements View.OnClickListener {
    Button next_step_2;
    LinearLayout bind_carnum_head;
    EditText carnumber;
    TextView bind_carnum_headtv;

    String []province_list  = new String[]{"京","津","沪","冀","豫","云","辽","黑","湘","皖","鲁","新","苏","浙","赣","鄂","桂","甘","晋","蒙","陕","吉","闽","贵","粤","川","青","藏","琼","宁","渝"};
    String []letter_list  = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    String province = "鄂";
    String letter = "A";

    String StopPlaceId = "";

    public BindCarNumbleActivity() {
        super(R.layout.act_bindcarnumble);
    }

    public void getData() {
        StopPlaceId =  getIntent().getStringExtra("data");
        getDataFromNet(InterfaceFinals.getCarNumber , user.getUserId());
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.getCarNumber:
//                bind_carnum_headtv.setText(resultData1.getObject().toString().substring(0,2));
//                carnumber.setText(resultData1.getObject().toString().substring(2));
                break;
            case InterfaceFinals.bindCarNumber:
                Intent intent = new Intent(BindCarNumbleActivity.this, RechargeMonthCardActivity.class);
                intent.putExtra("data",StopPlaceId);
                startActivity(intent);
//                overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
                finish();
                break;
        }
    }

    public void initView() {
        tv_title.setText("车牌号");
        next_step_2 = (Button) findViewById(R.id.next_step_2);
        next_step_2.setOnClickListener(this);
        bind_carnum_head = (LinearLayout) findViewById(R.id.bind_carnum_head);
        bind_carnum_head.setOnClickListener(this);
        carnumber = (EditText) findViewById(R.id.carnumber);
        bind_carnum_headtv = (TextView) findViewById(R.id.bind_carnum_headtv);

        bind_carnum_headtv.setText(province + letter);

        carnumber.setTransformationMethod(new AllCapTransformationMethod(true));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step_2:
                if (TextUtils.isEmpty(carnumber.getText().toString())) {
                    showToast("请输入您的车牌号码");
                    break;
                }
                if (!MyUtils.isCarNumber(bind_carnum_headtv.getText().toString().toUpperCase() + carnumber.getText().toString().trim().toUpperCase())) {
                    showToast("请输入正确的的车牌号码");
                    break;
                }
                getDataFromNet(InterfaceFinals.bindCarNumber , bind_carnum_headtv.getText().toString().toUpperCase() + carnumber.getText().toString().trim().toUpperCase() , "2");
                break;
            case R.id.bind_carnum_head:

                bottomwindow(bind_carnum_head ,province_list);

                break;
        }
    }

    PopupWindow popupWindow;
    void bottomwindow(View view,String[] list) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.window_popup, null);
        DisplayMetrics  dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        popupWindow = new PopupWindow(layout,
                //ViewGroup.LayoutParams.WRAP_CONTENT,
                screenWidth - 80,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setFocusable(false);

        popupWindow.showAsDropDown(view);
        //添加按键事件监听
        setButtonListeners(layout ,list);
        //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
//        popupWindow.setOnDismissListener(new poponDismissListener());
//        backgroundAlpha(1f);
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
    Boolean starts = true; // 判断popupwindow使用的哪一个list  true为province_list  false为letter_list
    GridView popupwindow_gv;
    private void setButtonListeners(LinearLayout layout, final String[] list) {
        popupwindow_gv = (GridView) layout.findViewById(R.id.popupwindow_gv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BindCarNumbleActivity.this , R.layout.window_popup_item ,R.id.popupwindow_item_tv , list);
        popupwindow_gv.setAdapter(adapter);

        popupwindow_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (starts){
                    province = list[position];
                    bind_carnum_headtv.setText(province + letter);
                    starts = false;
                    popupWindow.dismiss();
                    popupWindow = null;
                    bottomwindow(bind_carnum_head ,letter_list);

                   // ToastUtil.shortShow(BindCarNumbleActivity.this,list[position]);
                }else{
                    letter = list[position];
                    bind_carnum_headtv.setText(province + letter);
                    starts = true;
                    popupWindow.dismiss();
                    popupWindow = null;
                   // ToastUtil.shortShow(BindCarNumbleActivity.this,list[position]);
                }
            }
        });

    }

    public class AllCapTransformationMethod extends ReplacementTransformationMethod {

        private char[] lower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        private char[] upper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        private boolean allUpper = false;

        public AllCapTransformationMethod(boolean needUpper) {
            this.allUpper = needUpper;
        }

        @Override
        protected char[] getOriginal() {
            if (allUpper) {
                return lower;
            } else {
                return upper;
            }
        }

        @Override
        protected char[] getReplacement() {
            if (allUpper) {
                return upper;
            } else {
                return lower;
            }
        }
    }
}