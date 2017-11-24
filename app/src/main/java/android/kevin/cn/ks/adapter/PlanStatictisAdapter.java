package android.kevin.cn.ks.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.domain.PlanStatistics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * plan统计列表
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/21.
 */
public class PlanStatictisAdapter extends BaseAdapter {

    private List<PlanStatistics> list;
    private static PlanStatictisAdapter.ViewHolder viewHolder;
    private Context context;

    public PlanStatictisAdapter(Context context, List<PlanStatistics> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getPlanId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlanStatistics plan = list.get(position);
        if (convertView == null) {
            viewHolder = new PlanStatictisAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.statistics_item_layout, null);
            viewHolder.planItem = (TextView) convertView.findViewById(R.id.plan_name);
            viewHolder.keepDays = (TextView) convertView.findViewById(R.id.keep_days);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.planItem.setText(plan.getPlan().getName());
        viewHolder.keepDays.setText(Integer.toString(plan.getKeepDays()));

        return convertView;
    }

    private static class ViewHolder {
        TextView planItem;
        TextView keepDays;
    }
}
