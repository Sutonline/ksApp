package cn.ks.util;

import cn.ks.common.Constants;
import cn.ks.convert.fastjson.FastJsonConvertFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.REST_BASE_URL)
                    .callbackExecutor(Executors.newFixedThreadPool(5))
                    .addConverterFactory(FastJsonConvertFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

}
