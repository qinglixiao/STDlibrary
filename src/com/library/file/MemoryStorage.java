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
 * 描 述 ：内存存取文件
 * 创 建 日 期 : 2013-6-26
 * 作 者 ： lx
 * 修 改 日 期 :
 * 修 改 者 ：
 * @version : 1.0
 */
public class MemoryStorage implements IStorageOperator {
	// 上下文
	private Context mContext;
	// 输出流
	private FileOutputStream mFileOutputStream;
	// 输入流
	private FileInputStream mFileInputStream;
	// 输出缓冲流
	private BufferedWriter mBufferedWriter;
	// 输入缓冲流
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
			throw new FileAccessException("文件创建失败！", e);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("文件创建失败！", e);
		}

	}

	private boolean open(String name) {
		// TODO Auto-generated method stub
		try {
			// 打开输出流
			mFileOutputStream = mContext.openFileOutput(name, Context.MODE_APPEND|Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(mFileOutputStream, "UTF-8"));
			mBufferedWriter = new BufferedWriter(printWriter);

			// 打开输入流
			mFileInputStream = mContext.openFileInput(name);
			InputStreamReader inputStrReader = new InputStreamReader(mFileInputStream, "UTF-8");
			mBufferedReader = new BufferedReader(inputStrReader);

			return true;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("文件不存在", e);
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new FileAccessException("文件编码异常", e);
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
			throw new FileAccessException("写入文件时出现异常！", e);
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
			// 将输入流转码
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
			throw new FileAccessException("写入失败！", e);
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
			throw new FileAccessException("读取文件时出现异常！", e);
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
			throw new FileAccessException("文件关闭时出现异常", e);
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
			throw new BaseSTDException("文件删除失败！");
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
			//反序列化失败 - 删除缓存文件
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
