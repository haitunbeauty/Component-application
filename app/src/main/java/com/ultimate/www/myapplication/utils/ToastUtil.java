package com.ultimate.www.myapplication.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author 李宝
 * @date 2019/12/5
 * @Describe Toast提示工具
 */
public class ToastUtil {

    private static Toast toast;

    /**
     * show toast
     * @param context context
     * @param msg     message string
     */
    public static void show(Context context, String msg) {
        toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * show toast
     * @param context context
     * @param msgId   message resource id
     */
    public static void show(Context context, int msgId) {
        toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        toast.setText(msgId);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
