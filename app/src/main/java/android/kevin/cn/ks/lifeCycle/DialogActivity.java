package android.kevin.cn.ks.lifeCycle;

import android.app.Activity;
import android.kevin.cn.ks.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

/**
 * 对话activity
 * Created by yongkang.zhang on 2017/10/26.
 */

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);
    }
}
