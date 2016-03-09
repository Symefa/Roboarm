package com.hanif.tesudpclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,changeButton,tulisButton;
	TextView tv1,tv2,tv3,tv4;
	EditText et1,et2;
	int angka1,angka2,angka3,angka4;
	long now,prev;
	public static String port,host;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore preferences
        
        prev=0;
        prev=System.currentTimeMillis();
        angka1=angka2=angka3=angka4=90;
        et1 = (EditText) findViewById(R.id.editTextz);
        et2 = (EditText) findViewById(R.id.editText2);
        btn1 = (Button) findViewById(R.id.buton1);
        btn2 = (Button) findViewById(R.id.buton2);
        btn3 = (Button) findViewById(R.id.buton3);
        btn4 = (Button) findViewById(R.id.buton4);
        btn5 = (Button) findViewById(R.id.buton5);
        btn6 = (Button) findViewById(R.id.buton6);
        btn7 = (Button) findViewById(R.id.buton7);
        btn8 = (Button) findViewById(R.id.buton8);
        
        changeButton = (Button) findViewById(R.id.buttonk);
        tulisButton = (Button) findViewById(R.id.buttonh);
        
        btn1.setId(1);
        btn2.setId(2);
        btn3.setId(3);
        btn4.setId(4);
        btn5.setId(5);
        btn6.setId(6);
        btn7.setId(7);
        btn8.setId(8);
        /*
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        */
        btn1.setOnTouchListener(multiple);
        btn2.setOnTouchListener(multiple);
        btn3.setOnTouchListener(multiple);
        btn4.setOnTouchListener(multiple);
        btn5.setOnTouchListener(multiple);
        btn6.setOnTouchListener(multiple);
        btn7.setOnTouchListener(multiple);
        btn8.setOnTouchListener(multiple);
        
        changeButton.setOnClickListener(ganti);
        tulisButton.setOnClickListener(ganti2);
        
        et1.setText("192.168.43.136", TextView.BufferType.EDITABLE);
        et2.setText("1000", TextView.BufferType.EDITABLE);
        
        host = et1.getText().toString();
        port = et2.getText().toString();
    }
    
    public OnClickListener ganti = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			
			Intent intent = new Intent(getApplicationContext(), TouchActivity.class);
			startActivity(intent);
		}
    	
    };
    
    public OnClickListener ganti2 = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			
			Intent intent = new Intent(getApplicationContext(), TulisActivity.class);
			startActivity(intent);
		}
    	
    };

   private OnTouchListener multiple = new OnTouchListener(){

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		now = System.currentTimeMillis();
		if(now-prev>=10){
			switch(arg0.getId()){
			case 1:
				if(angka1>0)
					angka1-=2;
				sendData(null,String.format("2 %d", angka1));
				break;
			case 2:
				if(angka1<179)
					angka1+=2;
				sendData(null,String.format("2 %d", angka1));
				break;
			case 3:
				if(angka2<179)
					angka2+=2;
				sendData(null,String.format("1 %d", angka2));
				break;
			case 4:
				if(angka2>0)
					angka2-=2;
				sendData(null,String.format("1 %d", angka2));
				break;
			case 5:
				if(angka3<179)
					angka3+=2;
				sendData(null,String.format("4 %d", angka3));
				break;
			case 6:
				if(angka3>0)
					angka3-=2;
				sendData(null,String.format("4 %d", angka3));
				break;
			case 7:
				if(angka4<179)
					angka4+=2;
				sendData(null,String.format("3 %d", angka4));
				break;
			case 8:
				if(angka1>0)
					angka4-=2;
				sendData(null,String.format("3 %d", angka4));
				break;
			}
			
			prev=now;
		}/*
		tv1.setText(String.format("%d", angka1));
		tv2.setText(String.format("%d", angka2));
		tv3.setText(String.format("%d", angka3));
		tv4.setText(String.format("%d", angka4));*/
		return false;
	}
	   
   };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

  
    

    public void sendData(View view, String a) {
        Context context = getApplicationContext();

        host = et1.getText().toString();
        port = et2.getText().toString();
        
        if (!host.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b")) {
            CharSequence text = "Error: Invalid IP Address";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        
        
        if (!port.matches("^(6553[0-5]|655[0-2]\\d|65[0-4]\\d\\d|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3}|0)$")) {
            CharSequence text = "Error: Invalid Port Number";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        
        try{
        
        String uriString = "udp://" + host + ":" + port + "/";
        uriString += Uri.encode(a);
        
        
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        startActivity(intent);
        }
        catch(Exception e){
        	Log.e("HANIF", e.toString());
        }
    }

    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();


        EditText editTextIp = (EditText) findViewById(R.id.editTextz);
        EditText editTextPort = (EditText) findViewById(R.id.editText2);
        if (on) {
            editTextIp.setInputType(InputType.TYPE_CLASS_TEXT);
            editTextPort.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            editTextIp.setInputType(InputType.TYPE_CLASS_PHONE);
            editTextPort.setInputType(InputType.TYPE_CLASS_PHONE);
        }
    }
}