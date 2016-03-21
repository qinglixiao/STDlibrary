package com.library.util;

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
     *
     * copy file
     *
     * @param src
     *            source file
     * @param dest
     *            target file
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
     * delete file
     *
     * @param file
     *            file
     * @return true if delete success
     */
    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        }
        return file.delete();
    }

    /**
     *
     * 描          述 ：级联删除文件/目录，如果为目录则删除该目录下所有内容包括此目录
     * 创建日期  : 2014-7-31
     * 作           者 ： lx
     * 修改日期  :
     * 修   改   者 ：
     * @version   : 1.0
     * @param path 目录/文件
     *
     */
    public void delete(String path) {
        File directory = new File(path);
        if(directory.isFile()){
            directory.delete();
            return;
        }
        File[] children = directory.listFiles();
        if (children.length == 0) {
            directory.delete();
        }
        else {
            for (int i = 0; i < children.length; i++) {
                if (children[i].isFile())
                    children[i].delete();
                else if (children[i].isDirectory())
                    delete(children[i].getPath());
            }
            directory.delete();
        }
    }

}
