package com.xiaoyacz.kidsmath.game.util;

import java.util.Random;

public class ArrayUtil {
	public static <T> void shuffleArray(T[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			T a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
}
