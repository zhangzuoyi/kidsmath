package com.xiaoyacz.kidsmath.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xiaoyacz.kidsmath.game.objects.Cloud;
import com.xiaoyacz.kidsmath.game.util.BalloonManager;
import com.xiaoyacz.kidsmath.game.util.Constants;
import com.xiaoyacz.kidsmath.game.util.NumImageManager;
import com.xiaoyacz.kidsmath.game.util.SoundsManager;
import com.xiaoyacz.kidsmath.game.util.PastersManager;

public class Assets implements Disposable {
	private static final int BALLOON_SIZE=5;
	public static final Assets instance=new Assets();
	private AssetManager manager;
	public AtlasRegion gameBackground;
	public AtlasRegion mainBackground;
	public AtlasRegion optionButton;
	public AtlasRegion logo;
	public AtlasRegion tplus;
	public AtlasRegion tminus;
	public AtlasRegion tequal;
	public AtlasRegion tquestion;
	public BitmapFont font;
	public BitmapFont fontNum;
	public BalloonManager bManager;
	public PastersManager pasters;
	public Cloud cloud;
	public AtlasRegion tson;
	public AtlasRegion tsoff;
	public AtlasRegion tback;
	public AtlasRegion thorn;
	public AtlasRegion strawberry;
	public AtlasRegion strawberry30;
	public AtlasRegion flower;
	public AtlasRegion hotBalloonPaster;
	public AtlasRegion hotBalloonPlay;
	public AtlasRegion greaterThen;
	public AtlasRegion lessThen;
	public AtlasRegion bgGrey;
	public SoundsManager sounds;
	public NumImageManager numImages;
	
	private Assets(){
		
	}

	public void init(){
		font=new BitmapFont(Gdx.files.internal("images/kidsmath.fnt"));
		fontNum=new BitmapFont(Gdx.files.internal("images/kidsmath-num.fnt"));
		manager = new AssetManager();
//		manager.load(Constants.RED_BALLOON, Texture.class);
//		manager.load(Constants.BLUE_BALLOON, Texture.class);
//		manager.load(Constants.GREEN_BALLOON, Texture.class);
//		manager.load(Constants.PURPLE_BALLOON, Texture.class);
//		manager.load(Constants.ORANGE_BALLOON, Texture.class);
//		manager.load(Constants.ICON_MINUS, Texture.class);
//		manager.load(Constants.ICON_PLUS, Texture.class);
//		manager.load(Constants.ICON_QUESTION, Texture.class);
//		manager.load(Constants.ICON_EQUAL, Texture.class);
//		manager.load(Constants.SPEAKER_ON, Texture.class);
//		manager.load(Constants.SPEAKER_OFF, Texture.class);
//		manager.load(Constants.BG_GAME, Texture.class);
		manager.load(Constants.PASTER_ATLAS, TextureAtlas.class);
		manager.load(Constants.IMAGES_ATLAS, TextureAtlas.class);
//		manager.load(Constants.CLOUD, Texture.class);
//		manager.load(Constants.ICON_BACK, Texture.class);
//		manager.load(Constants.ICON_HORN, Texture.class);
//		manager.load(Constants.ICON_STRAWBERRY, Texture.class);
//		manager.load(Constants.ICON_STRAWBERRY_30, Texture.class);
//		manager.load(Constants.ICON_FLOWER, Texture.class);
//		manager.load(Constants.HOT_BALLOON_PASTER, Texture.class);
//		manager.load(Constants.HOT_BALLOON_PLAY, Texture.class);
//		manager.load(Constants.ICON_GT, Texture.class);
//		manager.load(Constants.ICON_LT, Texture.class);
//		manager.load(Constants.BG_GREY, Texture.class);
		loadSounds();
//		loadNumImages();
		manager.finishLoading();
		
		getSounds();
		
		TextureAtlas pasterAtlas=manager.get(Constants.PASTER_ATLAS);
		pasters=new PastersManager(pasterAtlas);
		TextureAtlas imagesAtlas=manager.get(Constants.IMAGES_ATLAS);
		getImages(imagesAtlas);
		getNumImages(imagesAtlas);
	}
	private void getImages(TextureAtlas atlas){
		AtlasRegion[] balloons=new AtlasRegion[BALLOON_SIZE];
		balloons[0]=atlas.findRegion(Constants.RED_BALLOON);
		balloons[1]=atlas.findRegion(Constants.BLUE_BALLOON);
		balloons[2]=atlas.findRegion(Constants.GREEN_BALLOON);
		balloons[3]=atlas.findRegion(Constants.PURPLE_BALLOON);
		balloons[4]=atlas.findRegion(Constants.ORANGE_BALLOON);
		bManager=new BalloonManager(balloons);
		tplus = atlas.findRegion(Constants.ICON_PLUS);
		tminus = atlas.findRegion(Constants.ICON_MINUS);
		tequal = atlas.findRegion(Constants.ICON_EQUAL);
		tquestion = atlas.findRegion(Constants.ICON_QUESTION);
		AtlasRegion tCloud=atlas.findRegion(Constants.CLOUD);
		cloud=new Cloud(new Sprite(tCloud));
		tson=atlas.findRegion(Constants.SPEAKER_ON);
		tsoff=atlas.findRegion(Constants.SPEAKER_OFF);
		tback=atlas.findRegion(Constants.ICON_BACK);
		thorn=atlas.findRegion(Constants.ICON_HORN);
		strawberry=atlas.findRegion(Constants.ICON_STRAWBERRY);
		strawberry30=atlas.findRegion(Constants.ICON_STRAWBERRY_30);
		flower=atlas.findRegion(Constants.ICON_FLOWER);
		gameBackground=atlas.findRegion(Constants.BG_GAME);
		hotBalloonPaster=atlas.findRegion(Constants.HOT_BALLOON_PASTER);
		hotBalloonPlay=atlas.findRegion(Constants.HOT_BALLOON_PLAY);
		greaterThen=atlas.findRegion(Constants.ICON_GT);
		lessThen=atlas.findRegion(Constants.ICON_LT);
		bgGrey=atlas.findRegion(Constants.BG_GREY);
		mainBackground=atlas.findRegion(Constants.BG_MAIN);
		optionButton=atlas.findRegion(Constants.OPTION_BUTTON);
		logo=atlas.findRegion(Constants.LOGO);
	}
	private void loadSounds(){
		manager.load(Constants.SOUND_CORRECT, Sound.class);
		manager.load(Constants.SOUND_WRONG, Sound.class);
		manager.load(Constants.SOUND_ZERO, Music.class);
		manager.load(Constants.SOUND_ONE, Music.class);
		manager.load(Constants.SOUND_TWO, Music.class);
		manager.load(Constants.SOUND_THREE, Music.class);
		manager.load(Constants.SOUND_FOUR, Music.class);
		manager.load(Constants.SOUND_FIVE, Music.class);
		manager.load(Constants.SOUND_SIX, Music.class);
		manager.load(Constants.SOUND_SEVEN, Music.class);
		manager.load(Constants.SOUND_EIGHT, Music.class);
		manager.load(Constants.SOUND_NINE, Music.class);
		manager.load(Constants.SOUND_TEN, Music.class);
		manager.load(Constants.SOUND_MINUS, Music.class);
		manager.load(Constants.SOUND_PLUS, Music.class);
		manager.load(Constants.MUSIC_BG1, Music.class);
		manager.load(Constants.MUSIC_BG2, Music.class);
		manager.load(Constants.MUSIC_BG3, Music.class);
		manager.load(Constants.SOUND_WHICH_NUM, Music.class);
		manager.load(Constants.SOUND_WHICH_NUM_IS, Music.class);
		manager.load(Constants.SOUND_COUNT_NUM, Music.class);
		manager.load(Constants.SOUND_EQUAL_WHICH, Music.class);
		manager.load(Constants.SOUND_BIG, Music.class);
		manager.load(Constants.SOUND_SMALL, Music.class);
		manager.load(Constants.SOUND_EARN_PASTER, Music.class);
	}
	private void getSounds(){
		sounds=new SoundsManager();
		sounds.nums=new Music[11];
		sounds.nums[0]=manager.get(Constants.SOUND_ZERO);
		sounds.nums[1]=manager.get(Constants.SOUND_ONE);
		sounds.nums[2]=manager.get(Constants.SOUND_TWO);
		sounds.nums[3]=manager.get(Constants.SOUND_THREE);
		sounds.nums[4]=manager.get(Constants.SOUND_FOUR);
		sounds.nums[5]=manager.get(Constants.SOUND_FIVE);
		sounds.nums[6]=manager.get(Constants.SOUND_SIX);
		sounds.nums[7]=manager.get(Constants.SOUND_SEVEN);
		sounds.nums[8]=manager.get(Constants.SOUND_EIGHT);
		sounds.nums[9]=manager.get(Constants.SOUND_NINE);
		sounds.nums[10]=manager.get(Constants.SOUND_TEN);
		sounds.correctSound=manager.get(Constants.SOUND_CORRECT);
		sounds.wrongSound=manager.get(Constants.SOUND_WRONG);
		sounds.bg1Music=manager.get(Constants.MUSIC_BG1);
		sounds.bg1Music.setLooping(true);
		sounds.bg2Music=manager.get(Constants.MUSIC_BG2);
		sounds.bg2Music.setLooping(true);
		sounds.bg3Music=manager.get(Constants.MUSIC_BG3);
		sounds.bg3Music.setLooping(true);
		Assets.instance.sounds.setBgMusicVol();
		sounds.whichNum=manager.get(Constants.SOUND_WHICH_NUM);
		sounds.whichNumIs=manager.get(Constants.SOUND_WHICH_NUM_IS);
		sounds.countNum=manager.get(Constants.SOUND_COUNT_NUM);
		sounds.equalWhich=manager.get(Constants.SOUND_EQUAL_WHICH);
		sounds.small=manager.get(Constants.SOUND_SMALL);
		sounds.big=manager.get(Constants.SOUND_BIG);
		sounds.minus=manager.get(Constants.SOUND_MINUS);
		sounds.plus=manager.get(Constants.SOUND_PLUS);
		sounds.earnPaster=manager.get(Constants.SOUND_EARN_PASTER);
	}
//	private void loadNumImages(){
//		manager.load(Constants.NUM_0, Texture.class);
//		manager.load(Constants.NUM_1, Texture.class);
//		manager.load(Constants.NUM_2, Texture.class);
//		manager.load(Constants.NUM_3, Texture.class);
//		manager.load(Constants.NUM_4, Texture.class);
//		manager.load(Constants.NUM_5, Texture.class);
//		manager.load(Constants.NUM_6, Texture.class);
//		manager.load(Constants.NUM_7, Texture.class);
//		manager.load(Constants.NUM_8, Texture.class);
//		manager.load(Constants.NUM_9, Texture.class);
//	}
	private void getNumImages(TextureAtlas atlas){
		AtlasRegion[] images=new AtlasRegion[10];
		images[0]=atlas.findRegion(Constants.NUM_0);
		images[1]=atlas.findRegion(Constants.NUM_1);
		images[2]=atlas.findRegion(Constants.NUM_2);
		images[3]=atlas.findRegion(Constants.NUM_3);
		images[4]=atlas.findRegion(Constants.NUM_4);
		images[5]=atlas.findRegion(Constants.NUM_5);
		images[6]=atlas.findRegion(Constants.NUM_6);
		images[7]=atlas.findRegion(Constants.NUM_7);
		images[8]=atlas.findRegion(Constants.NUM_8);
		images[9]=atlas.findRegion(Constants.NUM_9);
		numImages=new NumImageManager(images);
	}
	@Override
	public void dispose() {
		font.dispose();
		fontNum.dispose();
		manager.dispose();
	}
	
}
