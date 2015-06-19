package com.xiaoyacz.kidsmath.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xiaoyacz.kidsmath.game.util.ArrayUtil;

public class NumRecognizeModel {
	public static final int SIZE=10;
	private int answer;
	private Integer[] options;
	private List<Integer> removeList;//已消除的
	
//	public static void main(String[] args){
//		NumRecognizeModel model=new NumRecognizeModel();
//		for(int i:model.getOptions()){
//			System.out.println(i);
//		}
//	}
	
	private NumRecognizeModel(){
		Random rd=new Random();
		options=new Integer[SIZE];
		answer=rd.nextInt(SIZE);
		options[0]=answer;
		for(int i=1;i<SIZE;i++){
			setOption(i , rd);
		}
		ArrayUtil.shuffleArray(options);
		removeList=new ArrayList<Integer>();
	}
	/**
	 * 保证数字的重复次数不超过两次
	 * @param i
	 * @param rd
	 */
	private void setOption(int i, Random rd){
		if(i<2){
			options[i]=rd.nextInt(SIZE);
		}else{
			int num=rd.nextInt(SIZE);
			while(true){
				int sameNum=0;
				for(int k=0;k<i;k++){
					if(options[k] == num){
						sameNum++;
					}
				}
				if(sameNum >1){
					num=rd.nextInt(SIZE);
				}else{
					break;
				}
			}
			options[i]=num;
		}
	}
	
	public static NumRecognizeModel newInstance(){
		return new NumRecognizeModel();
	}

//	private void shuffleArray(int[] ar) {
//		Random rnd = new Random();
//		for (int i = ar.length - 1; i > 0; i--) {
//			int index = rnd.nextInt(i + 1);
//			// Simple swap
//			int a = ar[index];
//			ar[index] = ar[i];
//			ar[i] = a;
//		}
//	}
	/**
	 * 是否已完成
	 * @return
	 */
	public boolean isFinished(){
		int num=0;
		for(int option:options){
			if(answer == option){
				num++;
			}
		}
		return num == removeList.size();
	}

	public void addRemoved(Integer index){
		removeList.add(index);
	}
	public boolean isRemoved(Integer index){
		return removeList.contains(index);
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

	public List<Integer> getRemoveList() {
		return removeList;
	}

	public void setRemoveList(List<Integer> removeList) {
		this.removeList = removeList;
	}
	
	
}
