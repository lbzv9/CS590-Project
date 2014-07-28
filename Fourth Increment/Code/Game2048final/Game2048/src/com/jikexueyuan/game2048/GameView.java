package com.jikexueyuan.game2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;


public class GameView extends GridLayout {

	  public Double leftval=0.0,rightval=0.0,upval=0.0,downval=0.0;
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		initGameView();

	}

	public GameView(Context context) {
		super(context);
		
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initGameView();
	}
	
	public void initGameView(){
		setColumnCount(4);
		setBackgroundColor(0xffbbada0);
		
		
		
		setOnTouchListener(new View.OnTouchListener() {
			
			public float startX,startY,offsetX,offsetY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					this.startX = event.getX();
					this.startY = event.getY();
					//Log.i("X",String.valueOf(startX));
					//Log.i("Y",String.valueOf(startY));
					break;
				case MotionEvent.ACTION_UP:
					this.offsetX = event.getX()-startX;
					this.offsetY = event.getY()-startY;
					//Log.i("offset x",String.valueOf(offsetX)+"  "+String.valueOf(event.getX()));
					//Log.i("offset y",String.valueOf(offsetY)+"   "+String.valueOf(event.getY()));
					
					
					if (Math.abs(offsetX)>Math.abs(offsetY)) {
						if (offsetX<-5) {
							swipeLeft();
						}else if (offsetX>5) {
							swipeRight();
						}
					}else{
						if (offsetY<-5) {
							swipeUp();
						}else if (offsetY>5) {
							swipeDown();
						}
					}
					
					break;
				}
				return true;
			}
			
		});
		
		
		
	}
	
	
	


	
	
	public void onTouch(){
		
		Log.i("Before", "Swipe");
		swipeLeft();
		
		//Done.
	}
	
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cardWidth = (Math.min(w, h)-10)/4;
		
		addCards(cardWidth,cardWidth);
		
		startGame();
	}
	
	public void addCards(int cardWidth,int cardHeight){
		
		Card c;
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardWidth, cardHeight);
				
				cardsMap[x][y] = c;
			}
		}
	}
	
	public void startGame(){
		
		MainActivity.getMainActivity().clearScore();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardsMap[x][y].setNum(0);
			}
		}
		
		addRandomNum();
		addRandomNum();
	}
	
	public void addRandomNum(){
		
		emptyPoints.clear();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum()<=0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}
		
		Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
		cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
	}
	
	
	public void swipeLeft(){
		 leftval=leftval+1.0;
		
		Log.i("After", "Swipe");
		boolean merge = false;
		String leftvalue=String.valueOf(leftval);
	
		setDefaults("leftvalue",leftvalue,getContext());
		//Log.i("CardsNo","HIII");
		
		
		int s = cardsMap[0][0].getNum();
		
		Log.i("CardsNo",String.valueOf(s));
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				
				for (int x1 = x+1; x1 < 4; x1++) {
					//int s = cardsMap[x1][y].getNum();
					//Log.i("CardsNo",String.valueOf(s));
					if (cardsMap[x1][y].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							
							x--;
							
							merge = true;
						}else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true;
						}
						
						break;
					}
				}
			}
		}
		
		/*if (merge) {
			addRandomNum();
			checkComplete();
		}*/
	}
	public void swipeRight(){
		
		rightval=rightval+1.0;
		
		String rightvalue=String.valueOf(rightval);
		boolean merge = false;
		setDefaults("rightvalue",rightvalue,getContext());
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >=0; x--) {
				
				for (int x1 = x-1; x1 >=0; x1--) {
					if (cardsMap[x1][y].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							
							x++;
							merge = true;
						}else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true;
						}
						
						break;
					}
				}
			}
		}
		
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}
	public void swipeUp(){
		
		upval=upval+1.0;
		String upvalue=String.valueOf(upval);
		boolean merge = false;
		setDefaults("upvalue",upvalue,getContext());
		
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				
				for (int y1 = y+1; y1 < 4; y1++) {
					if (cardsMap[x][y1].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							
							y--;
							
							merge = true;
						}else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true;
						}
						
						break;
						
					}
				}
			}
		}
		
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}
	public void swipeDown(){
		
		downval=downval+1.0;
		String downvalue=String.valueOf(downval);
		boolean merge = false;
		setDefaults("downvalue",downvalue,getContext());
		

		
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >=0; y--) {
				
				for (int y1 = y-1; y1 >=0; y1--) {
					if (cardsMap[x][y1].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							
							y++;
							merge = true;
						}else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true;
						}
						
						break;
					}
				}
			}
		}
		
		if (merge) {
			addRandomNum();
			checkComplete();
		}
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
	
	public void checkComplete(){
		
		boolean complete = true;
		
		ALL:
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum()==0||
						(x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
						(x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
						(y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
						(y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))) {
					
					complete = false;
					break ALL;
				}
			}
		}
		
		if (complete) {
			new AlertDialog.Builder(getContext()).setTitle("ä½ å¥½").setMessage("æ¸¸æˆ�ç»“æ�Ÿ").setPositiveButton("é‡�æ�¥", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					startGame();
				}
			}).show();
		}
		
	}
	
	public Card[][] cardsMap = new Card[4][4];
	public List<Point> emptyPoints = new ArrayList<Point>();
	
/*public static GameView gameview = null;
	
	public static GameView getGameView() {
		return gameview;
	}*/
}
