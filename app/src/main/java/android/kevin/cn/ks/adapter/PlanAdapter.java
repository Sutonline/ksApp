package android.kevin.cn.ks.adapter;

import android.content.Context;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.domain.Plan;
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
    public static ViewHolder viewHolder;

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
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plan plan = list.get(position);
        View view;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.plan_item_layout, null);
            viewHolder.planItem = (TextView) view.findViewById(R.id.plan_item);
            view.setTag(viewHolder);
        }

        viewHolder.planItem.setText(plan.getName());

        return viewHolder.planItem;
    }

    private static class ViewHolder {
        TextView planItem;
    }
}
