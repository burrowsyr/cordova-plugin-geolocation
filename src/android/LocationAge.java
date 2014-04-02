package org.apache.cordova.geolocation;

import android.annotation.TargetApi;
import android.location.Location;
import android.os.Build;
import android.os.SystemClock;

public final class LocationAge {

	private final Location location;

	public LocationAge(Location location) {
		this.location = location;
	}

	public long milliseconds() {
		if(location==null)return Long.MAX_VALUE;
		return age(location);
	}
	
	private long age(Location last) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
			return age_api_17(last);
		return age_api_pre_17(last);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private long age_api_17(Location last) {
		return (SystemClock.elapsedRealtimeNanos() - last
				.getElapsedRealtimeNanos()) / 1000000;
	}

	private long age_api_pre_17(Location last) {
		long localUTCTime = System.currentTimeMillis();
		long lastTimeUTC = last.getTime();
		return localUTCTime - lastTimeUTC;
	}

}
