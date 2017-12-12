package cn.ks.common;

import android.util.Log;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;

/**
 * @author yongkang.zhang
 * @date 2017/12/1
 */
public class RxExceptionHandler<T extends Throwable> implements Consumer<T> {

    private static String TAG = "RxExceptionHandler";

    private static final String TIMEOUT_EXCEPTION = "网络连接超时";
    private static final String CONNECT_TIMEOUT = "连接超时";
    private static final String JSON_EXCEPTION = "获取数据异常";
    private static final String UNKNOWN_HOST_EXCEPTION = "网络异常";

    private Consumer<? super Throwable> onError;

    public RxExceptionHandler( Consumer<? super Throwable> onError) {
        this.onError = onError;
    }

    @Override
    public void accept(T t) {
        t.printStackTrace();
        if (t instanceof SocketTimeoutException) {
            Log.e(TAG, "onError: SocketTimeoutException---");

        } else if (t instanceof ConnectException) {
            Log.e(TAG, "onError: CONNECT_EXCEPTION---");

        } else if (t instanceof UnknownHostException) {
            Log.e(TAG, "onError: UNKNOWN_HOST_EXCEPTION---");

        } else if (t instanceof JSONException) {
            Log.e("TAG", "onError: JSON_EXCEPTION");

        } else {
            try {
                Log.e(TAG, "发生了异常," + t.getMessage());
                onError.accept(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}