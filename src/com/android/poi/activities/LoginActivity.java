package com.android.poi.activities;

import com.android.poi.tasks.LoginTask;
import com.android.poi.utils.Connection;
import com.android.poi.utils.Dialogs;
import com.poi.client.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private Button btnLogin;
	private TextView registerScreen;
	private EditText getUsername;
	private EditText getPassword;
	private String username;
	private String password;
	private Connection con;
	private String message;
	private Dialogs dialogs;
	private static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		//Get context of activity
		context = this;
		
		//Create an object Connection that contains all the methods to call web methods of poi service
		con = new Connection(this);
						
		//Load properties to the object Connection
		con.loadProperties();
						
		//Create dialogs
		dialogs = new Dialogs();
		dialogs.setContext(this);
		
		
		//Initialize widgets
		initializeWidgets();
		
		//Link to registration activity
		registrationLink();
		
		//Proceed to login
		checkLogin();    
		
	}
	


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		dialogs.dismissAlert();
	}




	/**
	 * This function is used to initialize the widgets that we set on layout
	 */
	private void initializeWidgets()
	{
		registerScreen = (TextView) findViewById(R.id.link_to_register);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		getUsername = (EditText) findViewById(R.id.log_username);
		getPassword = (EditText) findViewById(R.id.log_password);
	}
	
	/**
	 * This function is used to check if user pressed the registration link
	 */
	private void registrationLink()
	{
		//Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
                //Switching to Register screen
                Intent openRegister = new Intent("android.intent.action.REGISTER");
                
                //Pass objects Connection and Dialogs to the next activity
                openRegister.putExtra("Connection", con);
                openRegister.putExtra("Dialogs", dialogs);
                
                startActivity(openRegister);
            }
        });
		
	}
	
	/**
	 * This function is used when the login button is pressed to:
	 * 1) check if username and password are valid
	 * 2) check if user is register to poi service
	 * 3) proceed to the next functionality of poi application if user is register to poi service
	 * 	  by calling the asynchronous task LoginTask
	 */
	private void checkLogin()
	{
		
		//Listening to login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	//Get username and password
        		username = getUsername.getText().toString();
        		password = getPassword.getText().toString();
        		
        		//Set message to send to web service
        		message = username+"#"+password;	
            	
            	//Check if username and password are valid
            	if(username.isEmpty()) {
            		dialogs.setAlert("Wrong Credentials", "Please enter a valid username");
            	}
            	else if(password.isEmpty()) {
            		dialogs.setAlert("Wrong Credentials", "Please enter a valid password");
            	}
            	else {
            		
            		//Try to connect with service and if the connection is successful go to tabs activity
            		LoginTask login = new LoginTask(context, con, dialogs, message);
    				login.execute();
    				
    				
            	}
          }                       
       });
        
	}

}
