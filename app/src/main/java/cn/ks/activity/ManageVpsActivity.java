package cn.ks.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.kevin.cn.ks.R;
import cn.ks.adapter.VpsAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * 管理事务
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/7.
 */
public class ManageVpsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_vps_layout);
        initList();
    }

    private void initList() {
        SwipeMenuListView listView = findViewById(R.id.vps_list);
        SwipeMenuCreator creator = menu -> {
            SwipeMenuItem openItem = new SwipeMenuItem(
                    getApplicationContext());
            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                    0xCE)));
            openItem.setWidth(dp2px(90));
            openItem.setTitle("Open");
            openItem.setTitleSize(18);
            openItem.setTitleColor(Color.WHITE);
            menu.addMenuItem(openItem);
        };
        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener((position, menu, index) -> {
            Intent intent = new Intent(getActivity(), VpsDetailActivity.class);
            Integer id = AppContext.VPS_LIST.get(index).getId();
            intent.putExtra("id", id);
            startActivity(intent);
            // false : close the menu; true : not close the menu
            return false;
        });

        // Right
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        VpsAdapter adapter = new VpsAdapter(getActivity(), AppContext.VPS_LIST);
        listView.setAdapter(adapter);
    }


}
