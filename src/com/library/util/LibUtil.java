package com.library.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

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
	public static String getAppHomeDirectory(Context context) {
		String mApplicationName = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
		String dir = Environment.getExternalStoragePublicDirectory(mApplicationName).getPath();
		File file = new File(dir);
		if(!file.exists())
			file.mkdirs();
		return dir;
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
	public static String getSdCardRootDirectory() {
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
	 * 网络是否已连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		NetworkInfo networkInfo = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}

	/**
	 * Cmwap网络是否已连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnectedByCmwap(Context context) {
		NetworkInfo networkInfo = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return networkInfo != null && networkInfo.isConnected()
				&& networkInfo.getExtraInfo() != null
				&& networkInfo.getExtraInfo().toLowerCase().contains("cmwap");
	}

	/**
	 * Wifi网络是否已连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnectedByWifi(Context context) {
		NetworkInfo networkInfo = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return networkInfo != null && networkInfo.isConnected();
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
	 * 描          述 ：获取本机手机号码
	 * 创建日期  : 2014-12-9
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @return
	 *
	 */
	public static String getPhoneNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String simSerialNum = telephonyManager.getLine1Number();
		if (!simSerialNum.isEmpty())
			return simSerialNum.substring(simSerialNum.length() - 11);
		return simSerialNum;
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
	 * 描          述 ：dp单位转化为px(系统自带处理方式)
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
	public static float convertDpToPx(Context context, float dp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
	}

	/**
	 *
	 * 描          述 ：sp单位转化为px(系统自带处理方式)
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
	public static float convertSpToPx(Context context, float sp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
	}

	/**
	 *
	 * 描          述 ：px单位转化为dp（系统处理方式）
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
	public static float convertPxToDp(Context context, float px) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return px / scale;
	}

	/**将软键盘遮盖的部分推至顶部
	 * @param root
	 *            最外层布局，需要调整的布局
	 * @param scrollToView
	 *            被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
	 */
	private void controlKeyboardLayout(final View root, final View scrollToView) {
		root.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				// 获取root在窗体的可视区域
				root.getWindowVisibleDisplayFrame(rect);
				// 获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
				int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;

				// 如果内容被遮挡则将内容上推至软键盘顶部
				if (rootInvisibleHeight > 100) {
					int[] location = new int[2];
					// 获取scrollToView在窗体的坐标
					scrollToView.getLocationInWindow(location);
					// 计算root滚动高度，使scrollToView在可见区域
					int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
					root.scrollTo(0, srollHeight);
				}
				else {
					// 键盘隐藏
					root.scrollTo(0, 0);
				}
			}
		});
	}

	/**
	 * 获取版本名称
	 *
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String versionName = "-1";
		try {
			versionName = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 根据包名判断应用是否在前台运行
	 *
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static boolean isAppOnForeground(Context context, String packageName) {
		if (packageName != null) {
			// Returns a list of application processes that are running on the
			// device
			ActivityManager activityManager = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningAppProcessInfo> appProcesses = activityManager
					.getRunningAppProcesses();
			if (appProcesses != null) {
				for (RunningAppProcessInfo appProcess : appProcesses) {
					// The name of the process that this object is associated
					// with.
					if (appProcess.processName.equals(packageName)
							&& RunningAppProcessInfo.IMPORTANCE_FOREGROUND == appProcess.importance) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 根据进程名称获取进程Id
	 *
	 * @param processName
	 * @return
	 */
	public static int getProcessPid(Context context, String processName) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> procList = null;
		int result = -1;
		procList = activityManager.getRunningAppProcesses();
		for (Iterator<RunningAppProcessInfo> iterator = procList.iterator(); iterator
				.hasNext();) {
			RunningAppProcessInfo procInfo = iterator.next();
			if (procInfo.processName.equals(processName)) {
				result = procInfo.pid;
				break;
			}
		}
		return result;
	}

	public static String getProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return "";
	}

	private void test(){

	}

}