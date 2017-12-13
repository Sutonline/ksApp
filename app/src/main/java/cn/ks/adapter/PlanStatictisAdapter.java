package cn.ks.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.kevin.cn.ks.R;
import cn.ks.domain.Plan;
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

    private List<Plan> list;
    private Context context;

    public PlanStatictisAdapter(Context context, List<Plan> list) {
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
        Plan plan = list.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new PlanStatictisAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.statistics_item_layout, null);
            viewHolder.planItem = convertView.findViewById(R.id.plan_name);
            viewHolder.longest_days = convertView.findViewById(R.id.longest_days);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.planItem.setText(plan.getName());
        viewHolder.longest_days.setText(Integer.toString(plan.getLongestDays() == null ? 0 : plan.getLongestDays()));

        return convertView;
    }

    private static class ViewHolder {
        TextView planItem;
        TextView longest_days;
    }
}
