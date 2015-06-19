package com.xiaoyacz.kidsmath.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xiaoyacz.kidsmath.game.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Kids Math";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new MyGame(), config);
	}
}
