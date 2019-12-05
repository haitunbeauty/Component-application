package com.ultimate.www.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ultimate.www.myapplication.ComponentApplication;

/**
 * @author 李宝
 * @date 2019/12/4
 * @Describe share存储
 */
public class SharedPrefsUtil {

	private static SharedPreferences getPreferences(Context context, String sharedName){
		return context.getSharedPreferences(sharedName, Context.MODE_PRIVATE);
	}
	
	public static boolean getBoolean(Context context, String sharedName, String key){
		return getPreferences(context, sharedName).getBoolean(key, false);
	}
	public static float getFloat(Context context, String sharedName, String key){
		return getPreferences(context, sharedName).getFloat(key, 0.0f);
	}
	
	public static int getInt(Context context, String sharedName, String key){
		return getPreferences(context, sharedName).getInt(key, 0);
	}
	
	public static long getLong(Context context, String sharedName, String key){
		return getPreferences(context, sharedName).getLong(key, 0L);
	}
	
	public static String getString(Context context, String sharedName, String key){
		return getPreferences(context, sharedName).getString(key, "");
	}



//TODO----------------------------------------------------------------------------------------------


	public static int getInt(String sharedName, String key){
		return getPreferences(ComponentApplication.getInstant(), sharedName).getInt(key, 0);
	}

	public static String getString(String sharedName, String key){
		return getPreferences(ComponentApplication.getInstant(), sharedName).getString(key, "");
	}


//TODO----------------------------------------------------------------------------------------------

	public static void set(Context context, String sharedName, String key, Object value){
		commit(context,sharedName,key,value);
	}

	public static void set(String sharedName, String key, Object value){
		commit(ComponentApplication.getInstant(),sharedName,key,value);
	}


	private static void commit(Context context, String sharedName, String key, Object value){
		Editor editor = getPreferences(context, sharedName).edit();
		if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean)value);
		}else if (value instanceof Float) {
			editor.putFloat(key, (Float)value);
		}else if (value instanceof Integer) {
			editor.putInt(key, (Integer)value);
		}else if (value instanceof Long) {
			editor.putLong(key, (Long)value);
		}else if (value instanceof String) {
			editor.putString(key, (String)value);
		}
		editor.commit();
	}




	public static void remove(Context context, String sharedName, String key){
		Editor editor = getPreferences(context, sharedName).edit();
		editor.remove(key);
		editor.commit();
	}

}
