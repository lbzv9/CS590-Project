package com.jikexueyuan.game2048;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;




public class MainActivity extends Activity {
	
	GameView view;
	Button button;
	Context context=this;
	Game gml = new Game();
	int i=0,j=0,k=0,l=0;
	//String allgestures;
	
	public MainActivity() {
		mainActivity = this;
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvScore = (TextView) findViewById(R.id.tvScore);
		//String leftgesture=GameView.getDefaults("leftvalue", context);
		//String upgesture=GameView.getDefaults("upvalue", context);
		//String rightgesture=GameView.getDefaults("rightvalue", context);
		
	//	String allgestures=leftgesture+"\t"+upgesture+"\t"+rightgesture;
		
		button = (Button) findViewById(R.id.button1);
	//	Game  gm1 = new Game();
        // Defining click event listener for the button btn_chart
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String leftgesture=GameView.getDefaults("leftvalue", context);
				String upgesture=GameView.getDefaults("upvalue", context);
				String rightgesture=GameView.getDefaults("rightvalue", context);
				String downgesture=GameView.getDefaults("downvalue", context);
				
				if(leftgesture!=null)
				{
					i=1;
				}
				if(upgesture!=null)
				{
					j=1;
				}
				if(rightgesture!=null)
				{
					k=1;
				}
				if(downgesture!=null)
				{
					l=1;
				}
				//Integer i = Integer.valueOf(leftgesture);
				int gestures=i+j+k+l;
				String gesture = Integer.toString(gestures);
				//String s = String.valueOf(i);
				String allgestures=leftgesture+"\t"+rightgesture+"\t"+upgesture+"\t"+downgesture+"\t"+gesture;
				gml.gesturevalues(allgestures);
				System.out.println("Data saved in file");
				//System.out.println();
				Intent report = new Intent(MainActivity.this,Report.class);
				startActivity(report);
				
			}
			 
			
			
 
		});
		
		//to call connection service
		startService(new Intent(this,ConnectionService.class));
		
		
		Intent game = new Intent(this,Game.class);
		startActivity(game);
		
		
		
		
		
	}
	
	
	


	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clearScore(){
		score = 0;
		showScore();
	}
	
	public void showScore(){
		tvScore.setText(score+"");
	}
	
	public void addScore(int s){
		score+=s;
		showScore();
	}

	private int score = 0;
	private TextView tvScore;
	
	private static MainActivity mainActivity = null;
	
	public static MainActivity getMainActivity() {
		return mainActivity;
	}

}
