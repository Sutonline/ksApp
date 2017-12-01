package android.kevin.cn.ks.util;

/**
 * dataManager的工厂
 * @author yongkang.zhang
 * @date 2017/12/1
 */
public class DataManagerFactory {

    public static <T> T getManager(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("clazz必须有public的空构造器");
        }
    }

}
