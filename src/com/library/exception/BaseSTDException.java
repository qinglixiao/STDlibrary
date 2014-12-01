package com.library.exception;

import android.text.TextUtils;

/**
 * 
 * ��          �� ������Զ��嶥���쳣�࣬�����Զ��쳣����̳������
 * ��������  : 2013-6-8
 * ��           �� �� ���
 * �޸�����  : (�ļ����޸�����)
 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
 * @version   : 1.0
 */
public  class BaseSTDException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/*�쳣���:��ʽ--�����_��ţ���Ÿ�ʽ��0--01--001-0001����������*/
	protected  String mID="0";

	/*�쳣�Ѻ���ʾ--��Ҫ����Ϣ��ʾ��ʹ��*/
	protected  String mMessage="";
	
	/*�쳣����--��Ҫ�ǶԱ��쳣����ȫ��˵����ͬʱ�����쳣��ԭ�������
	 *������Ҫ������־�ã������ʹ����BUG������ָ����
	 */
	protected  String mDescription="���쳣ûȷ�������壬��Ҫ���쳣��ջ�з���";
	
	/**
	 * 
	 * ��          �� ����ȡ�쳣���
	 * ��������  : 2013-6-8
	 * ��           �� �� ���
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @return    �����쳣�������
	 *
	 */
	public  final String getID(){
		return mID;
	}
	
	/**
	 * 
	 * ��          �� ����ȡ�쳣�������������Ƕ��������쳣ʱ�ɶ����˸����ġ�
	 * ��������  : 2013-6-8
	 * ��           �� �� ���
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @return  �����쳣����
	 *
	 */
	public final String getDescription(){
		return mDescription;
	}
	
	/**
	 * 
	 * ��          �� ����ȡ�쳣�Ѻ���ʾ��Ϣ
	 * ��������  : 2013-6-8
	 * ��           �� �� ���
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
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
	 * ��          �� �����캯��
	 * ��������  : 2013-6-8
	 * ��           �� �� ���
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 *
	 */
	public BaseSTDException(){
		super();
	}
	
	/**
	 * 
	 * ��          �� �����캯��
	 * ��������  : 2013-6-8
	 * ��           �� �� ���
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param msg �Ѻ���ʾ����
	 *
	 */
	public BaseSTDException(String msg){
		super(msg);
	}
	
	/**
	 * 
	 * ��          �� �����캯��
	 * ��������  : 2013-6-8
	 * ��           �� �� ���
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param source �쳣��Դ:����+����+������
	 * @param ex ת���쳣ǰ��ԭʼ�쳣
	 *
	 */
	public BaseSTDException(Throwable ex){
		super(ex);
	}
	
	/**
	 * 
	 * ��          �� �����캯��
	 * ��������  : 2013-6-8
	 * ��           �� �� ���
	 * �޸�����  : (�ļ����޸�����)
	 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
	 * @version   : 1.0
	 * @param msg �Ѻ���ʾ��Ϣ
	 * @param ex ת���쳣ǰ��ԭʼ�쳣
	 *
	 */
	public BaseSTDException(String msg,Throwable ex){
		super(msg,ex);
	}
}
