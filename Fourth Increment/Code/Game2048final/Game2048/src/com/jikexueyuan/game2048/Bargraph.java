package com.jikexueyuan.game2048;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;

public class Bargraph extends Activity {
	
	
	public String[] mMonth = new String[] {
				"Jan", "Feb" , "Mar", "Apr", "May", "Jun",
				"Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
			};
	String day;
	Context context=this;
	public String line;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bargraph);
        openChart();
        openChart1();
        
        // Getting reference to the button btn_chart
      /* Button btnChart = (Button) findViewById(R.id.btn_chart);
        
        // Defining click event listener for the button btn_chart
        OnClickListener clickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Draw the Income vs Expense Chart
				openChart();				
			}
		};*/
		
		// Setting event click listener for the button btn_chart of the MainActivity layout
		//btnChart.setOnClickListener(clickListener);
        
    }
    
    public void openChart(){    	
    	
    	// Pie Chart Section Names
    	String[] code = new String[] {
    			"Left", "Right", "Up", "Down"
    			
    	};   
    	//System.out.println("IN BARGRAPH");
    	
    	try{
    		 File f = new File(Environment.getExternalStorageDirectory()+"/Data2/sensor.txt");
			

			
			//FileInputStream fis = new FileInputStream(f);
			 BufferedReader bufferr = new BufferedReader(new FileReader(f));
			 System.out.println(f.exists());
			
			 String sp = "";
			 while((line = bufferr.readLine())!=null)
			 {
				 sp = line;
			 
				 System.out.println(sp);
			 
			 String[] splits = sp.split("\t");
			 day=Report.getDefaults("day", context);
			 System.out.println("day is currently"+day+splits[0]);
			 
			 if(splits[0].equalsIgnoreCase(day))
			 {
			 
			 System.out.println(splits[0]);
			// System.out.println(Double.parseDouble(splits[0]));
			 
			 Double[] distribution = {Double.parseDouble(splits[1]),Double.parseDouble(splits[2]),Double.parseDouble(splits[3]),Double.parseDouble(splits[4])};
			 
    	
			 
			 
    	// Pie Chart Section Value
    	//double[] distribution = { 3.9, 12.9, 55.8, 1.9, 23.7, 1.8 } ;
    	
    	// Color of each Pie Chart Sections
    	int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
    					 Color.YELLOW };
    	
    	// Instantiating CategorySeries to plot Pie Chart    	
    	CategorySeries distributionSeries = new CategorySeries(" Pie Chart Distribution");
    	
    	System.out.println(distribution.length);
    	for(int i=0 ;i < distribution.length;i++){
    		// Adding a slice with its values and name to the Pie Chart
    		distributionSeries.add(code[i], distribution[i]);
    		
    	}   
    	
    	// Instantiating a renderer for the Pie Chart
    	DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
    	for(int i = 0 ;i<distribution.length;i++){    		
    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
    		seriesRenderer.setColor(colors[i]);
    		seriesRenderer.setDisplayChartValues(true);
    		// Adding a renderer for a slice
    		defaultRenderer.addSeriesRenderer(seriesRenderer);
    		
    	}
    	
    	String t = "Pie Chart Analysis as on "+splits[0];
    	defaultRenderer.setChartTitle(t);
    	defaultRenderer.setChartTitleTextSize(20);
    	defaultRenderer.setZoomButtonsVisible(true);  
    	
    		
    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "AChartEnginePieChartDemo");    	
    	//System.out.println("CHECKKKK");
    	
    	
    	// Start Activity
    	startActivity(intent);
    	break;
    	
    	
    }
			 }
			 }
    	
    	catch(Exception e){}
    }
    
    
    
    
    
 public void openChart1(){    	
    	
    	// Pie Chart Section Names
    	String[] code = new String[] {
    			"Win", "Loss"
    			
    	};   
    	//System.out.println("IN BARGRAPH");
    	
    	try{
    		 File f = new File(Environment.getExternalStorageDirectory()+"/Data2/sensor.txt");
			

			
			//FileInputStream fis = new FileInputStream(f);
			 BufferedReader bufferr = new BufferedReader(new FileReader(f));
			 System.out.println(f.exists());
			
			 String sp = "";
			 while((line = bufferr.readLine())!=null)
			 {
				 sp = line;
			 
				 System.out.println(sp);
			 
			 String[] splits = sp.split("\t");
			 day=Report.getDefaults("day", context);
			 System.out.println("day is currently"+day+splits[0]);
			 
			 if(splits[0].equalsIgnoreCase(day))
			 {
			 
			 System.out.println(splits[0]);
			// System.out.println(Double.parseDouble(splits[0]));
			 
			 Double[] distribution = {Double.parseDouble(splits[1]),Double.parseDouble(splits[2])};
			 
    	
			 
			 
    	// Pie Chart Section Value
    	//double[] distribution = { 3.9, 12.9, 55.8, 1.9, 23.7, 1.8 } ;
    	
    	// Color of each Pie Chart Sections
    	int[] colors = { Color.BLACK, Color.LTGRAY };
    	
    	// Instantiating CategorySeries to plot Pie Chart    	
    	CategorySeries distributionSeries = new CategorySeries(" Pie Chart Distribution");
    	
    	System.out.println(distribution.length);
    	for(int i=0 ;i < distribution.length;i++){
    		// Adding a slice with its values and name to the Pie Chart
    		distributionSeries.add(code[i], distribution[i]);
    		
    	}   
    	
    	// Instantiating a renderer for the Pie Chart
    	DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
    	for(int i = 0 ;i<distribution.length;i++){    		
    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
    		seriesRenderer.setColor(colors[i]);
    		seriesRenderer.setDisplayChartValues(true);
    		// Adding a renderer for a slice
    		defaultRenderer.addSeriesRenderer(seriesRenderer);
    		
    	}
    	
    	String t = "Pie Chart Analysis as on "+splits[0];
    	defaultRenderer.setChartTitle(t);
    	defaultRenderer.setChartTitleTextSize(20);
    	defaultRenderer.setZoomButtonsVisible(true);  
    	
    		
    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "AChartEnginePieChartDemo");    	
    	//System.out.println("CHECKKKK");
    	
    	
    	// Start Activity
    	startActivity(intent);
    	break;
    	
    	
    }
			 }
			 }
    	
    	catch(Exception e){}
    }
 
 
 
 
 
 
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}