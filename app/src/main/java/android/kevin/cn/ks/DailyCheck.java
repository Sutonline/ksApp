package android.kevin.cn.ks;

import android.app.Activity;
import android.os.Bundle;

import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

/**
 * author: yongkang.zhang
 * Created by yongkang.zhang on 2017/11/6.
 */

public class DailyCheck extends Activity {

    private static final int[] MENU_IMAGE = new int[]{R.drawable.bat, R.drawable.bear, R.drawable.butterfly, R.drawable.cat,
        R.drawable.dolphin, R.drawable.horse, R.drawable.elephant, R.drawable.owl, R.drawable.squirrel
    };

    private static final String[] MENU_NAME = new String[]{"打卡", "熊", "蝴蝶", "猫", "海豚", "horse", "大象",
        "猫头鹰", "松鼠"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_check_layout);
        initMenu();

    }

    private void initMenu() {
        BoomMenuButton bmb = findViewById(R.id.boom_menu_button);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder().normalImageRes(MENU_IMAGE[i])
                    .normalText(MENU_NAME[i]);
            bmb.addBuilder(builder);
        }
    }
}
