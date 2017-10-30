package android.kevin.cn.ks.message;

import android.app.Activity;
import android.kevin.cn.ks.R;
import android.kevin.cn.ks.message.Adapter.MsgAdapter;
import android.kevin.cn.ks.message.domain.Msg;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息活动
 * Created by yongkang.zhang on 2017/10/30.
 */

public class MsgActivity extends Activity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;

    private List<Msg> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message_layout);

    }


    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice taling to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }





}
