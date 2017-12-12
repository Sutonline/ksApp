package cn.ks.activity;

import android.kevin.cn.ks.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
import java.util.List;

import cn.ks.adapter.PlanStatictisAdapter;
import cn.ks.common.RxExceptionHandler;
import cn.ks.common.RxResultCompat;
import cn.ks.common.RxSchedulerHelper;
import cn.ks.data.manage.PlanDataManager;
import cn.ks.domain.Plan;
import cn.ks.layout.StatisticsDaysLayout;
import cn.ks.util.DataManagerFactory;

/**
 * 统计活动
 * @author yongkang.zhang
 */
public class StatisticsActivity extends BaseActivity {

    private StatisticsDaysLayout curDays;
    private StatisticsDaysLayout successUps;
    private StatisticsDaysLayout failTimes;
    private StatisticsDaysLayout longestDays;
    private SwipeMenuListView listView;
    private ButtonRectangle delete;
    // 最近三条计划
    private List<Plan> recentList = new ArrayList<>();
    private PlanDataManager planDataManager = DataManagerFactory.getManager(PlanDataManager.class);
    private Plan curPlan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_layout);
        // init components
        curDays = findViewById(R.id.curDays);
        successUps = findViewById(R.id.successUps);
        failTimes = findViewById(R.id.failTimes);
        longestDays = findViewById(R.id.longestDays);
        listView = findViewById(R.id.plan_history_list);
        delete = findViewById(R.id.delete);

        init();
    }

    void init() {
        // load data
        loadData();

        // 初始化plan
        refreshCurrent();
    }

    void refreshCurrent() {
        boolean hasPlan = curPlan != null;
        curDays.setCreator(new StatisticsDaysLayout.Creator()
                .name("当前天数")
                .days("".concat(String.valueOf(hasPlan ? curPlan.getKeepDays() : 0)))
                .dayLineColor("#2bbc20")
                .dayRoundColor("#2bbc20")
        );
        successUps.setCreator(new StatisticsDaysLayout.Creator()
                .name("up次数")
                .days("".concat(String.valueOf(hasPlan ? curPlan.getSuccessUps() : 0)))
                .dayLineColor("#2bbc20")
                .dayRoundColor("#2bbc20")
        );

        failTimes.setCreator(new StatisticsDaysLayout.Creator()
                .name("失败次数")
                .days("".concat(String.valueOf(hasPlan ? curPlan.getGiveCnt() : 0)))
                .dayLineColor("#2bbc20")
                .dayRoundColor("#2bbc20")
        );

        longestDays.setCreator(new StatisticsDaysLayout.Creator()
                .name("最长天数")
                .days("".concat(String.valueOf(hasPlan ? curPlan.getLongestDays() : 0)))
                .dayLineColor("#2bbc20")
                .dayRoundColor("#2bbc20")
        );


    }

    void initHistory() {

        // 设置adapter
        PlanStatictisAdapter adapter = new PlanStatictisAdapter(getActivity(), this.recentList);
        listView.setAdapter(adapter);

        // 设置delete
        delete.setOnClickListener(v -> emptyList());
    }

    void loadData() {
        refreshRecentPlan();
        refreshCurPlan();
    }

    void refreshRecentPlan() {
        planDataManager.listRencentPlan()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(plans -> {
                    this.recentList.addAll(plans);
                    initHistory();
                }, new RxExceptionHandler<>(e -> shortShow("加载最近计划错误")));
    }

    void refreshCurPlan() {
        planDataManager.getCurrent()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(plan -> {
                    this.curPlan = plan;
                    refreshCurrent();
                }, new RxExceptionHandler<>(e -> shortShow("获取当前计划错误")));
    }

    private void emptyList() {
        planDataManager.deleteRecentPlan()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(result -> {
                    if (result) {
                        shortShow("删除成功");
                    } else {
                        Log.e("StatictisActivity", result.toString());
                    }
                });
    }
}
