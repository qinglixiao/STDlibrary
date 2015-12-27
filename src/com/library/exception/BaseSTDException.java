package com.library.exception;

import android.text.TextUtils;


public  class BaseSTDException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	protected  String mID="0";

	protected  String mMessage="";
	
	protected  String mDescription="";
	
	public  final String getID(){
		return mID;
	}
	
	public final String getDescription(){
		return mDescription;
	}
	
	public  final String getMessage() {
		if(TextUtils.isEmpty(mMessage))
			return super.getMessage();
		else 
			return mMessage;
	}
	
	public BaseSTDException(){
		super();
	}
	
	public BaseSTDException(String msg){
		super(msg);
	}
	
	public BaseSTDException(Throwable ex){
		super(ex);
	}
	
	public BaseSTDException(String msg,Throwable ex){
		super(msg,ex);
	}
}
