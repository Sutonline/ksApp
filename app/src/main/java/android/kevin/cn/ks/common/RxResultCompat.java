package android.kevin.cn.ks.common;

import android.kevin.cn.ks.enums.ResultTypeEnum;
import android.kevin.cn.ks.exception.BizException;

import java.util.Objects;
import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author yongkang.zhang
 * @date 2017/12/1
 */
public class RxResultCompat {

    public static <T> ObservableTransformer<Result<T>, T> convert() {
        return upstream -> upstream.flatMap(tResult -> {
            if (tResult == null) {
                return Observable.error(new BizException("result信息不能为空"));
            }

            if (Objects.equals(tResult.getCode(), ResultTypeEnum.ERROR.getCode())) {
                return Observable.error(new BizException(tResult.getCode(), tResult.getMessage()));
            }

            return Observable.just(tResult.getT());
        });
    }


    public static <T> Observer<T> handle(Consumer<T> consumer, Consumer<Throwable> errorConsumer) {
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                // to do implemented
            }

            @Override
            public void onNext(T t) {
                consumer.accept(t);
            }

            @Override
            public void onError(Throwable e) {
                errorConsumer.accept(e);
            }

            @Override
            public void onComplete() {
                // to do implemented
            }
        };
    }
}
