package com.example.dxnima.zhidao.constant;

/**
 * <网络请求url地址>
 * <功能详细描述>
 * Created by DXnima on 2019/4/1.
 */
public class URLUtil {

    /**
     * 服务器地址
     * http://i24388o999.qicp.vip:18855   8080
     */
    //public static final String SERVER = "http://i24388o999.qicp.vip:36195/";//8888
    public static final String SERVER = "http://i24388o999.qicp.vip:36195/";//8080
    /**
     * 用户登陆
     */
    public static final String USER_LOGIN = SERVER+"user/login.do";

    /**
     * 用户注册
     * */
    public static final String USER_REGISTER=SERVER+"user/register.do";

    /**
     * 用户修改密码
     * */
    public static final String USER_UPDATEUSER=SERVER+"user/updateUser.do";

    /**
     * 获取用户信息
     * */
    public static final String USER_GETUSERINFO=SERVER+"user/get_user_info.do";

    /**
     * 退出登陆
     * */
    public static final String USER_LOGINOUT=SERVER+"user/loginOut.do";

    /**
     * 信息发送
     */
    public static final String MSG_SENDMSG=SERVER+"msg/sendMsg.do";

    /**
     * 获取已经发送信息
     */
    public static final String MSG_ALLSENDMSG=SERVER+"msg/allMsgByCode.do";

    /**
     * 关注主题
     */
    public static final String GETSUBJECT_FOCUS=SERVER+"getSubject/focusSubject.do";

    /**
     * 查询所有关注的主题
     */
    public static final String GETSUBJECT_ALLFOCUS=SERVER+"getSubject/allFocusSubject.do";

    /**
     * 添加主题
     */
    public static final String SUBJECT_ADD=SERVER+"subject/addSubject.do";

    /**
     * 查询所有添加的主题
     */
    public static final String SUBJECT_ALL=SERVER+"subject/allSendSubject.do";

}
