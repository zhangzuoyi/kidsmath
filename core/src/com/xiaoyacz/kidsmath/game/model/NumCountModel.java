package com.xiaoyacz.kidsmath.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.xiaoyacz.kidsmath.game.util.ArrayUtil;

public class NumCountModel {
	public static final int SIZE=5;
	private int answer;
	private Integer[] options;
	
	private NumCountModel(){
		options=new Integer[SIZE];
		answer=MathUtils.random(1, 9);
		options[0]=answer;
		for(int i=1;i<SIZE;i++){
			setOption(i);
		}
		ArrayUtil.shuffleArray(options);
	}
	/**
	 * 保证数字的重复次数不超过1次
	 * @param i
	 * @param rd
	 */
	private void setOption(int i){
		int num=MathUtils.random(1, 9);
		while(true){
			int sameNum=0;
			for(int k=0;k<i;k++){
				if(options[k] == num){
					sameNum++;
				}
			}
			if(sameNum >0){
				num=MathUtils.random(1, 9);
			}else{
				break;
			}
		}
		options[i]=num;
	}
	
	public static NumCountModel newInstance(){
		return new NumCountModel();
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public Integer[] getOptions() {
		return options;
	}

	public void setOptions(Integer[] options) {
		this.options = options;
	}
	
	
}
