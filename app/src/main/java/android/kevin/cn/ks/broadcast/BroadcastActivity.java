package android.kevin.cn.ks.broadcast;

import android.app.Activity;
import android.content.IntentFilter;
import android.kevin.cn.ks.R;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 广播活动
 * Created by yongkang.zhang on 2017/11/1.
 */

public class BroadcastActivity extends Activity {

    private IntentFilter intentFilter;

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
}
