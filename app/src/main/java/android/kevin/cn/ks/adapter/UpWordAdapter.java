package android.kevin.cn.ks.adapter;

import android.content.Context;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.domain.UpWord;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author yongkang.zhang
 *         Created by yongkang.zhang on 2017/11/28.
 */
public class UpWordAdapter extends BaseAdapter {

    private List<UpWord> list;
    private ViewHolder viewHolder;
    private Context context;

    public UpWordAdapter(List<UpWord> list, Context context) {
        this.list = list;
        this.context = context;
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
        UpWord upWord = list.get(position);
        if (convertView == null) {
            viewHolder = new UpWordAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.up_item_layout, null);
            viewHolder.upWordNo = convertView.findViewById(R.id.up_word_no);
            viewHolder.upWordItem = convertView.findViewById(R.id.up_word_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (UpWordAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.upWordItem.setText(upWord.getWord());
        viewHolder.upWordNo.setText("".concat(String.valueOf(position + 1)).concat("、"));

        return convertView;
    }

    private static class ViewHolder {
        TextView upWordNo;
        TextView upWordItem;
    }
}
