package com.android.poi.activities;

import com.poi.client.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {
	
	private Thread timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		timer = new Thread() {
			public void run() {
				try {
					sleep(4000);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent openLogin = new Intent("android.intent.action.LOGIN");
					startActivity(openLogin);
				}
			}
			
		};
		timer.start();
	}


}
