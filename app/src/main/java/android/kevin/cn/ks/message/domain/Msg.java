package android.kevin.cn.ks.message.domain;

/**
 * 消息
 * Created by yongkang.zhang on 2017/10/30.
 */

public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
