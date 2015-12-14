package com.library.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import com.library.util.LibUtil;

/**
 * �� �� ��SD����ȡ�ļ�
 * �� �� �� �� : 2013-6-24
 * �� �� �� lx
 * �� �� �� �� :
 * �� �� �� ��
 * 
 * @version : 1.0
 */
public class SDCardHelper implements IStorageOperator {
	// �ļ�
	private File mFile;
	// �����
	private BufferedWriter mBufferedWriter;
	private BufferedOutputStream mBufferedOutputStream;
	// ������
	private BufferedReader mBufferedReader;
	private BufferedInputStream mBufferedInputStream;
	// �豸������
	private Context mContext;

	public SDCardHelper(Context context) {
		mContext = context;
	}

	public void openOrCreateFile(String dir, String fileName) {
		mFile = new File(dir, fileName);
		if(!mFile.isFile())//����Ŀ¼
			new File(mFile.getParent()).mkdirs();
		if(!mFile.exists())//����ļ��������򴴽��ļ�
			try {
				mFile.createNewFile();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		open();
	}
	
	private boolean open() {
		// TODO Auto-generated method stub
		try {
			if (mFile == null) {
				return false;
			}
			// �������
			FileOutputStream fileOutputStream = new FileOutputStream(mFile, true);
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
			mBufferedWriter = new BufferedWriter(printWriter);
			mBufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			// ��������
			FileInputStream fileInputStream = new FileInputStream(mFile);
			InputStreamReader inputStrReader = new InputStreamReader(fileInputStream, "UTF-8");
			mBufferedReader = new BufferedReader(inputStrReader);
			mBufferedInputStream = new BufferedInputStream(fileInputStream);
			return true;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("�ļ�������", e);
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("�ļ����벻��֧��", e);
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
			return mFile.length();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("д���ļ�ʱ�����쳣��", e);
		}
	}

	@Override
	public long writeStream(InputStream stream) {
		if (stream == null) {
			return -1;
		}
		try {
			byte[] buffer = new byte[1024];
			while(stream.read(buffer) != -1){
				mBufferedOutputStream.write(buffer);
				mBufferedOutputStream.flush();
			}
			return mFile.length();
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
		// TODO Auto-generated method stub
		try {
			return new FileInputStream(mFile);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("����������ʱ�����쳣", e);
		}
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
			mFile = null;
		}
	}

	public boolean delete(String dir, String name) {
		// TODO Auto-generated method stub
		try {
			File f = new File(dir, name);
			return f.delete();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseSTDException(e);
		}
	}

	/**
	 * 
	 * ��          �� ������ɾ���ļ�/Ŀ¼�����ΪĿ¼��ɾ����Ŀ¼���������ݰ�����Ŀ¼
	 * ��������  : 2014-7-31
	 * ��           �� �� lx
	 * �޸�����  : 
	 * ��   ��   �� ��
	 * @version   : 1.0
	 * @param path Ŀ¼/�ļ�
	 *
	 */
	public void delete(String path) {
		File directory = new File(path);
		if(directory.isFile()){
			directory.delete();
			return;
		}
		File[] children = directory.listFiles();
		if (children.length == 0) {
			directory.delete();
		}
		else {
			for (int i = 0; i < children.length; i++) {
				if (children[i].isFile())
					children[i].delete();
				else if (children[i].isDirectory())
					 delete(children[i].getPath());
			}
			directory.delete();
		}
	}

	@Override
	public long getAvailableSize() {
		// TODO Auto-generated method stub
		StatFs fs = new StatFs(LibUtil.getSdCardRootDirectory());
		int blockSize = fs.getBlockSize();
		int avalableBlock = fs.getAvailableBlocks();
		return new BigDecimal(blockSize).multiply(new BigDecimal(avalableBlock)).longValue();
	}

	@Override
	public boolean saveObject(Serializable obj, String fullName) {
		// TODO Auto-generated method stub
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = mContext.openFileOutput(fullName, Context.MODE_PRIVATE);
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
				fos.close();
			}
			catch (Exception e) {
			}
		}
	}

	@Override
	public Object getObject(String fullName) {
		// TODO Auto-generated method stub
		FileInputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			inputStream = new FileInputStream(fullName);
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
			if (e instanceof InvalidClassException) {
				File data = mContext.getFileStreamPath(fullName);
				data.delete();
			}
		}
		finally {
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
