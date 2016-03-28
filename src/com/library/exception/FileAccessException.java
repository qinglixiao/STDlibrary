package com.library.exception;

public class FileAccessException extends BaseSTDException{
	private static final long serialVersionUID = 1L;
	

	{
		this.mID = "5";
		this.mMessage = "";
		this.mDescription = "";
	}
	

	public FileAccessException() {
		super();
	}
	

	public FileAccessException(String msg) {
		super(msg);
	}



	public FileAccessException(Throwable ex) {
		super(ex);
	}
	

	public FileAccessException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
