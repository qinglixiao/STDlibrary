package com.library.exception;
/**
 * 
 * 描          述 ：文件存取异常类
 * 创 建 日 期  : 2013-6-24
 * 作          者 ： lx
 * 修 改 日 期  : 
 * 修   改   者 ：
 * @version   : 1.0
 */
public class FileAccessException extends BaseSTDException{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化块，初始化自定义异常基本信息
	 */
	{
		this.mID = "5";
		this.mMessage = "文件存取异常！";
		this.mDescription = "路径下无此文件或无该文件的访问权限";
	}
	
	/**
	 * 
	 * 描          述 ：无参构造
	 * 创 建 日 期  : 2013-6-25
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 *
	 */
	public FileAccessException() {
		super();
	}
	
	/**
	 * 
	 * 描          述 ：有参构造
	 * 创 建 日 期  : 2013-6-25
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param msg 用户提示友好信息
	 *
	 */
	public FileAccessException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * 描          述 ：有参构造
	 * 创 建 日 期  : 2013-6-25
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param ex 系统异常抛出对象
	 *
	 */
	public FileAccessException(Throwable ex) {
		super(ex);
	}
	
	/**
	 * 
	 * 描          述 ：有参构造
	 * 创 建 日 期  : 2013-6-25
	 * 作          者 ： lx
	 * 修 改 日 期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param msg 用户提示友好信息
	 * @param ex 系统异常抛出对象
	 *
	 */
	public FileAccessException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
