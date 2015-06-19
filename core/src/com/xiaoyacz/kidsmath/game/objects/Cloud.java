package com.xiaoyacz.kidsmath.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.xiaoyacz.kidsmath.game.util.Constants;

public class Cloud {
	private float x;
	private float y;
	private float speedX=0.5f;
	private Sprite cloud;
	public Cloud(Sprite cloud){
		this.cloud=cloud;
		y=Constants.SCENE_HEIGHT-100;
		x=MathUtils.random(Constants.SCENE_WIDTH);
	}
	public void move(){
		x=x+speedX;
		if(x > Constants.SCENE_WIDTH){
			x=-80;
		}
	}
	public void render(SpriteBatch batch){
		cloud.setPosition(x, y);
		cloud.draw(batch);
	}
}
