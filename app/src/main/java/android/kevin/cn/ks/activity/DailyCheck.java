package android.kevin.cn.ks.activity;

import android.kevin.cn.ks.R;
import android.kevin.cn.ks.common.CommonHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.Dialog;

/**
 * author: yongkang.zhang
 * Created by yongkang.zhang on 2017/11/6.
 */

public class DailyCheck extends BaseActivity {

    private static final int IS_HAS_PLAN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_check_layout);
        initButton();
    }

    private void initButton() {
        LinearLayout plan = (LinearLayout) findViewById(R.id.plan);
        ButtonRectangle addPlan = (ButtonRectangle) findViewById(R.id.add_plan);
        ButtonRectangle sos = (ButtonRectangle) findViewById(R.id.sos_btn);
        ButtonRectangle check = (ButtonRectangle) findViewById(R.id.check_btn);
        ButtonRectangle giveUp = (ButtonRectangle) findViewById(R.id.gp_btn);
        TextView textView = (TextView) findViewById(R.id.plan_name);
        if (IS_HAS_PLAN == 1) {
            plan.setVisibility(View.VISIBLE);
            addPlan.setVisibility(View.INVISIBLE);
        } else {
            plan.setVisibility(View.INVISIBLE);
            addPlan.setVisibility(View.VISIBLE);
        }

        boolean wrapInScrollView = true;


        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(DailyCheck.this)
                        .title("测试dialog")
                        .customView(R.layout.plan_list_layout, true)
                        .positiveText("缺人吗")
                        .show();
            }
        });


    }


}
