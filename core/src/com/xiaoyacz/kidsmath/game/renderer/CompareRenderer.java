package com.xiaoyacz.kidsmath.game.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xiaoyacz.kidsmath.game.Assets;
import com.xiaoyacz.kidsmath.game.GameScreen;
import com.xiaoyacz.kidsmath.game.model.NumCompareModel;
import com.xiaoyacz.kidsmath.game.model.NumCompareModel.CompareType;
import com.xiaoyacz.kidsmath.game.model.NumPlusModel;
import com.xiaoyacz.kidsmath.game.util.Constants;
import com.xiaoyacz.kidsmath.game.util.SoundsManager;

public class CompareRenderer implements AbstractRenderer {
	private GameScreen screen;
	private NumCompareModel model;
	private Sprite num1;
	private Sprite num2;
	private Sprite symbol;
	private boolean isCorrect;
	float marginLeft=200;
	float balloonY=240;
	float cellSize=140;
	
	public CompareRenderer(NumCompareModel model, GameScreen screen){
		this.model=model;
		this.screen=screen;
		isCorrect=false;
	}

	@Override
	public void init() {
		float x=marginLeft;
		float y=balloonY;
		num1=new Sprite(Assets.instance.bManager.getBalloon(model.getNum1()));
		num1.setSize(BALLOON_SIZE, BALLOON_SIZE);
		num1.setPosition(x, y);
		if(model.getNum1()>model.getNum2()){
			symbol=new Sprite(Assets.instance.greaterThen);
		}else{
			symbol=new Sprite(Assets.instance.lessThen);
		}
		x=x+cellSize;
		symbol.setPosition(x, y);
		symbol.setSize(BALLOON_SIZE, BALLOON_SIZE);
		x=x+cellSize;
		num2=new Sprite(Assets.instance.bManager.getBalloon(model.getNum2()));
		num2.setSize(BALLOON_SIZE, BALLOON_SIZE);
		num2.setPosition(x, y);
		playSound();
	}
	
	public void playSound(){
		if(model.getType() == CompareType.BIG){
			Assets.instance.sounds.whichNumBig();
		}else{
			Assets.instance.sounds.whichNumSmall();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		float xOffset=30;
		float yOffset=75;
		
		num1.draw(batch);
		Assets.instance.fontNum.draw(batch, String.valueOf(model.getNum1()), num1.getX()+xOffset, num1.getY()+yOffset);
		num2.draw(batch);
		Assets.instance.fontNum.draw(batch, String.valueOf(model.getNum2()), num2.getX()+xOffset, num2.getY()+yOffset);
		if(isCorrect){
			symbol.draw(batch);
		}
		drawNumCount(batch);
	}
	
	private void drawNumCount(SpriteBatch batch){
		float x1=marginLeft;
		float x2=x1+2*cellSize;
		float yLine1=balloonY - 40;
		float yLine2=yLine1 - 40;
		float x=x1;
		float y=yLine1;
		for(int i=0; i<model.getNum1(); i++){
			batch.draw(Assets.instance.strawberry30, x, y);
			x=x + 35;
			if(i == 4){
				x=x1;
				y=yLine2;
			}
		}
		x=x2;
		y=yLine1;
		for(int i=0; i<model.getNum2(); i++){
			batch.draw(Assets.instance.strawberry30, x, y);
			x=x + 35;
			if(i == 4){
				x=x2;
				y=yLine2;
			}
		}
	}

	@Override
	public boolean handlerInput(float x, float y) {
		boolean result=false;
		int i=0;
		Sound sound=null;
		if(num1.getBoundingRectangle().contains(x, y)){
			if(model.isCorrect(model.getNum1())){
				sound=Assets.instance.sounds.correctSound;
				isCorrect=true;
				result=true;
				screen.nextQuestion();
			}else{
				sound=Assets.instance.sounds.wrongSound;
			}
		}
		if(num2.getBoundingRectangle().contains(x, y)){
			if(model.isCorrect(model.getNum2())){
				sound=Assets.instance.sounds.correctSound;
				isCorrect=true;
				result=true;
				screen.nextQuestion();
			}else{
				sound=Assets.instance.sounds.wrongSound;
			}
		}

		if(sound != null){
			sound.play();
		}
		
		return result;
	}

}
