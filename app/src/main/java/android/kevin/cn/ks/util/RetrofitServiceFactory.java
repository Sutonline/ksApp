package android.kevin.cn.ks.util;

import android.kevin.cn.ks.common.Constants;
import android.kevin.cn.ks.convert.fastjson.FastJsonConvertFactory;

import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * retrofit service的factory
 * @author yongkang.zhang
 * @date 2017/12/1
 */
public class RetrofitServiceFactory {

    // 单例retrofit
    private static Retrofit retrofit;

    public static <T> T getServiceBean(Class<T> clazz) {
        if (retrofit == null) {
            init();
        }

        return retrofit.create(clazz);
    }

    // 单例的实例化
    private static synchronized void init() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.REST_BASE_URL)
                    .callbackExecutor(Executors.newFixedThreadPool(5))
                    .addConverterFactory(FastJsonConvertFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

}
