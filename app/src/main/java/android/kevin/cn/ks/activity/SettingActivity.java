package android.kevin.cn.ks.activity;

import android.kevin.cn.ks.R;
import android.kevin.cn.ks.adapter.UpWordAdapter;
import android.kevin.cn.ks.common.Result;
import android.kevin.cn.ks.common.RxResultCompat;
import android.kevin.cn.ks.common.RxSchedulerHelper;
import android.kevin.cn.ks.data.manage.UpWordDataManager;
import android.kevin.cn.ks.domain.UpWord;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                }, e -> {
                    shortShow("加载错误");
                    Log.e("SettingActivity", "加载错误", e);
                });
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
                        } else {
                            shortShow("添加up word错误");
                        }
                    }, e -> Log.e("SettingActivity", e.getMessage()));

        });
    }

}
