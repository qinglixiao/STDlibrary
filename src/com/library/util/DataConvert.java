package com.library.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.library.exception.BaseSTDException;

import java.util.List;

/**
 *
 * 描          述 ：数据转换工具类
 * 实现Json、Xml与java对象的相互转化
 * 创建日期  : 2013-6-17
 * 作           者 ： lx
 * 修改日期  : (文件的修改日期)
 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
 * @version   : 1.0
 */
public class DataConvert {

	/**
	 *
	 * 描          述 ：将json格式的对象流转成成T类型对象
	 * 创建日期  : 2013-6-18
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStr：json数据流
	 * @param clazz ：目标对象类型
	 * @return
	 *
	 */
	public static <T> T parseObjectFromJSON(String objStr, Class<T> clazz) {
		try {
			T retObj = null;
			if (!TextUtils.isEmpty(objStr)) {
				retObj = JSON.parseObject(objStr, clazz);
			}
			return retObj;
		}
		catch (Exception ex) {
			throw new BaseSTDException("在JSON格式数据转换时错误！", ex);
		}
	}

	/**
	 *
	 * 描          述 ：对象序列化为JSON字符串
	 * 创建日期  : 2013-6-18
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param obj
	 * @return
	 *
	 */
	public static <T> String toJSONString(T obj) {
		try {
			String retStr = "";
			if (null != obj)
				retStr = JSON.toJSONString(obj);
			return retStr;
		}
		catch (Exception ex) {
			throw new BaseSTDException("在对象序列化为JSON字符串时发生错误！", ex);
		}
	}

	/**
	 *
	 * 描          述 ：将JSON格式的字符流转换成目标类型的集合对象
	 * 创建日期  : 2013-6-18
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStrs json数据流
	 * @param clazz 集合中元素类型
	 * @return
	 *
	 */
	public static <T> List<T> parseArrayFromJSON(String objStrs, Class<T> clazz) {
		try {
			List<T> retArray = null;
			if (!TextUtils.isEmpty(objStrs)) {
				retArray = JSON.parseArray(objStrs, clazz);
			}
			return retArray;
		}
		catch (Exception ex) {
			throw new BaseSTDException("在JSON格式数据转换成实体集时错误！", ex);
		}
	}

}
