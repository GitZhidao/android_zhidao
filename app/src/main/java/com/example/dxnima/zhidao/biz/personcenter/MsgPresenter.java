package com.example.dxnima.zhidao.biz.personcenter;

import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.biz.BasePresenter;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.http.OkHttpManager;
import com.example.dxnima.zhidao.capabilities.http.ITRequestResult;
import com.example.dxnima.zhidao.constant.URLUtil;

import java.util.List;

/**
 * Created by DXnima on 2019/4/9.
 */
public class MsgPresenter extends BasePresenter<IMsgView> {
    public static List<Msg> msgList=null;
    public MsgPresenter() {
    }

    //获取全部Msg
    public void allSendMsg(){
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
         httpManager.requestAsyncGet(URLUtil.MSG_ALLSENDMSG, new ITRequestResult<Msg>() {
             @Override
             public void onSuccessful(List<Msg> entity) {
                 msgList = entity;
             }

             @Override
             public void onFailure(String errorMsg) {
             }

             @Override
             public void onCompleted() {
             }
         }, Msg.class);
    }
}