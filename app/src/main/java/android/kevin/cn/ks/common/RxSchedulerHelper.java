package android.kevin.cn.ks.common;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 用来限定线程的帮助类
 * @author yongkang.zhang
 * @date 2017/12/1
 */
@SuppressWarnings("unchecked")
public class RxSchedulerHelper {

    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
