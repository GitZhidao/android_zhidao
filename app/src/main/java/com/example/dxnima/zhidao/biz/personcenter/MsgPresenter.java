package com.example.dxnima.zhidao.biz.personcenter;

import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.biz.BasePresenter;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IMsgView;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.http.OkHttpManager;
import com.example.dxnima.zhidao.capabilities.http.ITRequestResult;
import com.example.dxnima.zhidao.capabilities.http.Param;
import com.example.dxnima.zhidao.constant.URLUtil;

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
        httpManager.requestAsyncPostByTag(URLUtil.MSG_ALLSENDMSG,getName(), new ITRequestResult<Msg>() {
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
}