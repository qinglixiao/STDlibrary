package com.library.exception;

/**
 * �� �� ��ҳ����ת�쳣��
 * �� �� �� �� : 2013-6-21
 * �� �� �� lx
 * �� �� �� �� :
 * �� �� �� ��
 * @version : 1.0
 */
public class PageSwitchException extends BaseSTDException {
	private static final long serialVersionUID = 1L;

	/**
	 * ��ʼ���飬��ʼ���Զ����쳣������Ϣ
	 */
	{
		this.mID = "4";
		this.mMessage = "ҳ����ת�쳣��";
		this.mDescription = "ҳ����ת�����г����쳣���쳣����ԭ��������û����ݲ�������ȷ,���������˲����ڵ���Դ�ļ�";
	}

	/**
	 * �� �� ���޲ι���
	 * �� �� �� �� : 2013-6-21
	 * �� �� �� lx
	 * �� �� �� �� :
	 * �� �� �� ��
	 * @version : 1.0
	 */
	public PageSwitchException() {
		super();
	}

	/**
	 * �� �� ���вι���
	 * �� �� �� �� : 2013-6-21
	 * �� �� �� lx
	 * �� �� �� �� :
	 * �� �� �� ��
	 * @version : 1.0
	 * @param msg �û���ʾ�Ѻ���Ϣ
	 */
	public PageSwitchException(String msg) {
		super(msg);
	}

	/**
	 * �� �� ���вι���
	 * �� �� �� �� : 2013-6-21
	 * �� �� �� lx
	 * �� �� �� �� :
	 * �� �� �� ��
	 * @version : 1.0
	 * @param ex ϵͳ�쳣�׳�����
	 */
	public PageSwitchException(Throwable ex) {
		super(ex);
	}

	/**
	 * �� �� ���вι���
	 * �� �� �� �� : 2013-6-21
	 * �� �� �� lx
	 * �� �� �� �� :
	 * �� �� �� ��
	 * @version : 1.0
	 * @param msg �û���ʾ�Ѻ���Ϣ
	 * @param ex ϵͳ�쳣�׳�����
	 */
	public PageSwitchException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
