package com.ultimate.www.myapplication.test;

import android.content.Context;

import com.ultimate.www.myapplication.utils.ToastUtil;

/**
 * @author 李宝
 * @date 2019/12/5
 * @Describe 测试用的普通类 用于创建bug和修复bug
 */
public class HotFixBugTest {

    /**
     * @author 李宝
     * @date 2019/12/5
     * @Describe 这是有bug的类
     * path : D:/MyApplication/app/build/intermediates/javac/release/classes/com/ultimate/www/myapplication/test/HotFixBugTest.class
     * 在生成dex文件的时候path中转义字符"/"要换成"\" 注释里我只能这么写 不然会报“非法的 Unicode 转义”
     */
    public void getBug(Context context) {
        ToastUtil.show(context,"3秒钟之后抛出空指针异常,app会闪退");
        //3秒钟之后抛出空指针异常
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    throw new NullPointerException();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
    * @日期 2019/12/5 10:57
    * @Processor libao
    * @Description  用于打补丁包（zip包）修复Bug
    * @Parameter
    */
//    public void getBug(Context context) {
//        ToastUtil.show(context,"我去掉了空指针异常，Bug已经修复");
//    }

}
