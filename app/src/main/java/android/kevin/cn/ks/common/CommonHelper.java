package android.kevin.cn.ks.common;

import android.content.Context;
import android.view.View;

import com.gc.materialdesign.widgets.Dialog;

/**
 * @author yongkang.zhang
 *         Created by yongkang.zhang on 2017/11/9.
 */

public class CommonHelper {


    public static Dialog getDialog(Context context, String title, String message, View.OnClickListener acceptListener, View.OnClickListener cancleListener) {
        Dialog dialog = new Dialog(context, title, message);
        if (acceptListener != null) {
            dialog.setOnAcceptButtonClickListener(acceptListener);
        }

        if (cancleListener != null) {
            dialog.setOnCancelButtonClickListener(cancleListener);
        }

        return dialog;
    }
}
