package com.xiaoyacz.kidsmath.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xiaoyacz.kidsmath.game.model.NumCompareModel;
import com.xiaoyacz.kidsmath.game.model.NumCountModel;
import com.xiaoyacz.kidsmath.game.model.NumMinusModel;
import com.xiaoyacz.kidsmath.game.model.NumPlusModel;
import com.xiaoyacz.kidsmath.game.model.NumRecognizeModel;
import com.xiaoyacz.kidsmath.game.objects.GrowingFlower;
import com.xiaoyacz.kidsmath.game.renderer.AbstractRenderer;
import com.xiaoyacz.kidsmath.game.renderer.CompareRenderer;
import com.xiaoyacz.kidsmath.game.renderer.CountRenderer;
import com.xiaoyacz.kidsmath.game.renderer.MinusRenderer;
import com.xiaoyacz.kidsmath.game.renderer.PlusRenderer;
import com.xiaoyacz.kidsmath.game.renderer.RecognizeRenderer;
import com.xiaoyacz.kidsmath.game.util.Constants;
import com.xiaoyacz.kidsmath.game.util.GamePreferences;

public class GameScreen implements Screen {
	private static final float WORLD_TO_SCREEN = 1.0f;
	private MyGame game;
	private Quiz quiz;
	private AbstractRenderer renderer;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Vector3 tmp;
	private Sprite speakerOn;
	private Sprite speakerOff;
	private Sprite buttonBack;
	private Sprite buttonHorn;
	private ParticleEffect starEffect;
	private List<GrowingFlower> flowers;
	
	public GameScreen(MyGame game){
		this.game=game;
		quiz=new Quiz();
		flowers=new ArrayList<GrowingFlower>();
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT, camera);
		batch = new SpriteBatch();
//		book = new Texture(Gdx.files.internal("mygdx/book.png"));
		camera.position.set(Constants.SCENE_WIDTH * 0.5f, Constants.SCENE_HEIGHT * 0.5f, 0.0f);
//		font2=new BitmapFont(Gdx.files.internal("mygdx/dejavu2.fnt"));
		tmp = new Vector3();
		
		starEffect= new ParticleEffect();
		starEffect.load(Gdx.files.internal("mygdx/star2.particle"), Gdx.files.internal("mygdx"));
//		starEffect.load(Gdx.files.internal("particles/dust.pfx"), Gdx.files.internal("particles"));
//		starEffect.start();
//		backgroundMusic.play();
		newModel();
		initSpeaker();
		initButtonBack();
		initButtonHorn();
		playBackgroundMusic();
	}
	private void initSpeaker(){
		float x=Constants.SCENE_WIDTH - 60;
		float y=Constants.SCENE_HEIGHT - 60;
		speakerOn=new Sprite(Assets.instance.tson);
		speakerOn.setPosition(x, y);
		speakerOff=new Sprite(Assets.instance.tsoff);
		speakerOff.setPosition(x, y);
	}
	private void initButtonBack(){
		buttonBack=new Sprite(Assets.instance.tback);
		buttonBack.setPosition(10, 10);
	}
	private void initButtonHorn(){
		buttonHorn=new Sprite(Assets.instance.thorn);
		float x=(Constants.SCENE_WIDTH - 50)/2;
		float y=Constants.SCENE_HEIGHT - 60;
		buttonHorn.setPosition(x, y);
	}

	private void newModel(){
		Gdx.input.setInputProcessor(null);
		float delay = 1; // seconds
		Timer.schedule(new Timer.Task(){
		    @Override
		    public void run() {
		    	if(quiz.getCorrectNum()>=Constants.NUM_PER_PASTER){
					game.setScreen(new PasterScreen(game, PasterScreen.SCREEN_GAME));
					return;
				}
		    	Object obj=quiz.getModel();
		    	if( obj instanceof NumRecognizeModel){
		    		NumRecognizeModel model=(NumRecognizeModel) obj;
		    		RecognizeRenderer regRenderer=new RecognizeRenderer(model, GameScreen.this);
		    		renderer=regRenderer;
		    	}else if( obj instanceof NumPlusModel){
		    		NumPlusModel model=(NumPlusModel) obj;
		    		PlusRenderer plusRenderer=new PlusRenderer(model, GameScreen.this);
		    		renderer=plusRenderer;
		    	}else if( obj instanceof NumMinusModel){
		    		NumMinusModel model=(NumMinusModel) obj;
		    		MinusRenderer minusRenderer=new MinusRenderer(model, GameScreen.this);
		    		renderer=minusRenderer;
		    	}else if( obj instanceof NumCountModel){
		    		NumCountModel model=(NumCountModel) obj;
		    		CountRenderer countRenderer=new CountRenderer(model, GameScreen.this);
		    		renderer=countRenderer;
		    	}else if( obj instanceof NumCompareModel){
		    		NumCompareModel model=(NumCompareModel) obj;
		    		CompareRenderer compareRenderer=new CompareRenderer(model, GameScreen.this);
		    		renderer=compareRenderer;
		    	}
		    	renderer.init();
		    	Gdx.input.setInputProcessor(new InputHandler());
		    }
		}, delay);
	}
	public void nextQuestion(){
		addFlower();
		quiz.addCorrectNum();
		newModel();
	}
	private void addFlower(){
		float x=100+50*quiz.getCorrectNum();
		float y=5;
		Sprite flower=new Sprite(Assets.instance.flower);
		GrowingFlower gf=new GrowingFlower(flower, x, y);
		flowers.add(gf);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		tmp.set(Gdx.input.getX(), Gdx.input.getY(), 0.0f);
//		camera.unproject(tmp);
//		starEffect.setPosition(tmp.x, tmp.y);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		int width = Assets.instance.gameBackground.getRegionWidth();
		int height = Assets.instance.gameBackground.getRegionHeight();
		batch.draw(Assets.instance.gameBackground,
				   0.0f, 0.0f);
		Assets.instance.cloud.move();
		Assets.instance.cloud.render(batch);
//		drawBooks();
		if(renderer != null)
			renderer.render(batch);
//		if (starEffect.isComplete()) {
//			starEffect.reset();
//		}
		starEffect.update(delta);
		starEffect.draw(batch);
		renderSpeaker();
		buttonBack.draw(batch);
		buttonHorn.draw(batch);
		drawFlowers();
		batch.end();
	}
	private void drawFlowers(){
		for(GrowingFlower gf:flowers){
			gf.render(batch);
		}
	}
	private void playBackgroundMusic(){
		if(GamePreferences.instance.music){
			if( ! Assets.instance.sounds.bg2Music.isPlaying()){
				Assets.instance.sounds.bg2Music.play();
			}
		}else{
			if( Assets.instance.sounds.bg2Music.isPlaying()){
				Assets.instance.sounds.bg2Music.pause();
			}
		}
	}
	private void renderSpeaker(){
		if(GamePreferences.instance.music){
			speakerOn.draw(batch);
		}else{
			speakerOff.draw(batch);
		}
	}
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, false);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		if( Assets.instance.sounds.bg2Music.isPlaying()){
			Assets.instance.sounds.bg2Music.pause();
		}
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		starEffect.dispose();
	}
	private void onSpeakerClick(float x, float y){
		if(speakerOn.getBoundingRectangle().contains(x, y) || speakerOff.getBoundingRectangle().contains(x, y)){
			GamePreferences.instance.music= ! GamePreferences.instance.music;
			GamePreferences.instance.save();
		}
		playBackgroundMusic();
	}
	private void onBackClick(float x, float y){
		if(buttonBack.getBoundingRectangle().contains(x, y) ){
			game.setScreen(new MainScreen(game));
		}
	}
	private void onHornClick(float x, float y){
		if(buttonHorn.getBoundingRectangle().contains(x, y) ){
			renderer.playSound();
		}
	}
	private class InputHandler extends InputAdapter{
		@Override
		public boolean touchDown (int screenX, int screenY, int pointer, int button) {
			tmp.set(screenX, screenY, 0.0f);
			camera.unproject(tmp);
//			touched=numberOne.getBoundingRectangle().contains(tmp.x, tmp.y);
			boolean showParticle=renderer.handlerInput(tmp.x, tmp.y);
			onSpeakerClick(tmp.x, tmp.y);
			onBackClick(tmp.x, tmp.y);
			onHornClick(tmp.x, tmp.y);
			if(showParticle){
				starEffect.setPosition(tmp.x, tmp.y);
//				starEffect.setDuration(2);
				starEffect.start();
			}
			
			return true;
		}
	}
	
}
