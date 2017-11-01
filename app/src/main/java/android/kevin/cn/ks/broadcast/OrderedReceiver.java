package android.kevin.cn.ks.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 有序接收器
 * Created by yongkang.zhang on 2017/11/1.
 */

public class OrderedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "another received", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
