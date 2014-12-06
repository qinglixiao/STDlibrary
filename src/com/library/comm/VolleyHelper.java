package com.library.comm;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.library.core.LruBitmapCache;

public class VolleyHelper {
	private static final String TAG = "VolleyHelper";
	private static VolleyHelper mInstance;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private static Context mCtx;

	private VolleyHelper(final Context context) {
		mCtx = context;
		mRequestQueue = getRequestQueue();
		mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
			//			private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
			private final LruCache<String, Bitmap> cache = new LruBitmapCache(context.getApplicationContext());

			@Override
			public Bitmap getBitmap(String url) {
				return cache.get(url);
			}

			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				cache.put(url, bitmap);
			}
		});
	}

	public static synchronized VolleyHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new VolleyHelper(context);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			// getApplicationContext() is key, it keeps you from leaking the
			// Activity or BroadcastReceiver if someone passes one in.
			mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	public void cancelAll(Object requestTag) {
		mRequestQueue.cancelAll(requestTag);
	}

	public void cancelAll(RequestFilter requestFilter) {
		mRequestQueue.cancelAll(requestFilter);
	}

	public void postJson(String url, String requestBody, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(requestBody);
			addToRequestQueue(new JsonObjectRequest(Method.POST, url, jsonObject, listener, errorListener));
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}

	}

	public void getJson(String url, Listener<JSONObject> listener, ErrorListener errorListener) {
		addToRequestQueue(new JsonObjectRequest(Method.GET, url, null, listener, errorListener));
	}
	
	public void getString(String url, Listener<String> listener, ErrorListener errorListener){
		addToRequestQueue(new StringRequest(url, listener, errorListener));
	}

}
