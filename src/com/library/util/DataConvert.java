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
 * ��          �� ������ת��������
 * ʵ��Json��Xml��java������໥ת��
 * ��������  : 2013-6-17
 * ��           �� �� lx
 * �޸�����  : (�ļ����޸�����)
 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
 * @version   : 1.0
 */
public class DataConvert {

	/**
	 * 
	 * ��          �� ����json��ʽ�Ķ�����ת�ɳ�T���Ͷ���
	 * ��������  : 2013-6-18
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStr��json������
	 * @param clazz ��Ŀ���������
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
			throw new BaseSTDException("��JSON��ʽ����ת��ʱ����", ex);
		}
	}

	/**
	 * 
	 * ��          �� ����xml��ʽ�Ķ�����ת�ɳ�T���Ͷ���
	 * ��������  : 2013-6-18
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param <T>
	 * @param clazz ��Ŀ���������
	 * @return
	 *
	 */
	public static <T> T parseObjectFromXML(String xmlString, Class<T> clazz) {
		XStream xStream = new XStream();
		xStream.alias(clazz.getSimpleName().toLowerCase(), clazz);//��xml������������Ӧ����Ӧ��xml�������������һ�µ�����£�
		xStream.processAnnotations(clazz); //�����ע����Ӧ�ã�������Ӵ˾���ע�ⲻ����Ч
		T reobj = (T) xStream.fromXML(xmlString);
		return reobj;
	}

	/**
	 * 
	 * ��          �� ����xml��ʽ�Ķ�����ת����T���Ͷ���
	 * ��������  : 2013-6-19
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStr xml������
	 * @param clazz  Ŀ���������
	 * @param alias  ���ͱ������ϣ���Ҫʵ��xml�ڵ������������������Ķ�Ӧ��<xml�ڵ���������������>
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
				xs.processAnnotations(clazz); //�����ע����Ӧ��
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
			throw new BaseSTDException("XML�ļ������ͽڵ�����Ŀ��������������һ��", ex);
		}
		catch (ClassCastException ex) {
			throw new BaseSTDException("DataConvert.parseObjectFromXML������ǿ��ת������", ex);
		}
		catch (Exception ex) {
			throw new BaseSTDException("��XML��ʽ����ת����StudentPOʵ��ʱ����", ex);
		}
	}

	/**
	 * 
	 * ��          �� ���������л�ΪJSON�ַ���
	 * ��������  : 2013-6-18
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
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
			throw new BaseSTDException("�ڶ������л�ΪJSON�ַ���ʱ��������", ex);
		}
	}

	/**
	 * 
	 * ��          �� ���������л�ΪXML�ַ���
	 * ��������  : 2013-6-19
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param <T>
	 * @param obj
	 * @param alias ���ͱ������ϣ���Ҫʵ��xml�ڵ��������������͵Ķ�Ӧ��
	 *              �����������֪��xml�ڵ��ת����ʲô���͡�
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
			throw new BaseSTDException("�ڶ������л�ΪXML�ַ���ʱ��������", ex);
		}
	}
	
	/**
	 * 
	 * ��          �� ���������л�ΪXML�ַ���
	 * ��������  : 2013-6-19
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
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
			throw new BaseSTDException("�ڶ������л�ΪXML�ַ���ʱ��������", ex);
		}
	}

	/**
	 * 
	 * ��          �� ����JSON��ʽ���ַ���ת����Ŀ�����͵ļ��϶���
	 * ��������  : 2013-6-18
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStr json������
	 * @param clazz ������Ԫ������
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
			throw new BaseSTDException("��JSON��ʽ����ת����ʵ�弯ʱ����", ex);
		}
	}

	/**
	 * 
	 * ��          �� ����XML��ʽ���ַ���ת����Ŀ�����͵ļ��϶���
	 * ��������  : 2013-6-19
	 * ��           �� �� lx
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param <T>
	 * @param objStrs xml������
	 * @param clazz ������Ԫ������
	 * @param alias ���ͱ������ϣ���Ҫʵ��xml�ڵ��������������͵Ķ�Ӧ��
	 *              �����������֪��xml�ڵ��ת����ʲô���͡�
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
			throw new BaseSTDException("XML�ļ������ͽڵ�����Ŀ��������������һ��", ex);
		}
		catch (ClassCastException ex) {
			throw new BaseSTDException("DataConvert.parseObjectFromXML������ǿ��ת������", ex);
		}
		catch (Exception ex) {
			throw new BaseSTDException("��XML��ʽ����ת����ʵ��ʱ����", ex);
		}
	}
}
