package com.library.util;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.library.exception.BaseSTDException;
import com.library.exception.DataConvertException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;

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
		catch (ConversionException ex) {
			throw new DataConvertException(ex);
		}
		catch (Exception ex) {
			throw new BaseSTDException("在JSON格式数据转换时错误！", ex);
		}
	}

	/**
	 * 
	 * 描          述 ：将xml格式的对象流转成成T类型对象
	 * 创建日期  : 2013-6-18
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param <T>
	 * @param clazz ：目标对象类型
	 * @return
	 *
	 */
	public static <T> T parseObjectFromXML(String xmlString, Class<T> clazz) {
		XStream xStream = new XStream();
		xStream.alias(clazz.getSimpleName().toLowerCase(), clazz);//将xml根结点名与类对应（适应于xml根结点名跟类名一致的情况下）
		xStream.processAnnotations(clazz); //如果有注解则应用，如果不加此句则注解不会生效
		T reobj = (T) xStream.fromXML(xmlString);
		return reobj;
	}

	/**
	 * 
	 * 描          述 ：将xml格式的对象流转换成T类型对象
	 * 创建日期  : 2013-6-19
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStr xml数据流
	 * @param clazz  目标对象类型
	 * @param alias  类型别名集合，主要实现xml节点名与具体对象属性名的对应。<xml节点名，对象属性名>
	 *  
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseObjectFromXML(String objStr, Class<T> clazz, Hashtable<String, String> aliasField) {
		try {
			T retObj = null;
			if (!TextUtils.isEmpty(objStr)) {
				XStream xs = new XStream();
				xs.alias(clazz.getSimpleName().toLowerCase(), clazz);
				xs.processAnnotations(clazz); //如果有注解则应用
				if (null != aliasField && aliasField.size() > 0) {
					for (Iterator<String> it = aliasField.keySet().iterator(); it.hasNext();) {
						String key = it.next().toString();
						xs.aliasField(key, clazz, aliasField.get(key));
					}
				}
				Object obj = xs.fromXML(objStr);
				if (null != obj)
					retObj = (T) obj;
			}
			return retObj;
		}
		catch (ConversionException ex) {
			throw new DataConvertException(ex);
		}
		catch (CannotResolveClassException ex) {
			throw new BaseSTDException("XML文件的类型节点名与目标对象的类型名不一致", ex);
		}
		catch (ClassCastException ex) {
			throw new BaseSTDException("DataConvert.parseObjectFromXML中类型强制转换错误！", ex);
		}
		catch (Exception ex) {
			throw new BaseSTDException("在XML格式数据转换成StudentPO实体时错误！", ex);
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
	 * 描          述 ：对象序列化为XML字符串
	 * 创建日期  : 2013-6-19
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param <T>
	 * @param obj
	 * @param alias 类型别名集合，主要实现xml节点名与具体对象类型的对应。
	 *              否则解析器不知道xml节点该转换成什么类型。
	 * @return
	 *
	 */
	public static <T> String toXMLString(T obj, Hashtable<String, Class> alias) {
		try {
			String retStr = "";
			if (null != obj) {
				XStream xs = new XStream();
				xs.processAnnotations(obj.getClass());  
				if (null != alias && alias.size() > 0) {
					for (Iterator<String> it = alias.keySet().iterator(); it.hasNext();) {
						String key = it.next().toString();
						xs.alias(key, alias.get(key));
					}
				}
				retStr = xs.toXML(obj);
			}
			return retStr;
		}
		catch (Exception ex) {
			throw new BaseSTDException("在对象序列化为XML字符串时发生错误！", ex);
		}
	}
	
	/**
	 * 
	 * 描          述 ：对象序列化为XML字符串
	 * 创建日期  : 2013-6-19
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param <T>
	 * @return
	 *
	 */
	public static <T> String toXMLString(T obj) {
		try {
			String retStr = "";
			if (null != obj) {
				XStream xs = new XStream();
				xs.processAnnotations(obj.getClass());  
				retStr = xs.toXML(obj);
			}
			return retStr;
		}
		catch (Exception ex) {
			throw new BaseSTDException("在对象序列化为XML字符串时发生错误！", ex);
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
	 * @param objStr json数据流
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
		catch (ConversionException ex) {
			throw new DataConvertException(ex);
		}
		catch (Exception ex) {
			throw new BaseSTDException("在JSON格式数据转换成实体集时错误！", ex);
		}
	}

	/**
	 * 
	 * 描          述 ：将XML格式的字符流转换成目标类型的集合对象
	 * 创建日期  : 2013-6-19
	 * 作           者 ： lx
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStrs xml数据流
	 * @param clazz 集合中元素类型
	 * @param alias 类型别名集合，主要实现xml节点名与具体对象类型的对应。
	 *              否则解析器不知道xml节点该转换成什么类型。
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> parseArrayFromXML(String objStrs, Class<T> clazz, Hashtable<String, Class> alias) {
		try {
			List<T> retArray = null;
			if (!TextUtils.isEmpty(objStrs)) {
				XStream xs = new XStream();
				xs.processAnnotations(clazz);  
				if (null != alias && alias.size() > 0) {
					for (Iterator<String> it = alias.keySet().iterator(); it.hasNext();) {
						String key = it.next().toString();
						xs.alias(key, alias.get(key));
					}
				}
				Object obj = xs.fromXML(objStrs);
				if (null != obj)
					retArray = (List<T>) obj;
			}
			return retArray;
		}
		catch (ConversionException ex) {
			throw new DataConvertException(ex);
		}
		catch (CannotResolveClassException ex) {
			throw new BaseSTDException("XML文件的类型节点名与目标对象的类型名不一致", ex);
		}
		catch (ClassCastException ex) {
			throw new BaseSTDException("DataConvert.parseObjectFromXML中类型强制转换错误！", ex);
		}
		catch (Exception ex) {
			throw new BaseSTDException("在XML格式数据转换成实体时错误！", ex);
		}
	}
}
