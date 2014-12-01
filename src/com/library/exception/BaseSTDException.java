package com.library.exception;

import android.text.TextUtils;

/**
 * 
 * 描          述 ：框架自定义顶级异常类，其它自定异常必须继承这个类
 * 创建日期  : 2013-6-8
 * 作           者 ： 申金
 * 修改日期  : (文件的修改日期)
 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
 * @version   : 1.0
 */
public  class BaseSTDException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/*异常编号:格式--组件名_编号；编号格式：0--01--001-0001，依次类推*/
	protected  String mID="0";

	/*异常友好提示--主要在消息提示中使用*/
	protected  String mMessage="";
	
	/*异常描述--主要是对本异常进行全面说明，同时给出异常的原因或处理方案
	 *作用主要是做日志用，给框架使用者BUG处理做指导。
	 */
	protected  String mDescription="本异常没确定的意义，需要从异常堆栈中分析";
	
	/**
	 * 
	 * 描          述 ：获取异常编号
	 * 创建日期  : 2013-6-8
	 * 作           者 ： 申金
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @return    返回异常编号内容
	 *
	 */
	public  final String getID(){
		return mID;
	}
	
	/**
	 * 
	 * 描          述 ：获取异常描述，本描述是定义子类异常时由定义人给定的。
	 * 创建日期  : 2013-6-8
	 * 作           者 ： 申金
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @return  返回异常描述
	 *
	 */
	public final String getDescription(){
		return mDescription;
	}
	
	/**
	 * 
	 * 描          述 ：获取异常友好提示信息
	 * 创建日期  : 2013-6-8
	 * 作           者 ： 申金
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @return
	 *
	 */
	public  final String getMessage() {
		if(TextUtils.isEmpty(mMessage))
			return super.getMessage();
		else 
			return mMessage;
	}
	
	/**
	 * 
	 * 描          述 ：构造函数
	 * 创建日期  : 2013-6-8
	 * 作           者 ： 申金
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 *
	 */
	public BaseSTDException(){
		super();
	}
	
	/**
	 * 
	 * 描          述 ：构造函数
	 * 创建日期  : 2013-6-8
	 * 作           者 ： 申金
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param msg 友好提示内容
	 *
	 */
	public BaseSTDException(String msg){
		super(msg);
	}
	
	/**
	 * 
	 * 描          述 ：构造函数
	 * 创建日期  : 2013-6-8
	 * 作           者 ： 申金
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param source 异常来源:包名+类名+方法名
	 * @param ex 转义异常前的原始异常
	 *
	 */
	public BaseSTDException(Throwable ex){
		super(ex);
	}
	
	/**
	 * 
	 * 描          述 ：构造函数
	 * 创建日期  : 2013-6-8
	 * 作           者 ： 申金
	 * 修改日期  : (文件的修改日期)
	 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
	 * @version   : 1.0
	 * @param msg 友好提示信息
	 * @param ex 转义异常前的原始异常
	 *
	 */
	public BaseSTDException(String msg,Throwable ex){
		super(msg,ex);
	}
}
