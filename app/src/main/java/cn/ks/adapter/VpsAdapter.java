package cn.ks.adapter;

import android.content.Context;
import android.kevin.cn.ks.R;

import cn.ks.domain.Vps;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author yongkang.zhang
 * @date 2017/12/8
 */
public class VpsAdapter extends BaseAdapter {

    private Context context;
    private List<Vps> list;
    private VpsAdapter.ViewHolder viewHolder;

    public VpsAdapter(Context context, List<Vps> list) {
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
        Vps vps = list.get(position);
        if (convertView == null) {
            viewHolder = new VpsAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.vps_item_layout, null);
            viewHolder.vpsItem = convertView.findViewById(R.id.vps_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VpsAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.vpsItem.setText(vps.getName());

        return convertView;
    }

    private static class ViewHolder {
        TextView vpsItem;
    }
}
