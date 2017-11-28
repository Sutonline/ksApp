package android.kevin.cn.ks.layout;

import android.content.Context;
import android.graphics.Color;
import android.kevin.cn.ks.R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * statictis统计days layout
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/27.
 */
public class StatisticsDaysLayout extends RelativeLayout {

    // 名称
    private TextView name;
    // 天数
    private TextView days;
    // 线
    private ImageView dayLine;
    // 圆形
    private ImageView dayRound;

    public StatisticsDaysLayout(Context context) {
        super(context);
        inflat(context);
    }

    public StatisticsDaysLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflat(context);
    }

    public StatisticsDaysLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflat(context);
    }

    public StatisticsDaysLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflat(context);
    }

    private void inflat(Context context) {
        LayoutInflater.from(context).inflate(R.layout.statistics_days_layout, this);
        name = findViewById(R.id.name);
        days = findViewById(R.id.days);
        dayLine = findViewById(R.id.days_line);
        dayRound = findViewById(R.id.days_round);
    }


    public void setCreator(Creator creator) {
        this.name.setText(creator.name);
        this.days.setText(creator.days);
        this.dayLine.setColorFilter(Color.parseColor(creator.dayLineColor));
        this.dayRound.setColorFilter(Color.parseColor(creator.dayRoundColor));
    }


    public static class Creator {
        private String name;
        private String days;
        private String dayLineColor;
        private String dayRoundColor;

        public Creator name(String name) {
            this.name = name;
            return this;
        }

        public Creator days(String days) {
            this.days = days;
            return this;
        }

        public Creator dayLineColor(String color) {
            this.dayLineColor = color;
            return this;
        }

        public Creator dayRoundColor(String color) {
            this.dayRoundColor = color;
            return this;
        }

    }



}
