package com.library.file;

import java.io.InputStream;
import java.io.Serializable;


public interface IStorageOperator {
	
	/**
	 * 
	 * ��          �� ��д���ַ���
	 * �� �� �� ��  : 2013-6-24
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param str д�����ļ�����
	 * @return
	 *
	 */
	long writeString(String str);
	/**
	 * 
	 * ��          �� ��д��������
	 * �� �� �� ��  : 2013-6-24
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param stream
	 * @return
	 *
	 */
	long writeStream(InputStream stream);
	/**
	 * 
	 * ��          �� ����ȡ�ַ���
	 * �� �� �� ��  : 2013-6-24
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @return
	 *
	 */
	String readString();
	/**
	 * 
	 * ��          �� ����ȡ������
	 * �� �� �� ��  : 2013-6-24
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @return
	 *
	 */
	InputStream readStream();
	/**
	 * 
	 * ��          �� ���ر��ļ�
	 * �� �� �� ��  : 2013-6-24
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @return
	 *
	 */
	boolean close();
	
	/**
	 * 
	 * ��          �� ����ȡ�豸�����ֽ���
	 * �� �� �� ��  : 2013-6-24
	 * ��          �� �� lx
	 * �� �� �� ��  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @return
	 *
	 */
	long getAvailableSize();
	
	boolean saveObject(Serializable obj,String key);
	
	Object getObject(String key);
}
