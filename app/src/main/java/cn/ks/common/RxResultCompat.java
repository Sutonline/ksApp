package cn.ks.common;

import cn.ks.enums.ResultTypeEnum;
import cn.ks.exception.BizException;

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
                return Observable.error(new BizException("结果不能为空"));
            }

            if (Objects.equals(tResult.getCode(), ResultTypeEnum.ERROR.getCode())) {
                return Observable.error(new BizException("返回结果不正确"));
            }

            return Observable.just(tResult.getT());
        });
    }

    public static <T> Consumer<Result<T>> handle(Consumer<T> consumer) {
        return tResult -> {
            if (tResult == null) {
                throw new BizException("result信息不能为空");
            }

            if (Objects.equals(tResult.getCode(), ResultTypeEnum.ERROR.getCode())) {
                throw new BizException(tResult.getCode(), tResult.getMessage());
            }

            consumer.accept(tResult.getT());
        };
    }

}
