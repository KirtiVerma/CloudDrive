package com.example.mycloud;



import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class userpage extends Activity {
	private WebView mWebView;
	int responsecode;
	
	final Activity activity = this;
    public Uri imageUri;
     
    private static final int FILECHOOSER_RESULTCODE   = 2888;
    private ValueCallback<Uri> mUploadMessage;
    private Uri mCapturedImageURI = null; 
	   
	 @Override 
	    protected void onActivityResult(int requestCode, int resultCode,  
	                                       Intent intent) { 
	         
	     if(requestCode==FILECHOOSER_RESULTCODE)  
	     {  
	        
	            if (null == this.mUploadMessage) {
	                return;
	 
	            }
	 
	           Uri result=null;
	            
	           try{
	                if (resultCode != RESULT_OK) {
	                     
	                    result = null;
	                     
	                } else {
	                     
	                    // retrieve from the private variable if the intent is null
	                    result = intent == null ? mCapturedImageURI : intent.getData(); 
	                } 
	            }
	            catch(Exception e)
	            {
	                Toast.makeText(getApplicationContext(), "activity :"+e,
	                 Toast.LENGTH_LONG).show();
	            }
	             
	            mUploadMessage.onReceiveValue(result);
	            mUploadMessage = null;
	      
	     }
	         
	    }
	
	
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
        
       
        
        //postData();
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        //Log.v("userage","working");
        //final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //String site_url=getResources().getString(R.string.site_url);
        
        mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                    String contentDisposition, String mimetype,
                    long contentLength) {
         	   
         	  
              Intent i = new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(url));
              //i.setType(mimetype);
              startActivity(i);
              
              
              /*
              Uri source = Uri.parse(url);
              Log.v("Oh yeah","Its working");
              // Make a new request pointing to the mp3 url
              DownloadManager.Request request = new DownloadManager.Request(source);
              // Use the same file name for the destination
              File destinationFile = new File (Environment.DIRECTORY_DOWNLOADS, source.getLastPathSegment());
              request.setDestinationUri(Uri.fromFile(destinationFile));
              // Add it to the manager
              manager.enqueue(request);
              */
         	   
         	   
            }
        });
        
         //mWebView= new WebView(this);
        /*mWebView.setWebViewClient(new WebViewClient() {
 	        @Override
 	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
 	            return super.shouldOverrideUrlLoading(view, url);
 	        }
 	    });*/
         WebSettings webSettings = mWebView.getSettings();
         webSettings.setJavaScriptEnabled(true);
         webSettings.setBuiltInZoomControls(false);
         String sessioncookie=Mythread.sessioncookie;
         //String Httpstring = temp1.endResult;
        // mWebView.loadUrl("http://172.16.83.177:8000/minor/try1/");
        // mWebView.setWebViewClient(new WebViewClient());
         
         //CookieSyncManager.createInstance(this);
         //Log.v("Working","till now");
 		CookieManager cookiemanager= CookieManager.getInstance();
         cookiemanager.setAcceptCookie(true);
         cookiemanager.removeAllCookie();
         //String url="http://172.16.82.181:8000/minor/";
         String url1="http://192.168.63.57:8000/minor/try1/";
 		//cookiemanager.setCookie(url1, sessioncookie);
 		//CookieSyncManager.getInstance().sync();
 		//Log.v("cookie",sessioncookie);
 		//cookiemanager.setCookie(url1, sessioncookie);
 		//Log.v("cookie",sessioncookie);
 		//CookieSyncManager.getInstance().sync();
 		//mWebView.loadDataWithBaseURL(url1,Httpstring,"text/html","utf-8",url1);
 		//Log.v("running","running");
 		//mWebView.loadDataWithBaseURL(url1,Httpstring,"text/html","utf-8",url1);
         //mWebView.loadUrl(url1);
 		//mWebView.setWebViewClient(new WebViewClient());
 		//cookiemanager.removeSessionCookie();
 		
 		//Map<String, String> headers = new HashMap<String, String>();
 		//headers.put("Cookie", sessioncookie);
 		//headers.put("Cookie", "foo=bar");
 		
 		//headers.put("Cookie", "domain=domain.com;path=/;cookieName=cookieValue;Expires=Thu, 2 Aug 2021 20:47:11 UTC;");
 		Map<String, String> headers1 = new HashMap<String, String>();
 		//headers1.put("ACCEPT", "text/html");
 		
 		
 		headers1.put("Connection","keep-alive");
 		headers1.put("Cookie", sessioncookie);
 		//headers1.put("sessionid",sessioncookie);
 		//Log.v("Headers",headers.toString());
 		
 		//Log.v("Headers",headers1.toString());
 		mWebView.loadUrl(url1, headers1);
 		//setContentView(mWebView);
 		final Map<String, String> headers2 = headers1;
 		
 		//Log.v("headers2",headers2.toString());
 		/*
 		String URL ="http://172.16.82.181:8000/minor/sharing/";
 		
 		WebView webview = new WebView(this);
 	    webview.setWebViewClient(new WebViewClient() {
 	        @Override
 	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
 	            return super.shouldOverrideUrlLoading(view, url);
 	        }
 	    });

 	    
 	    CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(this);
 	    cookieSyncManager.startSync();
 	    CookieManager cookieManager = CookieManager.getInstance();
 	    cookieManager.setAcceptCookie(true);
 	    cookieManager.removeSessionCookie();
 	    cookieManager.setCookie(URL, sessioncookie);
 	    cookieSyncManager.sync();

 	    webview.getSettings().setJavaScriptEnabled(true);
 	    webview.loadUrl(URL);
 	    mWebView.setWebViewClient(new WebViewClient());
 	    //setContentView(webview);
 		*/
 		// mWebView.setWebViewClient(new WebViewClient());
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
   	    	  /*else if(url.endsWith("/"))
   	    	  {
   	    		  
   	    		  view.loadUrl(url);
   	    		  return true;
   	    		  
   	    	  }
   	    	  else
   	    	  {
   	    		  //shouldOverride = true;
   	                Uri source = Uri.parse(url);
   	                Log.v("Oh yeah","Its working");
   	                // Make a new request pointing to the mp3 url
   	                DownloadManager.Request request = new DownloadManager.Request(source);
   	                // Use the same file name for the destination
   	                File destinationFile = new File (Environment.DIRECTORY_DOWNLOADS, source.getLastPathSegment());
   	                request.setDestinationUri(Uri.fromFile(destinationFile));
   	                // Add it to the manager
   	                manager.enqueue(request);
   	    		  
   	    		  return true;
   	    	  }*/
   	    	  //return true;
   	      }  
   	    });
 		
 		
 		mWebView.setWebChromeClient(new WebChromeClient()  
 		  {  
 			
 		// openFileChooser for Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType){  
                
                // Update message
                mUploadMessage = uploadMsg;
                 
                try{    
                 
                    // Create AndroidExampleFolder at sdcard
                     
                    File imageStorageDir = new File(
                                           Environment.getExternalStoragePublicDirectory(
                                           Environment.DIRECTORY_PICTURES)
                                           , "AndroidExampleFolder");
                                            
                    if (!imageStorageDir.exists()) {
                        // Create AndroidExampleFolder at sdcard
                        imageStorageDir.mkdirs();
                    }
                     
                    // Create camera captured image file path and name 
                    File file = new File(
                                    imageStorageDir + File.separator + "IMG_"
                                    + String.valueOf(System.currentTimeMillis()) 
                                    + ".jpg");
                                     
                    mCapturedImageURI = Uri.fromFile(file); 
                     
                    // Camera capture image intent
                    final Intent captureIntent = new Intent(
                                                  android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                                   
                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
                    
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT); 
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                     
                    // Create file chooser intent
                    Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
                     
                    // Set camera intent to file chooser 
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS
                                           , new Parcelable[] { captureIntent });
                     
                    // On select image call onActivityResult method of activity
                    startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
                     
                  }
                 catch(Exception e){
                     Toast.makeText(getBaseContext(), "Exception:"+e, 
                                Toast.LENGTH_LONG).show();
                 }
                 
            }
            
            
            
 		  });  
 		
 		
 		
 		
 		
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
