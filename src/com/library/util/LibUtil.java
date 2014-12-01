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
	public static String getAppInstallDirectory(Context context) {
		String mApplicationName = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
		return Environment.getExternalStoragePublicDirectory(mApplicationName).getPath();
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
	public static String getSdRootDirectory() {
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
	 * ��          �� ��dp��λת��Ϊpx
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
	public static int convertToPx(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	/**
	 * 
	 * ��          �� ��px��λת��Ϊdp
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
	public static int convertToDp(Context context, float px) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

}
