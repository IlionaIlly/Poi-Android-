package com.android.poi.tasks;

import com.android.poi.utils.Connection;
import com.android.poi.utils.Dialogs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class LoginTask extends AsyncTask<Void, Integer, String> {
	
	private Connection con;
	private Dialogs dialogs;
	private String message;
	private Context context;
	
	public LoginTask(Context context, Connection con, Dialogs dialogs, String message)
	{
		this.con = con;
		this.dialogs = dialogs;
		this.message = message;
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {		 
		 super.onPreExecute();			
	}
	
	@Override
	protected String doInBackground(Void... params) {			
		
		return con.login(message);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);					
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);	
		
		//Check if user is register to poi service
		if(result != null && !result.equals("false"))
		{
    	
        	/*If username and password are valid and the user is register to poi service
        	then proceed to the next functionality */
			dialogs.setAlert("Login Succeed", "Welcome to Poi");

        	//Switching to Tabs screen
        	Intent openTab = new Intent("android.intent.action.TABS");
        	context.startActivity(openTab);
		}
		else
		{
			dialogs.setAlert("Login Failed", "Try again or register");
			
		}
		
	}

}
