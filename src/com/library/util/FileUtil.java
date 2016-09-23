package com.library.util;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by gfy on 2016/3/21.
 */
public class FileUtil {
    /**
     * copy file
     *
     * @param src  source file
     * @param dest target file
     * @throws IOException
     */
    public static void copyFile(File src, File dest) throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dest).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    /**
     * 描          述 ：级联删除目录下的所有文件，如果为文件则直接删除
     * @param file 目录/文件
     * @param isContain 是否删除到传入的目录级别（true: 如传入 /sdcard/delivety/sp,则会将sp下的所有内容，及sp目录一并删除）
     * @version : 1.0
     */
    public static void delete(String file,boolean isContain) {
        if(TextUtils.isEmpty(file)){
            return;
        }
        File directory = new File(file);
        if(!directory.exists()){
            return;
        }
        if (directory.isFile()) {
            directory.delete();
            return;
        }
        File[] children = directory.listFiles();
        for (int i = 0; i < children.length; i++) {
            if (children[i].isDirectory()) {
                delete(children[i].getPath(),isContain);
            }
            children[i].delete();
        }
        if(isContain){
            directory.delete();
        }
    }

    /**
     * 描          述 ：级联删除目录下的所有文件，如果为文件则直接删除
     * @version : 1.0
     */
    public static void delete(File file,boolean isContain) {
        if (file != null) {
            delete(file.getAbsolutePath(),isContain);
        }
    }

}
