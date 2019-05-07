package com.example.dxnima.zhidao.bridge.cache.sharePref;

import android.content.Context;

import com.example.dxnima.zhidao.capabilities.cache.BaseSharedPreference;

/**
 * <用户信息缓存>
 *
 * Created by DXnima on 2019/4/1.
 */
public class EBSharedPrefUser extends BaseSharedPreference {
    /**
     * 登录名
     */
    public static final String USER_NAME = "user_name";

    /**
     * 编号查询得到的所有msg
     * */
    public static final String ALLMSG_BYCODE="allMsg_byCode";

    public EBSharedPrefUser(Context context, String fileName) {
        super(context,fileName);
    }
}
