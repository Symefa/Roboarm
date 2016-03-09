package com.hanif.tesudpclient;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TulisActivity extends Activity {
	
	EditText et;
	int posXTot, posYTot;
	double posXRel, posYRel;
	double posXHur, posYHur; 
	private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tulis);
		
		try{
		button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button10);
        
        button1.setId(1);
        button2.setId(2);
        button3.setId(3);
        button4.setId(4);
        button5.setId(5);
        button6.setId(6);
        button7.setId(7);
        button8.setId(8);
        button9.setId(9);
        button0.setId(10);
        
        button1.setOnClickListener(multiple);
        button2.setOnClickListener(multiple);
        button3.setOnClickListener(multiple);
        button4.setOnClickListener(multiple);
        button5.setOnClickListener(multiple);
        button6.setOnClickListener(multiple);
        button7.setOnClickListener(multiple);
        button8.setOnClickListener(multiple);
        button9.setOnClickListener(multiple);
        button0.setOnClickListener(multiple);
		
		posXTot = -20;
		posYTot = 10;
		
		posXRel = 0;
		posYRel = 0;
		
		String s = String.format("5 %d %d", posXTot, posYTot);
		
		sendData(null,s);
		}
		catch(Exception e){
			Log.e("Hanif", e.toString());
		}
	}
	
	private OnClickListener multiple = new OnClickListener(){
		int delay = 500;
		@Override
		public void onClick(View arg0) {
			switch(arg0.getId()){
			case 1:
				gerakV(0,5);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakH(0,3);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakV(5,0);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakH(3,6);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakV(0,5);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakH(6,9);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakV(5,0);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakV(0,5);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakH(9,12);
				try{
					Thread.sleep(delay);
				}
				catch(Exception e)
				{
					
				}
				gerakV(5,0);
				break;
			case 2:
				gerakH(0,5);
				gerakV(3,0);
				gerakH(5,0);
				gerakV(3,0);
				gerakH(0,5);
				break;
			case 3:
				gerakH(0,5);
				gerakV(3,0);
				gerakH(5,0);
				gerakH(0,5);
				gerakV(3,0);
				gerakH(5,0);
				break;
			case 4:
				gerakV(4,0);
				gerakH(0,3);
				gerakV(0,4);
				gerakV(8,0);
				break;
			case 5:
				gerakH(5,0);
				gerakV(3,0);
				gerakH(0,5);
				gerakV(3,0);
				gerakH(5,0);
			case 6:
				gerakH(5,0);
				gerakV(7,0);
				gerakH(0,5);
				gerakV(4,0);
				gerakH(5,0);
				break;
			case 7:
				gerakH(0,5);
				gerakV(7,0);
				break;
			case 8:
				gerakV(4,0);
				gerakH(0,4);
				gerakV(0,4);
				gerakH(4,0);
				gerakV(0,4);
				gerakH(0,4);
				gerakV(4,0);
				break;
			case 9:
				gerakH(4,0);
				gerakV(0,4);
				gerakH(0,4);
				gerakV(8,0);
				gerakH(4,0);
				break;
			case 10:
				gerakV(7,0);
				gerakH(5,0);
				gerakV(0,7);
				gerakH(0,5);
				break;
			}
		}
		
	};
	
	public void gerakV(int awal,int akhir){
		
		int n = akhir - awal;
		
		if(n>=0){
			for(int i=0; i<n; i++){
				posYTot += i;
			}
		}
		else{
			for(int i=0; i>n; i--){
				posYTot += i;
			}
			
			String s = String.format("5 %d %d", posXTot, posYTot);
			sendData(null,s);
		}
		
		posYRel += akhir-awal;
		posYHur += akhir-awal;
	}
	
	public void gerakH(int awal,int akhir){
		int n = akhir - awal;
		
		if(n>=0){
			for(int i=0; i<n; i++){
				posXTot += i;
			}
		}
		else{
			for(int i=0; i>n; i--){
				posXTot += i;
			}	
			String s = String.format("5 %d %d", posXTot, posYTot);
			sendData(null,s);
		}
		
		posXRel += akhir-awal;
		posXHur += akhir-awal;
	}
	
	public void pindahHur(double x, double y){
		//posXTot += pos
	}
	
	public void sendData(View view, String a) {
        Context context = getApplicationContext();

        
        String host = MainActivity.host;
        if (!host.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b")) {
            CharSequence text = "Error: Invalid IP Address";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        
        String port = MainActivity.port;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tulis, menu);
		return true;
	}

}
