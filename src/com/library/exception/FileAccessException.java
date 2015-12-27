package com.library.exception;
/**
 * 
 * ��          �� ���ļ���ȡ�쳣��
 * �� �� �� ��  : 2013-6-24
 * ��          �� �� lx
 * �� �� �� ��  : 
 * ��   ��   �� ��
 * @version   : 1.0
 */
public class FileAccessException extends BaseSTDException{
	private static final long serialVersionUID = 1L;
	
	/**
	 * ��ʼ���飬��ʼ���Զ����쳣������Ϣ
	 */
	{
		this.mID = "5";
		this.mMessage = "�ļ���ȡ�쳣��";
		this.mDescription = "·�����޴��ļ����޸��ļ��ķ���Ȩ��";
	}
	
	/**
	 * 
	 * ��          �� ���޲ι���
	 * �� �� �� ��  : 2013-6-25
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 *
	 */
	public FileAccessException() {
		super();
	}
	
	/**
	 * 
	 * ��          �� ���вι���
	 * �� �� �� ��  : 2013-6-25
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param msg �û���ʾ�Ѻ���Ϣ
	 *
	 */
	public FileAccessException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * ��          �� ���вι���
	 * �� �� �� ��  : 2013-6-25
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param ex ϵͳ�쳣�׳�����
	 *
	 */
	public FileAccessException(Throwable ex) {
		super(ex);
	}
	
	/**
	 * 
	 * ��          �� ���вι���
	 * �� �� �� ��  : 2013-6-25
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param msg �û���ʾ�Ѻ���Ϣ
	 * @param ex ϵͳ�쳣�׳�����
	 *
	 */
	public FileAccessException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
