package com.ultimate.www.myapplication;

import android.app.Application;
import android.content.Context;

import com.androidnetworking.AndroidNetworking;

public class ComponentApplication extends Application {

    public ComponentApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
//                .addNetworkInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        //TODO you can do something youself
//                        return null;
//                    }
//                })
//                .build();
//        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.initialize(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    //TODO 这里用单例 我就简单处理了
    public static Context getInstant(){
        return new ComponentApplication();
    }
}
