package android.kevin.cn.ks;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * 统计活动
 * Created by yongkang.zhang on 2017/10/20.
 */

public class SummaryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.summary);

        // 增加button
        Button button = (Button) findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SummaryActivity.this, "return to dailycheck", Toast.LENGTH_SHORT).show();;
                finish();
            }
        });
    }
}
