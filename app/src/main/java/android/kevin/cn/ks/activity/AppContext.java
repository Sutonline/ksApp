package android.kevin.cn.ks.activity;

import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

/**
 * App context信息
 * @author yongkang.zhang
 * @date 2017/12/5
 */
public class AppContext {

    // 用来缓存CHECK_INFO
    public static Map<LocalDate, Boolean> CHECK_INFO = new HashMap<>(1);


}
