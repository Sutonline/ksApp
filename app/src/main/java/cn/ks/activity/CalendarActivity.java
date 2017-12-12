package cn.ks.activity;

import android.graphics.Color;
import android.kevin.cn.ks.R;

import cn.ks.common.RxExceptionHandler;
import cn.ks.common.RxResultCompat;
import cn.ks.common.RxSchedulerHelper;
import cn.ks.data.manage.PlanDataManager;
import cn.ks.domain.PlanHistory;
import cn.ks.layout.CalendarDecorator;
import cn.ks.util.DataManagerFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 查看日历，要标记出来哪些有打卡
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/20.
 */
public class CalendarActivity extends BaseActivity {

    private CalendarDecorator decorator;
    private Long planId;
    private PlanDataManager planDataManager = DataManagerFactory.getManager(PlanDataManager.class);
    private List<PlanHistory> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        planId = getIntent().getLongExtra("planId", -1L);
        init();

        MaterialCalendarView mcv = findViewById(R.id.calendarView);
        mcv.setCurrentDate(new Date());
        mcv.addDecorator(decorator == null ? getCalendarDecorator() : decorator);
    }

    private CalendarDecorator getCalendarDecorator() {
        decorator = new CalendarDecorator();
        List<CalendarDay> list = new ArrayList<>();

        list.add(CalendarDay.today());
        int color = Color.parseColor("#ff80d5");
        decorator.setDays(list);
        decorator.setColor(color);

        return decorator;
    }

    void init() {
        refreshList();
    }

    void refreshList() {
        if (planId == -1) {
            Log.d("StatisticsActivity", "目前没有计划");
            return;
        }
        planDataManager.listHistory(planId)
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(checkRecords -> this.list.addAll(checkRecords), new RxExceptionHandler<>(e -> shortShow("加载打卡日期错误")));
    }

}
