package com.example.dxnima.zhidao.bridge.cache.sharePref;

import android.content.Context;

import com.example.dxnima.zhidao.capabilities.cache.BaseSharedPreference;

/**
 * <设置信息缓存>
 * Created by DXnima on 2019/4/1.
 */
public class EBSharedPrefSetting extends BaseSharedPreference {
    /**
     * 声音提醒 默认已开启
     */
    public static final String SOUND_REMINDER = "sound_reminder";

    /**
     * 震动提醒 默认已开启
     */
    public static final String VIBRATION_REMINDER = "vibration_reminder";

    public EBSharedPrefSetting(Context context, String fileName) {
        super(context, fileName);
    }
}
