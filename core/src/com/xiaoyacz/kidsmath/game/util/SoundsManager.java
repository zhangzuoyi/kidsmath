package com.xiaoyacz.kidsmath.game.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.xiaoyacz.kidsmath.game.Assets;

public class SoundsManager {
	public Music[] nums;
	public Sound correctSound;
	public Sound wrongSound;
	public Music bg1Music;
	public Music bg2Music;
	public Music bg3Music;
	public Music whichNum;
	public Music whichNumIs;
	public Music equalWhich;
	public Music countNum;
	public Music minus;
	public Music plus;
	public Music big;
	public Music small;
	public Music earnPaster;
	public void setBgMusicVol(){
		bg1Music.setVolume(GamePreferences.instance.volMusic);
		bg2Music.setVolume(GamePreferences.instance.volMusic);
		bg3Music.setVolume(GamePreferences.instance.volMusic);
	}
	public void whichNumBig(){
		whichNum.setOnCompletionListener(new OnCompletionListener(){

			@Override
			public void onCompletion(Music music) {
				big.play();
			}
			
		});
		whichNum.play();
	}
	public void whichNumSmall(){
		whichNum.setOnCompletionListener(new OnCompletionListener(){

			@Override
			public void onCompletion(Music music) {
				small.play();
			}
			
		});
		whichNum.play();
	}
	public void countNum(){
		countNum.play();
	}
	public void plusExpression(int num1, int num2){
		if(num1>10 || num2>10){//暂支持10以下的
			return;
		}
		Music[] musicList=new Music[4];
		musicList[0]=nums[num1];
		musicList[1]=plus;
		musicList[2]=nums[num2];
		musicList[3]=equalWhich;
		//例如2加1等于几
		PlayListHelper helper=new PlayListHelper(musicList);
		helper.init();
	}
	public void minusExpression(int num1, int num2){
		if(num1>10 || num2>10){//暂支持10以下的
			return;
		}
		Music[] musicList=new Music[4];
		musicList[0]=nums[num1];
		musicList[1]=minus;
		musicList[2]=nums[num2];
		musicList[3]=equalWhich;
		//例如2减1等于几
		PlayListHelper helper=new PlayListHelper(musicList);
		helper.init();
	}
	public void whichNumIs(final int num){
		if(num>10){//暂支持10以下的
			return;
		}
		whichNumIs.setOnCompletionListener(new OnCompletionListener(){

			@Override
			public void onCompletion(Music music) {
				nums[num].play();
			}
			
		});
		whichNumIs.play();
	}
	public void earnPaster(){
		earnPaster.play();
	}

	class PlayListHelper{
		Music[] musicList;
		int currentIndex;
		PlayListHelper(Music[] musicList){
			this.musicList=musicList;
		}
		void init(){
			currentIndex=0;
			for(Music music: musicList){
				music.setOnCompletionListener(new OnCompletionListener(){

					@Override
					public void onCompletion(Music music) {
						playNext();
					}
					
				});
			}
			musicList[0].play();
		}
		void playNext(){
			currentIndex++;
			if(currentIndex < musicList.length){
				musicList[currentIndex].play();
			}
		}
	}
}
