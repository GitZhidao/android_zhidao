package com.example.dxnima.zhidao;

import android.app.Application;
import android.os.Build;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.example.dxnima.zhidao.bean.ListBaseResp;
import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.bean.table.User;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefManager;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefUser;
import com.example.dxnima.zhidao.bridge.security.SecurityManager;
import com.example.dxnima.zhidao.capabilities.json.GsonHelper;
import com.example.dxnima.zhidao.capabilities.log.EBLog;
import com.example.dxnima.zhidao.constant.URLUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
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



        /**
     * 测试okhttp  post方法
     *json
     * */
    public void testOkhttpPost() {
        url = URLUtil.USER_LOGIN;
        OkHttpClient mOkHttpClient = new OkHttpClient();
        User user = new User();
        user.setUsername("wnm");
        user.setPassword("123");
        String json = GsonHelper.toJson(user);
        System.out.println(json);
        //创建RequestBody对象，将参数按照指定的MediaType封装
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url(url)
                .addHeader("cookie", "JSESSIONID=AEE33E9E46B8764C25AC216B76523DDE")
                .build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            String result = response.body().string();
            Log.e("androixx.cn", result);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testPostValues(){
        url = URLUtil.MSG_ALLSENDMSG;
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder=new FormEncodingBuilder();
        builder.add("code","zd22137");
        RequestBody body = builder.build();
        Request request = new Request
                .Builder()
                .post(body)//Post请求的参数传递
                .url(url)
                .addHeader("cookie", "JSESSIONID=C43140FB486700720E6905FCCE96BB97")
                .build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            String result = response.body().string();
            Msg msgs=GsonHelper.toType(result,Msg.class);
            List<Msg> msgList= ((ListBaseResp<Msg>) msgs).getData();//json数据中data数据对象
            EBLog.i(TAG, result);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析没有数据头的纯数组
     */
    private void parseNoHeaderJArray(String strByJson) {
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<Msg> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            Msg userBean = gson.fromJson(user, Msg.class);
            userBeanList.add(userBean);
        }
    }
}