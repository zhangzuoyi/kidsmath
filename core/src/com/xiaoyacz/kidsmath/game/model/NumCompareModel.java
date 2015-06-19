package com.xiaoyacz.kidsmath.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.xiaoyacz.kidsmath.game.util.ArrayUtil;

public class NumCompareModel {
	public enum CompareType{
		BIG,
		SMALL
	}
	private int num1;
	private int num2;
	private CompareType type;
	
	private NumCompareModel(){
		num1=MathUtils.random(0, 9);
		while(true){
			num2=MathUtils.random(0, 9);
			if(num2 != num1){
				break;
			}
		}
		int i=MathUtils.random(0, 1);
		if(i == 1){
			type=CompareType.BIG;
		}else{
			type=CompareType.SMALL;
		}
	}
	/**
	 * 保证数字的重复次数不超过1次
	 * @param i
	 * @param rd
	 */
	
	public static NumCompareModel newInstance(){
		return new NumCompareModel();
	}
	public int getNum1() {
		return num1;
	}
	public int getNum2() {
		return num2;
	}
	public CompareType getType() {
		return type;
	}
	public boolean isCorrect(int num){
		int answer=-1;
		if(type == CompareType.BIG){
			answer=num1 > num2? num1 : num2;
		}else{
			answer=num1 > num2? num2 : num1;
		}
		
		return num == answer;
	}
}
