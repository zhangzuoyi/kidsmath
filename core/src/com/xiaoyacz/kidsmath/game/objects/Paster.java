package com.xiaoyacz.kidsmath.game.objects;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.xiaoyacz.kidsmath.game.Assets;

public class Paster {
	private Image image;
	private String key;
	
	public static Paster newInstance(String key){
		Image image=new Image(Assets.instance.pasters.pasters.get(key));
		image.setSize(50, 50);
		return new Paster(image, key);
	}
	
	public static Paster newInstance(String key, float x, float y){
		Paster paster=newInstance(key);
		paster.image.setPosition(x, y);
		return paster;
	}
	
	public Paster(Image image, String key){
		this.image=image;
		this.key=key;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
