package com.library.file;

import java.io.InputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.os.Environment;

public class StorageHelper implements IStorageOperator {
	// 文件操作具体类
	private IStorageOperator mOperator;
	// 日志记录器
	private static Logger mloger = null;

	private StorageHelper() {

	}

	/**
	 * 描 述 ：创建文件操作实体类
	 * 创 建 日 期 : 2013-6-24
	 * 作 者 ： lx
	 * 修 改 日 期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 */
	public static StorageHelper createInstance(Context context) {
		StorageHelper helper = new StorageHelper();
		//有SD卡则优先存储到卡里
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			helper.mOperator = new SDCardHelper(context);
		else
			//无卡则直接存入内存
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
			mloger.info("文件操作类为空");
			return false;
		}
		return mOperator.close();
	}

	@Override
	public long getAvailableSize() {
		// TODO Auto-generated method stub
		if (mOperator == null) {
			mloger.info("文件操作类为空");
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
