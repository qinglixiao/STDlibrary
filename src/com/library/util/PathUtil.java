package com.library.util;

import java.io.File;

import android.text.TextUtils;

public class PathUtil {
	/**
	 * 
	 * 描          述 ：合并两个路径
	 * 创建日期  : 2014-7-29
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param path1
	 * @param path2
	 * @return
	 *
	 */
	public static String merge(String path1,String path2){
		if(TextUtils.isEmpty(path2))
			return path1;
		if((path1.endsWith("\\") || path1.endsWith("/")) && !TextUtils.isEmpty(path1))
			path1 = path1.substring(0, path1.length() - 2);
		if((path2.startsWith("\\") || path2.startsWith("/")) && path2.length() > 1)
			path2 = path2.substring(1);
		return path1 + File.separator + path2;
	}
}
