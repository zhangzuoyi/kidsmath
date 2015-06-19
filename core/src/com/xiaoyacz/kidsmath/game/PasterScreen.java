package com.xiaoyacz.kidsmath.game;

import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xiaoyacz.kidsmath.game.objects.Paster;
import com.xiaoyacz.kidsmath.game.util.Constants;
import com.xiaoyacz.kidsmath.game.util.GamePreferences;
import com.xiaoyacz.kidsmath.game.util.ObtainedPastersManager;

public class PasterScreen implements Screen {
	public static final int SCREEN_MAIN=1;
	public static final int SCREEN_GAME=2;
	private Viewport viewport;
	private Stage stage;
	private Image bgImage;
	private ImageButton backButton;
	private Table pasterAddLayer;
	private ImageButton buttonSpeaker;

	public MyGame game;
	private int previousScreen;
	
	public PasterScreen(MyGame game, int previousScreen){
		this.game=game;
		this.previousScreen=previousScreen;
	}
	@Override
	public void show() {
		viewport = new StretchViewport(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		bgImage=new Image(Assets.instance.pasters.bg);
		stage.addActor(bgImage);
		addBackButton();
		if(previousScreen == SCREEN_GAME){
			addNewPasterWindow();
			Assets.instance.sounds.earnPaster();
		}
//		addNewPasterWindow();
		addPasters();
		addSpeakerButton();
		playBackgroundMusic();
	}
	private void addSpeakerButton(){
		ImageButton.ImageButtonStyle ibs = new ImageButton.ImageButtonStyle();
		if(GamePreferences.instance.music){
			ibs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.tson));
		}else{
			ibs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.tsoff));
		}
		
		buttonSpeaker=new ImageButton(ibs);
		buttonSpeaker.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GamePreferences.instance.music= ! GamePreferences.instance.music;
				GamePreferences.instance.save();
				changeSpeakerIcon();
				playBackgroundMusic();
			};
		});
		float x=Constants.SCENE_WIDTH - 60;
		float y=Constants.SCENE_HEIGHT - 60;
		buttonSpeaker.setPosition(x, y);
		stage.addActor(buttonSpeaker);
	}
	private void changeSpeakerIcon(){
		ImageButton.ImageButtonStyle ibs = new ImageButton.ImageButtonStyle();
		if(GamePreferences.instance.music){
			ibs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.tson));
		}else{
			ibs.up = new TextureRegionDrawable(new TextureRegion(Assets.instance.tsoff));
		}
		buttonSpeaker.setStyle(ibs);
	}
	private void playBackgroundMusic(){
		if(GamePreferences.instance.music){
			if( ! Assets.instance.sounds.bg3Music.isPlaying()){
				Assets.instance.sounds.bg3Music.play();
			}
		}else{
			if( Assets.instance.sounds.bg3Music.isPlaying()){
				Assets.instance.sounds.bg3Music.pause();
			}
		}
	}
	private void addBackButton(){
		ImageButton.ImageButtonStyle ibs = new ImageButton.ImageButtonStyle();
		ibs.up = new TextureRegionDrawable( new TextureRegion(Assets.instance.tback) );
		backButton=new ImageButton(ibs);
		backButton.setPosition(10, 10);
		stage.addActor(backButton);
		backButton.addListener( new ClickListener() {             
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(previousScreen == SCREEN_GAME){
					game.setScreen(new GameScreen(game));
				}else{
					game.setScreen(new MainScreen(game));
				}
			};
		});
	}
	
	private void addNewPasterWindow(){
		List<String> keys=Assets.instance.pasters.getRandomPasters(14);
//		Stack stack = new Stack();
//		Window.WindowStyle ws = new Window.WindowStyle();
//		ws.titleFont=Assets.instance.font;
//		Window window = new Window("Paster", ws);
		pasterAddLayer = new Table();
		int i=0;
		for(String key: keys){
			Paster paster=Paster.newInstance(key);
			
//			paster.addListener((new DragListener() {
//			    public void touchDragged (InputEvent event, float x, float y, int pointer) {
//			            // example code below for origin and position
////			    	paster.setOrigin(Gdx.input.getX(), Gdx.input.getY());
//			    	paster.setPosition(x, y);
//			            System.out.println("touchdragged" + x + ", " + y);
//
//			        }
//
//			    }));
//			paster.addListener(new DragListener() {
//			    public void drag(InputEvent event, float x, float y, int pointer) {
//			    	paster.moveBy(x - paster.getWidth() / 2, y - paster.getHeight() / 2);
//			    }
//			});
			dragDrop(paster);
//			paster.setScale(0.5f);
			pasterAddLayer.add(paster.getImage());
			i++;
			if(i>13){
				break;
			}
		}
//		window.add(layer);
//		layer.setColor(Color.LIGHT_GRAY);
		pasterAddLayer.setBackground(new TextureRegionDrawable(new TextureRegion(Assets.instance.bgGrey)));
		pasterAddLayer.setSize(Constants.SCENE_WIDTH-100, 50);
		pasterAddLayer.setPosition(100, 10);
//		stack.pack();
		stage.addActor(pasterAddLayer);
	}
	
	private void dragDrop(final Paster paster){
		final Skin skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		final DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(paster.getImage()) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				pasterAddLayer.setVisible(false);
				Payload payload = new Payload();
				payload.setObject(paster.getKey());
				
				paster.getImage().setColor(Color.GREEN);

				payload.setDragActor(paster.getImage());

//				Label validLabel = new Label("Some payload!", skin);
//				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(paster.getImage());

//				Label invalidLabel = new Label("Some payload!", skin);
//				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(paster.getImage());

				dragAndDrop.setDragActorPosition(-(paster.getImage().getWidth()/2), paster.getImage().getHeight()/2);
				return payload;
			}
		});
		dragAndDrop.addTarget(new Target(bgImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
//				getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
//				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
				String key=payload.getObject().toString();
				Image image=(Image)source.getActor();
//				paster.moveBy(x - paster.getWidth() / 2, y - paster.getHeight() / 2);
				image.setVisible(true);
				image.setPosition(x- image.getWidth() / 2, y- image.getHeight() / 2);
				image.setColor(Color.WHITE);
				ObtainedPastersManager.instance.addPaster(paster);
				ObtainedPastersManager.instance.save();
				stage.addActor(image);
				dragAndDrop.removeSource(source);
//				pasterAddLayer.setVisible(false);
			}
		});
	}
	
	private void addPasters(){
		ObtainedPastersManager.instance.load();
		for(Paster paster: ObtainedPastersManager.instance.getPasters()){
			stage.addActor(paster.getImage());
		}
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
		if( Assets.instance.sounds.bg3Music.isPlaying()){
			Assets.instance.sounds.bg3Music.pause();
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
