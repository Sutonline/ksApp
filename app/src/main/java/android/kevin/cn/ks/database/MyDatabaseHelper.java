package android.kevin.cn.ks.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by yongkang.zhang on 2017/11/4.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static String CREATE_BOOK;

    static {
        StringBuffer sb = new StringBuffer();
        sb.append("create table book(")
                .append("id integer primary key autoincrement,")
                .append("author text, ")
                .append("price real, ")
                .append("pages integer, ")
                .append("name text)");
        CREATE_BOOK = sb.toString();
    }

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "Created successed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
