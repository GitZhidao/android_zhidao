package com.example.dxnima.zhidao;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.bean.table.User;
import com.example.dxnima.zhidao.bridge.security.SecurityManager;
import com.example.dxnima.zhidao.capabilities.json.GsonHelper;
import com.example.dxnima.zhidao.constant.URLUtil;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest<T> extends ApplicationTestCase<Application> {
    private static final String TAG = "ApplicationTest";
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
    String url= URLUtil.USER_LOGIN;
    public ApplicationTest() {
        super(Application.class);
    }
        public void testMain() {
        }

}