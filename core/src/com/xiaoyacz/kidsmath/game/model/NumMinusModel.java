package com.xiaoyacz.kidsmath.game.model;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.xiaoyacz.kidsmath.game.util.ArrayUtil;

public class NumMinusModel {
	public static final int MAX=10;
	public static final int SIZE=5;
	private int num1;
	private int num2;
	private Integer[] options;
	private Integer answerIndex;//选择的选项序号
	
	public static NumMinusModel newInstance(){
		return new NumMinusModel();
	}
	private NumMinusModel(){
		Random rd=new Random();
		num1=MathUtils.random(1, 9);
		num2=MathUtils.random(0, num1);
		genetateOptions(rd);
	}

	private void genetateOptions(Random rd){
		options=new Integer[SIZE];
		options[0]=num1 - num2;
		for(int i=1;i<SIZE;i++){
			options[i]=rd.nextInt(MAX);
			while(true){
				boolean isDuplicate=false;
				for(int j=0;j<i; j++){
					if(options[j] == options[i]){
						isDuplicate=true;
						break;
					}
				}
				if(isDuplicate){
					options[i]=rd.nextInt(MAX);
				}else{
					break;
				}
			}
		}
		ArrayUtil.shuffleArray(options);
	}
	public boolean isCorrectAnswer(){
		if(answerIndex == null){
			return false;
		}else if(num1 - num2 == options[answerIndex]){
			return true;
		}
		return false;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public Integer[] getOptions() {
		return options;
	}
	public void setOptions(Integer[] options) {
		this.options = options;
	}
	public int getAnswerIndex() {
		return answerIndex;
	}
	public void setAnswerIndex(int answerIndex) {
		this.answerIndex = answerIndex;
	}
	
}
