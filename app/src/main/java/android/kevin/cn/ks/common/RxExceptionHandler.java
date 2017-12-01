package android.kevin.cn.ks.common;

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

    private Consumer<? super Throwable> consumer;

    public RxExceptionHandler(Consumer<? super Throwable> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void accept(T t) throws Exception {
        consumer.accept(t);
    }


}
