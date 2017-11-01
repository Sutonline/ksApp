package android.kevin.cn.ks.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * broadcast接收类
 * Created by yongkang.zhang on 2017/11/1.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
    }
}
