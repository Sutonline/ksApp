package android.kevin.cn.ks.broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 基本活动
 * Created by yongkang.zhang on 2017/11/2.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
