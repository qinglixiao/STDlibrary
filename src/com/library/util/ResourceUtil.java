package com.library.util;

import android.graphics.drawable.Drawable;

import com.std.framework.activity.App;

/**
 * 项目资源工具类
 * Created by gfy on 2016/4/7.
 */
public class ResourceUtil {

    /**
     * 根据图像名称获取drawable
     *
     * @param drawableName
     * @return
     */
    public static Drawable getDrawable(String drawableName) {
        int id = getDrawableId(drawableName);
        if (id == -1)
            return null;
        else
            return App.instance.getResources().getDrawable(id);
    }

    /**
     * 获取string
     *
     * @param stringName
     * @return
     */
    public static String getString(String stringName) {
        int id = getStringId(stringName);
        if (id == -1)
            return "";
        else
            return App.instance.getResources().getString(id);
    }

    /**
     * 获取颜色
     *
     * @param colorName
     * @return
     */
    public static int getColor(String colorName) {
        int id = getColorId(colorName);
        if (id == -1)
            return -1;
        else
            return App.instance.getResources().getColor(id);
    }

    /**
     * 获取给定颜色对应的id
     *
     * @param colorName
     * @return
     */
    public static int getColorId(String colorName) {
        try {
            return App.instance.getResources().getIdentifier(colorName, "color", App.instance.getPackageName());
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 获取给定字符串对应的id
     *
     * @param stringName
     * @return
     */
    public static int getStringId(String stringName) {
        try {
            return App.instance.getResources().getIdentifier(stringName, "string", App.instance.getPackageName());
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 获取给定图像对应的id
     *
     * @param drawableName
     * @return
     */
    public static int getDrawableId(String drawableName) {
        try {
            return App.instance.getResources().getIdentifier(drawableName, "drawable", App.instance.getPackageName());
        } catch (Exception ex) {
            return -1;
        }
    }

}
