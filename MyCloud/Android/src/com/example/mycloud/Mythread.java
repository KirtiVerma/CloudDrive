package com.example.mycloud;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

@SuppressLint("SetJavaScriptEnabled")
public class Mythread extends Activity implements Runnable{
	String site_url = "http://192.168.63.57:8000/minor/";
	static String sessioncookie,endResult,session;
	String user,pass;
	
	
	Mythread(String user,String pass)
	{
		this.user=user;
		this.pass=pass;
	}
	
	public void run(){
		//HttpClient httpclient = new DefaultHttpClient();
		HttpClient httpclient1 = new DefaultHttpClient();
		sessioncookie=null;
		endResult=null;
		session=null;
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
		//HttpPost httppost = new HttpPost("http://172.16.83.177:8000/minor/register");
		HttpPost httppost = new HttpPost("http://192.168.63.57:8000/minor/my_view/");
		//HttpGet httpget = new HttpGet("http://172.16.82.190:8000/minor/try1");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		//String params="mridul";
		nameValuePairs.add(new BasicNameValuePair("username",user));
		nameValuePairs.add(new BasicNameValuePair("password",pass));
		//nameValuePairs.add(new BasicNameValuePair("firstname", "utka"));
		//nameValuePairs.add(new BasicNameValuePair("lastname", "rsh"));
		//nameValuePairs.add(new BasicNameValuePair("email", "utka@gmail.com"));
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
			//Log.v("TAG","" + user);
			
		    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		   
		}
		catch (UnsupportedEncodingException e) {
		    // writing error to Log
		    e.printStackTrace();
		}
			try{
				
				BasicResponseHandler myHandler=new BasicResponseHandler();
		 HttpResponse response = httpclient1.execute(httppost);
		 Log.v("Response Code"," "+response.toString()+" ");
		 endResult= myHandler.handleResponse(response);
		 List<Cookie> cookies1 = ((AbstractHttpClient) httpclient1).getCookieStore().getCookies();
		 if (cookies1.isEmpty()) {
			  // Log.v("None","None");
			} else {
			    for (int i = 0; i < cookies1.size(); i++) {
			        Log.v("- ",cookies1.get(0).toString());
			    }
			//sessioncookie=cookies1.get(0).toString();
			    sessioncookie="domain="+cookies1.get(0).getDomain().toString()+";"+cookies1.get(0).getName().toString() +"="+ cookies1.get(0).getValue().toString()+";"+"expires="+cookies1.get(0).getExpiryDate().toString()+";"+"path="+cookies1.get(0).getPath().toString()+";";
			    //Log.v("sessioncookie",sessioncookie);
			    session=cookies1.get(0).getName().toString() +"="+ cookies1.get(0).getValue().toString()+";";
			    
			   
			   // sessioncookie=cookies1.get(0).getValue().toString();
		 	//cookie1 = cookie1.substring(0, cookie1.indexOf(";"));
	        //String cookieName = cookie.substring(0, cookie.indexOf("="));
	        //String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
			}
			} catch(Exception e){
				e.printStackTrace();
			}
			/*URI uri = null;
			try {
				uri = new URI("http://172.16.83.177:8000/minor/try1/");
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.v(" ", "");
			HttpRequestBase request = new HttpGet(uri);
			try {
				request.addHeader("Cookie", getCookieFromAppCookieManager(uri.toString()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpResponse response1 = null;
			try {
				response1= httpclient1.execute(request);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*HttpResponse response = null;
			 httpget= new HttpGet("http://172.16.83.177:8000/minor/try1/");
			try {
				 response = httpclient1.execute(httpget);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//Log.v("Get Response Code"," "+response.getStatusLine().getStatusCode()+" ");
			
			//mWebView = (WebView) findViewById(R.id.activity_main_webview);
			//WebSettings webSettings = mWebView.getSettings();
	        //webSettings.setJavaScriptEnabled(true);
	        
	       // mWebView.loadUrl("http://172.16.83.177:8000/minor/try1/");
	       //  mWebView.setWebViewClient(new WebViewClient());
		
		
	}

/*public static String getCookieFromAppCookieManager(String url) throws MalformedURLException {
    CookieManager cookieManager = CookieManager.getInstance();
    if (cookieManager == null)
        return null;
    String rawCookieHeader = null;
    URL parsedURL = new URL(url);

    // Extract Set-Cookie header value from Android app CookieManager for this URL
    rawCookieHeader = cookieManager.getCookie(parsedURL.getHost());
    if (rawCookieHeader == null)
        return null;
    return rawCookieHeader;*/
}