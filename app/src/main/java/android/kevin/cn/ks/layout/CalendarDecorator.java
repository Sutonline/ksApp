package android.kevin.cn.ks.layout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.List;

import lombok.Data;

/**
 * @author yongkang.zhang
 *         Created by yongkang.zhang on 2017/11/20.
 */
@Data
public class CalendarDecorator implements DayViewDecorator {

    private int color;
    private List<CalendarDay> days;

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return days.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(7, color));
    }
}
