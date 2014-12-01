package com.library.util;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;

public class LibUtil {
	/**
	 * 
	 * 描          述 ：检查外部存储设备是是否可用
	 * 创建日期  : 2013-11-25
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	public static boolean isExternalStorageAvailable() {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * 描          述 ：获取应用在SD卡的安装目录
	 * 创建日期  : 2013-11-25
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @return
	 *
	 */
	public static String getAppInstallDirectory(Context context) {
		String mApplicationName = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
		return Environment.getExternalStoragePublicDirectory(mApplicationName).getPath();
	}

	/**
	 * 
	 * 描          述 ：获取SD卡根目录
	 * 创建日期  : 2013-11-25
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	public static String getSdRootDirectory() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	/**
	 * 
	 * 描          述 ：获取应用缓存目录(内存)
	 * 创建日期  : 2014-7-30
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @return
	 *
	 */
	public static String getCacheDirectory(Context context) {
		return context.getCacheDir().getPath();
	}

	/**
	 * 
	 * 描          述 ：获取SD卡上的下载目录
	 * 创建日期  : 2013-11-26
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	public static String getDownLoadDirectory() {
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
	}

	/**
	 * 
	 * 描          述 ：通过给定的图片名获取对应的资源ID
	 * 创建日期  : 2014-1-27
	 * 作           者 ： lx
	 * 修改日期  : ·
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @param drawableName
	 * @return
	 * 查找成功返回：id
	 *失败返回：0
	 */
	public int getDrawableId(Context context, String drawableName) {
		return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
	}

	/**
	 * 
	 * 描          述 ：通过给定的布局文件名获取对应的资源ID
	 * 创建日期  : 2014-1-27
	 * 作           者 ： lx
	 * 修改日期  : ·
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @param layoutName
	 * @return
	 * 查找成功返回：id
	 *失败返回：0
	 */
	public int getLayoutId(Context context, String layoutName) {
		return context.getResources().getIdentifier(layoutName, "layout", context.getPackageName());
	}

	/**
	 * 
	 * 描          述 ：判断网络是否可用
	 * 创建日期  : 2014-6-11
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @return
	 *
	 */
	public static boolean isNetWorkAvailable(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null)
			return info.isAvailable();
		else
			return false;
	}

	/**
	 * 
	 * 描          述 ：获取设备串号
	 * 创建日期  : 2014-6-25
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @return
	 *
	 */
	public static String getDeviceIMEI(Context context) {
		TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return manager.getDeviceId();
	}

	/**
	 * 
	 * 描          述 ：是否具有操作SD卡权限
	 * 创建日期  : 2014-9-10
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @return
	 *
	 */
	public static boolean hasExternalStoragePermission(Context context) {
		String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
		int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
		return perm == PackageManager.PERMISSION_GRANTED;
	}

	/**
	 * 
	 * 描          述 ：获取手机状态栏高度
	 * 创建日期  : 2014-11-28
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @return
	 *
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * 
	 * 描          述 ：dp单位转化为px
	 * 创建日期  : 2014-11-30
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @param dp
	 * @return
	 *
	 */
	public static int convertToPx(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	/**
	 * 
	 * 描          述 ：px单位转化为dp
	 * 创建日期  : 2014-11-30
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @param px
	 * @return
	 *
	 */
	public static int convertToDp(Context context, float px) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

}
