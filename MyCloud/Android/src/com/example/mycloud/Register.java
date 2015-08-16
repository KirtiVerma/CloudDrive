package com.example.mycloud;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.app.Activity;
import android.util.Log;

public class Register extends Activity implements Runnable {
	String user, pass, first,last,email;
	//String site_url=getResources().getString(site_url);
	
	Register(String user,String pass,String first,String last,String email)
	{
		//super("mythread");
		this.user=user;
		this.pass=pass;
		this.first=first;
		this.last=last;
		this.email=email;
		
		
	}
	
	public void run(){
		//HttpClient httpclient = new DefaultHttpClient();
		HttpClient httpclient1 = new DefaultHttpClient();
		//HttpPost httppost = new HttpPost("https://webkiosk.jiit.ac.in/CommonFiles/UserAction.jsp");
		/*String CSRFTOKEN = "null";
		try {
			httpclient.execute(new HttpGet("http://172.16.83.177:8000/minor"));
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CookieStore cookieStore = ((AbstractHttpClient) httpclient).getCookieStore();
		List <Cookie> cookies =  cookieStore.getCookies();
		for (Cookie cookie: cookies) {
		    if (cookie.getName().equals("csrftoken")) {
		    	
		        CSRFTOKEN = cookie.getValue();
		        
		        Log.v("working",CSRFTOKEN);
		    }
		}
		*/
		
		Log.v("running","running");
		HttpPost httppost = new HttpPost("http://192.168.63.57:8000/minor/register/");
		//HttpPost httppost = new HttpPost("http://172.16.83.177:8000/minor/my_view/");
		//HttpGet httpget = new HttpGet("http://172.16.82.190:8000/minor/try1");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		//String params="utka";
		nameValuePairs.add(new BasicNameValuePair("username",user));
		nameValuePairs.add(new BasicNameValuePair("password", pass));
		nameValuePairs.add(new BasicNameValuePair("firstname", first));
		nameValuePairs.add(new BasicNameValuePair("lastname", last));
		nameValuePairs.add(new BasicNameValuePair("email", email));
		//Log.v("user",user);
    	//Log.v("pass",pass);
    	//Log.v("first",first);
    	//Log.v("last",last);
    	//Log.v("email",email);
	/*
	 
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("InstCode", "JIIT"));
		nameValuePairs.add(new BasicNameValuePair("UserType", "S"));
		nameValuePairs.add(new BasicNameValuePair("MemberCode", "11503862"));
		nameValuePairs.add(new BasicNameValuePair("DATE1", "22-03-1993"));
		nameValuePairs.add(new BasicNameValuePair("password", "maheshwari@12"));*/
		//httppost.setHeader("User-Agent", USER_AGENT);
		//httppost.setHeader("Accept", 
	      //       "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		//httppost.setHeader("Accept-Language", "en-US,en;q=0.5");
		//httppost.setHeader("Cookie", getCookies());
		//httppost.setHeader("Connection", "keep-alive");
		//httppost.setHeader("Referer", "https://accounts.google.com/ServiceLoginAuth");
		//httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		//httppost.setHeader("Referer",site_url);
		//httppost.setHeader("X-CSRFToken", CSRFTOKEN);
		//DefaultHttpClient client = new DefaultHttpClient();
		//final BasicCookieStore cookieStore1 =  new BasicCookieStore();

		//BasicClientCookie csrf_cookie = new BasicClientCookie("csrftoken", CSRFTOKEN);
		//csrf_cookie.setDomain(CSRF_COOKIE_DOMAIN);
		//cookieStore.addCookie(csrf_cookie);

		// Create local HTTP context - to store cookies
		//HttpContext localContext = new BasicHttpContext();
		// Bind custom cookie store to the local context
		//localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore1);
		
		try {
			
			
		    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    
		}
		catch (UnsupportedEncodingException e) {
		    // writing error to Log
		    e.printStackTrace();
		}
			try{
				
				
		 HttpResponse response = httpclient1.execute(httppost);
		 Log.v("Response Code"," "+response.getStatusLine().getStatusCode()+" ");
		 
		 /*List<Cookie> cookies1 = ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
		 if (cookies.isEmpty()) {
			   Log.v("None","None");
			} else {
			    for (int i = 0; i < cookies.size(); i++) {
			        Log.v("- ",cookies.get(i).toString());
			    }
			}
		 */
			} catch(Exception e){
				e.printStackTrace();
			}
			//Log.v(" ", "");
			/*
			HttpResponse response = null;
			HttpGet httpget= new HttpGet("http://172.16.82.181:8000/minor/");
			try {
				 response = httpclient1.execute(httpget);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.v("Get Response Code"," "+response.getStatusLine().getStatusCode()+" ");
			*/
		
		
		
	}

}

