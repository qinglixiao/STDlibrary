package com.library.file;

import java.io.InputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.os.Environment;

public class StorageHelper implements IStorageOperator {
	// �ļ�����������
	private IStorageOperator mOperator;
	// ��־��¼��
	private static Logger mloger = null;

	private StorageHelper() {

	}

	/**
	 * �� �� �������ļ�����ʵ����
	 * �� �� �� �� : 2013-6-24
	 * �� �� �� lx
	 * �� �� �� �� :
	 * �� �� �� ��
	 * @version : 1.0
	 */
	public static StorageHelper createInstance(Context context) {
		StorageHelper helper = new StorageHelper();
		//��SD�������ȴ洢������
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			helper.mOperator = new SDCardHelper(context);
		else
			//�޿���ֱ�Ӵ����ڴ�
			helper.mOperator = new MemoryStorage(context);
		if (mloger == null)
			mloger = LoggerFactory.getLogger(StorageHelper.class);
		return helper;
	}

	
	@Override
	public long writeString(String str) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String readString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public InputStream readStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		if (mOperator == null) {
			mloger.info("�ļ�������Ϊ��");
			return false;
		}
		return mOperator.close();
	}

	@Override
	public long getAvailableSize() {
		// TODO Auto-generated method stub
		if (mOperator == null) {
			mloger.info("�ļ�������Ϊ��");
			return -1;
		}
		return mOperator.getAvailableSize();
	}

	@Override
	public long writeStream(InputStream stream) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean saveObject(Serializable obj, String key) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
