package com.android.poi.activities;

import com.android.poi.tasks.RegisterTask;
import com.android.poi.utils.Connection;
import com.android.poi.utils.Dialogs;
import com.poi.client.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity{
	
	private TextView loginScreen;
	private Button btnRegister;
	private EditText getUsername;
	private EditText getPassword;
	private EditText getPasswordRep;
	private String username;
	private String password;
	private String passwordRep;
	private Connection con;
	private String message;
	private Dialogs dialogs;
	private static Context context;
	
	//SQLiteDatabase db;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		//Get context of activity
		context = this;
		
		//Get connection configuration from login activity
		con = (Connection) getIntent().getSerializableExtra("Connection");
		
		//Get dialogs from login activity
		//dialogs = (Dialogs) getIntent().getSerializableExtra("Dialogs");
		dialogs = new Dialogs();
		dialogs.setContext(this);
		
		//Initialize widgets
		initializeWidgets();
		
		//Link to login activity
		loginLink();
		
		//Check registration
		checkRegistration();
	

	}
	
	/**
	 * This function is used to initialize the widgets that we set on layout
	 */
	private void initializeWidgets()
	{
		loginScreen = (TextView) findViewById(R.id.link_to_login);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		getUsername = (EditText) findViewById(R.id.reg_username);
		getPassword = (EditText) findViewById(R.id.reg_password);
		getPasswordRep = (EditText) findViewById(R.id.reg_passwordRep);
		
	}
	
	/**
	 * This function is used to check if user pressed the login link
	 */
	private void loginLink()
	{
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
	}
	
	/**
	 * This function is used when register button is pressed to:
	 * 1) check if username and password are valid
	 * 2) check if user is already registered in poi service
	 * 3) proceed to login activity if registration is completed by calling the asynchronous task RegisterTask
	 */
	private void checkRegistration()
	{
		btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	//Get username, password and passwordRep
            	username = getUsername.getText().toString();
            	password = getPassword.getText().toString();
            	passwordRep = getPasswordRep.getText().toString();
            	
            	message = username+"#"+password+"#"+passwordRep;
            
            	
            	//Check if username and password are valid
            	if(username.isEmpty()) {
            		dialogs.setAlert("Wrong Credentials", "Please enter a valid username");
            	}
            	else if(password.isEmpty()) {
            		dialogs.setAlert("Wrong Credentials", "Please enter a valid password");
            	}
            	else if(!password.equals(passwordRep)) {
            		dialogs.setAlert("Wrong Credentials", "Passwords do not match");
            	}
            	else {
            	
            		//Try to connect with service
            		RegisterTask register = new RegisterTask(context, con, dialogs, message);
    				register.execute();
            		
            	}
            	
            	
            }
        });
		
		
	}
	
}
