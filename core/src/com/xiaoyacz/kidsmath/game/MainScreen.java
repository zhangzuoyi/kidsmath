package com.xiaoyacz.kidsmath.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xiaoyacz.kidsmath.game.util.Constants;
import com.xiaoyacz.kidsmath.game.util.GamePreferences;

public class MainScreen implements Screen {
	private Viewport viewport;
	private Stage stage;
	private Image image;
	private TextButton buttonPlay;
	private TextButton buttonPaster;
	private ImageButton buttonOption;
	private ImageButton buttonExit;
	private BitmapFont font;
	// options
	private Window winOptions;
	private TextButton btnWinOptSave;
	private TextButton btnWinOptCancel;
//	private CheckBox chkSound;
//	private Slider sldSound;
	private CheckBox chkMusic;
	private Slider sldMusic;
	private Skin skinLibgdx;
	public MyGame game;
	
	public MainScreen(MyGame game){
		this.game=game;
	}
	@Override
	public void show() {
		viewport = new StretchViewport(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont(Gdx.files.internal("images/kidsmath.fnt"));
		image=new Image(Assets.instance.mainBackground);
		stage.addActor(image);
		addLogo();
		addMainButton();
		addOptionButton();
		addExitButton();
		stage.addActor(buildOptionsWindowLayer());
		playBackgroundMusic();
	}
	private void addLogo(){
		Table layer = new Table();
		layer.setSize(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
		layer.top().left();
		layer.pad(10, 10, 10, 10);
		Image image=new Image(Assets.instance.logo);
		layer.add(image);
		stage.addActor(layer);
	}
	private void addMainButton(){
//		Table layer = new Table();
//		layer.setSize(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
//		layer.center();
		TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
		tbs.font = font;
		tbs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.hotBalloonPlay));
		buttonPlay = new TextButton("", tbs);
		buttonPlay.addListener( new ClickListener() {             
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new GameScreen(game));
			};
		});
		buttonPlay.setPosition(200, 200);
		buttonPlay.addAction(Actions.forever(Actions.sequence(
				Actions.delay(1f),
				Actions.moveBy(0, 5),
				Actions.delay(1f),
				Actions.moveBy(0, -5))));
//		layer.add(buttonPlay).pad(10, 0, 10, 0).row();
//		ImageButton.ImageButtonStyle ibs = new ImageButton.ImageButtonStyle();
//		ibs.up = new TextureRegionDrawable(new TextureRegion(ttPlay));
//		buttonPlay=new ImageButton(ibs);
//		float x=(SCENE_WIDTH-buttonPlay.getWidth())/2;
//		buttonPlay.setPosition(x, 300);
//		stage.addActor(buttonPlay);
		tbs = new TextButton.TextButtonStyle();
		tbs.font = font;
		tbs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.hotBalloonPaster));
		buttonPaster = new TextButton("", tbs);
		buttonPaster.addListener( new ClickListener() {             
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PasterScreen(game, PasterScreen.SCREEN_MAIN));
			};
		});
		buttonPaster.setPosition(400, 130);
		buttonPaster.addAction(Actions.forever(Actions.sequence(
				Actions.delay(1f),
				Actions.moveBy(0, -5),
				Actions.delay(1f),
				Actions.moveBy(0, 5))));
//		buttonPaster.setPosition(x, 100);
//		stage.addActor(buttonPaster);
//		layer.add(buttonPaster).pad(10, 0, 10, 0).row();
		stage.addActor(buttonPlay);
		stage.addActor(buttonPaster);
	}
	private void addOptionButton(){
		Table layer = new Table();
		layer.pad(10, 10, 10, 10);
		layer.right().bottom();
		ImageButton.ImageButtonStyle ibs = new ImageButton.ImageButtonStyle();
		ibs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.optionButton));
		buttonOption=new ImageButton(ibs);
		buttonOption.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onOptionsClicked();
			}
		});
		layer.add(buttonOption);
		layer.setSize(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
		stage.addActor(layer);
//		Stack stack = new Stack();
//		stage.addActor(stack);
//		stack.setSize(SCENE_WIDTH, SCENE_HEIGHT);
//		stack.add(layer);
	}
	private void addExitButton(){
		ImageButton.ImageButtonStyle ibs = new ImageButton.ImageButtonStyle();
		ibs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.tback));
		buttonExit=new ImageButton(ibs);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			};
		});
		buttonExit.setPosition(10, 10);
		stage.addActor(buttonExit);
	}
	
	private void playBackgroundMusic(){
		if(GamePreferences.instance.music){
			if( ! Assets.instance.sounds.bg1Music.isPlaying()){
				Assets.instance.sounds.bg1Music.play();
			}
		}else{
			if( Assets.instance.sounds.bg1Music.isPlaying()){
				Assets.instance.sounds.bg1Music.pause();
			}
		}
	}
	private Table buildOptionsWindowLayer() {
		skinLibgdx = new Skin(Gdx.files.internal("images/uiskin.json"), new TextureAtlas("images/uiskin.atlas"));
		winOptions = new Window("选项", skinLibgdx);
		// + Audio Settings: Sound/Music CheckBox and Volume Slider
		winOptions.add(buildOptWinAudioSettings()).row();
		// + Character Skin: Selection Box (White, Gray, Brown)
//		winOptions.add(buildOptWinSkinSelection()).row();
		// + Separator and Buttons (Save, Cancel)
		winOptions.add(buildOptWinButtons()).pad(10, 0, 10, 0);

		// Make options window slightly transparent
		winOptions.setColor(1, 1, 1, 0.8f);
		// Hide options window by default
		winOptions.setVisible(false);
		// Let TableLayout recalculate widget sizes and positions
		winOptions.pack();
		// Move options window to bottom right corner
		winOptions.setPosition(Constants.SCENE_WIDTH - winOptions.getWidth() - 50, 50);
		return winOptions;
	}
	private Table buildOptWinAudioSettings() {
		Table tbl = new Table();
		// + Title: "Audio"
		tbl.pad(10, 10, 0, 10);
//		tbl.add(new Label("Audio", skinLibgdx, "default-font", Color.ORANGE)).colspan(3);
		tbl.row();
		tbl.columnDefaults(0).padRight(10);
		tbl.columnDefaults(1).padRight(10);
		// + Checkbox, "Sound" label, sound volume slider
//		chkSound = new CheckBox("", skinLibgdx);
//		tbl.add(chkSound);
//		tbl.add(new Label("音效", skinLibgdx));
//		sldSound = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
//		tbl.add(sldSound);
//		tbl.row();
		// + Checkbox, "Music" label, music volume slider
		chkMusic = new CheckBox("", skinLibgdx);
		tbl.add(chkMusic);
		tbl.add(new Label("背景音乐", skinLibgdx));
		sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
		tbl.add(sldMusic);
		tbl.row();
		return tbl;
	}
	private Table buildOptWinButtons() {
		Table tbl = new Table();
		// + Separator
		Label lbl = null;
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.75f, 0.75f, 0.75f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
		tbl.row();
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.5f, 0.5f, 0.5f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
		tbl.row();
		// + Save Button with event handler
		btnWinOptSave = new TextButton("保存", skinLibgdx);
		tbl.add(btnWinOptSave).padRight(30);
		btnWinOptSave.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onSaveClicked();
			}
		});
		// + Cancel Button with event handler
		btnWinOptCancel = new TextButton("取消", skinLibgdx);
		tbl.add(btnWinOptCancel);
		btnWinOptCancel.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onCancelClicked();
			}
		});
		return tbl;
	}
	private void onSaveClicked() {
		saveSettings();
		onCancelClicked();
	}

	private void onCancelClicked() {
		buttonOption.setVisible(true);
		winOptions.setVisible(false);
	}
	private void saveSettings() {
		GamePreferences prefs = GamePreferences.instance;
//		prefs.sound = chkSound.isChecked();
//		prefs.volSound = sldSound.getValue();
		prefs.music = chkMusic.isChecked();
		prefs.volMusic = sldMusic.getValue();
		prefs.save();
		Assets.instance.sounds.setBgMusicVol();
		playBackgroundMusic();
	}
	private void loadSettings() {
		GamePreferences prefs = GamePreferences.instance;
		prefs.load();
//		chkSound.setChecked(prefs.sound);
//		sldSound.setValue(prefs.volSound);
		chkMusic.setChecked(prefs.music);
		sldMusic.setValue(prefs.volMusic);
	}
	private void onOptionsClicked() {
		loadSettings();
		buttonOption.setVisible(false);
		winOptions.setVisible(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		if( Assets.instance.sounds.bg1Music.isPlaying()){
			Assets.instance.sounds.bg1Music.pause();
		}
		dispose();
	}

	@Override
	public void dispose() {
		skinLibgdx.dispose();
		font.dispose();
		stage.dispose();
	}

}
