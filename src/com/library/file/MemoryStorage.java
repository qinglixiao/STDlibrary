package com.library.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import com.library.exception.BaseSTDException;
import com.library.exception.FileAccessException;

/**
 * �� �� ���ڴ��ȡ�ļ�
 * �� �� �� �� : 2013-6-26
 * �� �� �� lx
 * �� �� �� �� :
 * �� �� �� ��
 * @version : 1.0
 */
public class MemoryStorage implements IStorageOperator {
	// ������
	private Context mContext;
	// �����
	private FileOutputStream mFileOutputStream;
	// ������
	private FileInputStream mFileInputStream;
	// ���������
	private BufferedWriter mBufferedWriter;
	// ���뻺����
	private BufferedReader mBufferedReader;

	public MemoryStorage(Context context) {
		mContext = context;
	}

	public boolean openOrCreateFile(String name) {
		// TODO Auto-generated method stub
		try {
			mFileOutputStream = mContext.openFileOutput(name, Context.MODE_PRIVATE);
			mFileOutputStream.close();
			open(name);
			return true;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("�ļ�����ʧ�ܣ�", e);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("�ļ�����ʧ�ܣ�", e);
		}

	}

	private boolean open(String name) {
		// TODO Auto-generated method stub
		try {
			// �������
			mFileOutputStream = mContext.openFileOutput(name, Context.MODE_APPEND|Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(mFileOutputStream, "UTF-8"));
			mBufferedWriter = new BufferedWriter(printWriter);

			// ��������
			mFileInputStream = mContext.openFileInput(name);
			InputStreamReader inputStrReader = new InputStreamReader(mFileInputStream, "UTF-8");
			mBufferedReader = new BufferedReader(inputStrReader);

			return true;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("�ļ�������", e);
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("�ļ������쳣", e);
		}
	}

	@Override
	public long writeString(String str) {
		// TODO Auto-generated method stub
		if (mBufferedWriter == null) {
			return -1;
		}
		try {
			mBufferedWriter.write(str);
			mBufferedWriter.flush();
			return str.length();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("д���ļ�ʱ�����쳣��", e);
		}
	}

	@Override
	public long writeStream(InputStream stream) {
		// TODO Auto-generated method stub
		if (stream == null) {
			return -1;
		}
		if (mBufferedWriter == null) {
			return -1;
		}
		try {
			// ��������ת��
			InputStreamReader inputStrReader = new InputStreamReader(stream, "UTF-8");
			BufferedReader reader = new BufferedReader(inputStrReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				mBufferedWriter.write(line);
				mBufferedWriter.flush();
			}
			return 1;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("д��ʧ�ܣ�", e);
		}
	}

	@Override
	public String readString() {
		// TODO Auto-generated method stub
		if (mBufferedReader == null) {
			return "";
		}
		try {
			StringBuilder builder = new StringBuilder();
			String line = "";
			while ((line = mBufferedReader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("��ȡ�ļ�ʱ�����쳣��", e);
		}

	}

	@Override
	public InputStream readStream() {
		return mFileInputStream;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		try {
			if (mBufferedReader != null)
				mBufferedReader.close();
			if (mBufferedWriter != null) {
				mBufferedWriter.flush();
				mBufferedWriter.close();
			}
			return true;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("�ļ��ر�ʱ�����쳣", e);
		}
		finally {
			mBufferedReader = null;
			mBufferedWriter = null;
			mFileInputStream = null;
			mFileOutputStream = null;
		}
	}

	public boolean delete(String name) {
		// TODO Auto-generated method stub
		try {
			if (TextUtils.isEmpty(name))
				return false;
			return mContext.deleteFile(name);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseSTDException("�ļ�ɾ��ʧ�ܣ�");
		}
	}

	@Override
	public long getAvailableSize() {
		// TODO Auto-generated method stub
		File root = Environment.getRootDirectory();
		StatFs sf = new StatFs(root.getPath());
		int blockSize = sf.getBlockSize();
		int availCount = sf.getAvailableBlocks();
		return new BigDecimal(blockSize).multiply(new BigDecimal(availCount)).longValue();
	}

	@Override
	public boolean saveObject(Serializable obj, String key) {
		// TODO Auto-generated method stub
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = mContext.openFileOutput(key, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				oos.close();
			}
			catch (Exception e) {
			}
			try {
				fos.close();
			}
			catch (Exception e) {
			}
		}
	}

	@Override
	public Object getObject(String key) {
		// TODO Auto-generated method stub
		FileInputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			inputStream = new FileInputStream(key);
			objectInputStream = new ObjectInputStream(inputStream);
			return (Serializable) objectInputStream.readObject();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�����л�ʧ�� - ɾ�������ļ�
			if(e instanceof InvalidClassException){
				File data = mContext.getFileStreamPath(key);
				data.delete();
			}
		}
		finally{
			try {
				objectInputStream.close();
				inputStream.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
