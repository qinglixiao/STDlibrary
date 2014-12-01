package com.library.comm;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.Toast;

public class LocationHelper {
	// ����GPS�ľ�ȷ��λ
	public static final int GPS = 0;
	// ��������Ĵ��Զ�λ
	public static final int NETWORK = 1;
	// �Զ�ѡ���ȷ��λ
	public static final int BEST = 2;
	// ��С�ƶ��������λ��
	private static final long MIS_DISTANCE_UPDATE = 0;
	// ����ʱ�����λ��
	private static final long MIS_TIME_UPDATE = 0;
	// λ�ø��ıȽ�ʱ�䣬Ĭ��Ϊ1����
	private static final long COMPARE_TIME = 1000 * 60;

	private Context mContext;
	private LocationManager mLocationManager;
	private Location mCurrentLocation;

	private onLocationChangedListener onLocationChangedListener;

	public LocationHelper(Context context) {
		mContext = context;
		mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	/**
	 * 
	 * �� �� ������λ�ø��¼���
	 * �������� : 2013-12-31
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * @param listener
	 * 
	 */
	public void setOnLocationChangedListener(onLocationChangedListener listener) {
		onLocationChangedListener = listener;
		if (listener != null) {
			registNetWorkListener();
			registGPSListener();
		}
		else{
			unRegistNetWorkListener();
			unRegistGPSListener();
		}
			
	}

	/**
	 * 
	 * �� �� ��ע��GPSλ�ø��¼���
	 * �������� : 2013-12-31
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * 
	 */
	private void registGPSListener() {
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIS_DISTANCE_UPDATE, MIS_TIME_UPDATE, gpsListener);
	}

	/**
	 * 
	 * �� �� ��ע�����綨λλ�ø��¼���
	 * �������� : 2013-12-31
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * 
	 */
	private void registNetWorkListener() {
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIS_DISTANCE_UPDATE, MIS_TIME_UPDATE, netListener);
	}

	/**
	 * 
	 * �� �� ��ж��GPSλ�ø��¼���
	 * �������� : 2013-12-31
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * 
	 */
	private void unRegistGPSListener() {
		mLocationManager.removeUpdates(gpsListener);
	}

	/**
	 * 
	 * �� �� ��ж�����綨λ����λ�ü���
	 * �������� : 2013-12-31
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * 
	 */
	private void unRegistNetWorkListener() {
		mLocationManager.removeUpdates(netListener);
	}

	/**
	 * 
	 * �� �� ����ȡ��ǰλ��
	 * �������� : 2013-12-31
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * @param mode ��λ��ʽ
	 *                LocationHelper.GPS
	 *                LocationHelper.NETWORK
	 *                LocationHelper.BEST
	 * 
	 * @return
	 *         �ɹ�����ǰλ��
	 *         ʧ�ܣ�null
	 * 
	 */
	public Location getLocation(int mode) {
		switch (mode) {
			case GPS:
				return mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			case NETWORK:
				return mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			case BEST:
				if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
					return mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				else if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
					return mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}

		return null;
	}

	public boolean isBetterLocation(Location location) {
		if (mCurrentLocation == null && location != null) {
			// A new location is always better than no location
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - mCurrentLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > COMPARE_TIME;
		boolean isSignificantlyOlder = timeDelta < -COMPARE_TIME;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be worse
		}
		else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - mCurrentLocation.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(), mCurrentLocation.getProvider());

		// Determine location quality using a combination of timeliness and accuracy
		if (isMoreAccurate) {
			return true;
		}
		else if (isNewer && isLessAccurate) {
			return true;
		}
		else if (isNewer && isSignificantlyLessAccurate && isFromSameProvider) {
			return true;
		}
		return false;

	}

	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

	private LocationListener gpsListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			if (isBetterLocation(location)) {
				mCurrentLocation = location;
				onLocationChangedListener.onLocationChanged(location);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			registNetWorkListener();
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			unRegistNetWorkListener();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			if (LocationProvider.OUT_OF_SERVICE == status) { // GPS����ʧ,�л������綨λ
				registNetWorkListener();
			}
			else if (LocationProvider.AVAILABLE == status) {
				unRegistNetWorkListener();
			}
		}

	};

	private LocationListener netListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText(mContext, provider + "_enabled", 1).show();
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText(mContext, provider + "_disabled", 1).show();
		}

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			if (isBetterLocation(location)) {
				mCurrentLocation = location;
				onLocationChangedListener.onLocationChanged(mCurrentLocation);
			}
		}
	};

	public interface onLocationChangedListener {
		void onLocationChanged(Location location);
	}
}
