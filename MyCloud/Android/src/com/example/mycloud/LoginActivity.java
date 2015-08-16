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
 
public class LoginActivity extends Activity {
	private EditText  username=null;
	private EditText  password=null;
	//private Button login;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        Button login1 = (Button)findViewById(R.id.btnLogin);
        //TextView textView;
		//textView.setText(result);
        login1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	String result=username.getText().toString();
            	String result1=password.getText().toString();
            	Log.v("username",result);
            	//Log.v("password",result1);
            	Mythread temp1=new Mythread(result,result1);
            	Thread t1= new Thread(temp1);
            	t1.start();
            	try {
					t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	Toast.makeText(getApplicationContext(), "User Login", Toast.LENGTH_SHORT).show();
            	Intent i = new Intent(getApplicationContext(), userpage.class);
                startActivity(i);
            }
        });
	
        TextView registerScreen = (TextView) findViewById(R.id.linktoregister);
 
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}