package cn.ks.activity;

import cn.ks.domain.Vps;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * App context信息
 * @author yongkang.zhang
 * @date 2017/12/5
 */
public class AppContext {

    // 用来缓存CHECK_INFO
    public static Map<LocalDate, Boolean> CHECK_INFO = new HashMap<>(1);

    // 用来缓存定义vps的map
    public static List<Vps> VPS_LIST = new ArrayList<>(1);

    static {
        Vps vps = new Vps(1, "Vultr", "Vultr provider");
        VPS_LIST.add(vps);
    }


}
