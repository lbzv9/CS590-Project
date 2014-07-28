package com.jikexueyuan.game2048;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends Activity {
	 
	Button button;
	Spinner spinner2;
	String day;
	TextView tv;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		
		
	
		
		addItemsOnSpinner2();
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
		
		
		
		
 
		button = (Button) findViewById(R.id.button3);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				
				Intent bargraph = new Intent(Report.this,Bargraph.class);
				System.out.println("Bargraph");
				startActivity(bargraph);
 
			}
 
		});
		
		button = (Button) findViewById(R.id.button4);
		 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "Effective mixture of all the gestures wins the game for you", Toast.LENGTH_SHORT).show();
 
				Intent piechart = new Intent(Report.this,Piechart.class);
				startActivity(piechart);
 
			}
 
		});
 
 
	}
	public static void setDefaults(String key, String value, Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getDefaults(String key, Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(key, null);
	}

	public static void clearDefaults(Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();

		editor.clear();
		editor.commit();
	}
	
	public void addItemsOnSpinner2() {
		 
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list = new ArrayList<String>();
		list.add("25-07-2014");
		list.add("26-07-2014");
		list.add("27-07-2014");
		//ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			//android.R.layout.simple_spinner_item, list);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//dataAdapter.setDropDownViewResource(android.R.layout.spinner_item);
		
		spinner2.setAdapter(dataAdapter);
	  }
	
	

	public void addListenerOnSpinnerItemSelection() {
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		
		spinner2.setOnItemSelectedListener(new Items());
		
		
	
	  }
	
	public void addListenerOnButton()
	{
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		
		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {
			@Override

			public void onClick(View v) {

				day=String.valueOf(spinner2.getSelectedItem());
				setDefaults("day",day,getBaseContext());
				System.out.println("day is"+day);
		
			if(String.valueOf(spinner2.getSelectedItem()) == "25-07-2014")
				
				
			{
				
				
				Toast.makeText(getBaseContext(), "Effective mixture of all the gestures wins the game for you", Toast.LENGTH_LONG).show();
				
					TextView text1 = (TextView) findViewById(R.id.textView2);
					TextView text2 = (TextView) findViewById(R.id.textView3);
					TextView text3 = (TextView) findViewById(R.id.textView4);
					TextView text4 = (TextView) findViewById(R.id.textView5);
					text1.setText("No. of Left Motions: 10");
					text2.setText("No. of Right Motions: 8");
					text3.setText("No. of Up Motions: 3");
					text4.setText("No. of Down Motions: 5");
					
					            
			}
			
			if(String.valueOf(spinner2.getSelectedItem()) == "26-07-2014")
			{
				
				Toast.makeText(getBaseContext(), "Effective mixture of all the gestures wins the game for you", Toast.LENGTH_LONG).show();
				TextView text1 = (TextView) findViewById(R.id.textView2);
				TextView text2 = (TextView) findViewById(R.id.textView3);
				TextView text3 = (TextView) findViewById(R.id.textView4);
				TextView text4 = (TextView) findViewById(R.id.textView5);
				text1.setText("No. of Left Motions: 15");
				text2.setText("No. of Right Motions: 5");
				text3.setText("No. of Up Motions: 6");
				text4.setText("No. of Down Motions: 3");
					            
			}
			
			if(String.valueOf(spinner2.getSelectedItem()) == "27-07-2014")
			{
				Toast.makeText(getBaseContext(), "Effective mixture of all the gestures wins the game for you", Toast.LENGTH_LONG).show();
				TextView text1 = (TextView) findViewById(R.id.textView2);
				TextView text2 = (TextView) findViewById(R.id.textView3);
				TextView text3 = (TextView) findViewById(R.id.textView4);
				TextView text4 = (TextView) findViewById(R.id.textView5);
				text1.setText("No. of Left Motions: 14");
				text2.setText("No. of Right Motions: 16");
				text3.setText("No. of Up Motions: 1");
				text4.setText("No. of Down Motions: 5");
					            
				
			}
			}
		});
		
	}
	
	 
 
}