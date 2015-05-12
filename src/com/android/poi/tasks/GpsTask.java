package com.android.poi.tasks;

import java.util.List;

import com.android.poi.utils.GpsInfos;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;

public class GpsTask extends AsyncTask<Void, Integer, String> implements LocationListener{

	private GpsInfos gps;
	private Context context;
	private String best;
	private Location location;
	private LocationManager locationManager;
	
	
	public GpsTask(Context context, GpsInfos gps)
	{
		this.context = context;
		this.gps = gps;
	}
	
	
	/**********************Start AsynchronousTask's functions *********************/
	@Override
	protected void onPreExecute() {		 
		 super.onPreExecute();			
	}
	
	@Override
	protected String doInBackground(Void... arg0) {
		
		findlocation();

		return null;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);					
	}
	
	@Override
	protected void onPostExecute(String result) {
		//super.onPostExecute(result);	
	}
	
	/**********************End AsynchronousTask's functions *********************/
	
	
	
	/**********************Start LocationListener's functions *********************/
	public void findlocation()
	{
		locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		dumpProviders();
		Criteria criteria = new Criteria();
        best = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(best);
        dumpLocation(location);
	}
	
	private void dumpProviders()
	{

        List<String> providers = locationManager.getAllProviders();
        for (String p : providers) {

            dumpProviders(p);
        }
    }

    private void dumpProviders(String s)
    {

        LocationProvider info = locationManager.getProvider(s);
        StringBuilder builder = new StringBuilder();
        builder.append("name: ").append(info.getName());
    }
    
    private void dumpLocation(Location loc) 
    {
    	double latitude = 0.0;
        double longitude = 0.0;
        double altitude = 0.0;
        if (loc != null)
        {
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
            altitude = loc.getAltitude();
        }
        gps.setLatitude(latitude);
        gps.setLongitude(longitude);
        gps.setAltitude(altitude);
    }

	@Override
	public void onLocationChanged(Location arg0) 
	{
		dumpLocation(location);	
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub	
	}
	/**********************End LocationListener's functions *********************/
	
	
	

}
