package android.kevin.cn.ks.activity;

import android.kevin.cn.ks.R;
import android.kevin.cn.ks.adapter.PlanStatictisAdapter;
import android.kevin.cn.ks.common.RxResultCompat;
import android.kevin.cn.ks.common.RxSchedulerHelper;
import android.kevin.cn.ks.data.manage.PlanDataManager;
import android.kevin.cn.ks.domain.Plan;
import android.kevin.cn.ks.layout.StatisticsDaysLayout;
import android.kevin.cn.ks.util.DataManagerFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计活动
 *
 * @author yongkang.zhang
 *         Created by yongkang.zhang on 2017/11/21.
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

        // 初始化当前
        initCurrent();

        // 初始化历史
        initHistory();
    }

    void initCurrent() {
        curDays.setCreator(new StatisticsDaysLayout.Creator()
                .name("当前天数")
                .days("".concat(String.valueOf(curPlan.getKeepDays())))
                .dayLineColor("#ffcccc")
                .dayRoundColor("#ffcccc")
        );
        successUps.setCreator(new StatisticsDaysLayout.Creator()
                .name("up次数")
                .days("".concat(String.valueOf(curPlan.getSuccessUps())))
                .dayLineColor("#0454d6")
                .dayRoundColor("#ffcccc")
        );

        failTimes.setCreator(new StatisticsDaysLayout.Creator()
                .name("失败次数")
                .days("".concat(String.valueOf(curPlan.getGiveCnt())))
                .dayLineColor("#ffcccc")
                .dayRoundColor("#ffcccc")
        );

        longestDays.setCreator(new StatisticsDaysLayout.Creator()
                .name("最长天数")
                .days("".concat(String.valueOf(curPlan.getLongestDays())))
                .dayLineColor("#ffcccc")
                .dayRoundColor("#ffcccc")
        );


    }

    void initHistory() {

        // 设置adapter
        PlanStatictisAdapter adapter = new PlanStatictisAdapter(getActivity(), this.recentList);
        listView.setAdapter(adapter);

        // 设置delete
        delete.setOnClickListener(v -> {
            emptyList();
        });
    }

    void loadData() {
        refreshRecentPlan();
        refreshCurPlan();
    }

    void refreshRecentPlan() {
        planDataManager.listRencentPlan()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(plans -> this.recentList.addAll(plans), e -> handleException("加载最近计划错误"));
    }

    void refreshCurPlan() {
        planDataManager.getCurrent()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(plan -> this.curPlan = plan, e -> handleException());
    }

    private void emptyList() {
        planDataManager.deleteRecentPlan()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(result -> {
                    if (result) {
                        shortShow("删除成功");
                    }
                }, e -> handleException());
    }
}
