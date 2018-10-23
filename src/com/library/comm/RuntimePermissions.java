package com.library.comm;

/**
 * Description: Android运行时请求的权限
 * Author: lixiao
 * Create on: 2018/8/3.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RuntimePermissions {
    //====================================
    // Android 6.0 或以上
    // 系统危险级别权限，需要运行时动态请求获取
    //====================================

    /**
     * 读日历
     */
    public static final String READ_CALENDAR = "android.permission.READ_CALENDAR";
    /**
     * 写日历
     */
    public static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
    /**
     * 相机-关于相机所有功能
     */
    public static final String CAMERA = "android.permission.CAMERA";
    /**
     * 读通讯录
     */
    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS";
    /**
     * 写通讯录
     */
    public static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    /**
     * 系统账户列表
     */
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
    /**
     * 精准定位-GPS
     */
    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    /**
     * 粗略定位-wifi,蜂窝网络
     */
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    /**
     * 录音
     */
    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";
    /**
     * 手机号码、手机联系人
     */
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    /**
     * 不通过拨号键拨打电话
     */
    public static final String CALL_PHONE = "android.permission.CALL_PHONE";
    /**
     * 读取通话记录
     */
    public static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    /**
     * 写通话记录
     */
    public static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    /**
     * 添加电话留言
     */
    public static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
    /**
     * 使用SIP服务
     */
    public static final String USE_SIP = "android.permission.USE_SIP";
    /**
     * Allows an application to see the number being dialed during an outgoing call
     * with the option to redirect the call to a different number
     * or abort the call altogether.
     */
    public static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";
    /**
     * 从传感器中读取健康记录
     */
    public static final String BODY_SENSORS = "android.permission.BODY_SENSORS";
    /**
     * 发短信
     */
    public static final String SEND_SMS = "android.permission.SEND_SMS";
    /**
     * 收短信
     */
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    /**
     * 读短信
     */
    public static final String READ_SMS = "android.permission.READ_SMS";
    /**
     * 收wap推送信息
     */
    public static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    /**
     *收mms
     */
    public static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";
    /**
     *读sd卡
     */
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    /**
     *写sd卡
     */
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

}
