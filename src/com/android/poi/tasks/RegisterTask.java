package com.android.poi.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.android.poi.utils.Connection;
import com.android.poi.utils.Dialogs;

public class RegisterTask extends AsyncTask<Void, Integer, String>{
	
	private Connection con;
	private Dialogs dialogs;
	private String message;
	private Context context;
	
	public RegisterTask(Context context, Connection con, Dialogs dialogs, String message)
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
		
		return con.register(message);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);					
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);	
		
		//Check if user is already registered in poi service
		if(result != null)
		{
			if(result.equals("ERROR: Already registered"))
			{
				dialogs.setAlert("Registration Failed", "Already registered");
			}
			else if(result.equals("ERROR: Database insert failed"))
			{
				dialogs.setAlert("Registration Failed", "Database insert failed");
			}
			else
			{
				dialogs.setAlert("Registration Succeed", "Please login");
				
				//Proceed to login activity if registration is completed
				Intent openLogin = new Intent("android.intent.action.LOGIN");
                context.startActivity(openLogin);
			}
		}
		else
		{
			dialogs.setAlert("Registration Failed", "Please try again");
		}
		
	}


}
