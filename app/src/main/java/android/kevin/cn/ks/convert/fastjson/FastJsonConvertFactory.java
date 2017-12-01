package android.kevin.cn.ks.convert.fastjson;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * FastJson 的 Convert工厂类，提供 ResponseBody的转换和RequestBody 的转换
 */
@SuppressWarnings("unchecked")
public class FastJsonConvertFactory extends Converter.Factory {

    public static FastJsonConvertFactory create() {
        return new FastJsonConvertFactory();
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter();
    }
}
