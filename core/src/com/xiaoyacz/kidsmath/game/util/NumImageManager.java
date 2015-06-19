package com.xiaoyacz.kidsmath.game.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class NumImageManager {
	private AtlasRegion[] images;
	
	public NumImageManager(AtlasRegion[] images){
		this.images=images;
	}
	
	public AtlasRegion get(int num){
		return images[num];
	}
}
