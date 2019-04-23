package com.example.dxnima.zhidao.bridge.security;

import android.content.Context;

import com.example.dxnima.zhidao.bridge.BridgeLifeCycleListener;
import com.example.dxnima.zhidao.capabilities.security.SecurityUtils;


/**
 * <加解密管理类>
 *
 * Created by DXnima on 2019/4/1.
 */
public class SecurityManager implements BridgeLifeCycleListener {
    @Override
    public void initOnApplicationCreate(Context context) {

    }

    @Override
    public void clearOnApplicationQuit() {

    }

    /**
     * md5 加密
     * @param str
     * @return
     */
    public String get32MD5Str(String str){
        return SecurityUtils.get32MD5Str(str);
    }

}
