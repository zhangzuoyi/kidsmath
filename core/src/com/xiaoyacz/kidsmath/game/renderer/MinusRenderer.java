package com.xiaoyacz.kidsmath.game.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xiaoyacz.kidsmath.game.Assets;
import com.xiaoyacz.kidsmath.game.GameScreen;
import com.xiaoyacz.kidsmath.game.model.NumMinusModel;
import com.xiaoyacz.kidsmath.game.util.Constants;

public class MinusRenderer implements AbstractRenderer {
	private GameScreen screen;
	private NumMinusModel model;
	private List<Sprite> numberList;
	private Sprite num1;
	private Sprite operation;
	private Sprite num2;
	private Sprite equal;
	private Sprite question;
	private Sprite answer;
	int marginTop=80;
	int marginBottom=100;
	int marginLeft=110;
	int cellMargin=20;
	int cellWidth=100;
	
	public MinusRenderer(NumMinusModel model, GameScreen screen){
		this.model=model;
		this.screen=screen;
	}

	@Override
	public void init() {
		numberList=new ArrayList<Sprite>();
		int i=0;
		int x=marginLeft;
		int y=marginBottom;
		for(int num:model.getOptions()){
			Sprite sprite=new Sprite(Assets.instance.bManager.getBalloon(2));
			sprite.setSize(BALLOON_SIZE, BALLOON_SIZE);
			numberList.add(sprite);
			sprite.setPosition(x, y);
			x=x+cellMargin+cellWidth;
			i++;
		}
		initExpression();
		playSound();
	}
	private void initExpression(){
		float x=marginLeft;
		float y=Constants.SCENE_HEIGHT-marginTop-cellWidth;
//		num1=new Sprite(Assets.instance.bManager.getBalloon(0));
//		num1.setSize(BALLOON_SIZE, BALLOON_SIZE);
		num1=new Sprite(Assets.instance.numImages.get(model.getNum1()));
		num1.setPosition(x, y);
		
		x=x+cellMargin+cellWidth;
		operation=new Sprite(Assets.instance.tminus);
		operation.setSize(BALLOON_SIZE, BALLOON_SIZE);
		operation.setPosition(x, y);
		
		x=x+cellMargin+cellWidth;
//		num2=new Sprite(Assets.instance.bManager.getBalloon(1));
//		num2.setSize(BALLOON_SIZE, BALLOON_SIZE);
		num2=new Sprite(Assets.instance.numImages.get(model.getNum2()));
		num2.setPosition(x, y);
		
		x=x+cellMargin+cellWidth;
		equal=new Sprite(Assets.instance.tequal);
		equal.setSize(BALLOON_SIZE, BALLOON_SIZE);
		equal.setPosition(x, y);
		
		x=x+cellMargin+cellWidth;
		answer=new Sprite(Assets.instance.bManager.getBalloon(2));
		answer.setSize(BALLOON_SIZE, BALLOON_SIZE);
		answer.setPosition(x, y);
		question=new Sprite(Assets.instance.tquestion);
		question.setSize(BALLOON_SIZE, BALLOON_SIZE);
		question.setPosition(x, y);
	}
	public void playSound(){
		Assets.instance.sounds.minusExpression(model.getNum1(), model.getNum2());
	}

	@Override
	public void render(SpriteBatch batch) {
		float xOffset=30;
		float yOffset=75;
//		font2.getData().setScale(2);
//		font2.draw(batch, String.valueOf(model.getAnswer()), 100, 500);
		int i=0;
		for(int num:model.getOptions()){
//			Sprite sprite=new Sprite(numbers.findRegion("number"+num));
			Sprite sprite=numberList.get(i);
			sprite.draw(batch);
			Assets.instance.fontNum.draw(batch, String.valueOf(num), sprite.getX()+xOffset, sprite.getY()+yOffset);
			i++;
		}
		num1.draw(batch);
//		Assets.instance.fontNum.draw(batch, String.valueOf(model.getNum1()), num1.getX()+xOffset, num1.getY()+yOffset);
		operation.draw(batch);
		num2.draw(batch);
//		Assets.instance.fontNum.draw(batch, String.valueOf(model.getNum2()), num2.getX()+xOffset, num2.getY()+yOffset);
		equal.draw(batch);
		if(model.isCorrectAnswer()){
			answer.draw(batch);
			Assets.instance.fontNum.draw(batch, String.valueOf(model.getNum1() - model.getNum2()), answer.getX()+xOffset, answer.getY()+yOffset);
		}else{
			question.draw(batch);
		}
		drawNumCount(batch);
	}
	private void drawNumCount(SpriteBatch batch){
		int x1=marginLeft;
		int x2=x1+2*cellMargin+2*cellWidth;
		float yLine1=Constants.SCENE_HEIGHT-marginTop-cellWidth-40;
		float yLine2=yLine1 - 40;
		int x=x1;
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
		for(Sprite sprite:numberList){
			if(sprite.getBoundingRectangle().contains(x, y)){
//				starEffect.setPosition(tmp.x, tmp.y);
//				starEffect.reset();
//				starEffect.allowCompletion();
				model.setAnswerIndex(i);
				if( model.getNum1() - model.getNum2() == model.getOptions()[i]){
					result=true;
					sound=Assets.instance.sounds.correctSound;
					Gdx.input.setInputProcessor(null);
//					correctNum++;
					screen.nextQuestion();
				}else{
					sound=Assets.instance.sounds.wrongSound;
				}
				break;
			}
				
			i++;
		}
//		sounds[model.getAnswer()].play();
		if(sound != null){
			sound.play();
		}
		
		return result;
	}

}
