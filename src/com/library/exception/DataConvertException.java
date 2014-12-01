package com.library.exception;

/**
 * 
 * ��          �� ���Ա��ļ�����ϸ������ԭ���ϲ�������50��
 * ��������  : 2013-6-8
 * ��           �� �� ���
 * �޸�����  : (�ļ����޸�����)
 * ��   ��   �� ��(�ļ����޸��ߣ��ļ�������֮�����)
 * @version   : 1.0
 */
public class DataConvertException extends BaseSTDException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ��ʼ���飬��ʼ���Զ����쳣������Ϣ
	 */
	{
		this.mID="1";
		this.mMessage="����ת������";
		this.mDescription="XML��JSON��������Javaʵ������໥ת��ʱ����ͨ����XML,JSON��ʽ����ȷ��";
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
	public DataConvertException(){
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
	public DataConvertException(String msg){
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
	public DataConvertException(Throwable ex){
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
	public DataConvertException(String msg,Throwable ex){
		super(msg,ex);
	}

}
