package com.ultimate.www.myapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.ultimate.www.myapplication.R;
import com.ultimate.www.myapplication.test.HotFixBugTest;
import com.ultimate.www.myapplication.utils.FileUtils;
import com.ultimate.www.myapplication.utils.HotFixUtils;
import com.ultimate.www.myapplication.utils.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author 李宝
 * @date 2019/12/5
 * @Describe  热更新的签名安装包我上传了我的阿里云  可以下载安装测试
 * path ；https://lb-apk-package.oss-cn-chengdu.aliyuncs.com/zip_file/hotFixBugTest.apk?Expires=1575532414&OSSAccessKeyId=TMP.hhgdqbTd3fyfCiHxe1yVa2XKagSzCqPGgEjFGunvzWKJiGxfC8dU9A9n1gtEube2QUaREyqufA9A1TTz5aRHBEgFGD8HrEczyqtjVVnwXqxRoSS5n4P8VwF7koAaiR.tmp&Signature=YsP99q8wwxzUvJCqIXFBitZdN4c%3D
 *
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String DEX_DIR = "patch";
    private HomeViewModel homeViewModel;
    private File patchDir;
    private String tempFileName;
    private String zipPath;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        init();
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final Button buttonRun = root.findViewById(R.id.btn_run);
        final Button buttonRepair = root.findViewById(R.id.btn_repair);
        buttonRun.setOnClickListener(this);
        buttonRepair.setOnClickListener(this);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    /**
    * @日期 2019/12/5 9:30
    * @Processor libao
    * @Description 热补丁存放目录 ： /storage/emulated/0/Android/data/com.ultimate.www.myapplication/files/patch
    * @Description 热补丁文件路径 ： /storage/emulated/0/Android/data/com.ultimate.www.myapplication/files/patch/dexfile.zip
    */
    private void init() {
        patchDir = new File(getActivity().getExternalFilesDir(null), DEX_DIR);
        tempFileName = patchDir.getPath();
        zipPath = tempFileName+"/dexfile.zip";
        if (!patchDir.exists()) {
            patchDir.mkdirs();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_run:
                new HotFixBugTest().getBug(getActivity());
                break;
            case R.id.btn_repair:
                repair();
                break;
        }
    }

    private void repair() {
        //TODO 1、下载zip文件
        AndroidNetworking.download("https://lb-apk-package.oss-cn-chengdu.aliyuncs.com/zip_file/hotFixBugTest.zip?Expires=1575529580&OSSAccessKeyId=TMP.hhgdqbTd3fyfCiHxe1yVa2XKagSzCqPGgEjFGunvzWKJiGxfC8dU9A9n1gtEube2QUaREyqufA9A1TTz5aRHBEgFGD8HrEczyqtjVVnwXqxRoSS5n4P8VwF7koAaiR.tmp&Signature=i35zALaT5F9RyljGNGNrqgWduP4%3D",patchDir.getPath(),"dexfile.zip")
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        Log.e("progress",bytesDownloaded+":byte");
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        ToastUtil.show(getActivity(),"补丁下载完成");
                        //TODO 2、解压zip文件
                        unzipFile(zipPath);
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("err",error+":ANError");
                    }
                });
    }

    /**
     * 解压zip压缩文件到指定目录
     * @param zipPath
     * tempFileName 临时文件夹名（）
     */

    public boolean unzipFile(String zipPath) {
        try {
            Log.e("hotfix1024", "开始解压的文件：" + zipPath + "," + "解压的目标路径：" + tempFileName);

            // 创建解压目标目录 ,我这里用的是热补丁下载存放的目录，可以选择自定义
            File file = new File(tempFileName);

            // 如果目标目录不存在，则创建
            if (!file.exists()) {
                file.mkdirs();
            }

            // 打开压缩文件
            InputStream inputStream = new FileInputStream(zipPath);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);

            // 读取一个进入点
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            // 使用1Mbuffer
            byte[] buffer = new byte[1024 * 1024];

            // 解压时字节计数
            int count = 0;

            // 如果zipEntry为空说明已经遍历完所有压缩包中文件和目录
            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {  //如果是一个文件
                    String fileName = zipEntry.getName();
                    Log.e("hotfix1024", "解压文件 原来 文件的位置： " + fileName);
                    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);  //截取文件的名字 去掉原文件夹名字
                    Log.e("hotfix1024", "解压文件 的名字： " + fileName);
                    file = new File(tempFileName + File.separator + fileName);  //放到新的解压的文件路径
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while ((count = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.close();
                }
                zipEntry = zipInputStream.getNextEntry();// 解压下一个文件
            }

            ToastUtil.show(getActivity(),"补丁解压完成");

            //关闭流
            zipInputStream.close();

            //删除补丁包（zip文件）
            FileUtils.delFile(zipPath);

            //TODO 3、热修复
            new HotFixUtils().doHotFix(getActivity());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("hotfix1024", "unzipFile Exception" + e.toString());
            return false;
        }
    }



}