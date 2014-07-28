package com.jikexueyuan.game2048;


import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class Game extends Activity {
	//Maze maze;
	GameView view;
	String data1="";
	public Double leftg=0.0,rightg=0.0,upg=0.0,downg=0.0;
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		//Intent intent = getIntent();
		  // startService(new Intent(this,ConnectionService.class));
		//Bundle extras = intent.getExtras();
		//this.maze = (Maze)getLastNonConfigurationInstance();
		
		view = new GameView(this);
		registerReceiver(receiver, new IntentFilter("myproject"));
		setContentView(view);
		
	}
	/*@Override
	protected void onResume() {
		super.onResume();
		
		//create a register   myproject is the same key used in connectionservice
		registerReceiver(receiver, new IntentFilter("myproject"));
	}*/
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			if (bundle!=null) {
				
				//extra data inserted into the fired intent
				String data = bundle.getString("data");
				Log.i("data in main class", data);
				
				
				if ("up".equalsIgnoreCase(data)) {
					  upg = upg+1.0;
					  System.out.println("upvalue"+upg);
					view.swipeUp();
				}
				else if ("right".equalsIgnoreCase(data)) {
					rightg = rightg+1.0;
					System.out.println("rightvalue"+rightg);
					view.swipeRight();
				}
				else if ("left".equalsIgnoreCase(data)) {
					leftg = leftg+1.0;
					System.out.println("leftvalue"+leftg);
					view.swipeLeft();
				}
				else if ("down".equalsIgnoreCase(data)) {
					downg = downg+1.0;
					System.out.println("downvalue"+downg);
					view.swipeDown();
				}
				
				//Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
			}else{
				Log.i("data in main class", "bundle null");
				//Toast.makeText(getApplicationContext(), "not", Toast.LENGTH_SHORT).show();
			}
			//handleResult(bundle);
		}

		
	};
	
	
	public void gesturevalues(String d)
	{
		//Double[] values={leftg,rightg,upg};
		
		String[] gesturevalues=d.split("\t");
		//String rights= String.valueOf(values[1]);
		//System.out.println("my right value is"+gesturevalues[2]);
		
		//String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(0));
		String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
		//int a = Integer.parseInt(gesturevalues[0]);
		//int b = Integer.parseInt(gesturevalues[1]);
		//int c = a + b;
		//String str = Integer.toString(c);
		
		String gestureDirections=currentDate+"\t"+gesturevalues[0]+"\t"+gesturevalues[1]+"\t"+gesturevalues[2]+"\t"+gesturevalues[3]+"\t"+gesturevalues[4]+"\n";
				//+"\t"+str+"\n" ;
		
		
         SaveData(gestureDirections);		
		
		
		
	}
	
	 private void SaveData(String string) {
	      // Log.i("string", string);
	        File sdCard = Environment.getExternalStorageDirectory();
	        File directory = new File (sdCard.getAbsolutePath() + "/Data2");
	        if(!directory.exists())
	        directory.mkdirs();
	        String fname = "sensor.txt";
	        File file = new File (directory, fname);
	        
	        try {
	            if(!file.exists())
	                file.createNewFile();
	               FileOutputStream out = new FileOutputStream(file,true);
	               out.write(string.getBytes());
	               out.flush();
	               out.close();

	        } catch (Exception e) {
	               e.printStackTrace();
	        }
	    }

	    	
	    }
	
	//public Object onRetainNonConfigurationInstance() {
		//return this.view;
	//}

