package com.library.util;

import android.os.Build;

/**
 * 
 * 描          述 ：手机系统相关属性
 * 创建日期  : 2015-3-25
 * 作           者 ： lx
 * 修改日期  : 
 * 修   改   者 ：
 * @version   : 1.0
 */
public class About {
	/**
	 * 手机系统api级别
	 */
	public static int API_INT = Build.VERSION.SDK_INT;

	/**
	 * 本机版本是否高于API14
	 * (对应Android 4.0)
	 * @return
	 */
	public static boolean aboveApi14(){
		return API_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	}

}
