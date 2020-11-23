package cqupt.match.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import cqupt.match.game.resource.Res;
import cqupt.match.game.screen.GameScreen;
import cqupt.match.game.stage.GameStage;

import static cqupt.match.game.resource.Res.FONT_CHARACTERS;

public class Monopoly extends Game {
	//世界的宽高
	private float worldWidth;
	private float worldHeight;

	//玩家人数
	private int number = 0;
	//自己的标识符
	private int index = -1;
	//游戏场景
	private GameScreen gameScreen;

	//资源管理器
	private AssetManager assetManager;
	//纹理图集
	private TextureAtlas textureAtlas;
	//位图字体资源
	private BitmapFont bitmapFont;

	public Monopoly(int index,int number){
		this.number = number;
		this.index = index;
	}

	public void init(){
		//初始化游戏世界大小
		worldWidth = Res.FIX_WORLD_WIDTH;
		worldHeight = Gdx.graphics.getHeight() * worldWidth / Gdx.graphics.getWidth();

		//加载资源
		assetManager = new AssetManager();
		assetManager.load(Res.ATLAS_PATH,TextureAtlas.class);
		assetManager.load(Res.BITMAP_FONT_PATH, BitmapFont.class);
		//等待资源加载完毕
		assetManager.finishLoading();

		bitmapFont = assetManager.get(Res.BITMAP_FONT_PATH);
//		bitmapFont = TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("font.ttf"), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		textureAtlas = assetManager.get(Res.ATLAS_PATH);
		gameScreen = new GameScreen(this);
		//设置游戏场景
		setScreen(gameScreen);
	}

	public TextureAtlas getTextureAtlas(){
		return textureAtlas;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

	public float getWorldHeight() {
		return worldHeight;
	}

	public float getWorldWidth() {
		return worldWidth;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	@Override
	public void create () {
		init();
	}

	public int getNumber() {
		return number;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
