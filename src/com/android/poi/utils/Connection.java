package com.android.poi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.poi.client.R;

import android.content.Context;
import android.util.Log;



/**
 * This class is used to initialize from a property file the variables to set up the connection 
 * to the poi service and it's methods. 
 * 
 *
 */
public class Connection implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String IP;
	private String PORT;
	private String NAMESPACE;
	private String SERVICE_NAME;
	private String REGISTER_METHOD;
	private String LOGIN_METHOD;
	private String SET_METHOD;
	private String GET_METHOD;
	private String SOAP_ACTION;
	private String URL;
	private int time_out;
	
	//We don't want the field context to be serialized
	private transient Context context;
	
	/**
	 * Constructor of Connection class.
	 * time_out is used to set up the connection timeout with web service.
	 */
	public Connection(Context context)
	{
		//Initialize the context from the activity that calls this constructor
		this.context = context;
		
		//50 secs connection time out
		time_out = 50*1000;
	}
	
	/**
	 * Register to poi service
	 * @param message
	 * @return
	 */
	public String register(String message)
	{
		return connect(message, REGISTER_METHOD);
	}
	
	/**
	 * Login to poi service
	 * @param message
	 * @return
	 */
	public String login(String message)
	{
		return connect(message, LOGIN_METHOD);
	}
	
	/**
	 * Insert poi
	 * @param message
	 * @return
	 */
	public String setMonitorData(String message)
	{
		return connect(message, SET_METHOD);
	}
	
	/**
	 * Get poi
	 * @param message
	 * @return
	 */
	public String getMapData(String message)
	{
		return connect(message, GET_METHOD);
	}
	
	/**
	 * Connects to web service to call web method "method"
	 * and to send the message "message"
	 * @param message
	 * @param method
	 * @return
	 */
	public String connect(String message, String method)
	{
		//Initialize soap request + add parameters
        SoapObject request = new SoapObject(NAMESPACE, method);       
       

        //Use this to add parameters
        PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0"; 
		propInfo.setValue(message);
		request.addProperty(propInfo);
       

        //Declare the version of the SOAP request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        Log.i("step", "envelope created successfully");
        
        envelope.setOutputSoapObject(request);
        //envelope.dotNet = true;
       
        try {
        	  
        	  URL = "http://" +IP+ ":" +PORT+ "/" + SERVICE_NAME + "?WSDL" ;
              HttpTransportSE androidHttpTransport = new HttpTransportSE(URL, time_out);
              
              //This is the actual part that will call the webservice
              SOAP_ACTION = "\"" + NAMESPACE + method + "\"";
              androidHttpTransport.call(SOAP_ACTION, envelope);
             
             
              //Get the SoapResult from the envelope body.
              SoapObject result = (SoapObject)envelope.bodyIn;
              String Result = result.getProperty(0).toString();

              return Result;
              
        } catch (Exception e) {
              e.printStackTrace();
        }	
        
		return null;
	}
	
	/**
	 * Load properties from property file "config.properties"
	 */
	public void loadProperties()
	{
		Properties prop = new Properties();
		InputStream rawResource = null;
		
		try 
		{
	 
			rawResource = context.getResources().openRawResource(R.raw.config);
	 
			// load a properties file
			prop.load(rawResource);
	 
			// get the property value and print it out
			IP = prop.getProperty("IP");
			PORT = prop.getProperty("PORT");
			NAMESPACE = prop.getProperty("NAMESPACE");
			SERVICE_NAME = prop.getProperty("SERVICE_NAME");
			REGISTER_METHOD = prop.getProperty("REGISTER_METHOD");
			LOGIN_METHOD = prop.getProperty("LOGIN_METHOD");
			SET_METHOD = prop.getProperty("SET_METHOD");
			GET_METHOD = prop.getProperty("GET_METHOD");
						
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (rawResource != null) {
				try {
					rawResource.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
