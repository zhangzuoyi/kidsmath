package com.xiaoyacz.kidsmath.game;

import com.badlogic.gdx.Game;
import com.xiaoyacz.kidsmath.game.util.GamePreferences;

public class MyGame extends Game {

	@Override
	public void create() {
		GamePreferences.instance.load();
		Assets.instance.init();
		setScreen(new MainScreen(this));
		
	}

}
