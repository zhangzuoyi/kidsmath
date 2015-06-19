package com.xiaoyacz.kidsmath.game.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface AbstractRenderer {
	int BALLOON_SIZE=100;
	void init();
	void render(SpriteBatch batch);
	void playSound();
	/**
	 * 返回结果代表是否点击正确
	 * @param x
	 * @param y
	 * @return
	 */
	boolean handlerInput(float x, float y);
}
