package com.xiaoyacz.kidsmath.game.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xiaoyacz.kidsmath.game.Assets;
import com.xiaoyacz.kidsmath.game.GameScreen;
import com.xiaoyacz.kidsmath.game.model.NumRecognizeModel;
import com.xiaoyacz.kidsmath.game.util.Constants;

public class RecognizeRenderer implements AbstractRenderer {
	private GameScreen screen;
	private NumRecognizeModel model;
	private List<Sprite> numberList;
	
	public RecognizeRenderer(NumRecognizeModel model, GameScreen screen){
		this.model=model;
		this.screen=screen;
	}

	@Override
	public void init() {
		int marginTop=100;
		int marginLeft=110;//60
		int cellMargin=20;
		int cellWidth=100;//120
//		Assets.instance.sounds.nums[model.getAnswer()].play();//应该要放到一个统计的play方法中，由controller来调用
		numberList=new ArrayList<Sprite>();
		int i=0;
		float x=marginLeft;
		float y=Constants.SCENE_HEIGHT-marginTop-cellWidth;
		for(int num:model.getOptions()){
			Sprite sprite=new Sprite(Assets.instance.bManager.getBalloon(num));
			sprite.setSize(BALLOON_SIZE, BALLOON_SIZE);
			numberList.add(sprite);
			if(i==5){
				y=y-cellMargin*2-cellWidth;
				x=marginLeft;
			}
			sprite.setPosition(x, y);
			x=x+cellWidth+cellMargin;
			i++;
		}
		playSound();
	}
	
	public void playSound(){
		Assets.instance.sounds.whichNumIs(model.getAnswer());
	}

	@Override
	public void render(SpriteBatch batch) {
		int i=0;
		for(int num:model.getOptions()){
//			Sprite sprite=new Sprite(numbers.findRegion("number"+num));
			Sprite sprite=numberList.get(i);
			if( ! model.isRemoved(i)){
				sprite.draw(batch);
				Assets.instance.fontNum.draw(batch, String.valueOf(num), sprite.getX()+30, sprite.getY()+75);
			}
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
//				starEffect.setPosition(tmp.x, tmp.y);
//				starEffect.reset();
//				starEffect.allowCompletion();
				if( ! model.isRemoved(i)){
					if(model.getAnswer() == model.getOptions()[i]){
						result=true;
						model.addRemoved(i);
						sound=Assets.instance.sounds.correctSound;
						if(model.isFinished()){
//							correctNum++;
							screen.nextQuestion();
						}
					}else{
						sound=Assets.instance.sounds.wrongSound;
					}
				}
//				sounds[model.getOptions()[i]].play();
//				sound=sounds[model.getOptions()[i]];
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
