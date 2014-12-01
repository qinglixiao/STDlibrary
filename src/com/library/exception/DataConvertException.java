package com.library.exception;

/**
 * 
 * 描          述 ：对本文件的详细描述，原则上不能少于50字
 * 创建日期  : 2013-6-8
 * 作           者 ： 申金
 * 修改日期  : (文件的修改日期)
 * 修   改   者 ：(文件的修改者，文件创建者之外的人)
 * @version   : 1.0
 */
public class DataConvertException extends BaseSTDException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化块，初始化自定义异常基本信息
	 */
	{
		this.mID="1";
		this.mMessage="数据转换错误！";
		this.mDescription="XML、JSON数据流与Java实体对象相互转化时错误，通常是XML,JSON格式不正确！";
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
	public DataConvertException(){
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
	public DataConvertException(String msg){
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
	public DataConvertException(Throwable ex){
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
	public DataConvertException(String msg,Throwable ex){
		super(msg,ex);
	}

}
