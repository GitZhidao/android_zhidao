package com.example.dxnima.zhidao.biz.personcenter;

import android.util.Log;

import com.example.dxnima.zhidao.ZDApplication;
import com.example.dxnima.zhidao.bean.table.User;
import com.example.dxnima.zhidao.biz.BasePresenter;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefManager;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefUser;
import com.example.dxnima.zhidao.bridge.http.OkHttpManager;
import com.example.dxnima.zhidao.capabilities.http.ITRequestResult;
import com.example.dxnima.zhidao.constant.URLUtil;
import com.example.dxnima.zhidao.dao.DaoSession;
import com.example.dxnima.zhidao.dao.UserDao;
import com.example.dxnima.zhidao.util.GeneralUtils;

import java.util.List;

/**
 *用户相关方法实现层
 * 登陆注册修改密码等
 * Created by DXnima on 2019/4/2.
 */
public class UserPresenter extends BasePresenter<IUserLoginView> {

    public static List<User> userList=null;

    GeneralUtils generalUtils=new GeneralUtils();
    public UserPresenter() {
    }

    DaoSession daoSession =ZDApplication.getInstances().getDaoSession();

    //网络层登陆实现
    public void loginInternet(final String username, String password) {
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        final User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        mvpView.showLoading();
        if (username=="" || password==""){
            mvpView.onError("不能为空！","");
        }
        //post请求
        httpManager.requestAsyncPostByTagClass(URLUtil.USER_LOGIN, getName(), new ITRequestResult<User>() {
            @Override
            public void onCompleted() {
                mvpView.hideLoading();
            }

            @Override
            public void onSuccessful(List<User> entity) {
                mvpView.onSuccess();
                userList=entity;
                EBSharedPrefManager manager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
                manager.getKDPreferenceUserInfo().saveString(EBSharedPrefUser.USER_NAME, entity.get(0).getUsername());
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

        }, User.class, user);


    }

    //注册网络层
    public void registerInternet(String username,String password,String email){
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        mvpView.showLoading();

        if (username=="" || password==""){
            mvpView.onError("不能为空！","");
        }
        else if (!generalUtils.IsPassword(password)){
            mvpView.onError("密码长度不够!","");
        }
        else if(!generalUtils.isEmail(email)){
            mvpView.onError("邮箱格式不正确！","");
        }
        else
        httpManager.requestAsyncPostByTagClass(URLUtil.USER_REGISTER, getName(), new ITRequestResult<User>() {
            @Override
            public void onCompleted() {
                mvpView.hideLoading();
            }

            @Override
            public void onSuccessful(List<User> entity) {
                mvpView.onSuccess();
                EBSharedPrefManager manager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
                manager.getKDPreferenceUserInfo().saveString(EBSharedPrefUser.USER_NAME, entity.get(0).getUsername());
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

        }, User.class,user);
    }



    //本地数据库登陆实现
    public void loginDatabase(String username, String password) {
        mvpView.showLoading();
        UserDao userDao = daoSession.getUserDao();
        List<User> userList = userDao.queryBuilder().build().list();
        if(userList == null){
            Log.e("register", "数据库里无数据");
        }
        else {
            int flag = 0;
            for (User user : userList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    mvpView.onSuccess();
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                mvpView.onError("密码不正确！", "");
            }
        }
    }

    //本地数据库注册
    public void registerDatabase(String username, String password,String email) {
        mvpView.showLoading();
        UserDao userDao = daoSession.getUserDao();
        List<User> userList = userDao.queryBuilder().build().list();
        if(userList == null){
            Log.e("register", "数据库里无数据");
        }else{
            int flag = 0;
            for(User user1 : userList){
                if(user1.getUsername().equals(username)){
                    flag = 1;
                    mvpView.onError("用户名已存在！", "");
                    break;
                }
            }
            if (flag == 0) {
                User user2 = new User(null,username,password,email);
                userDao.insert(user2);
                mvpView.onSuccess();
            }
        }
    }

}