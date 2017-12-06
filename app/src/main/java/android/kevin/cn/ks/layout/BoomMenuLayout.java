package android.kevin.cn.ks.layout;

import android.content.Context;
import android.content.Intent;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.activity.DailyCheck;
import android.kevin.cn.ks.activity.ManageVps;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;

/**
 * 全局boomMenu
 * author: yongkang.zhang
 * Created by yongkang.zhang on 2017/11/7.
 */
public class BoomMenuLayout extends RelativeLayout {

    private BoomMenuButton bmb;

    private static final int[] MENU_IMAGE = new int[]{R.drawable.bat, R.drawable.bear, R.drawable.butterfly, R.drawable.cat,
            R.drawable.dolphin, R.drawable.horse, R.drawable.elephant, R.drawable.owl, R.drawable.squirrel
    };

    private static final String[] MENU_NAME = new String[]{"打卡", "熊", "蝴蝶", "猫", "海豚", "horse", "大象",
            "猫头鹰", "松鼠"
    };

    public BoomMenuLayout(Context context) {
        super(context);
        initView(context);
    }

    public BoomMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BoomMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public BoomMenuLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    //定位
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //可以在这里确定这个viewGroup的：宽 = r-l.高 = b - t
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.bmb_layout, this);
        initMenu(context);
    }

    private void initMenu(final Context context) {
        bmb = findViewById(R.id.boom_menu_button);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder().normalImageRes(MENU_IMAGE[i])
                    .normalText(MENU_NAME[i]);
            bmb.addBuilder(builder);
        }

    }

    public BoomMenuButton getBmb() {
        return bmb;
    }
}
