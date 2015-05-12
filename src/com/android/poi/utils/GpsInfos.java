package com.android.poi.utils;

/**
 * This class is used to contain informations about location
 * 
 */
public class GpsInfos {

	private double latitude;
    private double longitude;
    private double altitude;
	
	public GpsInfos()
	{
		setLatitude(7.5);
	    setLongitude(0.0);
	    setAltitude(0.0);
	}
	

	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	public double getAltitude() {
		return altitude;
	}



	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

}
