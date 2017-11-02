package android.kevin.cn.ks;

import android.content.Intent;
import android.kevin.cn.ks.broadcast.BroadcastActivity;
import android.kevin.cn.ks.lifeCycle.DialogActivity;
import android.kevin.cn.ks.lifeCycle.NormalActivity;
import android.kevin.cn.ks.message.MsgActivity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DailyCheck extends AppCompatActivity {

    private String[] data = {
        "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_daily_check);
        Log.d("DailyCheckActivity", "onCreate execute11111");

        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
        Button chatButton = (Button) findViewById(R.id.start_msg_activity);

        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("cn.ks.broadcast.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyCheck.this, DialogActivity.class);
                startActivity(intent);
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyCheck.this, MsgActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DailyCheck.this, android.R.layout.simple_list_item_1,
                data);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You dare click add?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you dare click remove?", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    // requestCode: 唯一的请求码，多个活动来验证请求来源 resultCode: 确定结果是否成功 data: 数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("返回的数据是: FirstActivity", returnedData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DailyCheck", "onStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("DailyCheck", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DailyCheck", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DailyCheck", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DailyCheck", "onDestory");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("DailyCheck", "onRestart");
    }
}
