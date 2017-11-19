package com.library.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * 描          述 ：手机系统相关属性
 * 创建日期  : 2015-3-25
 * 作           者 ： lx
 * 修改日期  :
 * 修   改   者 ：
 *
 * @version : 1.0
 */
public class About {
    /**
     * 手机系统api级别
     */
    private static int API_INT = Build.VERSION.SDK_INT;

    /**
     * 本机版本是否高于API14
     * (对应Android 4.0)
     *
     * @return
     */
    public static boolean aboveApi14() {
        return API_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * 获取运营商
     *
     * @return
     */
    public static String getNetProvider(Context context) {
        String provider = "未知";
        try {
            TelephonyManager telephonyManager = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            String IMSI = telephonyManager.getSubscriberId();
            if (IMSI == null) {
                if (TelephonyManager.SIM_STATE_READY == telephonyManager
                        .getSimState()) {
                    String operator = telephonyManager.getSimOperator();
                    if (operator != null) {
                        if (operator.equals("46000")
                                || operator.equals("46002")
                                || operator.equals("46007")) {
                            provider = "中国移动";
                        } else if (operator.equals("46001")) {
                            provider = "中国联通";
                        } else if (operator.equals("46003")) {
                            provider = "中国电信";
                        }
                    }
                }
            } else {
                if (IMSI.startsWith("46000") || IMSI.startsWith("46002")
                        || IMSI.startsWith("46007")) {
                    provider = "中国移动";
                } else if (IMSI.startsWith("46001")) {
                    provider = "中国联通";
                } else if (IMSI.startsWith("46003")) {
                    provider = "中国电信";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }

    public static boolean isXiaomiSys() {
        return Build.MANUFACTURER.equalsIgnoreCase("xiaomi") || Build.BRAND.equalsIgnoreCase("xiaomi") || getSystemProperty("ro.miui.ui.version.name") != null;
    }

    public static boolean isMeizuSys() {
        String flag = getSystemProperty("ro.build.display.id");
        return flag != null && flag.length() > 0 && flag.toLowerCase().contains("flyme");
    }

    private static String getSystemProperty(String key) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", new Class[]{String.class, String.class});
            String ret = (String)get.invoke(clz, new Object[]{key, ""});
            return ret != null && ret.length() != 0?ret:null;
        } catch (Exception var4) {
            return null;
        }
    }
}
