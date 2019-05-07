package com.example.dxnima.zhidao.biz.personcenter;

import android.util.Log;

import com.example.dxnima.zhidao.bean.ListBaseResp;
import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.biz.BasePresenter;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IMsgView;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefManager;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefUser;
import com.example.dxnima.zhidao.bridge.http.OkHttpManager;
import com.example.dxnima.zhidao.capabilities.http.ITRequestResult;
import com.example.dxnima.zhidao.capabilities.http.OkHttpUtil;
import com.example.dxnima.zhidao.capabilities.http.Param;
import com.example.dxnima.zhidao.capabilities.json.GsonHelper;
import com.example.dxnima.zhidao.capabilities.log.EBLog;
import com.example.dxnima.zhidao.constant.URLUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Util;

import java.io.IOException;
import java.util.List;

/**
 * Created by DXnima on 2019/4/9.
 */
public class MsgPresenter extends BasePresenter<IMsgView> {
    public static List<Msg> msgList=null;
    public MsgPresenter() {
    }


    /**
     * 通过编号查询所有Msg
     * 请求参数code
     * */
    public void allMsgByCode(String code){
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.requestAsyncPost(URLUtil.MSG_ALLSENDMSG, new ITRequestResult<Msg>() {
            @Override
            public void onSuccessful(List<Msg> entity) {
                msgList=entity;
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg,"加载失败！");
            }

            @Override
            public void onCompleted() {
                mvpView.hideLoading();
            }
        }, Msg.class, new Param("code",code));
    }

    /**
     * 发送Msg
     * */
    public void sendMsg(Msg msg){
    }

    public List<Msg> msgList1=null;
    public List<Msg> testMsgByCode(String code) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("code", code);
        RequestBody body = builder.build();
        Request request = new Request
                .Builder()
                .post(body)//Post请求的参数传递
                .url(URLUtil.MSG_ALLSENDMSG)
                .addHeader("cookie", OkHttpUtil.sessionid)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Msg msgs = GsonHelper.toType(result, Msg.class);
                    msgList1 = ((ListBaseResp<Msg>) msgs).getData();//json数据中data数据对象
                    EBLog.i("", result);
                }
            }
        });
        return msgList1;
    }
}