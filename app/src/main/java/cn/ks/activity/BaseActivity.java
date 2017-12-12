package cn.ks.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import cn.ks.layout.BoomMenuLayout;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.OnBoomListener;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.function.Consumer;

/**
 * author: yongkang.zhang
 * Created by yongkang.zhang on 2017/11/7.
 */

public class BaseActivity extends AppCompatActivity {

    private BoomMenuLayout boomMenuLayout;
    public Activity currentAct;
    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        boomMenuLayout = new BoomMenuLayout(this);
        initBmbClickListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ((ViewGroup) getWindow().getDecorView()).addView(boomMenuLayout);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initBmbClickListener(final Context context) {
        boomMenuLayout.getBmb().setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                Intent intent;
                switch (index) {
                    case 0:
                        // 进入dailyCheck
                        intent = new Intent(context, DailyCheck.class);
                        break;
                    case 1:
                        // 进入manageVps
                        intent = new Intent(context, ManageVpsActivity.class);
                        break;
                    default:
                        intent = new Intent(context, ManageVpsActivity.class);
                        break;
                }
                startActivity(intent);
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });
    }

    protected Context getActivity() {
        return this;
    }

    protected void shortShow(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
