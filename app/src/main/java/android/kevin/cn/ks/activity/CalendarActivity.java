package android.kevin.cn.ks.activity;

import android.graphics.Color;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.layout.CalendarDecorator;
import android.os.Bundle;
import android.support.annotation.Nullable;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        MaterialCalendarView mcv = findViewById(R.id.calendarView);
        mcv.setCurrentDate(new Date());
        mcv.addDecorator(decorator == null ? getCalendarDecorator() : decorator);
    }

    private CalendarDecorator getCalendarDecorator() {
        decorator = new CalendarDecorator();
        List<CalendarDay> list = new ArrayList<>();

        list.add(CalendarDay.today());
        list.add(CalendarDay.from(2017, 10, 18));
        int color = Color.parseColor("#ff80d5");
        decorator.setDays(list);
        decorator.setColor(color);

        return decorator;
    }
}
