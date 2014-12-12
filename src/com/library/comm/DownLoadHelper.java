package com.library.comm;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.Notification;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Handler;
import android.view.View;

public class DownLoadHelper {
	private Context contextApp;
	private static DownLoadHelper instance;
	private DownloadManager mDownloadManager;
	public String saveDir;//文件保存目录
	private Handler handler;
	private long downloadId; 
	
	public static DownLoadHelper getInstance(Context context){
		if(instance == null){
			synchronized(DownLoadHelper.class){
				if(instance == null){
					instance = new DownLoadHelper();
					instance.mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
					instance.contextApp = context.getApplicationContext();
				}
			}
		}
		return instance;
	}
	
	public void setHandler(Handler handler){
		this.handler = handler;
	}
	
	public void saveDir(String dir){
		saveDir = dir;
	}
	
	public long down(String uriString,String fileName){
		Uri uri = Uri.parse(uriString);
		registerListener(uri);
		downloadId = mDownloadManager.enqueue(getRequest(uri, fileName));
		return  downloadId;
	}
	
	private void registerListener(Uri uri){
		contextApp.getContentResolver().registerContentObserver(uri, true, new DownLoadObserver(handler,mDownloadManager,downloadId));
	}
	
	private Request getRequest(Uri uri,String fileName){
		Request request = new Request(uri);
		request.setDestinationInExternalPublicDir(saveDir, fileName);
		request.setNotificationVisibility(View.VISIBLE);
		request.setTitle(fileName);
		request.setVisibleInDownloadsUi(true);
		return request;
	}
	
	private class DownLoadObserver extends ContentObserver{
		private Handler handler;
		private DownloadManager downloadManager;
		private long downloadId;
		
		public DownLoadObserver(Handler handler,DownloadManager downloadManager,long downloadId) {
			super(handler);
			this.handler = handler;
			this.downloadManager = downloadManager;
			this.downloadId = downloadId;
		}
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
		        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);  
		        Cursor cursor = mDownloadManager.query(query);  
		        while (cursor.moveToNext()) {  
		            int mDownload_so_far = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));  
		            int mDownload_all = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));  
		            int mProgress = (mDownload_so_far * 99) / mDownload_all;  
		        }  
		}
	}
	
}
