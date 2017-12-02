package android.kevin.cn.ks.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.adapter.PlanAdapter;
import android.kevin.cn.ks.common.RxResultCompat;
import android.kevin.cn.ks.common.RxSchedulerHelper;
import android.kevin.cn.ks.data.manage.PlanDataManager;
import android.kevin.cn.ks.domain.Plan;
import android.kevin.cn.ks.util.DataManagerFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import net.steamcrafted.materialiconlib.MaterialIconView;

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
    private ButtonRectangle up;
    private ButtonRectangle check;
    private ButtonRectangle giveUp;
    private TextView planName;
    // 日历icon
    private MaterialIconView calendar;
    // 统计资料icon
    private MaterialIconView statistics;
    // 设置icon
    private MaterialIconView settings;
    // check的8个button
    private static final Integer[] CHECK_BTNS = new Integer[]{R.id.check_btn_1, R.id.check_btn_2,
            R.id.check_btn_3, R.id.check_btn_4, R.id.check_btn_5, R.id.check_btn_6, R.id.check_btn_7, R.id.check_btn_8};
    private static int CHECKED_BTNS = 0;
    // 是否今天已签到
    private static int CHECK_FLAG = 0;
    // up 内容和压制内容
    private int curUpIndex = 0;
    private static final String[] UP_WORDS = new String[10];
    private PlanDataManager planDataManager = DataManagerFactory.getManager(PlanDataManager.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_check_layout);
        planLayout = findViewById(R.id.plan);
        addPlan = findViewById(R.id.add_plan);
        up = findViewById(R.id.up_btn);
        check = findViewById(R.id.check_btn);
        giveUp = findViewById(R.id.gp_btn);
        planName = findViewById(R.id.plan_name);
        calendar = findViewById(R.id.calendar);
        statistics = findViewById(R.id.statistics);
        settings = findViewById(R.id.settings);

        initButton();
    }

    private void initButton() {
        // 初始化计划dialog
        initPlan();

        // 加载check
        initCheck();

        // 初始化up
        initUp();

        // 初始化giveUp
        initGiveUp();

        // 初始化日历
        initCalendar();

        // 初始化统计
        initStatistics();

        // 初始化setting
        initSettings();
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
        check.setOnClickListener(v -> {
            DialogPlus dialogPlus = DialogPlus.newDialog(getActivity())
                    .setGravity(Gravity.CENTER)
                    .setContentHolder(new ViewHolder(R.layout.check_dialog_layout))
                    .setPadding(10, 10, 10, 10)
                    .setCancelable(true)
                    .create();
            for (int i = 0; i < 8; i++) {
                ButtonRectangle button = dialogPlus.getHolderView().findViewById(CHECK_BTNS[i]);
                button.setOnClickListener(checkBtnClick(dialogPlus));
            }
            dialogPlus.show();
        });
    }

    private void initUp() {
        up.setOnClickListener(new View.OnClickListener() {
            int cur_index = 0;

            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity())
                        .setContentHolder(new ViewHolder(R.layout.up_layout))
                        .setPadding(10, 10, 10, 10)
                        .setGravity(Gravity.CENTER)
                        .setCancelable(true)
                        .create();
                View view = dialogPlus.getHolderView();
                final TextView textView = view.findViewById(R.id.up_text);
                textView.setText(UP_WORDS[cur_index]);

                // 坚持成功
                ButtonRectangle keep = view.findViewById(R.id.up_keep);
                keep.setOnClickListener(v12 -> {
                    // 需要加进去统计
                    dialogPlus.dismiss();
                });

                // 继续
                final ButtonRectangle down = view.findViewById(R.id.up_down);
                down.setOnClickListener(v1 -> {
                    ButtonRectangle down1 = (ButtonRectangle) v1;
                    // 判断一下text
                    if (cur_index >= 9) {
                        down1.setText("boom");
                        down1.setOnClickListener((ve) -> {
                                dialogPlus.dismiss();
                                // 坚持天数置为0
                            }
                        );
                        return;
                    }

                    textView.setText(UP_WORDS[cur_index]);
                    cur_index += 1;
                });

                dialogPlus.show();
            }
        });
    }

    private void initGiveUp() {
        giveUp.setOnClickListener(v -> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity())
                    .setContentHolder(new ViewHolder(R.layout.give_up_layout))
                    .setPadding(10, 10, 10, 10)
                    .setCancelable(true)
                    .setGravity(Gravity.CENTER)
                    .create();

            // 确认按钮
            ButtonRectangle confirm = dialogPlus.getHolderView().findViewById(R.id.gp_confirm);
            confirm.setOnClickListener(v1 -> {
                // 放弃的时候
                IS_HAS_PLAN = 0;
                dialogPlus.dismiss();
            });

            // 取消按钮
            ButtonRectangle cancle = dialogPlus.getHolderView().findViewById(R.id.gp_cancle);
            cancle.setOnClickListener(v12 -> dialogPlus.dismiss());

            // 显示dialog
            dialogPlus.show();
        });

    }


    /**
     * 查看日历
     */
    private void initCalendar() {
        calendar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 初始化资料统计
     */
    private void initStatistics() {
        statistics.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StatisticsActivity.class);
            startActivity(intent);
        });
    }


    /**
     * 初始化设置
     */
    private void initSettings() {
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        });

    }


    // plan change dialog
    private View.OnClickListener planChange() {
        return v -> {
            PlanAdapter planAdapter = new PlanAdapter(getActivity(), PLAN_LIST);
            loadPlanList(planAdapter);
            final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity())
                    .setHeader(R.layout.plan_list_header_layout)
                    //  不使用确定和取消按钮
                    // .setFooter(R.layout.plan_list_footer_layout)
                    .setAdapter(planAdapter)
                    .setOnItemClickListener((dialog, item, view, position) -> {
                        showPlan(PLAN_LIST.get(position));
                        Toast.makeText(getActivity(), "选择的是: " + PLAN_LIST.get(position).getPlanId(),
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .setExpanded(true)
                    .setGravity(Gravity.CENTER)
                    .setInAnimation(R.anim.abc_fade_in)
                    .setOutAnimation(R.anim.abc_fade_out)
                    .setPadding(10, 10, 10, 30)
                    .setCancelable(true)
                    .create();
            dialogPlus.show();


        };
    }

    private View.OnClickListener checkBtnClick(final DialogPlus dialogPlus) {
        return v -> {
            // 每一个button点击的时候变颜色
            ButtonRectangle buttonRectangle = (ButtonRectangle) v;
            buttonRectangle.setBackgroundColor(Color.parseColor("#33cc33"));

            // 检查是否8个都点击了，如果是的话就打卡成功
            if (CHECKED_BTNS >= 8) {
                Toast.makeText(getActivity(), "签到成功", Toast.LENGTH_SHORT).show();
                dialogPlus.dismiss();
            }

            CHECKED_BTNS += 1;
        };
    }


    private void loadPlanList(PlanAdapter planAdapter) {
        // 清空list
        PLAN_LIST.clear();
        // 加载数据
        planDataManager.listAll()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(list -> {
                    if (list == null || list.isEmpty()) {
                        shortShow("没有up word");
                    } else {
                        PLAN_LIST.addAll(list);
                        planAdapter.notifyDataSetChanged();
                    }
                }, e -> Log.e("DailyCheck", e.getMessage()));
    }

    private static void initUpWords() {
        UP_WORDS[0] = "come on 1";
        UP_WORDS[1] = "come on 2";
        UP_WORDS[2] = "come on 3";
        UP_WORDS[3] = "come on 4";
        UP_WORDS[4] = "come on 5";
        UP_WORDS[5] = "come on 6";
        UP_WORDS[6] = "come on 7";
        UP_WORDS[7] = "come on 8";
        UP_WORDS[8] = "come on 9";
        UP_WORDS[9] = "come on 10";
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


    protected Context getActivity() {
        return DailyCheck.this;
    }
}
