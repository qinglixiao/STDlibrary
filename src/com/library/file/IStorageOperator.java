package com.library.file;

import java.io.InputStream;
import java.io.Serializable;


public interface IStorageOperator {
	
	/**
	 * 
	 * 描          述 ：写入字符串
	 * 创 建 日 期  : 2013-6-24
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param str 写入后的文件长度
	 * @return
	 *
	 */
	long writeString(String str);
	/**
	 * 
	 * 描          述 ：写入数据流
	 * 创 建 日 期  : 2013-6-24
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param stream
	 * @return
	 *
	 */
	long writeStream(InputStream stream);
	/**
	 * 
	 * 描          述 ：读取字符串
	 * 创 建 日 期  : 2013-6-24
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	String readString();
	/**
	 * 
	 * 描          述 ：读取数据流
	 * 创 建 日 期  : 2013-6-24
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	InputStream readStream();
	/**
	 * 
	 * 描          述 ：关闭文件
	 * 创 建 日 期  : 2013-6-24
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	boolean close();
	
	/**
	 * 
	 * 描          述 ：获取设备可用字节数
	 * 创 建 日 期  : 2013-6-24
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	long getAvailableSize();
	
	boolean saveObject(Serializable obj,String key);
	
	Object getObject(String key);
}
