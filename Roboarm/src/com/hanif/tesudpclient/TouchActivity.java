package com.hanif.tesudpclient;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TouchActivity extends Activity {
	
	
	String port,host;
	TextView a,b,c,d;
	int prevx,prevy;
	int glob_x,glob_y;
	int width,height;
	RelativeLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		port = MainActivity.port;
		host = MainActivity.host;
		
		Log.e("HANIF", "TES");
		
		String uriString = "udp://" + host + ":" + port + "/";
		uriString += Uri.encode("0");
		Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        

    	Display display = getWindowManager().getDefaultDisplay();
    	Point size = new Point();
    	display.getSize(size);
    	width = size.x;
    	height = size.y;
        
    	Log.e("HANIF",String.format("%d %d", width,height));
    	
        prevx=prevy=0;
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_touch);
		
		a = (TextView) findViewById(R.id.textViewz);
		b = (TextView) findViewById(R.id.textViewy);
		c = (TextView) findViewById(R.id.textViews);
		d = (TextView) findViewById(R.id.textViewt);
		
		
		layout = (RelativeLayout)findViewById(R.id.RelativeLayout);
		
		
		startActivity(intent);
		
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent object holds X-Y values
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            
        	int x = (int)event.getX();
        	int y = (int)event.getY();
        	
        	
        	glob_x=x;
        	glob_y=y;
            
            String uriString = "udp://" + host + ":" + port + "/";
            int jaraky = -(x-(width/2))/20;
            int jarakx = -(y-(height/2))/20;
            
            if(jarakx>20)
            	jarakx=20;
            if(jarakx<-20)
            	jarakx=-20;
            if(jaraky>12)
            	jaraky=12;
            if(jaraky<-12)
            	jaraky=-12;
            
            uriString += Uri.encode(String.format("5 %d %d", jarakx,jaraky));
            
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            
            a.setText(String.format("%d", glob_y));
            b.setText(String.format("%d", glob_x));
            c.setText(String.format("%d", jarakx));
            d.setText(String.format("%d", jaraky));
            
            
            
            if(jarakx-prevx>0 || jarakx-prevx<0 || jaraky-prevy>0 || jaraky-prevy<0){
            	startActivity(intent);
            	prevx=jarakx;
            	prevy = jaraky;
            }
        }
 
        return super.onTouchEvent(event);
    }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.touch, menu);
		return true;
	}

}
