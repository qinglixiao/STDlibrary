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
	 * ��          �� ������ⲿ�洢�豸���Ƿ����
	 * ��������  : 2013-11-25
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ����ȡӦ����SD���İ�װĿ¼
	 * ��������  : 2013-11-25
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ����ȡSD����Ŀ¼
	 * ��������  : 2013-11-25
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @return
	 *
	 */
	public static String getSdCardRootDirectory() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	/**
	 * 
	 * ��          �� ����ȡӦ�û���Ŀ¼(�ڴ�)
	 * ��������  : 2014-7-30
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ����ȡSD���ϵ�����Ŀ¼
	 * ��������  : 2013-11-26
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @return
	 *
	 */
	public static String getDownLoadDirectory() {
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
	}

	/**
	 * 
	 * ��          �� ��ͨ��������ͼƬ����ȡ��Ӧ����ԴID
	 * ��������  : 2014-1-27
	 * ��           �� �� lx
	 * �޸�����  : ��
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param context
	 * @param drawableName
	 * @return
	 * ���ҳɹ����أ�id
	 *ʧ�ܷ��أ�0
	 */
	public int getDrawableId(Context context, String drawableName) {
		return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
	}

	/**
	 * 
	 * ��          �� ��ͨ�������Ĳ����ļ�����ȡ��Ӧ����ԴID
	 * ��������  : 2014-1-27
	 * ��           �� �� lx
	 * �޸�����  : ��
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param context
	 * @param layoutName
	 * @return
	 * ���ҳɹ����أ�id
	 *ʧ�ܷ��أ�0
	 */
	public int getLayoutId(Context context, String layoutName) {
		return context.getResources().getIdentifier(layoutName, "layout", context.getPackageName());
	}

	/**
	 * 
	 * ��          �� ���ж������Ƿ����
	 * ��������  : 2014-6-11
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * �����Ƿ�������
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
	 * Cmwap�����Ƿ�������
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
	 * Wifi�����Ƿ�������
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
	 * ��          �� ����ȡ�豸����
	 * ��������  : 2014-6-25
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ����ȡ�����ֻ�����
	 * ��������  : 2014-12-9
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ���Ƿ���в���SD��Ȩ��
	 * ��������  : 2014-9-10
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ����ȡ�ֻ�״̬���߶�
	 * ��������  : 2014-11-28
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ��dp��λת��Ϊpx(ϵͳ�Դ�����ʽ)
	 * ��������  : 2014-11-30
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ��sp��λת��Ϊpx(ϵͳ�Դ�����ʽ)
	 * ��������  : 2014-11-30
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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
	 * ��          �� ��px��λת��Ϊdp��ϵͳ����ʽ��
	 * ��������  : 2014-11-30
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
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

	/**��������ڸǵĲ�����������
	 * @param root
	 *            ����㲼�֣���Ҫ�����Ĳ���
	 * @param scrollToView
	 *            �������ڵ���scrollToView������root,ʹscrollToView��root��������ĵײ�
	 */
	private void controlKeyboardLayout(final View root, final View scrollToView) {
		root.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				// ��ȡroot�ڴ���Ŀ�������
				root.getWindowVisibleDisplayFrame(rect);
				// ��ȡroot�ڴ���Ĳ���������߶�(������View�ڵ�������߶�)
				int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
				
				// ������ݱ��ڵ�����������������̶���
				if (scrollToView.getBottom() > rect.bottom) {
					int[] location = new int[2];
					// ��ȡscrollToView�ڴ��������
					scrollToView.getLocationInWindow(location);
					// ����root�����߶ȣ�ʹscrollToView�ڿɼ�����
					int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
					root.scrollTo(0, srollHeight);
				}
				else {
					// ��������
					root.scrollTo(0, 0);
				}
			}
		});
	}
	
	/**
	 * ��ȡ�汾����
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
	 * ���ݰ����ж�Ӧ���Ƿ���ǰ̨����
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
	 * ���ݽ������ƻ�ȡ����Id
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

}
