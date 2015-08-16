package com.example.mycloud;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Help extends Activity {
	
	private WebView mWebView;
	@SuppressLint({ "SetJavaScriptEnabled", "InlinedApi" }) @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userpage);
        
        
        Button logout = (Button)findViewById(R.id.button);
        
        Button reload = (Button)findViewById(R.id.button1);
        
        
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	
            	Toast.makeText(getApplicationContext(), "User Logout", Toast.LENGTH_SHORT).show();
            	Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            	
            }
        });
        
        reload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	
            	Toast.makeText(getApplicationContext(), "Reloading", Toast.LENGTH_SHORT).show();
            	Intent i = new Intent(getApplicationContext(), userpage.class);
                startActivity(i);
            	
            }
        });
        
        
        
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        String sessioncookie=Mythread.sessioncookie;
        
        CookieManager cookiemanager= CookieManager.getInstance();
        cookiemanager.setAcceptCookie(true);
        cookiemanager.removeAllCookie();
        
        String url1="http://172.16.93.136:8000/minor/try1/";
        
        Map<String, String> headers1 = new HashMap<String, String>();
        headers1.put("Connection","keep-alive");
 		headers1.put("Cookie", sessioncookie);
 		mWebView.loadUrl(url1, headers1);
 		
 		final Map<String, String> headers2 = headers1;
 		mWebView.setWebViewClient(new WebViewClient() {  
 	   	      @Override  
 	   	      public boolean shouldOverrideUrlLoading(WebView view, String url)  
 	   	      {  
 	   	    	  //if(url.endsWith(".html"))
 	   	    	  {
 	   	    		CookieManager cookiemanager1= CookieManager.getInstance();
 	   	    		cookiemanager1.setAcceptCookie(true);
 	   	         cookiemanager1.removeAllCookie();
 	   	    		  //Log.v("headers21",headers2.toString());
 	   	    		  
 	   	    	 
 	   	        view.loadUrl(url,headers2);  
 	   	        return true;  
 	   	    	  }
        
	}});
}
	@Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }	

}
