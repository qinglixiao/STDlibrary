package com.library.exception;

public class DataConvertException extends BaseSTDException {
	
	private static final long serialVersionUID = 1L;
	
	{
		this.mID="1";
		this.mMessage="";
		this.mDescription="";
	}


	public DataConvertException(){
		super();
	}
	
	public DataConvertException(String msg){
		super(msg);
	}
	
	public DataConvertException(Throwable ex){
		super(ex);
	}
	
	public DataConvertException(String msg,Throwable ex){
		super(msg,ex);
	}

}
