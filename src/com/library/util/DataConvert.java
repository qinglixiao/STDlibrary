package com.library.util;

import com.google.gson.Gson;
import com.library.exception.BaseSTDException;

/**
 * 描          述 ：数据转换工具类
 * 实现Json、Xml与java对象的相互转化
 * 创建日期  : 2013-6-17
 * 作           者 ： lx
 * 修改日期  : (文件的修改日期)
 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
 *
 * @version : 1.0
 */
public class DataConvert {
    private static Gson gson = new Gson();

    /**
     * 描          述 ：将json格式的对象流转成成T类型对象
     * 创建日期  : 2013-6-18
     * 作           者 ： lx
     * 修改日期  : (文件的修改日期)
     * 修   改   者 ：(文件的修改者，文件创建者之外的人)
     *
     * @param <T>
     * @param objStr：json数据流
     * @param clazz          ：目标对象类型
     * @return
     * @version : 1.0
     */
    public static <T> T parseObjectFromJson(String objStr, Class<T> clazz) {
        try {
            return gson.fromJson(objStr, clazz);
        } catch (Exception ex) {
            throw new BaseSTDException("在JSON格式数据转换时错误！", ex);
        }
    }

    /**
     * 描          述 ：对象序列化为JSON字符串
     * 创建日期  : 2013-6-18
     * 作           者 ： lx
     * 修改日期  : (文件的修改日期)
     * 修   改   者 ：(文件的修改者，文件创建者之外的人)
     *
     * @param obj
     * @return
     * @version : 1.0
     */
    public static <T> String toJsonString(T obj) {
        try {
            return gson.toJson(obj);
        } catch (Exception ex) {
            throw new BaseSTDException("在对象序列化为JSON字符串时发生错误！", ex);
        }
    }

}
