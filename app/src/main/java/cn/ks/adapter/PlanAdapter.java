package cn.ks.adapter;

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
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/10.
 */
public class PlanAdapter extends BaseAdapter {

    private Context context;
    private List<Plan> list;
    private ViewHolder viewHolder;

    public PlanAdapter(Context context, List<Plan> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getPlanId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plan plan = list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.plan_item_layout, null);
            viewHolder.planItem = convertView.findViewById(R.id.plan_item);
            viewHolder.planNo = convertView.findViewById(R.id.plan_no);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.planNo.setText("".concat(String.valueOf(position + 1)).concat("、"));
        viewHolder.planItem.setText(plan.getName());

        return convertView;
    }

    private static class ViewHolder {
        TextView planNo;
        TextView planItem;
    }
}
