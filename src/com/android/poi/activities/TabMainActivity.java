package com.android.poi.activities;

import com.android.poi.tabs.FragmentTab1;
import com.android.poi.tabs.FragmentTab2;
import com.android.poi.tabs.FragmentTab3;
import com.android.poi.utils.GpsInfos;
import com.android.poi.utils.TabListener;
import com.poi.client.R;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.app.Activity;

public class TabMainActivity extends Activity {
	// Declare Tab Variable
	private ActionBar.Tab Tab1, Tab2, Tab3;
	private Fragment fragmentTab1;
	private Fragment fragmentTab2;
	private Fragment fragmentTab3;
	private GpsInfos gps;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Create an object of GpsInfos
		gps = new GpsInfos();
		
		//Create 3 fragments
		fragmentTab1 = new FragmentTab1();
		fragmentTab2 = new FragmentTab2();
		fragmentTab3 = new FragmentTab3();
		
		ActionBar actionBar = getActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setText("Tab1");
		Tab2 = actionBar.newTab().setText("Tab2");
		Tab3 = actionBar.newTab().setText("Tab3");

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));

		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
	}
}

