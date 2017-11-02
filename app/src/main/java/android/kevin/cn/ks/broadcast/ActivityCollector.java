package android.kevin.cn.ks.broadcast;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动集合
 * Created by yongkang.zhang on 2017/11/2.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishedAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

}
