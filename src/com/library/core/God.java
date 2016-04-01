package com.library.core;

/**
 * Created by gfy on 2016/3/31.
 */
public class God {
    public static  <T> T love(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
