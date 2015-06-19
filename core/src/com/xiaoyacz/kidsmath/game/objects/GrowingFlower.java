package com.xiaoyacz.kidsmath.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GrowingFlower {
	private float x;
	private float y;
	private float speed=0.05f;
	private float scale;
	private Sprite flower;
	public GrowingFlower(Sprite flower, float x, float y){
		this.flower=flower;
		this.y=y;
		this.x=x;
		scale=0.1f;
	}

	public void render(SpriteBatch batch){
		flower.setScale(scale);
		flower.setPosition(x, y);
		flower.draw(batch);
		if(scale < 1){
			scale=scale+speed;
		}
	}
}
