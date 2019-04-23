package com.example.dxnima.zhidao.capabilities.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <sharepref基础存储功能模块>
 *
 * 1.定义ITRequestResult接口，用于处理网络请求后的回调，并且此接口中的回调在主线程中（OKHttp返回接口Callback是在子线程中 ）。
 2.TRequestCallBack接口实现。
 集中统一处理网络层异常码然后返回到UI层。
 集中统一处理网络层正常情况，通过json库，把网络返回解析成Java model返回给UI层。
 3.get，post，cancel方法封装，方便调用。
 * Created by DXnima on 2019/4/1.
 */
public class BaseSharedPreference {
    private String fileName;

    private Context context;

    public BaseSharedPreference(Context context, String fileName) {
        this.fileName = fileName;
        this.context = context;
    }

    /**
     * Retrieve the package shared preferences object.
     *
     * @return
     */
    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public void saveBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defvalue) {
        return getSharedPreferences().getBoolean(key, defvalue);
    }

    /**
     * Save a string value to the shared preference.
     *
     * @param key   to mark the store value.
     * @param value to saved value.
     */
    public void saveString(String key, String value) {
        getSharedPreferences().edit().putString(key, value).commit();
    }

    /**
     * Get the specified value through the key value.
     *
     * @param key to retrieve the value.
     * @return the string value returned.
     */
    public String getString(String key, String def) {
        return getSharedPreferences().getString(key, def);
    }

    /**
     * Save a integer value to the shared preference.
     *
     * @param key   to mark the store value.
     * @param value to saved value.
     */
    public void saveInt(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).commit();

    }

    /**
     * Get the specified value through the key value.
     *
     * @param key to retrieve the value.
     * @return the integer value returned.
     */
    public int getInt(String key, int def) {
        return getSharedPreferences().getInt(key, def);
    }

    /**
     * Save a Long value to the shared preference.
     *
     * @param key   to mark the store value.
     * @param value to saved value.
     */
    public void saveLong(String key, long value) {
        getSharedPreferences().edit().putLong(key, value).commit();
    }

    /**
     * Get the specified value through the key value.
     *
     * @param key to retrieve the value.
     * @return the integer value returned.
     */
    public long getLong(String key, long def) {
        return getSharedPreferences().getLong(key, def);
    }
}
