package com.ultimate.www.myapplication.utils;

import android.text.TextUtils;

import java.io.File;

/**
 * @author 李宝
 * @date 2019/12/5
 * @Describe 文件操作工具类
 */
public class FileUtils {

    /**
     * 删除指定路径下的所有文件
     * @param zipPath : 文件路径
     */
    public static void delFile(String zipPath) {

        if (!TextUtils.isEmpty(zipPath)){

            File file = new File(zipPath);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    delFile(files[i].getAbsolutePath());
                }
            } else {
                if (file.exists()) file.delete();
            }
        }

    }

}
