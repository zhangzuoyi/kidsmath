package com.xiaoyacz.kidsmath.game;

import com.badlogic.gdx.math.MathUtils;
import com.xiaoyacz.kidsmath.game.model.NumCompareModel;
import com.xiaoyacz.kidsmath.game.model.NumCountModel;
import com.xiaoyacz.kidsmath.game.model.NumMinusModel;
import com.xiaoyacz.kidsmath.game.model.NumPlusModel;
import com.xiaoyacz.kidsmath.game.model.NumRecognizeModel;

public class Quiz {
	private int correctNum;
	private static final int TYPES=4;
	private int lastType=2;
	
	public Quiz(){
		correctNum=0;
	}
	public Object getModel(){
		int index=MathUtils.random(TYPES);
		while(true){
			if(index != lastType){
				break;
			}
			index=MathUtils.random(TYPES);
		}
		lastType=index;
		if(index == 0){
			return NumRecognizeModel.newInstance();
		}else if(index == 1){
			return NumPlusModel.newInstance();
		}else if(index == 2){
			return NumMinusModel.newInstance();
		}else if(index == 3){
			return NumCountModel.newInstance();
		}else if(index == 4){
			return NumCompareModel.newInstance();
		}
		return NumRecognizeModel.newInstance();
	}
	public void addCorrectNum(){
		correctNum++;
	}
	public int getCorrectNum(){
		return correctNum;
	}
}
