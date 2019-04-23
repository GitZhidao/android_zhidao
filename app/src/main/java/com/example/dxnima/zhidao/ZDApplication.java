package com.example.dxnima.zhidao;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.BridgeLifeCycleSetKeeper;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.cache.localstorage.LocalFileStorageManager;
import com.example.dxnima.zhidao.dao.DaoMaster;
import com.example.dxnima.zhidao.dao.DaoSession;
import com.example.dxnima.zhidao.util.ToastUtil;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * <应用初始化>
 *     <功能详细描述>
 * Created by DXnima on 2019/4/2.
 */
public class ZDApplication extends Application {

    private DaoMaster.DevOpenHelper mHelper;

    private SQLiteDatabase db;

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    //静态单例
    public static ZDApplication instances;

    /**
     * app实例
     */
    public static ZDApplication zdApplication = null;

    /**
     * 本地activity栈
     */
    public static List<Activity> activitys = new ArrayList<Activity>();

    /**
     * 当前avtivity名称
     */
    public static String currentActivityName = "";

    @Override
    public void onCreate()
    {
        super.onCreate();
        initData();
        instances = this;
        setDatabase();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        zdApplication = this;
        BridgeFactory.init(this);
        BridgeLifeCycleSetKeeper.getInstance().initOnApplicationCreate(this);
        LocalFileStorageManager manager = BridgeFactory.getBridge(Bridges.LOCAL_FILE_STORAGE);
        Picasso picasso = new Picasso.Builder(this).downloader(
                new OkHttpDownloader(new File(manager.getCacheImgFilePath(this)))).build();
        Picasso.setSingletonInstance(picasso);
    }


    @Override
    public void onTerminate()
    {
        super.onTerminate();
        onDestory();
    }

    /**
     * 退出应用，清理内存
     */
    private void onDestory() {
        BridgeLifeCycleSetKeeper.getInstance().clearOnApplicationQuit();
        ToastUtil.destory();
    }


    /**
     *
     * <添加> <功能详细描述>
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void addActivity(Activity activity)
    {
        activitys.add(activity);
    }

    /**
     *
     * <删除>
     * <功能详细描述>
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void deleteActivity(Activity activity)
    {
        if (activity != null)
        {
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public static ZDApplication getInstances(){
        return instances;
    }

    /**     * 设置greenDao数据库
     *  */
    private void setDatabase() {
         // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
         // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
         // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
         // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
         // 此处sport-db表示数据库名称 可以任意填写
          mHelper = new DaoMaster.DevOpenHelper(this, "zhidao", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     * 使用DaoSession daoSession =ZDApplication.getInstances().getDaoSession();
     * */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


}