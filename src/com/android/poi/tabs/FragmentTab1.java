package com.android.poi.tabs;

import com.poi.client.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Fragment;

public class FragmentTab1 extends Fragment implements OnItemSelectedListener {
	
	Spinner s1,s2;
	TextView view;
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);
        view = (TextView)rootView.findViewById(R.id.fr1);
     
         s1 = (Spinner)rootView.findViewById(R.id.spinner1);
      // Create an ArrayAdapter using the string array and a default spinner layout
      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
              R.array.acc_type, android.R.layout.simple_spinner_item);
      // Specify the layout to use when the list of choices appears
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      // Apply the adapter to the spinner
      s1.setAdapter(adapter);
        return rootView;
    }

	@Override
	 public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
	            long arg3) {
		// TODO Auto-generated method stub
		
	 
	        
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
 
}