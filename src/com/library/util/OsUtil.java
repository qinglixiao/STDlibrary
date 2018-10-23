package com.library.util;

import android.os.Build;

/**
 * Description:手机属性工具类
 * Author: lixiao
 * Create on: 2018/8/3.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class OsUtil {

    /**
     * 获取手机api版本
     * @return
     */
    public static int getApiLevel(){
        return Build.VERSION.SDK_INT;
    }

    /**
     * api版本是否高于或等于android 6.0
     * @return
     */
    public static boolean isAboveApi60(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
