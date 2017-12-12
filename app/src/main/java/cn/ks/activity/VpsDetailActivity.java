package cn.ks.activity;

import android.graphics.Color;
import android.kevin.cn.ks.R;

import cn.ks.common.AxisDateFormatter;
import cn.ks.common.RxExceptionHandler;
import cn.ks.common.RxResultCompat;
import cn.ks.common.RxSchedulerHelper;
import cn.ks.data.manage.VpsDataManager;
import cn.ks.domain.VultrDomain;
import cn.ks.util.DataManagerFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yongkang.zhang
 * @date 2017/12/8
 */
public class VpsDetailActivity extends BaseActivity {

    @BindView(R.id.vps_detail_name)
    TextView vpsDetailName;
    @BindView(R.id.vps_detail_status)
    TextView vpsDetailStatus;
    @BindView(R.id.vps_detail_totalBand)
    TextView vpsDetailTotalBand;
    @BindView(R.id.vps_detail_curBand)
    TextView vpsDetailCurBand;
    @BindView(R.id.vps_detail_ip)
    TextView vpsDetailIp;
    @BindView(R.id.vps_detail_cost)
    TextView vpsDetailCost;
    @BindView(R.id.chart)
    LineChart chart;
    private VpsDataManager vpsDataManager = DataManagerFactory.getManager(VpsDataManager.class);
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vps_detail_layout);
        ButterKnife.bind(this);
        getVultr();
    }


    private void getVultr() {
        vpsDataManager.getVultr()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(v -> {
                    vpsDetailName.setText("Vultr");
                    vpsDetailStatus.setText(v.getBasicInfo().getPower_status());
                    vpsDetailTotalBand.setText(v.getBasicInfo().getAllowed_bandwidth_gb());
                    vpsDetailCurBand.setText(v.getBasicInfo().getCurrent_bandwidth_gb());
                    vpsDetailIp.setText(v.getBasicInfo().getMain_ip());
                    vpsDetailCost.setText(v.getBasicInfo().getCost_per_month());
                    // 设置chart的数据
                    setChartData(v.getBandwidthVo());
                }, new RxExceptionHandler<>(e -> shortShow("加载错误")));
    }

    private Consumer<? extends VultrDomain> consume() {
        return v -> {
            vpsDetailName.setText("Vultr");
            vpsDetailStatus.setText(v.getBasicInfo().getPower_status());
            vpsDetailTotalBand.setText(v.getBasicInfo().getAllowed_bandwidth_gb().concat("G"));
            vpsDetailCurBand.setText(v.getBasicInfo().getCurrent_bandwidth_gb().concat("G"));
            vpsDetailIp.setText(v.getBasicInfo().getMain_ip());
            vpsDetailCost.setText("$".concat(v.getBasicInfo().getCost_per_month()));
            // 设置chart的数据
            setChartData(v.getBandwidthVo());
        };
    }


    private void setChartData(VultrDomain.BandWidthVo vo) {
        // 实例化incomingBytes
        List<Entry> incomingEntryList = new ArrayList<>();
        for (int i = 1; i < vo.getIncomingBytes().size() - 1; i ++) {
            Float fdate = formatDate2Float(vo.getIncomingBytes().get(i - 1).getDate());
            Float mbytes = Float.parseFloat(vo.getIncomingBytes().get(i - 1).getBytes()) / (1000 * 1000);
            incomingEntryList.add(new Entry(fdate, mbytes, mbytes));
        }

        // 实例化outgoingBytes
        List<Entry> outgoingEntryList = new ArrayList<>();
        for (int i = 1; i < vo.getOutgoingBytes().size() - 1; i ++) {
            Float fdate = formatDate2Float(vo.getOutgoingBytes().get(i - 1).getDate());
            Float mbytes = Float.parseFloat(vo.getOutgoingBytes().get(i - 1).getBytes()) / (1000 * 1000);
            outgoingEntryList.add(new Entry(fdate, mbytes, mbytes));
        }

        // 实例化两个dataSet
        LineDataSet incomingDataSet = new LineDataSet(incomingEntryList, "incoming");
        incomingDataSet.setColor(Color.parseColor("#f4b642"));
        LineDataSet outgoingDataSet = new LineDataSet(outgoingEntryList, "outgoing");
        outgoingDataSet.setColor(Color.parseColor("#f45641"));

        // 实例化LineData
        LineData lineData = new LineData(incomingDataSet, outgoingDataSet);

        // 设置到chart里
        chart.setData(lineData);

        // 设置Axis的formatter
        chart.getXAxis().setValueFormatter(new AxisDateFormatter(chart));

        //refresh
        chart.invalidate();
    }

    private Float formatDate2Float(String date) {
        LocalDate localDate = formatter.parseLocalDate(date);
        Date past = new Date(2016, 0, 1);
        Date current = new Date(localDate.getYear(), localDate.getMonthOfYear() - 1, localDate.getDayOfMonth());
        return Float.parseFloat((Days.daysBetween(new DateTime(past), new DateTime(current)).getDays() + 1) + "");
    }

}
