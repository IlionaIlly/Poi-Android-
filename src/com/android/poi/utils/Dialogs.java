package com.android.poi.utils;

import java.io.Serializable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialogs implements Serializable {
	

	private static final long serialVersionUID = 2L;	
	private AlertDialog alertDialog;
	
	//We don't want the field context to be serialized
	private transient Context context;


	public Dialogs()
	{
	}
	

	/**
	 * This function is used to display alerts to user
	 * @param title
	 * @param message
	 */
	public void setAlert(String title, String message) {
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
 
		//set title
		alertDialogBuilder.setTitle(title);
 
		//set dialog message
		alertDialogBuilder
			.setMessage(message)
			.setCancelable(false)
			.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			});
		
		//create alert dialog
		alertDialog = alertDialogBuilder.create();
 
		//show it
		alertDialog.show();
	}
	
	public void dismissAlert()
	{
		alertDialog.dismiss();
	}
	
	public void setContext(Context context)
	{
		this.context = context;
	}

}
