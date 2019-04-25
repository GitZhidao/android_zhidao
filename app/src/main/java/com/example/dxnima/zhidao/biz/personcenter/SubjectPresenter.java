package com.example.dxnima.zhidao.biz.personcenter;

import com.example.dxnima.zhidao.bean.table.Subject;
import com.example.dxnima.zhidao.biz.BasePresenter;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.ISubjectView;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.http.OkHttpManager;
import com.example.dxnima.zhidao.capabilities.http.ITRequestResult;
import com.example.dxnima.zhidao.capabilities.http.Param;
import com.example.dxnima.zhidao.constant.URLUtil;

import java.util.List;

/**
 * Created by DXnima on 2019/4/23.
 */
public class SubjectPresenter extends BasePresenter<ISubjectView>{
    public static List<Subject> subjectList;

    /**
     * 获取所有发布的主题
     * */
    public void allSendSubject(){
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.requestAsyncGetByTag(URLUtil.SUBJECT_ALL,getName(),new ITRequestResult<Subject>() {
            @Override
            public void onSuccessful(List<Subject> entity) {
                subjectList=entity;
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg,"刷新失败");
            }

            @Override
            public void onCompleted() {
                mvpView.hideLoading();
            }
        }, Subject.class);
    }

    /**
     * 发布一个主题
     * */
    public void addSubject(String subtitle){
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.requestAsyncPost(URLUtil.SUBJECT_ADD, new ITRequestResult<Subject>() {
            @Override
            public void onSuccessful(List<Subject> entity) {
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        }, Subject.class, new Param("subtitle", subtitle));
    }
}