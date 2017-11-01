package android.kevin.cn.ks.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 自定义广播接收
 * Created by yongkang.zhang on 2017/11/1.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Received", Toast.LENGTH_SHORT).show();
    }
}
