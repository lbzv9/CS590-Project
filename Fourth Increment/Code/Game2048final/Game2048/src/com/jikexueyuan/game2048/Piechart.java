package com.jikexueyuan.game2048;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.LinearLayout;

public class Piechart extends Activity {
	
	private GraphicalView mChart;
	
	private XYSeries visitsSeries ;
	private XYMultipleSeriesDataset dataset;
	
	private XYSeriesRenderer visitsRenderer;
	private XYMultipleSeriesRenderer multiRenderer;
	String day;
	Context context=this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechart); 
       // System.out.println("HIIIII");
        
        // Setting up chart
        setupChart();
        
        // Start plotting chart
        new ChartTask().execute();
        
    }
    
   
    private void setupChart(){
    	
    	
    	//System.out.println("111111111111111111");
    	
    	// Creating an  XYSeries for Visits
    	visitsSeries = new XYSeries("Dates and No.of motions");
    	//System.out.println("2222222222222222222");
    	
    	// Creating a dataset to hold each series
    	dataset = new XYMultipleSeriesDataset();
    	// Adding Visits Series to the dataset
    	dataset.addSeries(visitsSeries);    	
    	
    	
    	// Creating XYSeriesRenderer to customize visitsSeries
    	visitsRenderer = new XYSeriesRenderer();
    	visitsRenderer.setColor(Color.GREEN);
    	visitsRenderer.setPointStyle(PointStyle.CIRCLE);
    	visitsRenderer.setFillPoints(true);
    	visitsRenderer.setLineWidth(2);
    	visitsRenderer.setDisplayChartValues(true);
    	visitsRenderer.setChartValuesTextSize(40);
    	
    	
    	// Creating a XYMultipleSeriesRenderer to customize the whole chart
    	multiRenderer = new XYMultipleSeriesRenderer();
    	
    	multiRenderer.setChartTitle("No.of Motions");
    	multiRenderer.setXTitle("Dates");
    	multiRenderer.setYTitle("No.of Motions");
    	multiRenderer.setZoomButtonsVisible(true);
    	
    	multiRenderer.setXAxisMin(0);
    	multiRenderer.setXAxisMax(10);
    	
    	multiRenderer.setYAxisMin(0);
    	multiRenderer.setYAxisMax(10);
    	
    	multiRenderer.setBarSpacing(1);
    	
    	// Adding visitsRenderer to multipleRenderer
    	// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
    	// should be same
    	multiRenderer.addSeriesRenderer(visitsRenderer);
    	
    	// Getting a reference to LinearLayout of the MainActivity Layout
    	LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);
    	
   		
    	mChart = (GraphicalView) ChartFactory.getBarChartView(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);
    	
    	
   		// Adding the Line Chart to the LinearLayout
    	chartContainer.addView(mChart);
    	
    }
    
    
    private class ChartTask extends AsyncTask<Void, String, Void>{

    	//private BufferedReader buf;

		// Generates dummy data in a non-ui thread
		@Override
		protected Void doInBackground(Void... params) {
			int i = 0;
			
			
				
				//StringBuilder text = new StringBuilder();
				try{
					
					File f = new File(Environment.getExternalStorageDirectory()+"/Data2/sensor.txt");
					
					
					//
					//FileInputStream fis = new FileInputStream(f);
					 BufferedReader bufferr = new BufferedReader(new FileReader(f));
					 
					//System.out.println("KKKKKKKKKKK");
				    String line;
				    String[] values = new String[1000];
				   
				    while ((line = bufferr.readLine()) != null && i<=6) {
				    	
				    	
				    	//System.out.println(line);
				    	
					values[i] = line;
					String[] splits = values[i].split("\t");
					day=Report.getDefaults("day", context);
					
					System.out.println(splits[0]);
					
					if(splits[0].equalsIgnoreCase(day))
					 {
					String[] values1 = new String[2];
					values1[0] = Integer.toString(i);
					values1[1] = splits[5];
				
					Thread.sleep(1000);
					publishProgress(values1);					
					i++;
				}
				    }
			}catch(Exception e){ }
			return null;
		}
		
		// Plotting generated data in the graph
		@Override
		protected void onProgressUpdate(String... values) {
			visitsSeries.add(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
			mChart.repaint();
		}
    	
    }    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
