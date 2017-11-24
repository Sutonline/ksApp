package android.kevin.cn.ks.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.adapter.PlanStatictisAdapter;
import android.kevin.cn.ks.domain.Plan;
import android.kevin.cn.ks.domain.PlanStatistics;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计活动
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/21.
 */
public class StatisticsActivity extends BaseActivity {

    private TextView curDays;
    private TextView successUps;
    private TextView failTimes;
    private TextView longestDays;
    private SwipeMenuListView listView;
    private ButtonRectangle delete;
    // 最近三条计划
    private List<PlanStatistics> list = new ArrayList<>();

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
        // 初始化当前
        initCurrent();

        // 初始化历史
        initHistory();
    }

    void initCurrent() {
        curDays.setText("5");
        successUps.setText("1");
        failTimes.setText("8");
        longestDays.setText("6");
    }

    void initHistory() {

        // 把list填充
        fillList();

        // 设置adapter
        PlanStatictisAdapter adapter = new PlanStatictisAdapter(getActivity(), list);
        listView.setAdapter(adapter);

        // 设置delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyList();
                shortShow("删除成功");
            }
        });
    }

    void fillList() {
        while(list.size() <= 3) {
            list.add(new PlanStatistics(1L, new Plan(Integer.parseInt(list.size() + ""), "测试", "描述"), 5));
        }
    }

    private void emptyList() {
    }
}
