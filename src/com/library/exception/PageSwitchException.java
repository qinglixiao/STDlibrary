package com.library.exception;

/**
 * 描 述 ：页面跳转异常类
 * 创 建 日 期 : 2013-6-21
 * 作 者 ： lx
 * 修 改 日 期 :
 * 修 改 者 ：
 * @version : 1.0
 */
public class PageSwitchException extends BaseSTDException {
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化块，初始化自定义异常基本信息
	 */
	{
		this.mID = "4";
		this.mMessage = "页面跳转异常！";
		this.mDescription = "页面跳转过程中出现异常，异常导致原因可能是用户传递参数不正确,或者引用了不存在的资源文件";
	}

	/**
	 * 描 述 ：无参构造
	 * 创 建 日 期 : 2013-6-21
	 * 作 者 ： lx
	 * 修 改 日 期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 */
	public PageSwitchException() {
		super();
	}

	/**
	 * 描 述 ：有参构造
	 * 创 建 日 期 : 2013-6-21
	 * 作 者 ： lx
	 * 修 改 日 期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param msg 用户提示友好信息
	 */
	public PageSwitchException(String msg) {
		super(msg);
	}

	/**
	 * 描 述 ：有参构造
	 * 创 建 日 期 : 2013-6-21
	 * 作 者 ： lx
	 * 修 改 日 期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param ex 系统异常抛出对象
	 */
	public PageSwitchException(Throwable ex) {
		super(ex);
	}

	/**
	 * 描 述 ：有参构造
	 * 创 建 日 期 : 2013-6-21
	 * 作 者 ： lx
	 * 修 改 日 期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param msg 用户提示友好信息
	 * @param ex 系统异常抛出对象
	 */
	public PageSwitchException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
