package com.xiaoyacz.kidsmath.game.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xiaoyacz.kidsmath.game.Assets;
import com.xiaoyacz.kidsmath.game.GameScreen;
import com.xiaoyacz.kidsmath.game.model.NumCountModel;
import com.xiaoyacz.kidsmath.game.util.Constants;

public class CountRenderer implements AbstractRenderer {
	private GameScreen screen;
	private NumCountModel model;
	private List<Sprite> numberList;
	
	public CountRenderer(NumCountModel model, GameScreen screen){
		this.model=model;
		this.screen=screen;
	}

	@Override
	public void init() {
		int marginBottom=90;
		int marginLeft=110;
		int cellMargin=20;
		int cellWidth=100;
		numberList=new ArrayList<Sprite>();
		int x=marginLeft;
		int y=marginBottom;
		for(int num:model.getOptions()){
			Sprite sprite=new Sprite(Assets.instance.bManager.getBalloon(1));
			sprite.setSize(BALLOON_SIZE, BALLOON_SIZE);
			numberList.add(sprite);
			sprite.setPosition(x, y);
			x=x+cellMargin+cellWidth;
		}
		playSound();
	}
	public void playSound(){
		Assets.instance.sounds.countNum();
	}

	@Override
	public void render(SpriteBatch batch) {
		int marginTop=100;
		int marginLeft=160;
		int cellMargin=10;
		int cellWidth=80;
		float xOffset=30;
		float yOffset=75;
		float x=marginLeft;
		float y=Constants.SCENE_HEIGHT-marginTop-cellWidth;
		for(int i=0;i<model.getAnswer();i++){
			Sprite sprite=new Sprite(Assets.instance.strawberry);
			sprite.setPosition(x, y);
			sprite.draw(batch);
			x=x+cellMargin+cellWidth;
			if(i == 4){
				x=marginLeft;
				y=y - cellMargin - cellWidth;
			}
		}
		int i=0;
		for(int num:model.getOptions()){
//			Sprite sprite=new Sprite(numbers.findRegion("number"+num));
			Sprite sprite=numberList.get(i);
			sprite.draw(batch);
			Assets.instance.fontNum.draw(batch, String.valueOf(num), sprite.getX()+xOffset, sprite.getY()+yOffset);
			i++;
		}
		
	}

	@Override
	public boolean handlerInput(float x, float y) {
		boolean result=false;
		int i=0;
		Sound sound=null;
		for(Sprite sprite:numberList){
			if(sprite.getBoundingRectangle().contains(x, y)){

				if( model.getAnswer() == model.getOptions()[i]){
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
