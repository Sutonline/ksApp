package cn.ks.convert.fastjson;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * @author yongkang.zhang
 * @date 2017/12/01
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private Type type;

    FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {

        InputStreamReader inputStreamReader;
        BufferedReader reader;

        inputStreamReader = new InputStreamReader(value.byteStream(), UTF_8);
        reader = new BufferedReader(inputStreamReader);

        StringBuilder sb = new StringBuilder();

        String line;
        if ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        inputStreamReader.close();
        reader.close();
        return JSON.parseObject(sb.toString(), type);
    }
}
