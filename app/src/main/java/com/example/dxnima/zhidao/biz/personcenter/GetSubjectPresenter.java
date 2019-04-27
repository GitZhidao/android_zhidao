package com.example.dxnima.zhidao.biz.personcenter;

import com.example.dxnima.zhidao.bean.table.Subject;
import com.example.dxnima.zhidao.biz.BasePresenter;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IGetSubjectView;
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
public class GetSubjectPresenter extends BasePresenter<IGetSubjectView> {
    public static List<Subject> focusSubjectList;

    /**
     * 关注一个主题
     * 请求参数code
     * */
    public void focusSubject(String code){
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.requestAsyncPost(URLUtil.GETSUBJECT_FOCUS, new ITRequestResult<Subject>() {
            @Override
            public void onSuccessful(List<Subject> entity) {
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        }, Subject.class, new Param("code", code));
    }

    /**
     * 查询所有关注的主题
     * */
    public void allFocusSubject(){
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.requestAsyncGetByTag(URLUtil.GETSUBJECT_ALLFOCUS, getName(), new ITRequestResult<Subject>() {
            @Override
            public void onSuccessful(List<Subject> entity) {
                focusSubjectList = entity;
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "请求失败！");
            }

            @Override
            public void onCompleted() {
                mvpView.hideLoading();
            }
        }, Subject.class);
    }
}
