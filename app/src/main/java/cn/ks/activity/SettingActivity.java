package cn.ks.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.kevin.cn.ks.R;
import cn.ks.adapter.UpWordAdapter;
import cn.ks.common.RxExceptionHandler;
import cn.ks.common.RxResultCompat;
import cn.ks.common.RxSchedulerHelper;
import cn.ks.data.manage.UpWordDataManager;
import cn.ks.domain.UpWord;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * @author yongkang.zhang
 * @date 2017/11/28
 */
public class SettingActivity extends BaseActivity {

    private SwipeMenuListView upWordList;
    private EditText upWordInput;
    private ButtonRectangle upWordBtn;
    private List<UpWord> list;
    private UpWordAdapter adapter;
    private UpWordDataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        upWordList = findViewById(R.id.up_word_list);
        upWordInput = findViewById(R.id.up_word_add_input);
        upWordBtn = findViewById(R.id.up_word_add_btn);
        list = new ArrayList<>(10);
        dataManager = new UpWordDataManager();

        init();
    }

    private void init() {
        // 加载list数据
        initList();

        // 加载add btn
        initAdd();
    }

    private void initList() {
        // SwipeMenuCreator
        SwipeMenuCreator creator = (menu) -> {
            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
            deleteItem.setWidth(dp2px(60));
            deleteItem.setIcon(R.drawable.ic_delete);
            menu.addMenuItem(deleteItem);
        };
        upWordList.setMenuCreator(creator);
        upWordList.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        upWordList.setOnMenuItemClickListener((position, menu, index) -> {
            dataManager.deleteById(this.list.get(position).getId())
                    .compose(RxSchedulerHelper.io_main())
                    .compose(RxResultCompat.convert())
                    .subscribe(result -> {
                        if (result) {
                            shortShow("删除成功");
                            loadData();
                        }
                    }, new RxExceptionHandler<>(e -> shortShow("删除错误")));
            return false;
        });
        adapter = new UpWordAdapter(this.list, getActivity());
        upWordList.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        this.list.clear();
        dataManager.listAll()
                .compose(RxSchedulerHelper.io_main())
                .compose(RxResultCompat.convert())
                .subscribe(words -> {
                    this.list.addAll(words);
                    this.adapter.notifyDataSetChanged();
                }, new RxExceptionHandler<>(e -> shortShow("加载upword错误")));
    }

    private void initAdd() {
        upWordBtn.setOnClickListener(v -> {
            if (this.list.size() >= 10) {
                shortShow("最大只能添加10个up word");
                return;
            }

            // 判断如果数据不是空，就加入list中
            String s = upWordInput.getText().toString();
            if (s.trim().equals("")) {
                shortShow("up word内容不能为空");
            }

            UpWord upWord = new UpWord(System.currentTimeMillis(), s, new Date(), 1);
            dataManager.save(upWord)
                    .compose(RxSchedulerHelper.io_main())
                    .compose(RxResultCompat.convert())
                    .subscribe(result -> {
                        if (result) {
                            list.add(upWord);
                            adapter.notifyDataSetChanged();
                            shortShow("添加成功");
                        } else {
                            shortShow("添加up word错误");
                        }
                    }, new RxExceptionHandler<>(e -> shortShow("添加upword错误")));

        });
    }

}
