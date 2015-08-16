package com.example.mycloud;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class RegisterActivity extends Activity {
	private EditText  username1=null;
	private EditText  password1=null;
	private EditText  firstname=null;
	private EditText  lastname=null;
	private EditText  email=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        
        
        username1 = (EditText)findViewById(R.id.reg_Username);
        password1 = (EditText)findViewById(R.id.reg_password);
        firstname = (EditText)findViewById(R.id.reg_firstname);
        lastname = (EditText)findViewById(R.id.reg_lastname);
        email = (EditText)findViewById(R.id.reg_email);
        Button register1 = (Button)findViewById(R.id.btnRegister);
        register1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	String result=username1.getText().toString();
            	String result1=password1.getText().toString();
            	String result2=firstname.getText().toString();
            	String result3=lastname.getText().toString();
            	String result4=email.getText().toString();
            	//Log.v("username",result);
            	//Log.v("password",result1);
            	//Log.v("firstname",result2);
            	//Log.v("lastname",result3);
            	Log.v("email",result4);
            	Register temp1= new Register(result,result1,result2,result3,result4);
            	Thread t1= new Thread(temp1);
            	t1.start();
            	try {
            		Log.v("running","running");
					t1.join();
					Log.v("running","running");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	Toast.makeText(getApplicationContext(), "User registered ", Toast.LENGTH_SHORT).show();
            	Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
 
        TextView loginScreen = (TextView) findViewById(R.id.linktologin);
 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
    }
}
