package android.kevin.cn.ks.activity;

import android.kevin.cn.ks.R;
import android.kevin.cn.ks.adapter.UpWordAdapter;
import android.kevin.cn.ks.domain.UpWord;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        upWordList = findViewById(R.id.up_word_list);
        upWordInput = findViewById(R.id.up_word_add_input);
        upWordBtn = findViewById(R.id.up_word_add_btn);

        init();
    }

    private void init() {
        // 加载list数据
        initList();

        // 加载add btn
        initAdd();
    }

    private void initList() {
        loadData();
        adapter = new UpWordAdapter(this.list, getActivity());
        upWordList.setAdapter(adapter);
    }

    private void loadData() {
        if (list != null) {
            return;
        }
        list = new ArrayList<>(10);
        list.add(new UpWord(1L, "加油~", new Date(), 1));
        list.add(new UpWord(2L, "加油~~", new Date(), 1));
        list.add(new UpWord(3L, "加油~~~", new Date(), 1));
    }

    private void initAdd() {
        upWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断如果数据不是空，就加入list中
                String s = upWordInput.getText().toString();
                if (s.trim().equals("")) {
                    shortShow("up word内容不能为空");
                }

                list.add(new UpWord(4L, s, new Date(), 1));
                adapter.notifyDataSetChanged();
            }
        });
    }

}
