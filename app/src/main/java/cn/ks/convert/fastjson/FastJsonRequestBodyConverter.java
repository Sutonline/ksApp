package cn.ks.convert.fastjson;


import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;


/**
 * @author yongkang.zhang
 * @date 2017/12/01
 */
public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private static final MediaType type = MediaType.parse("application/json; charset=UTF-8");


    @Override
    public RequestBody convert(@NonNull T value) throws IOException {
        return RequestBody.create(type, JSON.toJSONString(value).getBytes("UTF-8"));
    }
}
