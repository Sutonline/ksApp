package android.kevin.cn.ks.activity;

import android.content.Context;
import android.graphics.Color;
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
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongkang.zhang
 *         Created by yongkang.zhang on 2017/11/6.
 */

public class DailyCheck extends BaseActivity {

    private static int IS_HAS_PLAN = 0;
    private static final List<Plan> PLAN_LIST = new ArrayList<>();
    private LinearLayout planLayout;
    private ButtonRectangle addPlan;
    private ButtonRectangle up;
    private ButtonRectangle check;
    private ButtonRectangle giveUp;
    private TextView planName;
    //  check的8个button
    private static final Integer[] CHECK_BTNS = new Integer[]{R.id.check_btn_1, R.id.check_btn_2,
        R.id.check_btn_3, R.id.check_btn_4, R.id.check_btn_5, R.id.check_btn_6, R.id.check_btn_7, R.id.check_btn_8};
    private static int CHECKED_BTNS = 0;
    // 是否今天已签到
    private static int CHECK_FLAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_check_layout);
        planLayout = (LinearLayout) findViewById(R.id.plan);
        addPlan = (ButtonRectangle) findViewById(R.id.add_plan);
        up = (ButtonRectangle) findViewById(R.id.up_btn);
        check = (ButtonRectangle) findViewById(R.id.check_btn);
        giveUp = (ButtonRectangle) findViewById(R.id.gp_btn);
        planName = (TextView) findViewById(R.id.plan_name);

        initButton();
    }

    private void initButton() {
        // 初始化计划dialog
        initPlan();

        // 加载check
        initCheck();

        // 初始化up


        // 初始化giveUp



    }

    private void initPlan() {
        // plan change
        if (IS_HAS_PLAN == 1) {
            planLayout.setVisibility(View.VISIBLE);
            addPlan.setVisibility(View.INVISIBLE);
        } else {
            planLayout.setVisibility(View.INVISIBLE);
            addPlan.setVisibility(View.VISIBLE);
        }
        addPlan.setOnClickListener(planChange());
        planName.setOnClickListener(planChange());
    }

    private void initCheck() {
        // 每次init都置成0
        CHECKED_BTNS = 1;
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(DailyCheck.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.check_dialog_layout))
                        .setPadding(10, 10, 10, 10)
                        .setCancelable(true)
                        .create();
                for (int i = 0; i < 8; i++) {
                    ButtonRectangle button = (ButtonRectangle) dialogPlus.getHolderView().findViewById(CHECK_BTNS[i]);
                    button.setOnClickListener(checkBtnClick(dialogPlus));
                }
                dialogPlus.show();
            }
        });
    }

    private void initGiveUp() {
        giveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(getActivity())
                        .setContentHolder(new ViewHolder(R.layout.give_up_layout))
                        .setPadding(10, 10, 10, 10)
                        .setCancelable(true)
                        .create();
            }
        });

    }


    // plan change dialog
    private View.OnClickListener planChange() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPlanList();
                PlanAdapter planAdapter = new PlanAdapter(DailyCheck.this, PLAN_LIST);
                final DialogPlus dialogPlus = DialogPlus.newDialog(DailyCheck.this)
                        .setHeader(R.layout.plan_list_header_layout)
                        //  不使用确定和取消按钮
                        // .setFooter(R.layout.plan_list_footer_layout)
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
                        .setCancelable(true)
                        .create();
                dialogPlus.show();
            }
        };
    }

    private View.OnClickListener checkBtnClick(final DialogPlus dialogPlus) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 每一个button点击的时候变颜色
                ButtonRectangle buttonRectangle = (ButtonRectangle) v;
                buttonRectangle.setBackgroundColor(Color.parseColor("#33cc33"));

                // 检查是否8个都点击了，如果是的话就打卡成功
                if (CHECKED_BTNS >= 8) {
                    Toast.makeText(DailyCheck.this, "签到成功", Toast.LENGTH_SHORT).show();
                    dialogPlus.dismiss();
                }

                CHECKED_BTNS += 1;
            }
        };
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
        // 如果选择的是同一个 不变化
        if (this.planName.getText().equals(plan.getName())) {
            return;
        }
        this.planLayout.setVisibility(View.VISIBLE);
        this.addPlan.setVisibility(View.INVISIBLE);
        this.planName.setText(plan.getName());
    }


    private Context getActivity() {
        return DailyCheck.this;
    }
}
