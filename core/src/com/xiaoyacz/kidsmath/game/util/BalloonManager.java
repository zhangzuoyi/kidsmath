package com.xiaoyacz.kidsmath.game.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class BalloonManager {
	private AtlasRegion[] balloons;
	private int size;
	
	public BalloonManager(AtlasRegion[] balloons){
		this.balloons=balloons;
		size=balloons.length;
	}
	
	public AtlasRegion getBalloon(int num){
		int i=num % size;
		return balloons[i];
	}
}
