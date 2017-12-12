package cn.ks.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.kevin.cn.ks.R;
import cn.ks.adapter.PlanAdapter;
import cn.ks.common.RxExceptionHandler;
import cn.ks.common.RxResultCompat;
import cn.ks.common.RxSchedulerHelper;
import cn.ks.data.manage.PlanDataManager;
import cn.ks.data.manage.UpWordDataManager;
import cn.ks.domain.Plan;
import cn.ks.domain.UpWord;
import cn.ks.util.DataManagerFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import net.steamcrafted.materialiconlib.MaterialIconView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
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
    private TextView keepDays;
    // 日历icon
    private MaterialIconView calendar;
    // 统计资料icon
    private MaterialIconView statistics;
    // 设置icon
    private MaterialIconView settings;
    // check的8个button
    private static final Integer[] CHECK_BTNS = new Integer[]{R.id.check_btn_1, R.id.check_btn_2,
            R.id.check_btn_3, R.id.check_btn_4, R.id.check_btn_5, R.id.check_btn_6, R.id.check_btn_7, R.id.check_btn_8};
    private int checkedButtons = 0;
    private List<UpWord> upWordList;
    // 当期计划
    private Plan curPlan;
    private UpWordDataManager upWordDataManager = DataManagerFactory.getManager(UpWordDataManager.class);
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
        keepDays = findViewById(R.id.keep_days);
        calendar = findViewById(R.id.calendar);
        statistics = findViewById(R.id.statistics);
        settings = findViewById(R.id.settings);

        initButton();
    }

    private void initButton() {
        // init data
        loadData();

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
        hideOrShow();
        addPlan.setOnClickListener(planChange());
        planName.setOnClickListener(planChange());
    }

    private void initCheck() {

        // 每次init都置成0
        checkedButtons = 1;
        check.setOnClickListener(v -> {
            // 检查是否有计划
            if (IS_HAS_PLAN == 0) {
                shortShow("请先设置计划");
                return;
            }

            // 如果已签到，则返回
            if (AppContext.CHECK_INFO.get(LocalDate.now()) == Boolean.TRUE) {
                shortShow("今天已签到");
                return;
            }

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
            // 每次都要重新up
            int cur_index = 0;

            @Override
            public void onClick(View v) {
                if (IS_HAS_PLAN == 0) {
                    shortShow("请先设置计划");
                    return;
                }

                // 检查UP_WORD_LIST
                if (upWordList == null || upWordList.isEmpty()) {
                    shortShow("尚未设置upwrod");
                    return;
                }

                final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity())
                        .setContentHolder(new ViewHolder(R.layout.up_layout))
                        .setPadding(10, 10, 10, 10)
                        .setGravity(Gravity.CENTER)
                        .setCancelable(true)
                        .create();
                View view = dialogPlus.getHolderView();
                final TextView textView = view.findViewById(R.id.up_text);
                textView.setText(upWordList.get(cur_index).getWord());

                // 坚持成功
                ButtonRectangle keep = view.findViewById(R.id.up_keep);
                keep.setOnClickListener(v12 -> {
                    curPlan.setSuccessUps((curPlan.getSuccessUps() == null ? 0 : curPlan.getSuccessUps()) + 1);
                    planDataManager.updatePlan(curPlan)
                            .compose(RxSchedulerHelper.io_main())
                            .compose(RxResultCompat.convert())
                            .subscribe(result -> {}, new RxExceptionHandler<>(e -> {}));
                    dialogPlus.dismiss();
                });

                // 继续
                final ButtonRectangle down = view.findViewById(R.id.up_down);
                down.setOnClickListener(v1 -> {
                    ButtonRectangle down1 = (ButtonRectangle) v1;
                    // 判断一下text
                    if (cur_index >= upWordList.size()) {
                        down1.setText("boom");
                        down1.setOnClickListener((ve) -> {
                                planDataManager.giveUp(curPlan.getPlanId())
                                        .compose(RxSchedulerHelper.io_main())
                                        .compose(RxResultCompat.convert())
                                        .subscribe(result -> loadData(), new RxExceptionHandler<>(e -> {}));
                                dialogPlus.dismiss();
                            }
                        );
                        return;
                    }

                    textView.setText(upWordList.get(this.cur_index).getWord());
                    cur_index += 1;
                });

                dialogPlus.show();
            }
        });
    }

    private void initGiveUp() {
        giveUp.setOnClickListener(v -> {
            if (IS_HAS_PLAN == 0) {
                shortShow("尚未设置计划");
                return;
            }
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
                planDataManager.giveUp(this.curPlan.getPlanId())
                        .compose(RxSchedulerHelper.io_main())
                        .compose(RxResultCompat.convert())
                        .subscribe(result -> {
                            if (result) {
                                shortShow("放弃成功");
                                loadCurPlan();
                            }
                        }, new RxExceptionHandler<>(e -> shortShow("发生了额错误")));
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
            if (curPlan != null) {
                intent.putExtra("planId", curPlan.getPlanId());
            }
            startActivity(intent);
        });
    }

    /**
     * 初始化资料统计
     */
    private void initStatistics() {
        statistics.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StatisticsActivity.class);
            if (curPlan != null) {
                intent.putExtra("planId", curPlan.getPlanId());
            }
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
                        dialog.dismiss();
                    })
                    .setExpanded(true)
                    .setGravity(Gravity.CENTER)
                    .setInAnimation(R.anim.fade_in_center)
                    .setOutAnimation(R.anim.fade_out_center)
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
            if (checkedButtons >= 8) {
                this.curPlan.setCheckDate(new Date());
                planDataManager.check(this.curPlan)
                        .compose(RxSchedulerHelper.io_main())
                        .compose(RxResultCompat.convert())
                        .subscribe(result -> {
                            if (result) {
                                AppContext.CHECK_INFO.put(LocalDate.now(), Boolean.TRUE);
                                loadData();
                                shortShow("签到成功");
                            }
                        }, new RxExceptionHandler<>(e -> shortShow("网络错误")));
                dialogPlus.dismiss();
            }

            checkedButtons += 1;
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
                        shortShow("没有可用计划");
                    } else {
                        PLAN_LIST.addAll(list);
                        planAdapter.notifyDataSetChanged();
                    }
                }, new RxExceptionHandler<>(e -> shortShow("加载错误发生了错误")));
    }

    private void showPlan(Plan plan) {
        IS_HAS_PLAN = 1;

        planDataManager.changePlan(plan.getPlanId())
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(result -> {
                    if (result) {
                        this.curPlan = plan;
                        hideOrShow();
                        this.planName.setText(plan.getName());
                        this.keepDays.setText(String.valueOf(plan.getKeepDays() == null ? 0 : plan.getKeepDays()));
                    }
                }, new RxExceptionHandler<>(e -> shortShow("未知错误")));
    }


    protected Context getActivity() {
        return DailyCheck.this;
    }

    private void loadData() {
        // 加载当前计划
        loadCurPlan();

        // 加载upwords
        loadUpWords();

        // 是否打卡
        loadIsCheck();
    }

    private void loadCurPlan() {
        planDataManager.getCurrent()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(plan -> {
                    if (plan.getPlanId() == null) {
                        IS_HAS_PLAN = 0;
                    } else {
                        this.curPlan = plan;
                        showPlan(plan);
                    }
                    hideOrShow();
                }, new RxExceptionHandler<>(e -> shortShow("加载当前计划错误")));

    }

    private void loadUpWords() {
        upWordList = new ArrayList<>();
        upWordDataManager.listAll()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(list -> upWordList.addAll(list), new RxExceptionHandler<>(e -> {}));
    }

    private void loadIsCheck() {
        if (this.curPlan == null) {
            return;
        }

        planDataManager.isCheck(this.curPlan.getPlanId(), new Date())
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(result -> {
                    if (result) {
                        AppContext.CHECK_INFO.put(LocalDate.now(), Boolean.TRUE);
                    }
                }, new RxExceptionHandler<>(e -> shortShow("获取签到信息错误")));
    }

    private void hideOrShow() {
        // plan change
        if (IS_HAS_PLAN == 1) {
            planLayout.setVisibility(View.VISIBLE);
            addPlan.setVisibility(View.INVISIBLE);
        } else {
            planLayout.setVisibility(View.INVISIBLE);
            addPlan.setVisibility(View.VISIBLE);
        }
    }
}
