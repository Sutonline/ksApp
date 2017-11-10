package android.kevin.cn.ks.activity;

import android.kevin.cn.ks.R;
import android.kevin.cn.ks.adapter.PlanAdapter;
import android.kevin.cn.ks.domain.Plan;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/6.
 */

public class DailyCheck extends BaseActivity {

    private static int IS_HAS_PLAN = 0;
    private static final List<Plan> PLAN_LIST = new ArrayList<>();
    private LinearLayout planLayout;
    private ButtonRectangle addPlan;
    private ButtonRectangle sos;
    private ButtonRectangle check;
    private ButtonRectangle giveUp;
    private TextView planName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_check_layout);
        initButton();
    }

    private void initButton() {
        planLayout = (LinearLayout) findViewById(R.id.plan);
        addPlan = (ButtonRectangle) findViewById(R.id.add_plan);
        sos = (ButtonRectangle) findViewById(R.id.sos_btn);
        check = (ButtonRectangle) findViewById(R.id.check_btn);
        giveUp = (ButtonRectangle) findViewById(R.id.gp_btn);
        planName = (TextView) findViewById(R.id.plan_name);
        if (IS_HAS_PLAN == 1) {
            planLayout.setVisibility(View.VISIBLE);
            addPlan.setVisibility(View.INVISIBLE);
        } else {
            planLayout.setVisibility(View.INVISIBLE);
            addPlan.setVisibility(View.VISIBLE);
        }

        // 初始化添加计划按钮
        initAddPlan();

    }

    private void initAddPlan() {
        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPlanList();
                PlanAdapter planAdapter = new PlanAdapter(DailyCheck.this, PLAN_LIST);
                final DialogPlus dialogPlus = DialogPlus.newDialog(DailyCheck.this)
                        .setHeader(R.layout.plan_list_header_layout)
                        .setFooter(R.layout.plan_list_footer_layout)
                        .setAdapter(planAdapter)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                showPlan(PLAN_LIST.get(position));
                                Toast.makeText(DailyCheck.this, "选择的是: " + PLAN_LIST.get(position).getId(),
                                        Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .setExpanded(true)
                        .setGravity(Gravity.CENTER)
                        .setInAnimation(R.anim.abc_fade_in)
                        .setOutAnimation(R.anim.abc_fade_out)
                        .setPadding(10, 10, 10, 30)
                        .setCancelable(false)
                        .create();
                ButtonRectangle add = (ButtonRectangle) dialogPlus.getFooterView().findViewById(R.id.add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DailyCheck.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                        dialogPlus.dismiss();
                    }
                });
                ButtonRectangle cancle = (ButtonRectangle) dialogPlus.getFooterView().findViewById(R.id.cancle);
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.dismiss();
                    }
                });
                dialogPlus.show();
            }
        });
    }


    private void initPlanList() {
        // 防止重复添加
        if (PLAN_LIST.size() > 0) {
            return;
        }
        Plan plan = new Plan(1, "戒心", "戒心desc");
        PLAN_LIST.add(plan);
        plan = new Plan(2, "健身", "健身desc");
        PLAN_LIST.add(plan);
        plan = new Plan(3, "其他", "其他desc");
        PLAN_LIST.add(plan);
    }

    private void showPlan(Plan plan) {
        IS_HAS_PLAN = 1;
        this.planLayout.setVisibility(View.VISIBLE);
        this.addPlan.setVisibility(View.INVISIBLE);
        this.planName.setText(plan.getName());
    }

}
